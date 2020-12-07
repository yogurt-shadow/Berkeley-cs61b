package byow.lab12;
import org.junit.Test;
import static org.junit.Assert.*;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
	 private static final int WIDTH = 120;
    private static final int HEIGHT = 60;

	public static void addHexagon(TETile[][] tetile, TETile item,  Point p, int s){
		/** Position p is the lower_left corner of hexagon */
		int x = p.x();
		int y = p.y();
		int length = s;
		for(int height = y; height <= y + s - 1; height += 1){
		addrow(tetile, item, new Point(x + y - height, height), length);
		length += 2;
	}
	length -= 2;

	for(int height = s +  y; height <= y + 2 * s - 1; height += 1){
		addrow(tetile, item, new Point(height + x - y - 2*s + 1, height), length);
		length -= 2;
	}

	}

	private static void addrow(TETile[][] tetile, TETile item, Point p, int length){
		for(int i = p.x(); i <= p.x() + length - 1; i++){
			tetile[i][p.y()] = item;
		}
	}

	private static void fillworld(TETile[][] world){
		for(int i = 0; i < world.length; i++){
			for(int j = 0; j < world[0].length; j++){
				world[i][j] = Tileset.NOTHING;
			}
		}
	}

	private static Point leanPoint(Point p, int size, int number){
		if(number == 0){
			return p;
		}
		if(number <= 2){
			Point before = leanPoint(p, size, number - 1);
			return new Point(before.x() + 2 * size - 1, before.y() + size);
		}
		else{
			Point before = leanPoint(p, size, number - 1);
			return new Point(before.x(), before.y() + 2 * size);
		}

	}

	private static void fillhex(TETile[][] world, TETile[] items, Point p, int size){
		int x = p.x();
		int y = p.y();
		int[] length = new int[]{3, 4, 5, 4, 3};
		int[] start_copy = new int[]{0, 3, 7, 12, 16};
		for(int i = 0; i < 5; i++){
			TETile[] items2 = new TETile[length[i]];
			System.arraycopy(items, start_copy[i], items2, 0, length[i]);
			filllean(world, items2, leanPoint(p, size, i), size, length[i]);
		}
	}

	private static void filllean(TETile[][] world, TETile[] items, Point p, int size, int length){
		Point position = p;
		for(int i = 0; i < length; i++){
			addHexagon(world, items[i], position, size);
			position = new Point(position.x() - (2 * size - 1), position.y() + size);
		}
	}

	private static void random(TETile[] items){
	    for(int i = 0; i < items.length; i++){
	       items[i] = randomTile();
        }
    }

    private static TETile randomTile(){
	    Random r = new Random();
	    int j = r.nextInt(10);
	    switch (j){
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOOR;
            case 2: return Tileset.GRASS;
            case 3: return Tileset.WATER;
            case 4: return Tileset.FLOWER;
            case 5: return Tileset.LOCKED_DOOR;
            case 6: return Tileset.UNLOCKED_DOOR;
            case 7: return Tileset.SAND;
            case 8: return Tileset.MOUNTAIN;
            case 9: return Tileset.TREE;
            default: return Tileset.AVATAR;
        }
    }


	public static void main(String[] args){
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

		TETile[][] world = new TETile[WIDTH][HEIGHT];
		fillworld(world);

		Point p = new Point(40, 10);
		TETile[] items = new TETile[19];
		random(items);

		fillhex(world, items, p, 4);

		 ter.renderFrame(world);
	}
}
