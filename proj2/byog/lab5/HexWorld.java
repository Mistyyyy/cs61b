package byog.lab5;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;

    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);

    public static void addHexagon(TETile[][] tile, int size, int x, int y, TETile paint) {
        drawIncrease(tile, size, x, y, paint);
        int posX = x - size + 1;
        int posY = y - size;
        drawShrink(tile, size, posX, posY, paint);
    }

    public static void drawIncrease(TETile [][] tile, int size, int x, int y, TETile paint) {
        for (int i = 0; i < size; i++) {
            helpFillRow(tile, size + i * 2, x, y, paint);
            x--;
            y--;
        }
    }

    public static void drawShrink(TETile [][] tile, int size, int x, int y, TETile paint) {
        for (int i = 0; i < size; i++) {
            helpFillRow(tile, maxSize(size) - i * 2, x, y, paint);
            x++;
            y--;
        }
    }

    public static void helpFillRow(TETile[][] tile, int size, int x, int y, TETile paint) {
        for (int i = 0; i < size; i++) {
            tile[x + i][y] = paint;
        }
    }

    private static int maxSize(int size) {
        return size + (size - 1) * 2;
    }

    private static int getTotalRow(int size) {
        return size * 2;
    }

    private static int[] getNextLeftPos(int x, int y, int size) {
        int nextLeftX = x - size * 2 + 1;
        int nextLeftY = y - size;
        int [] res = { nextLeftX, nextLeftY };
        return res;
    }

    private static int[] getNextRightPos(int x, int y, int size) {
        int nextRightX = x + size * 2 - 1;
        int nextRightY = y - size;
        int [] res = { nextRightX, nextRightY };
        return res;
    }

    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(3);
        switch (tileNum) {
        case 0: return Tileset.WALL;
        case 1: return Tileset.FLOWER;
        default: return Tileset.NOTHING;
        }
    }

    private static void fillInitial(TETile[][] world) {
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] world = new TETile[WIDTH][HEIGHT];

        fillInitial(world);
        int startX = 23;
        int startY = 49;
        int initSize = 3;
        int[] startLeftPos = getNextLeftPos(startX, startY, initSize);
        int[] startRightPos = getNextRightPos(startX, startY, initSize);
        int offset = getTotalRow(initSize);

        int i = 0;

        while (startY - offset * (i + 1) > 0) {
            addHexagon(world, initSize, startX, startY - (offset * i), Tileset.FLOWER);
            i++;
        }

        i = 0;

        while (startLeftPos[1] - offset * (i + 1) > 0) {
            addHexagon(world, initSize, startLeftPos[0], startLeftPos[1] - (offset * i), Tileset.WALL);
            i++;
        }

        i = 0;
        while (startRightPos[1] - offset * (i + 1) > 0) {
            addHexagon(world, initSize, startRightPos[0], startRightPos[1] - (offset * i), Tileset.WALL);
            i++;
        }

        ter.renderFrame(world);
    }



}
