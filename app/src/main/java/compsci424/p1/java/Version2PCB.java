/* COMPSCI 424 Program 1
 * Name:
 */
package compsci424.p1.java;

import java.util.LinkedList;
import java.util.List;

/**
 * The process control block structure that is used to track a
 * process's parent, first child, younger sibling, and older sibling
 * (if they exist) in Version 2.
 */
public class Version2PCB {
    private int pid;
    private Version2PCB parent;
    private Version2PCB firstChild;
    private Version2PCB olderSibling;
    private Version2PCB youngerSibling;
    
    public Version2PCB(int pid) {
    	this.pid = pid;
    }
    
    public int getPid() {
    	return pid;
    }
    
    public Version2PCB getParent() {
    	return parent;
    }
    
    public void setParent(Version2PCB parent) {
    	this.parent = parent;
    }
    
    public Version2PCB getFirstChild() {
    	return firstChild;
    }
    
    public void setFirstChild(Version2PCB firstChild) {
    	this.firstChild = firstChild;
    	if(firstChild != null) {
    		firstChild.setParent(this);
    	}
    }
    
    public Version2PCB getOlderSibling() {
    	return olderSibling;
    }
    
    public void setOlderSibling(Version2PCB olderSibling) {
    	this.olderSibling = olderSibling;
    }
    
    public Version2PCB getYoungerSibling() {
    	return youngerSibling;
    }
    
    public void setYoungerSibling(Version2PCB youngerSibling) {
    	this.youngerSibling = youngerSibling;
    }
    
    public List<Version2PCB> getChildren(){
    	List<Version2PCB> children = new LinkedList<>();
    	Version2PCB currentChild = firstChild;
    	
    	while(currentChild != null) {
    		children.add(currentChild);
    		currentChild = currentChild.getYoungerSibling();
    	}
    	return children;
    }
    
    public String toString() {
    	return "PID: " + pid;
    }
}
