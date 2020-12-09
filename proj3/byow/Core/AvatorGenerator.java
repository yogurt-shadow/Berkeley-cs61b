package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.List;
import java.util.ArrayList;

import java.util.Random;
public class AvatorGenerator {
    private Random r;
    private final static int WIDTH = MapGenerator.WIDTH;
    private final static int HEIGHT = MapGenerator.HEIGHT;

    public AvatorGenerator(Random r){
        this.r = r;
    }

    public void add_avator(TETile[][] world){
        int x = RandomUtils.uniform(r, WIDTH);
        int y = RandomUtils.uniform(r, HEIGHT);
        if(world[x][y].equals(Tileset.FLOOR)){
            world[x][y] = Tileset.AVATAR;
        }
        else{
            add_avator(world);
        }
    }
}
