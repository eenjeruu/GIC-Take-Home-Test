Car Movement Simulator Project

Overview

The Car Movement Simulator is a Java application that models a simple environment where cars navigate a grid-based field. Each car can execute a sequence of commands: move forward, rotate left, and rotate right, respecting the boundaries of the field. The simulation checks for collisions and keeps a history of all car positions throughout the execution.

Design and Assumptions

- Environment: The simulator is designed to run in a Java environment (JDK 17 or higher) on any operating system that supports Java, including Windows, Linux, and macOS.
- Field: The grid (field) is rectangular, defined by its width and height, and does not wrap around. Cars cannot move outside the grid boundaries.
- Cars: Cars operate independently based on a sequence of commands. They can face one of four cardinal directions (N, E, S, W) and can move forward, rotate left, or rotate right.
- Collisions: The simulation detects collisions, which occur when two or more cars occupy the same grid position after a move. The simulation reports the first collision occurrence and halts further commands execution for all cars.
- History Logging: The simulator records the positions and orientations of all cars after each command execution step.

Running the Application

1. Setup Java Environment:
   Ensure that Java JDK 17 or higher is installed on the machine. 

2. Compile the Code:
   Navigate to the project directory and compile the code using the Java compiler. Run:
   javac *.java
   This command compiles all Java files in the current directory.

3. Execute the Simulator:
   After compilation, run the simulator using the Java interpreter:
   java AutoDrivingCarSimulator
   
4. Follow On-screen Prompts:
   The application may provide prompts for user input, such as the dimensions of the field and the sequences of commands for each car. Follow these prompts to proceed with the simulation.

Environment Requirements

- Java Development Kit (JDK): Version 11 or higher.
- Operating System: Windows, Linux, or macOS capable of running Java.
- Tools: Any Java IDE such as IntelliJ IDEA, Eclipse, or simple text editors for code editing. The command-line terminal or command prompt for compilation and execution.

Additional Notes

- Unit Tests: Unit tests are provided to ensure the reliability of critical functionalities, such as car movements and collision detection. Run these tests using a unit testing framework like JUnit to verify the application's correctness.
- Documentation: All classes and methods include JavaDoc comments for clarity and ease of understanding. This documentation aids in maintaining and extending the application.
