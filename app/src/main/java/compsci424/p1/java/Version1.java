/* COMPSCI 424 Program 1
 * Name:
 */
package compsci424.p1.java;

public class Version1 {
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
    	
        return 0;
    }

    int destroy (int targetPid) {
    	if(targetPid < 0 || targetPid >= pcbArray.length || pcbArray[targetPid] == null ) {
    		System.out.println("Error: Target process does not exist.");
    		return 1;
    	}
    	
    	destroyDescendants(targetPid);
    	
    	pcbArray[targetPid].getParent().removeChild(targetPid);
    	
    	pcbArray[targetPid] = null;
       
        return 0;
    }
    
    private void destroyDescendants(int targetPid) {
    	for(Version1PCB child : pcbArray[targetPid].getChildren()) {
    		destroyDescendants(child.getPid());
    		pcbArray[child.getPid()] = null;
    	}
    }

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
}
