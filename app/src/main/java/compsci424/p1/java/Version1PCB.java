/* COMPSCI 424 Program 1
 * Name:
 */
package compsci424.p1.java;

import java.util.LinkedList;
import java.util.List;

public class Version1PCB {
	private int processId;
    private int parent;
    private LinkedList<Integer> children;
    
    public Version1PCB(int processId) {
    	this.processId = processId;
    	this.children = new LinkedList<>();
    }
    
    public int getProcessId() {
    	return processId;
    }
    
    public Version1PCB() {
    	children = new LinkedList<>();
    }
    
    public int getParent() {
    	return parent;
    }
    
    public void setParent(int parent) {
    	this.parent = parent;
    }
    
    public LinkedList<Integer> getChildren(){
    	return children;
    }
    
    public void addChild(int child) {
    	children.add(child);
    }

}
