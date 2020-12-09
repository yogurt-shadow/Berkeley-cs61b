package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class MapGenerator{
	static final int WIDTH = Engine.WIDTH;
    static final int HEIGHT = Engine.HEIGHT;


   private static void fillworld(TETile[][] world){
		for(int i = 0; i < world.length; i++){
			for(int j = 0; j < world[0].length; j++){
				world[i][j] = Tileset.NOTHING;
			}
		}
	}

	public static TETile[][] map_generator(long seed){
   		Random r = new Random(seed);
		TETile[][] world = new TETile[WIDTH][HEIGHT];
		fillworld(world);

		RoomGenerator rg = new RoomGenerator(r);

		int room_number = RandomUtils.uniform(r, 8, 12);
		rg.rooms_generator(room_number, world);
		List<Room> rooms = rg.room_list();

		HallwayGenerator hg = new HallwayGenerator(r, rooms);
		hg.hallways_generator(world);
		List<Hallway> hallways = hg.hallway_list();

		WallGenerator.fillwall(world);

		LockedDoorGenerator locked = new LockedDoorGenerator();
		locked.lockeddoor(r, world);

		AvatorGenerator avator = new AvatorGenerator(r);
		avator.add_avator(world);



		return world;


	}


    public static void main(String[] args){
		Random r = new Random(124);
    	TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

		TETile[][] world = new TETile[WIDTH][HEIGHT];
		fillworld(world);

		RoomGenerator rg = new RoomGenerator(r);

		int room_numbers = RandomUtils.uniform(r, 8, 12);
		rg.rooms_generator(room_numbers, world);
		List<Room> rooms = rg.room_list();

		HallwayGenerator hg = new HallwayGenerator(r, rooms);
		hg.hallways_generator(world);
		List<Hallway> hallways = hg.hallway_list();

		WallGenerator.fillwall(world);

		LockedDoorGenerator locked = new LockedDoorGenerator();
		locked.lockeddoor(r, world);

		ter.renderFrame(world);


    }

}