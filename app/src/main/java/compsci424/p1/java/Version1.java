/* COMPSCI 424 Program 1
 * Name:
 */
package compsci424.p1.java;

import java.util.List;

public class Version1 {
	private Version1PCB[] pcbArray;
    /**
     * Default constructor. Use this to allocate (if needed) and
     * initialize the PCB array, create the PCB for process 0, and do
     * any other initialization that is needed. 
     */
    public Version1() {
    	pcbArray = new Version1PCB[16];
    	for(int i = 0; i < 16; i++) {
    		pcbArray[i] = new Version1PCB(i);
    	}
    }
 
    public void runCommandSequence(List<String> actions) {
    	for(String action : actions) {
    		String[] parts = action.split(" ");
    		String command = parts[0];
    		int processId = Integer.parseInt(parts[1]);
    		
    		switch(command) {
    		case "create":
    			create(processId);
    			break;
    		case "destroy":
    			destroy(processId);
    			break;
    		default:
    			System.out.println("Invalid command");
    		}
    		showProcessInfo();
    	}
    }
    
    int create(int parentPid) {
    	if(parentPid < 0 || parentPid >= pcbArray.length) {
    		System.out.println("Invalid parent process ID");
    		return 1;
    	}
    	
    	int childPid = freePid();
    	if(childPid == -1) {
    		System.out.println("No free PCB available for creation");
    		return 2;
    	}
    	
    	pcbArray[childPid] = new Version1PCB(childPid);
    	pcbArray[childPid].setParent(parentPid);
    	pcbArray[parentPid].addChild(childPid);
    	
        return 0;
    }

    int destroy (int targetPid) {
    	if(targetPid < 0 || targetPid >= pcbArray.length || pcbArray[targetPid] == null ) {
    		System.out.println("Invalid target process ID");
    		return 1;
    	}
    	
    	for(int child : pcbArray[targetPid].getChildren()) {
    		destroy(child);
    	}
  
    	pcbArray[targetPid] = null;
       
        return 0;
    }
    
    void showProcessInfo() {
    	 System.out.println("Process Hierarchy (Version 1):");
         for (int i = 0; i < pcbArray.length; i++) {
             if (pcbArray[i] != null) {
                 System.out.print("Process " + pcbArray[i].getProcessId() + ": parent is " + pcbArray[i].getParent() +
                         " and children are ");
                 if (pcbArray[i].getChildren().isEmpty()) {
                     System.out.println("empty");
                 } else {
                     for (int child : pcbArray[i].getChildren()) {
                         System.out.print(child + " ");
                     }
                     System.out.println();
                 }
             }
         }
         System.out.println();
     }
      
    private int freePid() {
    	for(int i = 0; i < pcbArray.length; i++) {
    		if(pcbArray[i] == null) {
    			return i;
    		}
    	}
    	return -1;
    }
}
