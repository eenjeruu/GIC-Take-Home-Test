import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SimulatorTest {
    private Simulator simulator;
    private Field field;

    @BeforeEach
    void setUp() {
        // Assuming a 10x10 field for simplicity in tests
        field = new Field(10, 10);
        simulator = new Simulator(field);
    }

    @Test
    void testSingleCarMovementAcrossBoundary(){
        Car car = new Car(0, 0, 'N');
        simulator.addCar(car, "FFFFFFFFFF");
        simulator.executeAllCommands();
        assertEquals("0 9 N", simulator.getCarPosition(0), "Car should end up at (0,9) facing North.");
    }

    @Test
    void testMultipleCarsStartingAtTheSamePosition(){
        simulator.addCar(new Car(0, 0, 'N'), "F");
        simulator.addCar(new Car(0, 0, 'E'), "F");
        simulator.addCar(new Car(0, 0, 'S'), "F");
        simulator.executeAllCommands();
        assertTrue(simulator.isCollisionDetected(), "An initial collision should be detected.");
    }


    @Test
    void testSingleCarMovesWithoutCollision() {
        Car car = new Car(0, 0, 'N');
        simulator.addCar(car, "FFF");
        simulator.executeAllCommands();
        // Expect the car to move forward three times without any collisions
        assertEquals("0 3 N", simulator.getCarPosition(0), "Car should end up at (0,3) facing North.");
    }

    @Test
    void testCollisionBetweenTwoCars() {
        simulator.addCar(new Car(0, 0, 'N'), "FFF");
        simulator.addCar(new Car(0, 2, 'N'), "F");
        simulator.executeAllCommands();
        // Since both cars attempt to occupy (0,3), a collision should be detected
        assertTrue(simulator.checkForCollisions(), "A collision should be detected.");
    }
    @Test
    void testCollisionBetweenThreeCars() {
        simulator.addCar(new Car(0, 0, 'N'), "FFF");
        simulator.addCar(new Car(0, 4, 'S'), "F");
        simulator.addCar(new Car(0, 6, 'S'), "FFF");
        simulator.executeAllCommands();
        assertTrue(simulator.isCollisionDetected(), "A collision should be detected.");
    }

    @Test
    void testNoCollisionWithDisjointPaths() {
        Field field = new Field(10, 10);
        Simulator simulator = new Simulator(field);

        simulator.addCar(new Car(0, 0, 'N'), "FFRFFF");
        simulator.addCar(new Car(3, 0, 'N'), "FFLFFF");

        simulator.executeAllCommands();
        assertFalse(simulator.isCollisionDetected(), "There should be no collisions.");
    }



    @Test
    void testMultipleCarsCollideAtSamePoint() {
        simulator.addCar(new Car(0, 0, 'E'), "FFF"); // Ends at (3, 0)
        simulator.addCar(new Car(1, 0, 'E'), "FF");  // Ends at (3, 0), collides with Car 1
        simulator.addCar(new Car(2, 0, 'E'), "F");   // Ends at (3, 0), collides with Car 1 and Car 2
        simulator.executeAllCommands();
        // Now, all cars indeed attempt to occupy (3,0), causing a three-way collision
        assertTrue(simulator.checkForCollisions(), "A collision involving multiple cars should be detected.");
    }

    @Test
    void testSequentialCommandExecution() {
        Field field = new Field(5, 5);
        Simulator simulator = new Simulator(field);
        // Car 1 moves in a square pattern, starting from the bottom left corner
        simulator.addCar(new Car(0, 0, 'N'), "FFRFFRFFRF");
        // Car 2 moves straight then turns and moves, starting from the top right corner and heading left
        simulator.addCar(new Car(4, 4, 'W'), "FFRFF");
        simulator.executeAllCommands();
        assertFalse(simulator.isCollisionDetected(), "Cars should move without colliding, following their commands in sequence.");
    }



    @Test
    void testCollisionAtFieldEdge() {
        Field field = new Field(3, 3);
        Simulator simulator = new Simulator(field);

        // Both cars head towards the same edge position
        simulator.addCar(new Car(0, 0, 'E'), "FF");
        simulator.addCar(new Car(2, 1, 'S'), "F");

        simulator.executeAllCommands();
        assertTrue(simulator.isCollisionDetected(), "A collision should be detected at the field edge.");
    }
    @Test
    void testParallelNonIntersectingPaths() {
        Field field = new Field(10, 10);
        Simulator simulator = new Simulator(field);

        // Car 1 moves around the perimeter
        simulator.addCar(new Car(0, 0, 'N'), "FFFFFFFFFRFFFFFFFFFRFFFFF");
        // Car 2 cuts across the middle, avoiding Car 1's path
        simulator.addCar(new Car(4, 0, 'N'), "FFFFRFFFF");

        simulator.executeAllCommands();
        assertFalse(simulator.isCollisionDetected(), "Cars should complete their paths without colliding.");
    }
    @Test
    void testMultipleSeparateCollisions() {
        Field field = new Field(5, 5);
        Simulator simulator = new Simulator(field);

        simulator.addCar(new Car(0, 0, 'E'), "FF"); // Ends at (2, 0), colliding with Car 2 at (2,0)

        simulator.addCar(new Car(1, 0, 'E'), "F"); // Starts at (1,0), colliding with Car 1 at (2,0)

        simulator.addCar(new Car(3, 0, 'N'), "F"); // Ends at (3, 0), colliding with car 2 at (3,0)

        simulator.executeAllCommands();
        assertTrue(simulator.isCollisionDetected(), "Multiple separate collisions should be detected throughout the simulation.");
    }








}
