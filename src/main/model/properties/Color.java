/**
 * main.model.properties is a collection of simple properties
 * used by Chess pieces and players.
 */
package main.model.properties;

/**
 * Color enum values to represent and distinguish Chess pieces and players.
 *
 * @author Sunaya Shivakumar
 */
public enum Color {
    BLACK,
    WHITE;

    /**
     * opposite method to return a color's opposite.
     *
     * @return opposite color.
     */
    public Color opposite() {
        if (this == BLACK) {
            return WHITE;
        } else {
            return BLACK;
        }
    }
}
