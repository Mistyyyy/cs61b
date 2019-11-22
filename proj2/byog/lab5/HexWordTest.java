package byog.lab5;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import org.junit.Test;
import static org.junit.Assert.*;

public class HexWordTest {

    private static int WIDTH = 50;
    private static int HEIGHT = 50;
    private static TETile TEST_Tile = Tileset.WALL;

    TERenderer ter = new TERenderer();
    @Test
    public void addHexagonTest() {
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        int testSize = 4;
        int originX = 15;
        int originY = 15;
        int posX = 15;
        int poxY = 15;
        HexWorld.addHexagon(world, testSize, posX, poxY, TEST_Tile);

        TETile[][] exp = new TETile[WIDTH][HEIGHT];
        for(int j = 0; j < testSize; j++) {
            for(int i = 0; i < j * 2 + testSize; i++) {
                exp[posX + i][poxY] = TEST_Tile;
            }
            poxY -= 1;
            posX -= 1;
        }
        posX = originX - testSize + 1;
        poxY = originY - testSize;
        for (int j = 0; j < testSize; j++) {
            for(int i = 0; i < (testSize + (testSize - 1) * 2) - j * 2; i++) {
                exp[posX + i][poxY] = TEST_Tile;
            }
            poxY -= 1;
            posX += 1;
        }
        assertArrayEquals(exp, world);
    }

    @Test
    public void drawIncreaseTest() {
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        int testSize = 4;
        int posX = 15;
        int poxY = 10;
        HexWorld.drawIncrease(world, testSize, posX, poxY, TEST_Tile);

        TETile[][] exp = new TETile[WIDTH][HEIGHT];
        for(int j = 0; j < testSize; j++) {
            for(int i = 0; i < j * 2 + testSize; i++) {
                exp[posX + i][poxY] = TEST_Tile;
            }
            poxY -= 1;
            posX -= 1;
        }
        assertArrayEquals(world, exp);
    }

    @Test
    public void drawShrinkTest() {
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        int testSize = 4;
        int posX = 15;
        int poxY = 9;
        HexWorld.drawShrink(world, testSize, posX, poxY, TEST_Tile);

        TETile[][] exp = new TETile[WIDTH][HEIGHT];
        for (int j = 0; j < testSize; j++) {
            for(int i = 0; i < (testSize + (testSize - 1) * 2) - j * 2; i++) {
                exp[posX + i][poxY] = TEST_Tile;
            }
            poxY -= 1;
            posX += 1;
        }
        assertArrayEquals(world, exp);
    }

    @Test
    public void fillRowTest() {
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        int testSize = 2;
        int posX = 4;
        int poxY = 10;
        HexWorld.helpFillRow(world, testSize, posX, poxY, TEST_Tile);

        TETile[][] exp = new TETile[WIDTH][HEIGHT];
        for (int i = 0; i < testSize; i++) {
            exp[posX + i][poxY] = TEST_Tile;
        }
        assertArrayEquals(world, exp);
    }
}
