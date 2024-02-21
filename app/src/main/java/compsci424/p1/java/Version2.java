/* COMPSCI 424 Program 1
 * Name:
 */
package compsci424.p1.java;

import java.util.List;

public class Version2 {
	private Version2PCB[] pcbArray;
  
    public Version2(int n) {
    	pcbArray = new Version2PCB[n];
    	for(int i = 0; i < n; i++) {
    		pcbArray[i] = new Version2PCB();
    	}
    }

    int create(int parentPid) {
    	if(parentPid < 0 || parentPid >= pcbArray.length) {
    		System.out.println("Invalid parent process ID");
    		return 1;
    	}

    	int newProcessId = -1;
        for (int i = 0; i < pcbArray.length; i++) {
            if (pcbArray[i] == null) {
                newProcessId = i;
                break;
            }
        }

        if (newProcessId == -1) {
            System.out.println("No free slot for new process");
            return 1;
        }

        pcbArray[newProcessId] = new Version2PCB(parentPid);

        if (pcbArray[parentPid].getFirstChild() == -1) {
            pcbArray[parentPid].setFirstChild(newProcessId);
        } else {
            int lastSibling = pcbArray[parentPid].getFirstChild();
            while (pcbArray[lastSibling].getYoungerSibling() != -1) {
                lastSibling = pcbArray[lastSibling].getYoungerSibling();
            }

            pcbArray[lastSibling].setYoungerSibling(newProcessId);
            pcbArray[newProcessId].setOlderSibling(lastSibling);
        }

    	System.out.println("Process " + newProcessId + " created under Process " + parentPid);


    	return 0; 
    }

    int destroy (int targetPid) {
    	if(targetPid < 0 || targetPid >= pcbArray.length) {
    		System.out.println("Invalid target process ID");
    		return 1;
    	}
    	
    	pcbArray[targetPid].markAsFree();
    	
    	int olderSibling = pcbArray[targetPid].getOlderSibling();
        int youngerSibling = pcbArray[targetPid].getYoungerSibling();

        if (olderSibling != -1) {
            pcbArray[olderSibling].setYoungerSibling(youngerSibling);
        }

        if (youngerSibling != -1) {
            pcbArray[youngerSibling].setOlderSibling(olderSibling);
        }
    	
       return 0;
   }
    
   void showProcessInfo() {
	    int current = pcbArray[0].getFirstChild();

        while (current != -1) {
            System.out.print("Process " + current + ": parent is " + pcbArray[current].getParent());
            current = pcbArray[current].getYoungerSibling();
            System.out.println();
        }
    }
}
