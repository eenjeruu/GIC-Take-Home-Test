import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CarTest {
    private Car car;

    @BeforeEach
    void setUp() {
        // Initialize the car at position (0,0) facing North ('N') before each test
        car = new Car(0, 0, 'N');
    }

    @Test
    void testMoveForwardFacingNorth() {
        car.moveForward();
        assertEquals(0, car.getX(), "X position should not change.");
        assertEquals(1, car.getY(), "Y position should increment by 1.");
    }

    @Test
    void testRotateRightFromNorth() {
        car.rotateRight();
        assertEquals('E', car.getDirection(), "Direction should change from N to E.");
    }

    @Test
    void testRotateLeftFromNorth() {
        car.rotateLeft();
        assertEquals('W', car.getDirection(), "Direction should change from N to W.");
    }

    @Test
    void testFullRotationRight() {
        car.rotateRight(); // to E
        car.rotateRight(); // to S
        car.rotateRight(); // to W
        car.rotateRight(); // back to N
        assertEquals('N', car.getDirection(), "Full rotation right should return to N.");
    }

    @Test
    void testFullRotationLeft() {
        car.rotateLeft(); // to W
        car.rotateLeft(); // to S
        car.rotateLeft(); // to E
        car.rotateLeft(); // back to N
        assertEquals('N', car.getDirection(), "Full rotation left should return to N.");
    }

    @Test
    void testMovementWithMultipleCommands() {
        // Simulate "FFRF" - Move forward twice, rotate right, move forward once.
        car.moveForward();
        car.moveForward();
        car.rotateRight();
        car.moveForward();
        assertEquals(1, car.getX(), "X position should be 1 after moving East.");
        assertEquals(2, car.getY(), "Y position should be 2 after moving North twice.");
        assertEquals('E', car.getDirection(), "Direction should be East.");
    }
}
