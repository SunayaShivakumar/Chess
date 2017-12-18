/**
 * test.model.properties is a collection of unit test
 * for all classes and enums in main.model.properties.
 */
package test.model.properties;

import junit.framework.TestCase;
import main.model.properties.Color;

/**
 * unit test for Enum Color.
 */
public class ColorTest extends TestCase {
    /** A Color to test with. */
    private Color testColor;

    /**
     * testOppositeOfBlack unit test if testColor is not null,
     * is indeed Color.BLACK, and that it's opposite is Color.WHITE.
     */
    public void testOppositeOfBlack() {
        testColor = Color.BLACK;
        assertNotNull(testColor);
        assertEquals(Color.BLACK, testColor);
        assertEquals(Color.WHITE, testColor.opposite());
    }

    /**
     * testOppositeOfWhite unit test if testColor is not null,
     * is indeed Color.WHITE, and that it's opposite is Color.BLACK.
     */
    public void testOppositeOfWhite() {
        testColor = Color.WHITE;
        assertNotNull(testColor);
        assertEquals(Color.WHITE, testColor);
        assertEquals(Color.BLACK, testColor.opposite());
    }
}