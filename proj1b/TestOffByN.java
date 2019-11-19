import org.junit.Test;
import static  org.junit.Assert.*;

public class TestOffByN {

    static CharacterComparator offByN0 = new OffByN(0);
    static CharacterComparator offByN1 = new OffByN(1);
    static CharacterComparator offByN2 = new OffByN(2);
    static CharacterComparator offByN3 = new OffByN(3);
    static CharacterComparator offByN4 = new OffByN(4);


    @Test
    public void equalCharTest() {
        assertTrue(offByN0.equalChars('a', 'a'));
        assertFalse(offByN0.equalChars('a', 'b'));

        assertTrue(offByN1.equalChars('a', 'b'));
        assertFalse(offByN1.equalChars('a', 'a'));

        assertTrue(offByN2.equalChars('a', 'c'));
        assertFalse(offByN2.equalChars('a', 'b'));

        assertTrue(offByN3.equalChars('a', 'd'));
        assertFalse(offByN3.equalChars('a', 'c'));

        assertTrue(offByN4.equalChars('a', 'e'));
        assertFalse(offByN4.equalChars('a', 'd'));
    }
}
