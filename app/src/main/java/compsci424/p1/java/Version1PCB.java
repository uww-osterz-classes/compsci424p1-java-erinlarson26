/* COMPSCI 424 Program 1
 * Name:
 */
package compsci424.p1.java;

import java.util.LinkedList;
import java.util.List;

public class Version1PCB {
    int parent;
    List<Integer> children;
     
    public Version1PCB(int parent) {
    	this.parent = parent;
    	this.children = new LinkedList<>();
    }
    
}
