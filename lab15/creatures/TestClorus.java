package creatures;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.awt.Color;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.Impassible;
import huglife.Empty;

public class TestClorus {
    @Test
    public void testReplicate() {
        // TODO
        Clorus c = new Clorus(2);
        Clorus p_offspring = c.replicate();
        assertEquals(1, c.energy(), 0.01);
        assertEquals(1, p_offspring.energy(), 0.01);
    }

    @Test
    public void testStay() {
        Clorus cl = new Clorus(2);
        cl.stay();
        assertEquals(1.99, cl.energy(), 0.0001);
    }

    @Test
    public void testAttack() {
        Clorus c = new Clorus(4);
        Plip p = new Plip(2);
        c.attack(p);
        assertEquals(6, c.energy(), 0.001);
    }

    @Test
    public void testChoose() {

        // No empty adjacent spaces; stay.
        Clorus c = new Clorus(2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        Action actual = c.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);


        // Plip exists. Still stay.
        Plip p = new Plip(1.2);
        HashMap<Direction, Occupant> topEmpty2 = new HashMap<Direction, Occupant>();
        topEmpty2.put(Direction.TOP, p);
        topEmpty2.put(Direction.BOTTOM, new Impassible());
        topEmpty2.put(Direction.LEFT, new Impassible());
        topEmpty2.put(Direction.RIGHT, new Impassible());

        actual = c.chooseAction(topEmpty2);
        expected = new Action(Action.ActionType.STAY);
        assertEquals(expected, actual);

        // Plip surrounded. Attack it.
        Clorus c4 = new Clorus(2.0);
        Plip p2 = new Plip(1.2);
        HashMap<Direction, Occupant> topEmpty3 = new HashMap<Direction, Occupant>();
        topEmpty3.put(Direction.TOP, p2);
        topEmpty3.put(Direction.BOTTOM, new Empty());
        topEmpty3.put(Direction.LEFT, new Impassible());
        topEmpty3.put(Direction.RIGHT, new Impassible());

        actual = c4.chooseAction(topEmpty3);
        expected = new Action(Action.ActionType.ATTACK, Direction.TOP);
        assertEquals(expected, actual);



        // Energy >= 1; replicate towards an empty space.
        Clorus c2 = new Clorus(3);
        HashMap<Direction, Occupant> allEmpty = new HashMap<Direction, Occupant>();
        allEmpty.put(Direction.TOP, new Empty());
        allEmpty.put(Direction.BOTTOM, new Empty());
        allEmpty.put(Direction.LEFT, new Empty());
        allEmpty.put(Direction.RIGHT, new Empty());

        actual = c2.chooseAction(allEmpty);
        Action unexpected = new Action(Action.ActionType.STAY);

        assertNotEquals(unexpected, actual);



        // Move.
        Clorus c3 = new Clorus(0.5);

        actual = c3.chooseAction(allEmpty);
        expected = new Action(Action.ActionType.STAY);

        assertNotEquals(expected, actual);


    }
}
