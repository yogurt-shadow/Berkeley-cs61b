package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.List;
import java.util.ArrayList;

public class Hallway{
	private Point start;
	private String orientation;
	private int length;

	public Hallway(Point start, String orientation, int length){
		this.start = start;
		this.orientation = orientation;
		this.length = length;
	}

	public int length(){
		return length;
	}

	public Point start(){
		return start;
	}

	public String orientation(){
		return orientation;
	}

	public Point end(){
		switch(orientation){
			case "vertical": return new Point(start.x(), start.y() + length - 1);
			case "horizontal": return new Point(start.x() + length - 1, start.y());
			default: return null;
		}
	}


}