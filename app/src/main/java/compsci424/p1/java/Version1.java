/* COMPSCI 424 Program 1
 * Name:
 */
package compsci424.p1.java;

import java.util.List;

public class Version1 {
	private static Version1PCB[] pcbArray;
    /**
     * Default constructor. Use this to allocate (if needed) and
     * initialize the PCB array, create the PCB for process 0, and do
     * any other initialization that is needed. 
     */
    public Version1(int n) {
    	pcbArray = new Version1PCB[n];
    	for(int i = 0; i < n; i++) {
    		pcbArray[i] = new Version1PCB(i == 0 ? -1 : -2);
    	}
    }
 
    int create(int parentPid) {
    	int q = allocatePCB();
    	pcbArray[q].parent = parentPid;
    	pcbArray[parentPid].children.add(q);
        return 0;
    }

    int destroy (int targetPid) {
    	for(int child : pcbArray[targetPid].children) {
    		destroy(child);
    		freePCB(child);
    	}
    	pcbArray[targetPid].children.clear();
    	freePCB(targetPid);
       
        return 0;
    }
    
    void showProcessInfo() {
    	for (int i = 0; i < pcbArray.length; i++) {
            System.out.print("Process " + i + ": parent is " + pcbArray[i].parent + " and ");
            if (pcbArray[i].children.isEmpty()) {
                System.out.println("children are empty");
            } else {
                System.out.print("children are ");
                List<Integer> children = pcbArray[i].children;
                for (int j = 0; j < children.size(); j++) {
                    System.out.print(children.get(j));
                    if (j < children.size() - 1) {
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
        }
    }
    
    private static int allocatePCB() {
    	for(int i = 0; i < pcbArray.length; i++) {
    		if(pcbArray[i].parent == -2) {
    			pcbArray[i].parent = -1;
    			return i;
    		}
    	}
    	throw new RuntimeException("No free PCB available.");
    }
    
    private static void freePCB(int index) {
        pcbArray[index] = new Version1PCB(-1);
    }
}
