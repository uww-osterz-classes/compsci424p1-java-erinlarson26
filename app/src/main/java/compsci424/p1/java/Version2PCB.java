/* COMPSCI 424 Program 1
 * Name:
 */
package compsci424.p1.java;

public class Version2PCB {
   private int parent;
   private int firstChild;
   private int youngerSibling;
   private int olderSibling;
   
   public Version2PCB() {
	   this.parent = -1;
	   this.firstChild = -1;
	   this.youngerSibling = -1;
	   this.olderSibling = -1;
   }
   
   public Version2PCB(int parent) {
	   this.parent = parent;
	   this.firstChild = -1;
	   this.youngerSibling = -1;
	   this.olderSibling = -1;
   }
    
   public int getParent() {
	   return parent;
   }
    
   public int getFirstChild() {
	   return firstChild;
   }
   
   public void setFirstChild(int firstChild) {
	   this.firstChild = firstChild;
   }
   
   public int getYoungerSibling() {
	   return youngerSibling;
   }
   
   public void setYoungerSibling(int youngerSibling) {
	   this.youngerSibling = youngerSibling;
   }
   
   public int getOlderSibling() {
	   return olderSibling;
   }
   
   public void setOlderSibling(int olderSibling) {
	   this.olderSibling = olderSibling;
   }
   
   public void markAsFree() {
	   parent = -1;
	   firstChild = -1;
	   youngerSibling = -1;
	   olderSibling = -1;
   }
}
