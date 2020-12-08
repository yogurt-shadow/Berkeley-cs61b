package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.List;
import java.util.ArrayList;

public class MapGenerator{
	static final int WIDTH = 60;
    static final int HEIGHT = 30;


   private static void fillworld(TETile[][] world){
		for(int i = 0; i < world.length; i++){
			for(int j = 0; j < world[0].length; j++){
				world[i][j] = Tileset.NOTHING;
			}
		}
	}


    public static void main(String[] args){

    	TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

		TETile[][] world = new TETile[WIDTH][HEIGHT];
		fillworld(world);

		RoomGenerator rg = new RoomGenerator();
		rg.rooms_generator(15, world);
		List<Room> rooms = rg.room_list();

		ter.renderFrame(world);


    }
}