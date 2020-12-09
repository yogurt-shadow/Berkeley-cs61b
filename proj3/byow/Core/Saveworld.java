package byow.Core;

import byow.SaveDemo.Editor;
import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import edu.princeton.cs.introcs.StdDraw;
import java.io.Serializable;
import java.awt.*;
import java.io.*;
import java.util.Map;

public class Saveworld implements Serializable{
    private TETile[][] world;

    public Saveworld(TETile[][] world){
        this.world = world;
    }

    public TETile[][] world(){
        return world;
    }
}
