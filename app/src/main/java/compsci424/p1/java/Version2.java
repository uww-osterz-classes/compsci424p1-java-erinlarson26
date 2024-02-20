/* COMPSCI 424 Program 1
 * Name:
 */
package compsci424.p1.java;

import java.util.List;

public class Version2 {
	private Version2PCB[] pcbArray;
  
    public Version2() {
    	pcbArray = new Version2PCB[16];
    	for(int i = 0; i < 16; i++) {
    		pcbArray[i] = null;//new Version2PCB();
    	}
    }
    
    public void runCommandSequence(List<String> actions) {
    	for(String action : actions) {
    		String[] parts = action.split(" ");
    		String command = parts[0];
    		int processId = Integer.parseInt(parts[1]);
    		
    		switch (command) {
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
    	
    	pcbArray[childPid] = new Version2PCB();
    	pcbArray[childPid].setParent(parentPid);
    	
    	int youngestSibling = pcbArray[parentPid].getFirstChild();
    	while(pcbArray[youngestSibling].getYoungerSibling() != -1) {
    		youngestSibling = pcbArray[youngestSibling].getYoungerSibling();
    	}
    	
    	pcbArray[youngestSibling].setYoungerSibling(childPid);
    	pcbArray[childPid].setOlderSibling(youngestSibling);
        return 0; 
    }

    int destroy (int targetPid) {
    	if(targetPid < 0 || targetPid >= pcbArray.length || pcbArray[targetPid] == null) {
    		System.out.println("Invalid target process ID");
    		return 1;
    	}
    	
    	int olderSibling = pcbArray[targetPid].getOlderSibling();
    	int youngerSibling = pcbArray[targetPid].getYoungerSibling();
    	
    	if(olderSibling != -1) {
    		pcbArray[olderSibling].setYoungerSibling(youngerSibling);
    	}
    	else {
    		pcbArray[pcbArray[targetPid].getParent()].setFirstChild(youngerSibling);
    	}
    	
    	if(youngerSibling != -1) {
    		pcbArray[youngerSibling].setOlderSibling(olderSibling);
    	}
    	
    	for(int child = pcbArray[targetPid].getFirstChild(); child != -1; child = pcbArray[child].getYoungerSibling()) {
    		destroy(child);
    	}
    	
    	pcbArray[targetPid] = null;
    	
       return 0;
   }
    
   void showProcessInfo() {
	   System.out.println("Process Hierarchy (Version 2):");
       for (int i = 0; i < pcbArray.length; i++) {
           if (pcbArray[i] != null) {
               System.out.println("Process " + pcbArray[i].getProcessId() +
                       ": parent is " + pcbArray[i].getParent() +
                       ", first child is " + pcbArray[i].getFirstChild() +
                       ", younger sibling is " + pcbArray[i].getYoungerSibling() +
                       ", older sibling is " + pcbArray[i].getOlderSibling());
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
