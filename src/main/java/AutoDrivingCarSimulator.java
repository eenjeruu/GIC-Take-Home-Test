import java.util.Scanner;

public class AutoDrivingCarSimulator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the width and height of the field:");
        int width = scanner.nextInt();
        int height = scanner.nextInt();
        Field field = new Field(width, height);

        Simulator simulator = new Simulator(field);

        System.out.println("Enter the number of cars:");
        int numberOfCars = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numberOfCars; i++) {
            System.out.println("Enter the initial X and Y position and direction (N, E, S, W) for car " + (i + 1) + ":");
            int startX = scanner.nextInt();
            int startY = scanner.nextInt();
            char startDirection = scanner.next().charAt(0);

            System.out.println("Enter the commands for the car " + (i + 1) + " (L, R, F):");
            String commands = scanner.next();

            Car car = new Car(startX, startY, startDirection);
            simulator.addCar(car, commands);
        }

        simulator.executeAllCommands();
        simulator.printFinalCarPositions();
    }
}
