/* COMPSCI 424 Program 1
 * Name:
 */
package compsci424.p1.java;

import java.util.List;

public class Version2 {
	private Version2PCB[] pcbArray;
  
    public Version2(int n) {
    	pcbArray = new Version2PCB[n];
    	pcbArray[0] = new Version2PCB();
    	
    	for(int i = 1; i < n; i++) {
    		pcbArray[i] = null; //new Version2PCB();
    	}
    }

    int create(int parentPid) {
    	int childPid = findFreePCB();
        if (childPid != -1) {
            pcbArray[childPid].setParent(parentPid);
            int olderSibling = findOlderSibling(parentPid);
            if (olderSibling != -1) {
                pcbArray[olderSibling].setYoungerSibling(childPid);
                pcbArray[childPid].setOlderSibling(olderSibling);
            } else {
                pcbArray[parentPid].setFirstChild(childPid);
            }
        }

    	return 0; 
    }

    int destroy (int targetPid) {
    	if (pcbArray[targetPid] != null) {
            int youngerSibling = pcbArray[targetPid].getYoungerSibling();
            while (youngerSibling != -1) {
                int nextSibling = pcbArray[youngerSibling].getYoungerSibling();
                destroy(youngerSibling);
                youngerSibling = nextSibling;
            }

            int olderSibling = pcbArray[targetPid].getOlderSibling();
            int parentPid = pcbArray[targetPid].getParent();

            if (olderSibling != -1) {
                pcbArray[olderSibling].setYoungerSibling(pcbArray[targetPid].getYoungerSibling());
            } else {
                pcbArray[parentPid].setFirstChild(pcbArray[targetPid].getYoungerSibling());
            }

            if (pcbArray[targetPid].getYoungerSibling() != -1) {
                pcbArray[pcbArray[targetPid].getYoungerSibling()].setOlderSibling(olderSibling);
            }

            pcbArray[targetPid] = null;
        }
       return 0;
   }
    
   void showProcessInfo() {
	   for (int i = 0; i < pcbArray.length; i++) {
	        if (pcbArray[i] != null) {
	            System.out.print("Process " + i + ": parent is " + pcbArray[i].getParent() + " and children are ");
	            if (pcbArray[i].getFirstChild() != -1) {
	                for (int child = pcbArray[i].getFirstChild(); child != -1; child = pcbArray[child].getYoungerSibling()) {
	                    System.out.print(child + " ");
	                }
	            } else {
	                System.out.print("empty");
	            }
	            System.out.println();
	        }
	    }
   }
   
   
   private int findFreePCB() {
       for (int i = 0; i < pcbArray.length; i++) {
           if (pcbArray[i] == null) {
               return i;
           }
       }
       return -1;
   }
   
   private int findOlderSibling(int parentPid) {
       int currentChild = pcbArray[parentPid].getFirstChild();
       int olderSibling = -1;
       while (currentChild != -1) {
           olderSibling = currentChild;
           currentChild = pcbArray[currentChild].getYoungerSibling();
       }
       return olderSibling;
   }
}

