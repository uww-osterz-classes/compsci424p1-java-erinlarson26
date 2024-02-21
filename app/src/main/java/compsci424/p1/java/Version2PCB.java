/* COMPSCI 424 Program 1
 * Name:
 */
package compsci424.p1.java;

public class Version2PCB {
   private int parent;
   private int firstChild;
   private int youngerSibling;
   private int olderSibling;
   private int children;
   
   public Version2PCB() {
	   this.setParent(-1);
	   this.setFirstChild(-1);
	   this.setYoungerSibling(-1);
	   this.setOlderSibling(-1);
   }

public int getOlderSibling() {
	return olderSibling;
}

public void setOlderSibling(int olderSibling) {
	this.olderSibling = olderSibling;
}

public int getYoungerSibling() {
	return youngerSibling;
}

public void setYoungerSibling(int youngerSibling) {
	this.youngerSibling = youngerSibling;
}

public int getParent() {
	return parent;
}

public void setParent(int parent) {
	this.parent = parent;
}

public int getFirstChild() {
	return firstChild;
}

public void setFirstChild(int firstChild) {
	this.firstChild = firstChild;
}

public int getChildren() {
	return children;
}

public void setChildren(int children) {
	this.children = children;
}
   
   


    
  
}
