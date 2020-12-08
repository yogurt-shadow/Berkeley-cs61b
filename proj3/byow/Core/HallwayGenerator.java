package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.List;
import java.util.ArrayList;

import java.util.Random;

public class HallwayGenerator{
	private List<Hallway> hallways;
	private List<Room> rooms;
	private final static int WIDTH = MapGenerator.WIDTH;
	private final static int HEIGHT = MapGenerator.HEIGHT;

	public HallwayGenerator(List<Room> rooms){
		this.rooms = rooms;
		hallways = new ArrayList<>();
	}

	private int randomlength(String orientation){
		Random r = new Random();
		switch(orientation){
			case "horizontal": return RandomUtils.uniform(r, 3,  WIDTH * 2 / 3);
			case "vertical": return RandomUtils.uniform(r, 3, HEIGHT * 2 / 3);
			default: return 0;
		}
	}

	private Room randomRoom(){
		Random r = new Random();
		int room_number = rooms.size();
		int random_number = RandomUtils.uniform(r, room_number);
		return rooms.get(random_number);
	}

	private Point startPoint1(Room room){
		Random r = new Random();
		int x = RandomUtils.uniform(r, room.left(), room.right() + 1);
		int y = RandomUtils.uniform(r, room.bottom(), room.up() + 1);
		return new Point(x, y);
	}

	private Hallway randomHallway(){
		Random r = new Random();
		int hallway_number = hallways.size();
		int random_number = RandomUtils.uniform(r, hallway_number);
		return hallways.get(random_number);
	}

	private Point startPoint2(Hallway hallway){
		Random r = new Random();
		int x, y;
		switch(hallway.orientation()){
			case "vertical":  x = hallway.start().x();  y = RandomUtils.uniform(r, hallway.start().y(), hallway.end().y() + 1);
			break;
			case "horizontal":  y = hallway.start().y();  x = RandomUtils.uniform(r, hallway.start().x(), hallway.end().x() + 1);
			break;
			default:  x = 0;  y = 0;
		}
		return new Point(x, y);
	}

	private String randomdirection(){
		Random r = new Random();
		double random_number = RandomUtils.uniform(r);
		if(random_number > 0.5){
			return "vertical";
		}
		else{
			return "horizontal";
		}
	}

	private boolean in_world(int x, String s){
		switch(s){
			case "width": return x >= 0 && x <= WIDTH;
			case "height": return x >= 0 && x <= HEIGHT;
			default: return false;
		}
	}

	private boolean contact_board(Hallway hallway){
		switch(hallway.orientation()){
			case "vertical": return !(in_world(hallway.start().x(), "width") && in_world(hallway.start().y(), "height") && in_world(hallway.end().y(), "height"));
			case "horizontal": return !(in_world(hallway.start().y(), "height") && in_world(hallway.start().x(), "width") && in_world(hallway.end().x(), "width"));
			default: return true;
		}
	}


	private Hallway hallway_generator(){
		String orientation = randomdirection();
		int length = randomlength(orientation);
		/**  hallway must be created on rooms or hallways */
		Random r = new Random();
		double choice = RandomUtils.uniform(r);
		Point start;
		if(choice > 0.5 || hallways.size() == 0){
			Room room = randomRoom();
			 start = startPoint1(room);
		}
		else{
			Hallway hallway = randomHallway();
			start = startPoint2(hallway);
		}

		Hallway hallway = new Hallway(start, orientation, length);
		if(contact_board(hallway)){
			return hallway_generator();
		}

		else{
			hallways.add(hallway);
			return hallway;
		}
	}

	public List<Hallway> hallway_list(){
		return hallways;
	}

	private void fillhallway(TETile[][] world, Hallway hallway){
		if(hallway.orientation() == "vertical"){
			int x = hallway.start().x();
			int y1 = hallway.start().y();
			int y2 = hallway.end().y();
			for(int i = y1; i <= y2; i++){
				world[x][i] = Tileset.FLOOR;
			}
		}

		else{
			int x1 = hallway.start().x();
			int x2 = hallway.end().x();
			int y = hallway.start().y();

			for(int i = x1; i <= x2; i++){
				world[i][y] = Tileset.FLOOR;
			}
		}
	}

	private void fillhallways(TETile[][] world){
		for(Hallway hallway: hallways){
			fillhallway(world, hallway);
		}
	}

	private boolean is_connected(){
		return true;
	}

	public void hallways_generator(TETile[][] world){
		/**  make sure rooms and hallways are connected */
		while(!is_connected()){
			hallway_generator();
		}
		fillhallways(world);
	}
}