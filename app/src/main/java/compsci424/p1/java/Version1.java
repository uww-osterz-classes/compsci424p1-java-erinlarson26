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
    public Version1(int n) {
    	pcbArray = new Version1PCB[n];
    	for(int i = 0; i < n; i++) {
    		pcbArray[i] = new Version1PCB();
    	}
    }
 
    int create(int parentPid) {
    	if(parentPid < 0 || parentPid >= pcbArray.length) {
    		System.out.println("Invalid parent process ID");
    		return 1;
    	}
    	
    	int newProcessId = pcbArray.length;
    	
    	pcbArray[newProcessId] = new Version1PCB(parentPid);
    	pcbArray[parentPid].addChild(newProcessId);
    	
    	System.out.println("Process " + newProcessId + " created under Process " + parentPid);
    	
        return 0;
    }

    int destroy (int targetPid) {
    	if(targetPid < 0 || targetPid >= pcbArray.length) {
    		System.out.println("Invalid target process ID");
    		return 1;
    	}
    	
    	for(int child : pcbArray[targetPid].getChildren()) {
    		destroy(child);
    	}
  
    	int parentPid = pcbArray[targetPid].getParent();
    	pcbArray[parentPid].removeChild(targetPid);
    	pcbArray[targetPid] = null;
       
        return 0;
    }
    
    void showProcessInfo() {
    	for (int i = 0; i < pcbArray.length; i++) {
    		System.out.print("Process " + i + ": parent is " + pcbArray[i].getParent());
    		if (!pcbArray[i].getChildren().isEmpty()) {
    			System.out.print(" and children are " + pcbArray[i].getChildren());
    		}
    		System.out.println();
    	}
    }
}
