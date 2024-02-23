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
    		pcbArray[i] = new Version2PCB();
    	} 
    } 

    int create(int parentPid) {
    	int childPid = findFreePCB();
        if (childPid != -1) {
            pcbArray[childPid].parent = parentPid;
            int olderSibling = findOlderSibling(parentPid);
            if (olderSibling != -1) {
                pcbArray[olderSibling].youngerSibling = childPid;
                pcbArray[childPid].olderSibling = olderSibling;
            } else {
                pcbArray[parentPid].firstChild = childPid;
            }
        }

    	return 0; 
    }

    int destroy (int targetPid) {
    	if (pcbArray[targetPid] != null) {
            for(int childPid = pcbArray[targetPid].firstChild; childPid != -1;) {
            	int nextSibling = pcbArray[childPid].youngerSibling;
            	destroy(childPid);
            	childPid = nextSibling;
            }
            pcbArray[targetPid] = new Version2PCB();
    	}
       return 0;
   }
    
   void showProcessInfo() {
	   for (int i = 0; i < pcbArray.length; i++) {
	        if (pcbArray[i] != null) {
	            System.out.print("Process " + i + ": parent is " + pcbArray[i].parent + " and children are ");
	            if (pcbArray[i].firstChild != -1) {
	                for (int child = pcbArray[i].firstChild; child != -1; child = pcbArray[child].youngerSibling) {
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
           if (pcbArray[i].parent == -1) {
               return i;
           }
       }
       return -1;
   }
   
   private int findOlderSibling(int parentPid) {
       int currentChild = pcbArray[parentPid].firstChild;
       int olderSibling = -1;
       while (currentChild != -1) {
           olderSibling = currentChild;
           currentChild = pcbArray[currentChild].youngerSibling;
       }
       return olderSibling;
   }
}

