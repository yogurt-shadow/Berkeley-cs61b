package creatures;

import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import static huglife.HugLifeUtils.randomEntry;
public class Clorus extends Creature{
    private int r, g, b;

    public Clorus(double e){
        super("clorus");
        energy = e;
        r = 34;
        g = 0;
        b = 231;
    }

    public Color color() {
        return color(r, g, b);
    }

    public void move() {
        final double lose_move = 0.03;
        if(energy - lose_move < 0){
            energy = 0;
        }
        else {
            energy -= lose_move;
        }
    }

    public void stay() {
        final double lose_stay = 0.01;
        if(energy - lose_stay < 0){
            energy = 0;
        }
        else {
            energy -= lose_stay;
        }
    }

    public void attack(Creature c) {
        this.energy += c.energy();
    }

    public Clorus replicate() {
        energy = 0.5 * energy;
        return new Clorus(energy);
    }

    public double energy(){return energy;}


    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipneighbors = new ArrayDeque<>();
        for(Direction dir: neighbors.keySet()){
            if(neighbors.get(dir).name().equals("empty")){
                emptyNeighbors.addFirst(dir);
            }
            if(neighbors.get(dir).name().equals("plip")){
                plipneighbors.addFirst(dir);
            }
        }

        /**  Case 1 */
        if(emptyNeighbors.isEmpty()){
            return new Action(Action.ActionType.STAY);
        }

        /**  Case 2 */
        if(plipneighbors.size() > 0){
            Direction dir2 = randomEntry(plipneighbors);
            return new Action(Action.ActionType.ATTACK, dir2);
        }

        /**  Case 3 */
        if(energy >= 1){
            return new Action(Action.ActionType.REPLICATE, randomEntry(emptyNeighbors));
        }

        /**  Case 4 */
        return  new Action(Action.ActionType.MOVE, randomEntry(emptyNeighbors));
    }
}
