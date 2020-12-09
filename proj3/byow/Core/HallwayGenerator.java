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
	private boolean room_connected;
	private Random r;

	public HallwayGenerator(Random r, List<Room> rooms){
		this.rooms = rooms;
		hallways = new ArrayList<>();
		room_connected = false;
		this.r = r;
	}

	private int randomlength(String orientation){

		switch(orientation){
			case "horizontal": return RandomUtils.uniform(r, 3,  WIDTH * 2 / 3);
			case "vertical": return RandomUtils.uniform(r, 3, HEIGHT * 2 / 3);
			default: return 0;
		}
	}

	private Room randomRoom(){

		int room_number = rooms.size();
		int random_number = RandomUtils.uniform(r, room_number);
		return rooms.get(random_number);
	}

	private Point startPoint1(Room room){

		int random_number = RandomUtils.uniform(r, room.edges().size());
		return room.edges().get(random_number);
	}

	private Hallway randomHallway(){

		int hallway_number = hallways.size();
		int random_number = RandomUtils.uniform(r, hallway_number);
		return hallways.get(random_number);
	}

	private Point startPoint2(Hallway hallway){

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
			case "width": return x > 0 && x <= WIDTH - 2;
			case "height": return x > 0 && x <= HEIGHT - 2;
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

	private Room left(Room room1, Room room2){
		if(room1.left() <= room2.left()){
			return room1;
		}
		else{
			return room2;
		}
	}

	private Room right(Room room1, Room room2){
		if(room1.left() > room2.left()){
			return room1;
		}
		else{
			return room2;
		}
	}

	private Room up(Room room1, Room room2){
		if(room1.bottom() > room2.bottom()){
			return room1;
		}
		else{
			return room2;
		}
	}

	private Room bottom(Room room1, Room room2){
		if(room1.bottom() > room2.bottom()){
			return room2;
		}
		else{
			return room1;
		}
	}

	private int min(int x1, int x2){
		if(x1 >= x2){
			return x2;
		}
		else{
			return x1;
		}
	}

	private int max(int x1, int x2){
		if(x1 > x2){
			return x1;
		}
		else{
			return x2;
		}
	}

	private void connect(Room room1, Room room2){
		/**  common horizontal line */
		for(int y = min(room1.bottom(), room2.bottom()); y <= max(room1.up(), room2.up()); y++){
			if(y >= room1.bottom() && y >= room2.bottom() && y <= room1.up() && y <= room2.up()){
				Room left = left(room1, room2);
				Room right = right(room1, room2);
				Point start = new Point(left.right(), y);
				int length = right.left() - left.right() + 1;
				Hallway hallway = new Hallway(start, "horizontal", length);
				hallways.add(hallway);
				return;
			}
		}

		/**  common vertical line */
		for(int x = min(room1.left(), room2.right()); x <= max(room1.right(), room2.right()); x++){
			if(x >= room1.left() && x <= room1.right() && x >= room2.left() && x <= room2.right()){
				Room up = up(room1, room2);
				Room bottom = bottom(room1, room2);
				Point start = new Point(x, bottom.up());
				int length = up.bottom() - bottom.up() + 1;
				Hallway hallway = new Hallway(start, "vertical", length);
				hallways.add(hallway);
				return;
			}
		}

		/**  right_up corner */
		Room left = left(room1, room2);
		Room right = right(room1, room2);
		if(right.bottom() >= left.up()){
			Point start1 = new Point(left.right(), left.up());
			int length1 = right.left() - left.right() + 1;
			Hallway hallway1 = new Hallway(start1, "horizontal", length1);

			Point start2 = hallway1.end();
			int length2 = right.bottom() - hallway1.end().y() + 1;
			Hallway hallway2 = new Hallway(start2, "vertical", length2);

			hallways.add(hallway1);
			hallways.add(hallway2);
		}

		else{
			Point start1 = new Point(left.right(), left.up());
			int length1 = right.left() - left.right() + 1;
			Hallway hallway1 = new Hallway(start1, "horizontal", length1);

			Point start2 = new Point(hallway1.end().x(), right.up());
			int length = hallway1.end().y() - right.up() + 1;
			Hallway hallway2 = new Hallway(start2, "vertical", length);

			hallways.add(hallway1);
			hallways.add(hallway2);
		}
	}


	/**  use hallway to connect rooms */
	private void is_connected(List<Room> rooms){
		for(int i = 0; i < rooms.size() - 1; i++){
			connect(rooms.get(i), rooms.get(i + 1));
		}
		room_connected = true;
	}

	private boolean is_connected(){
		return hallways.size() > 15 && room_connected;
	}

	public void hallways_generator(TETile[][] world){
		/**  make sure rooms and hallways are connected */
		is_connected(rooms);
		while(!is_connected()){
			hallway_generator();
		}
		fillhallways(world);
	}
}