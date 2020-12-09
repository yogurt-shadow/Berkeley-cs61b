package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.List;
import java.util.ArrayList;

import java.util.Random;

public class WallGenerator{
	private final static int WIDTH = MapGenerator.WIDTH;
	private final static int HEIGHT = MapGenerator.HEIGHT;
	private final static int[] dir1 = {-1, 1, 0, 0};
	private final static int[] dir2 = {0, 0, -1, 1};

	public static void fillwall(TETile[][] world){
		for(int i = 0; i < world.length; i++){
			for(int j = 0; j < world[0].length; j++){
				if(world[i][j] == Tileset.FLOOR)
				for(int k = 0; k < 4; k++){
					if(world[i + dir1[k]][j + dir2[k]] == Tileset.NOTHING){

						world[i + dir1[k]][j + dir2[k]] = Tileset.WALL;
					}
				}
			}
		}
	}
}