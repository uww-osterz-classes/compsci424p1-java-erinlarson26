/* COMPSCI 424 Program 1
 * Name:
 */
package compsci424.p1.java;

import java.util.LinkedList;
import java.util.List;

public class Version1PCB {
    private int parent;
    private LinkedList<Integer> children;
     
    public Version1PCB() {
    	this.parent = -1;
    	this.children = new LinkedList<>();
    }
    
    public Version1PCB(int parent) {
    	this.parent = parent;
    	this.children = new LinkedList<>();
    }
    
    public int getParent() {
    	return parent;
    }
    
    public List<Integer> getChildren(){
    	return children;
    }
    
    public void addChild(int childPid) {
    	children.add(childPid);
    }
    
    public void markAsFree() {
    	parent = -1;
    	children.clear();
    }

}
