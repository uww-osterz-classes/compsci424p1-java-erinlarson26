/* COMPSCI 424 Program 1
 * Name:
 */
package compsci424.p1.java;

/** 
 * Implements the process creation hierarchy for Version 1, which uses
 * linked lists.
 * 
 * This is a template. Program1.java *must* contain the main class
 * for this program. Otherwise, feel free to edit these files, even
 * these pre-written comments or my provided code. You can add any
 * classes, methods, and data structures that you need to solve the
 * problem and display your solution in the correct format.
 */
public class Version1 {
    // Declare any class/instance variables that you need here.
	private Version1PCB[] pcbArray;
    /**
     * Default constructor. Use this to allocate (if needed) and
     * initialize the PCB array, create the PCB for process 0, and do
     * any other initialization that is needed. 
     */
    public Version1() {
    	pcbArray = new Version1PCB[16];
    	pcbArray[0] = new Version1PCB(0);
    }

    /**
     * Creates a new child process of the process with ID parentPid. 
     * @param parentPid the PID of the new process's parent
     * @return 0 if successful, not 0 if unsuccessful
     */
    int create(int parentPid) {
    	if(parentPid < 0 || parentPid >= pcbArray.length || pcbArray[parentPid] == null) {
    		System.out.println("Error: Parent process does not exist.");
    		return 1;
    	}
    	
    	int childPid = freePid();
    	if(childPid == -1) {
    		System.out.println("Error: No free PIDs available for new processes.");
    		return 2;
    	}
    	
    	Version1PCB childPCB = new Version1PCB(childPid);
    	pcbArray[childPid] = childPCB;
    	
    	pcbArray[parentPid].addChild(childPCB);
    	
        // If parentPid is not in the process hierarchy, do nothing; 
        // your code may return an error code or message in this case,
        // but it should not halt

        // Assuming you've found the PCB for parentPid in the PCB array:
        // 1. Allocate and initialize a free PCB object from the array
        //    of PCB objects

        // 2. Insert the newly allocated PCB object into parentPid's
        //    list of children

        // You can decide what the return value(s), if any, should be.
        // If you change the return type/value(s), update the Javadoc.
        return 0; // often means "success" or "terminated normally"
    }

    /**
     * Recursively destroys the process with ID parentPid and all of
     * its descendant processes (child, grandchild, etc.).
     * @param targetPid the PID of the process to be destroyed
     * @return 0 if successful, not 0 if unsuccessful
     */
    int destroy (int targetPid) {
    	if(targetPid < 0 || targetPid >= pcbArray.length || pcbArray[targetPid] == null ) {
    		System.out.println("Error: Target process does not exist.");
    		return 1;
    	}
    	
    	destroyDescendants(targetPid);
    	
    	pcbArray[targetPid].getParent().removeChild(targetPid);
    	
    	pcbArray[targetPid] = null;
         // If targetPid is not in the process hierarchy, do nothing; 
         // your code may return an error code or message in this case,
         // but it should not halt

         // Assuming you've found the PCB for targetPid in the PCB array:
         // 1. Recursively destroy all descendants of targetPid, if it
         //    has any, and mark their PCBs as "free" in the PCB array 
         //    (i.e., deallocate them)

         // 2. Remove targetPid from its parent's list of children

         // 3. Deallocate targetPid's PCB and mark its PCB array entry
         //    as "free"

         // You can decide what the return value(s), if any, should be.
         // If you change the return type/value(s), update the Javadoc.
        return 0; // often means "success" or "terminated normally"
    }
    
    private void destroyDescendants(int targetPid) {
    	for(Version1PCB child : pcbArray[targetPid].getChildren()) {
    		destroyDescendants(child.getPid());
    		pcbArray[child.getPid()] = null;
    	}
    }
    /**
     * Traverse the process creation hierarchy graph, printing
     * information about each process as you go. See Canvas for the
     * *required* output format. 
     *         
     * You can directly use "System.out.println" statements (or
     * similar) to send the required output to stdout, or you can
     * change the return type of this function to return the text to
     * the main program for printing. It's your choice. 
     */
    void showProcessInfo() {
    	showProcessInfoRecursive(pcbArray[0], 0);
    }
    
    private void showProcessInfoRecursive(Version1PCB pcb, int indentLevel) {
    	if(pcb != null) {
    		for(int i = 0; i < indentLevel; i++) {
    			System.out.print(" ");
    		}
    		System.out.println(pcb);
    		for(Version1PCB child : pcb.getChildren()) {
    			showProcessInfoRecursive(child, indentLevel + 1);
    		}
    	}
    }
    
    private int freePid() {
    	for(int i = 1; i < pcbArray.length; i++) {
    		if(pcbArray[i] == null) {
    			return i;
    		}
    	}
    	return -1;
    }

    /* If you need or want more methods, feel free to add them. */
    
}
