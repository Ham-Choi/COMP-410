/**
 * COMP 410
 * See inline comment descriptions for methods we need that are
 * not described in the interface.
 *
 */
package assignment1;

public class StackSet implements StackSet_Interface {
	private Node head;  //this will be the entry point to your linked list 
	//( it points to the first data cell, the top for a stack )
	private final int limit;  //defines the maximum size the stackset may contain
	private int size;   //current count of elements in the stackset 

	public StackSet( int maxNumElts ){ //this constructor is needed for testing purposes. 
		head = null;                 //Please don't modify what you see here!
		limit = maxNumElts;          //you may add fields if you need to
		size = 0;
	}
	//implement all methods of the interface, and 
	//also include the getRoot method below that we made for testing purposes. 
	//Feel free to implement private helper methods!
	//you may add any fields you need to add to make it all work... 

	public Node getRoot(){ //leave this method as is, used by the grader to grab 
		return head;         //your data list easily.
	}
	@Override
	public boolean push(double elt) {
		// TODO Auto-generated method stub
		if (contains(elt)) {
			Node p = head;
			Node n = head;
			while (p != null) {
				if (elt == n.getValue()) {
					p.setNext(n.getNext());
					n.setNext(head);
					head = n;
					return true;
				}
				p = n;
				n = n.getNext();
			}
			return false;	
		}
		if (isFull()) {
			return false;
		} 
		if (size < limit) {
			Node temp = new NodeImpl(elt, head);
			head = temp;
			size++;
			return true;
		}
		return false;
	}
	@Override
	public boolean pop() {
		// TODO Auto-generated method stub
		if (head != null) {
			head = head.getNext();
			size--;
			return true;
		} else {
			return false;
		}
	}
	@Override
	public double peek() {
		// TODO Auto-generated method stub
		if (size() >= 1) {
			return head.getValue();
		} else {
			return Double.NaN;
		}
	}
	@Override
	public boolean contains(double elt) {
		// TODO Auto-generated method stub
		Node temp = head;
		while (temp != null) {
			if (temp.getValue() == elt) {
				return true;
			} else {
				temp = temp.getNext();
			}
		}
		return false;
	}
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}
	@Override
	public int limit() {
		// TODO Auto-generated method stub
		return limit;
	}
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size() == 0;
	}
	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return size == limit;
	}
}