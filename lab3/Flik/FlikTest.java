import static org.junit.Assert.*;

import org.junit.Test;

public class FlikTest {
    @Test
    public void TestFlik() {
        int target = 10000;
        int i = 0;
        for (int j = 0; i < target; ++i, ++j) {
           assertTrue("i is " + i + ", j is " + j, Flik.isSameNumber(i, j));
        }
    }
}
