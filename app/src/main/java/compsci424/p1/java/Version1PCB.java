/* COMPSCI 424 Program 1
 * Name:
 */
package compsci424.p1.java;

import java.util.LinkedList;
import java.util.List;

public class Version1PCB {
    private int parent;
    private List<Integer> children;
     
    public Version1PCB() {
    	this.setParent(-1);
    	this.setChildren(new LinkedList<>());
    }

	public List<Integer> getChildren() {
		return children;
	}

	public void setChildren(List<Integer> children) {
		this.children = children;
	}

	public int getParent() {
		return parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}
    

}
