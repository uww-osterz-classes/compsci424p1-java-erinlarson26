/* COMPSCI 424 Program 1
 * Name:
 */
package compsci424.p1.java;

import java.util.LinkedList;
import java.util.List;

/**
 * The process control block structure that is used to track a
 * process's parent and children (if any) in Version 1.
 */
public class Version1PCB {
    private int pid;
    private List<Version1PCB> children;
    private Version1PCB parent;
    
    public Version1PCB(int pid) {
    	this.pid = pid;
    	this.children = new LinkedList<>();
    	this.parent = null;
    }
    
    public int getPid() {
    	return pid;
    }
    
    public List<Version1PCB> getChildren(){
    	return children;
    }
    
    public Version1PCB getParent() {
    	return parent;
    }
    
    public void addChild(Version1PCB child) {
    	children.add(child);
    	child.parent = this;
    }
    
    public void removeChild(int childPid) {
    	children.removeIf(child -> child.getPid() == childPid);
    }
    
    public String toString() {
    	return "PID: " + pid;
    }
}
