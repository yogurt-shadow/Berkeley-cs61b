package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.List;
import java.util.ArrayList;

import java.util.Random;
public class LockedDoorGenerator {
    private final static int WIDTH = MapGenerator.WIDTH;
    private final static int HEIGHT = MapGenerator.HEIGHT;
    private Random r;

    public  void lockeddoor(Random r,TETile[][] world){
       this.r = r;
        int x = RandomUtils.uniform(r, WIDTH);
        int y = RandomUtils.uniform(r, HEIGHT);
        if(world[x][y].equals(Tileset.WALL)){
            world[x][y] = Tileset.LOCKED_DOOR;
        }
        else{
            lockeddoor(r, world);
        }
    }
}
