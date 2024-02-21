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
    	pcbArray[0] = new Version1PCB();
    	
    	
    	for(int i = 1; i < n; i++) {
    		pcbArray[i] = null; //new Version1PCB();
    	}
    }
 
    int create(int parentPid) {
    	int childPid = findFreePCB();
        if (childPid != -1) {
            pcbArray[childPid].setParent(parentPid);
            pcbArray[parentPid].getChildren().add(childPid);
        }
    	
        return 0;
    }

    int destroy (int targetPid) {
    	if (pcbArray[targetPid] != null) {
            for (int child : pcbArray[targetPid].getChildren()) {
                destroy(child);
            }
            
            int parentPid = pcbArray[targetPid].getParent();
            pcbArray[parentPid].getChildren().remove((Integer) targetPid);
            pcbArray[targetPid] = null;
        }
       
        return 0;
    }
    
    void showProcessInfo() {
    	  for (int i = 0; i < pcbArray.length; i++) {
              if (pcbArray[i] != null) {
                  System.out.print("Process " + i + ": parent is " + pcbArray[i].getParent() + " and children are ");
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
      }
    
    private int findFreePCB() {
        for (int i = 0; i < pcbArray.length; i++) {
            if (pcbArray[i] == null) {
                return i;
            }
        }
        return -1;
    }
}
