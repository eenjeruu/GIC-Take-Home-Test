public class Field {
    private final int width;
    private final int height;

    /**
     * Constructs a Field with the specified dimensions.
     *
     * @param width  the width of the field (number of horizontal grid points)
     * @param height the height of the field (number of vertical grid points)
     */
    public Field(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Checks if the given coordinates are within the bounds of the field.
     *
     * @param x the X-coordinate to check
     * @param y the Y-coordinate to check
     * @return true if the position is within the field's bounds, false otherwise
     */
    public boolean isWithinBounds(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
