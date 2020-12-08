package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.List;
import java.util.ArrayList;

import java.util.Random;

public class RoomGenerator{
	private List<Room> rooms;
	private final static int WIDTH = MapGenerator.WIDTH;
	private final static int HEIGHT = MapGenerator.HEIGHT;

	public RoomGenerator(){
		rooms = new ArrayList<>();
	}

	private Point randomPoint(){
		Random r = new Random();
		int x = RandomUtils.uniform(r, 5, WIDTH - 5);
		int y = RandomUtils.uniform(r, 3, HEIGHT - 3);
		return new Point(x, y);

	}

	private int randomlength(String s){
		Random r = new Random();
		switch(s){
			case "height": return RandomUtils.uniform(r, 2, HEIGHT / 4);
			case "width": return RandomUtils.uniform(r, 4, WIDTH / 4);
			default: return 0;
		}
	}

	private boolean contact_board(int x, String s){
		switch(s){
			case "width": return (x >= WIDTH - 1 || x < 0);
			case "height": return (x >= HEIGHT - 1 || x < 0);
			default: return false;
		}
	}

	private boolean room_contact_board(Room room){
		Point start  = room.start();
		int height = room.height();
		int width  = room.width();
		boolean width_contact = contact_board(start.x(), "width") || contact_board(start.x() + width - 1, "width");
		boolean height_contact = contact_board(start.y(), "height") || contact_board(start.y() + height - 1, "height");
		return width_contact || height_contact;
	}


	/**  whether two rectangle overlaps each other 
	 *   use reverse thinking
	 */
	private boolean overlap(Room room1, Room room2){
		return !(room1.left() > room2.right() + 1 || room1.right() < room2.left() - 1 || room1.bottom() > room2.up() + 1 || room1.up() < room2.bottom() - 1);
	}

	private boolean room_overlap(Room room){
		for(Room other_room: rooms){
			if(overlap(other_room, room)){
				return true;
			}
		}
		return false;
	}

	public Room room_generator(){
		Point start = randomPoint();
		int height = randomlength("height");
		int width = randomlength("width");

		Room room = new Room(width, height, start);
		if(room_overlap(room) || room_contact_board(room)){
			return room_generator();
		}
		else{
			rooms.add(room);
			return room;
		}
	}

	private void fillroom(TETile[][] world, Room room){
		for(int i = room.left(); i <= room.right(); i++){
			for(int j = room.bottom(); j <= room.up(); j++){
				world[i][j] = Tileset.FLOOR;
			}
		}
	}

	public List<Room> room_list(){
		return rooms;
	}

	private  void fillrooms(TETile[][] world, List<Room> room_list){
		for(Room room: room_list){
			fillroom(world, room);
		}
	}

	public void rooms_generator(int room_numbers, TETile[][] world){
		for(int i = 0; i < room_numbers; i++){
			room_generator();
		}

		fillrooms(world, rooms);
	}
}