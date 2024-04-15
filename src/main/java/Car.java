public class Car {
    private int x;
    private int y;
    private char direction; // 'N', 'S', 'E', 'W' for North, South, East, and West

    /**
     * Constructs a Car at the specified coordinates facing the given direction.
     *
     * @param x the initial X-coordinate
     * @param y the initial Y-coordinate
     * @param direction the initial facing direction ('N', 'S', 'E', or 'W')
     */
    public Car(int x, int y, char direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    // Move the car forward by one grid point in the current facing direction
    public void moveForward() {
        switch (direction) {
            case 'N':
                y++;
                break;
            case 'S':
                y--;
                break;
            case 'E':
                x++;
                break;
            case 'W':
                x--;
                break;
        }
    }

    // Rotate the car 90 degrees to the left
    public void rotateLeft() {
        direction = switch (direction) {
            case 'N' -> 'W';
            case 'W' -> 'S';
            case 'S' -> 'E';
            case 'E' -> 'N';
            default -> throw new IllegalStateException("Unexpected value: " + direction);
        };
    }

    // Rotate the car 90 degrees to the right
    public void rotateRight() {
        direction = switch (direction) {
            case 'N' -> 'E';
            case 'E' -> 'S';
            case 'S' -> 'W';
            case 'W' -> 'N';
            default -> throw new IllegalStateException("Unexpected value: " + direction);
        };
    }

    // Getters and setters
    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }


    public char getDirection() {
        return direction;
    }

}
