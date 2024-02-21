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
    		
    	int n = 16;
    	
    	Version1 version1 = new Version1(n);
    	Version2 version2 = new Version2(n);
    	
    	List<String> commands = new LinkedList<>();
    	
    	System.out.println("Enter commands of the form 'create N', 'destroy N', or 'end':");
    	String command;
    	while(true) {
    		command = scanner.nextLine();
    		if(command.equals("end")) {
    			break;
    		}
    		commands.add(command);
    	}
    	
    	for (String cmd : commands) {
            String[] parts = cmd.split(" ");
            if (parts.length < 2 || (!parts[0].equals("create") && !parts[0].equals("destroy"))) {
                System.out.println("Invalid command format: " + cmd);
                continue; // Skip processing this command
            }

            if (parts[0].equals("create")) {
                int parentPid = Integer.parseInt(parts[1]);
                version1.create(parentPid);
            } else if (parts[0].equals("destroy")) {
                int targetPid = Integer.parseInt(parts[1]);
                version1.destroy(targetPid);
            }
        }
    	version1.showProcessInfo();
    	
    	for (String cmd : commands) {
            String[] parts = cmd.split(" ");
            if (parts.length < 2 || (!parts[0].equals("create") && !parts[0].equals("destroy"))) {
                // Similar error handling for Version2
                System.out.println("Invalid command format: " + cmd);
                continue;
            }

            if (parts[0].equals("create")) {
                int parentPid = Integer.parseInt(parts[1]);
                version2.create(parentPid);
            } else if (parts[0].equals("destroy")) {
                int targetPid = Integer.parseInt(parts[1]);
                version2.destroy(targetPid);
            }
        }
    	version2.showProcessInfo();
    	
    	long startTimeV1 = System.currentTimeMillis();
        for (int i = 0; i < 200; i++) {
            for (String cmd : commands) {
                if (cmd.startsWith("create")) {
                    int parentPid = Integer.parseInt(cmd.split(" ")[1]);
                    version1.create(parentPid);
                } else if (cmd.startsWith("destroy")) {
                    int targetPid = Integer.parseInt(cmd.split(" ")[1]);
                    version1.destroy(targetPid);
                }
            }
        }
        long endTimeV1 = System.currentTimeMillis();
        System.out.println("Version1 running time: " + (endTimeV1 - startTimeV1) + " milliseconds");

        // Measure and display running time for Version2
        long startTimeV2 = System.currentTimeMillis();
        for (int i = 0; i < 200; i++) {
            for (String cmd : commands) {
                if (cmd.startsWith("create")) {
                    int parentPid = Integer.parseInt(cmd.split(" ")[1]);
                    version2.create(parentPid);
                } else if (cmd.startsWith("destroy")) {
                    int targetPid = Integer.parseInt(cmd.split(" ")[1]);
                    version2.destroy(targetPid);
                }
            }
        }
        long endTimeV2 = System.currentTimeMillis();
        System.out.println("Version2 running time: " + (endTimeV2 - startTimeV2) + " milliseconds");

        scanner.close();
    	
    }
}
