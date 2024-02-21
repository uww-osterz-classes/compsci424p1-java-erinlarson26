/* COMPSCI 424 Program 1
 * Name:
 * 
 * This is a template. Program1.java *must* contain the main class
 * for this program. Otherwise, feel free to edit these files, even
 * these pre-written comments or my provided code. You can add any
 * classes, methods, and data structures that you need to solve the
 * problem and display your solution in the correct format.
 */
package compsci424.p1.java;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


/**
 * Main class for this program. The required steps have been copied
 * into the main method as comments. Feel free to add more comments to
 * help you understand your code, or for any other reason. Also feel
 * free to edit this comment to be more helpful for you.
 */
public class Program1 {
    // Declare any class/instance variables that you need here.
    /**
     * @param args command-line arguments, which can be ignored
     */
    public static void main(String[] args) {
    	 Scanner scanner = new Scanner(System.in);

         List<String> commands = new LinkedList<>();

         System.out.println("Enter commands of the form 'create N', 'destroy N', or 'end':");

         while (true) {
             String command = scanner.nextLine().trim();
             if (command.equals("end") || !command.matches("(create|destroy) \\d+")) {
                 break;
             }
             commands.add(command);
         }

         Version1 version1 = new Version1(16);
         Version2 version2 = new Version2(16);

         // Run command sequence once with Version 1 and display changes
         System.out.println("Running command sequence with Version 1:");
         runCommands(version1, commands);

         // Reset Version 1 state for Version 2
         version1 = new Version1(16);

         // Run command sequence once with Version 2 and display changes
         System.out.println("\nRunning command sequence with Version 2:");
         runCommands(version2, commands);

         // Measure and display running time for Version 1 (200 times)
         long startTimeV1 = System.currentTimeMillis();
         for (int i = 0; i < 200; i++) {
             runCommands(version1, commands);
         }
         long endTimeV1 = System.currentTimeMillis();
         System.out.println("\nVersion 1 running time: " + (endTimeV1 - startTimeV1) + " milliseconds");

         // Measure and display running time for Version 2 (200 times)
         version1 = new Version1(16); // Reset Version 1 state
         long startTimeV2 = System.currentTimeMillis();
         for (int i = 0; i < 200; i++) {
             runCommands(version2, commands);
         }
         long endTimeV2 = System.currentTimeMillis();
         System.out.println("Version 2 running time: " + (endTimeV2 - startTimeV2) + " milliseconds");

         scanner.close();
     }

     private static void runCommands(Version1 version, List<String> commands) {
         for (String cmd : commands) {
             String[] parts = cmd.split(" ");
             int param = Integer.parseInt(parts[1]);
             if (parts[0].equals("create")) {
                 version.create(param);
             } else if (parts[0].equals("destroy")) {
                 version.destroy(param);
             }
         }
     }

     private static void runCommands(Version2 version, List<String> commands) {
         for (String cmd : commands) {
             String[] parts = cmd.split(" ");
             int param = Integer.parseInt(parts[1]);
             if (parts[0].equals("create")) {
                 version.create(param);
             } else if (parts[0].equals("destroy")) {
                 version.destroy(param);
             }
         }
     }
}

