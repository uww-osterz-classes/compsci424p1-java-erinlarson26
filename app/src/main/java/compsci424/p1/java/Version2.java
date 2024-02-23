/* COMPSCI 424 Program 1
 * Name:
 */
package compsci424.p1.java;

import java.util.Arrays;
import java.util.List;

public class Version2 {
	private static Version2PCB[] pcbArray;
	private static int nextAvailableIndex;
  
    public Version2(int n) {
    	pcbArray = new Version2PCB[n];
    	for(int i = 0; i < n; i++) {
    		pcbArray[i] = new Version2PCB(-2);
    	}
    	pcbArray[0].parent = -1;
    	nextAvailableIndex = 1;
    } 

    int create(int parentPid) {
    	int q = allocatePCB();
    	pcbArray[q].parent = parentPid;
    	
    	if(pcbArray[parentPid].firstChild == -1) {
    		pcbArray[parentPid].firstChild = q;
    	}
    	else {
    		int youngestChild = pcbArray[parentPid].firstChild;
    		while(pcbArray[youngestChild].youngerSibling != -1) {
    			youngestChild = pcbArray[youngestChild].youngerSibling;
    		}
    		pcbArray[youngestChild].youngerSibling = q;
    		pcbArray[q] = new Version2PCB(-2);
    		pcbArray[q].olderSibling = youngestChild;
    	}

    	return 0; 
    }

    int destroy (int targetPid) {
    	int parent = pcbArray[targetPid].parent;
    	int olderSibling = pcbArray[targetPid].olderSibling;
    	int youngerSibling = pcbArray[targetPid].youngerSibling;
    	
    	if(olderSibling != -1) {
    		pcbArray[olderSibling].youngerSibling = youngerSibling;
    	}
    	else {
    		pcbArray[parent].firstChild = youngerSibling;
    	}
    	
    	if(youngerSibling != -1) {
    		pcbArray[youngerSibling].olderSibling = olderSibling;
    	}
    	
    	for(int child = pcbArray[targetPid].firstChild; child != -1;) {
    		int nextChild = pcbArray[child].youngerSibling;
    		destroy(child);
    		freePCB(child);
    		child = nextChild;
    	}
    	freePCB(targetPid);
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
   
   private static int allocatePCB() {
	   if(nextAvailableIndex < pcbArray.length) {
		   int allocatedIndex = nextAvailableIndex;
		   nextAvailableIndex++;
		   return allocatedIndex;
	   }
	   return 0;
   }
   
   private static void freePCB(int index) {
       pcbArray[index] = new Version2PCB(-2);
   }
   
}

