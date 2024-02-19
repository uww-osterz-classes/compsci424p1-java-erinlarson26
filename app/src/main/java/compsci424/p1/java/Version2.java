/* COMPSCI 424 Program 1
 * Name:
 */
package compsci424.p1.java;

public class Version2 {
	private Version2PCB[] pcbArray;
  
    public Version2() {
    	pcbArray = new Version2PCB[16];
    	pcbArray[0] = new Version2PCB(0);
    }

    int create(int parentPid) {
    	if(parentPid < 0 || parentPid >= pcbArray.length || pcbArray[parentPid] == null) {
    		System.out.println("Error: Parent process does not exist.");
    		return 1;
    	}
    	
    	int childPid = freePid();
    	if(childPid == -1) {
    		System.out.println("Error: No free PIDs available for new process.");
    		return 2;
    	}
    	
    	Version2PCB childPCB = new Version2PCB(childPid);
    	pcbArray[childPid] = childPCB;
    	
    	pcbArray[parentPid].setFirstChild(childPCB);
    	Version2PCB sibling = pcbArray[parentPid].getYoungerSibling();
    	if(sibling != null) {
    		childPCB.setOlderSibling(sibling);
    		sibling.setYoungerSibling(childPCB);
    	}
        return 0; 
    }

    int destroy (int targetPid) {
    	if(targetPid < 0 || targetPid >= pcbArray.length || pcbArray[targetPid] == null) {
    		System.out.println("Error: Target process does not exist.");
    		return 1;
    	}
    	
    	destroyDescendants(targetPid);
    	
    	Version2PCB parent = pcbArray[targetPid].getParent();
    	Version2PCB olderSibling = pcbArray[targetPid].getOlderSibling();
    	Version2PCB youngerSibling = pcbArray[targetPid].getYoungerSibling();
    	
    	if(parent != null) {
    		if(parent.getFirstChild() == pcbArray[targetPid]) {
    			parent.setFirstChild(youngerSibling);
    		}
    		
    		if(olderSibling != null) {
    			olderSibling.setYoungerSibling(youngerSibling);
    		}
    		
    		if(youngerSibling != null) {
    			youngerSibling.setOlderSibling(olderSibling);
    		}
    	}
    	
    	pcbArray[targetPid] = null;
    	
       return 0;
   }
    
   private void destroyDescendants(int targetPid) {
	   for(Version2PCB child : pcbArray[targetPid].getChildren()) {
		   destroyDescendants(child.getPid());
		   pcbArray[child.getPid()] = null;
	   }
   }

   void showProcessInfo() {
	   showProcessInfoRecursive(pcbArray[0], 0);
   }
   
   private void showProcessInfoRecursive(Version2PCB pcb, int indentLevel) {
	   if(pcb != null) {
		   for(int i = 0; i < indentLevel; i++) {
			   System.out.print(" ");
		   }
		   System.out.println(pcb);
		   for(Version2PCB child : pcb.getChildren()) {
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
