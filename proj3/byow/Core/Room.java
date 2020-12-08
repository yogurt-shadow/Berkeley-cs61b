package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.List;
import java.util.ArrayList;

public class Room{
	private int width;
	private int height;
	/**  start means the lower-left corner coordinate */
	private Point start;

	public Room(int width, int height, Point start){
		this.width = width;
		this.height = height;
		this.start = start;
	}

	public int width(){
		return width;
	}

	public int height(){
		return height;
	}

	public Point start(){
		return start;
	}

	public int left(){
		return start.x();
	}

	public int right(){
		return start.x() + width - 1;
	}

	public int bottom(){
		return start.y();
	}

	public int up(){
		return start.y() + height - 1;
	}


}