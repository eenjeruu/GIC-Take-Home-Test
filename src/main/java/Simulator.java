import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Simulator {
    private List<Car> cars;
    private Field field;
    private List<String> commandsList;
    private List<String> history;
    private boolean collisionDetected = false;
    private String collisionDetails = "";
    private List<String> collisionDetailsList = new ArrayList<>();

    /**
     * Creates a simulation on the given field.
     *
     * @param field The field on which the simulation takes place.
     */
    public Simulator(Field field) {
        this.field = field;
        this.cars = new ArrayList<>();
        this.commandsList = new ArrayList<>();
        this.history = new ArrayList<>();
    }

    /**
     * Adds a car and its associated movement commands to the simulation.
     *
     * @param car The car to be added.
     * @param commands The movement commands for the car.
     */
    public void addCar(Car car, String commands) {
        cars.add(car);
        commandsList.add(commands);
    }

    /**
     * Executes a single command for the given car.
     *
     * @param car The car to execute the command on.
     * @param command The command to execute.
     */
    private void executeCommand(Car car, char command) {
        switch (command) {
            case 'L': car.rotateLeft(); break;
            case 'R': car.rotateRight(); break;
            case 'F':
                int nextX = car.getX(), nextY = car.getY();
                switch (car.getDirection()) {
                    case 'N': nextY++; break;
                    case 'S': nextY--; break;
                    case 'E': nextX++; break;
                    case 'W': nextX--; break;
                }
                if (field.isWithinBounds(nextX, nextY)) car.moveForward();
                break;
        }
        recordHistory();
    }
    public void executeAllCommands() {
        collisionDetailsList.clear();
        checkInitialCollisions();
        if (collisionDetected) {
            System.out.println("Initial collision detected: " + String.join(", ", collisionDetailsList));
            return;
        }

        collisionDetected = false;
        int maxCommandLength = commandsList.stream().mapToInt(String::length).max().orElse(0);

        for (int step = 0; step < maxCommandLength; step++) {
            for (int carIndex = 0; carIndex < cars.size(); carIndex++) {
                if (step < commandsList.get(carIndex).length()) {
                    char command = commandsList.get(carIndex).charAt(step);
                    executeCommand(cars.get(carIndex), command);
                }
            }
            checkForCollisions();
        }

        if (!collisionDetailsList.isEmpty()) {
            collisionDetailsList.forEach(System.out::println);
        } else {
            System.out.println("No collision.");
        }
    }


    private void checkInitialCollisions() {
        checkForCollisions();
    }



    /**
     * Checks for collisions after each command execution step.
     *
     * @return True if a collision is detected, otherwise false.
     */
    boolean checkForCollisions() {
        Map<String, List<Integer>> positionToCarIndices = new HashMap<>();

        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            String posKey = car.getX() + "," + car.getY();
            positionToCarIndices.computeIfAbsent(posKey, k -> new ArrayList<>()).add(i);
        }

        boolean collisionDetectedInThisIteration = false;
        for (Map.Entry<String, List<Integer>> entry : positionToCarIndices.entrySet()) {
            if (entry.getValue().size() > 1) {
                collisionDetectedInThisIteration = true;
                // Store collision details
                collisionDetailsList.add("Collision detected at position (" + entry.getKey().replace(",", ", ") +
                        ") involving cars: " + entry.getValue().stream()
                        .map(index -> "Car " + (index + 1))
                        .collect(Collectors.joining(", ")));
            }
        }
        return collisionDetectedInThisIteration;
    }


    public int getNumberOfCollisions() {
        return collisionDetailsList.size();
    }

    public List<String> getCollisionDetails() {
        return new ArrayList<>(collisionDetailsList);
    }

    public boolean isCollisionDetected() {
        return !collisionDetailsList.isEmpty();
    }

    public String getCarPosition(int carIndex) {
        Car car = cars.get(carIndex);
        return car.getX() + " " + car.getY() + " " + car.getDirection();
    }

    private void recordHistory() {
        StringBuilder allCarPositions = new StringBuilder();
        for (Car car : cars) {
            allCarPositions.append(car.getX())
                    .append(" ")
                    .append(car.getY())
                    .append(" ")
                    .append(car.getDirection())
                    .append("\n");
        }
        history.add(allCarPositions.toString());
    }


    public void printFinalCarPositions() {
        for (int i = 0; i < cars.size(); i++) {
            System.out.println("Car " + i + ": " + getCarPosition(i));
        }
    }


}
