package assignment2_f20;

public class TreeMap_imp implements TreeMap {
	TMCell root;
	int size;
	// add fields as you need to in order to provide the required behavior
	// also you may add private methods to assist you as needed
	// in implementing
	//-------------------------------------------------------------
	TreeMap_imp () { 
		root = null; 
		// for added fields you can add appropriate initialization code here
	}
	//-------------------------------------------------------------
	// dont change, we need this for grading
	@Override
	public TMCell getRoot() { return this.root; }
	//-------------------------------------------------------------
	// "height" is a complete implementation 
	// of the public interface method height
	// it is here to illustrate for you how the textbook sets up 
	// the method implementation as recursion
	// you may include this in your project directly
	public int height() { // public interface method signature
		// this method is the public interface method
		// it is not recursive, but it calls a recursive
		// private method and passes it access to the tree cells
		return height_r(this.getRoot());
	}
	private int height_r(TMCell c) { 
		// inner method with different signature
		// this helper method uses recursion to traverse
		// and process the recursive structure of the tree of cells
		if (c==null) return -1;
		int lht = height_r(c.getLeft());
		int rht = height_r(c.getRight());
		return Math.max(lht,rht) + 1;
	}

	/*
   put: 
    in: a string (the key) and a Value object (associated with the key)
    return: a Value object, or null
    effect: 
      if key is NOT in the structure
         add a new cell to the structure and put the key into it, the value into it
         and return null
      if key IS in the structure
         do not add a new cell
         instead, replace the Value object in the existing cell
                  with the new Value object
                  return the old Value object (the one being replaced)  
	 */
	// recursive
	@Override
	public Value put(String k, Value v) {
		// TODO Auto-generated method stub
		TMCell rtcpy = getRoot();
		TMCell input = new TMCell_imp(k,v);
		return put_r(rtcpy , input);
	}

	/*
 get: 
    in: a string (the key) 
    return: a Value object, or null
    effect: 
      if key is NOT in the structure
         return null (this includes when the structure is empty, size 0)
      if key IS in the structure
         return the Value object from the cell containing the key
	 */
	// recursive
	@Override
	public Value get(String k) {
		// TODO Auto-generated method stub
		return get_r(getRoot(), k);
	}

	/*
 remove: 
    in: a string (the key of the pair to be removed)
    return: nothing in all cases (return void)
    effect: 
      if the key IS in the structure
        take the whole cell out of the structure... the (key,value) pair will not be in the
        structure after
      if the key is NOT in the structure
        make no changes to the state of the structure 
	 */
	// recursive
	@Override
	public void remove(String k) {
		// TODO Auto-generated method stub
		remove_r(getRoot(), k);
	}

	/*
 hasKey: 
    in: a string (the key)
    return: boolean
    effect: 
      if key IS in the structure (meaning there is a (key,value) pair for the key), 
        return true
      if key is NOT in the structure, return false
      in both cases, no change to the structure tate
	 */
	// recursive
	@Override
	public boolean hasKey(String k) {
		// TODO Auto-generated method stub
		if(searchKey(getRoot(), k) != null) {
			return true;
		}
		return false;
	}

	/*
 size:
    in: nothing
    return: int, the number of (key,value) pairs stored in the map structure
    effect: no change to state of tree nodes
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}


	/* 	  maxKey: 
	  		in: none
	    	return: string, the key from the tree that is largest
	    	effect: no tree state change
	      		if tree size if 0, return null */
	// Recursive
	@Override
	public String maxKey() {
		// TODO Auto-generated method stub
		if(size() == 0) { 
			return null;
		}
		TMCell rtcpy = getRoot();
		return traverse(rtcpy, rtcpy.getRight(), false).getKey();
	}

	/*	 minKey: 
		    in: none
		    return: string, the key from the tree that is smallest
		    effect: no tree state change
		      if tree size if 0, return null */
	// Recursive
	@Override
	public String minKey() {
		// TODO Auto-generated method stub
		if(size() <= 0) { 
			return null;
		}
		TMCell rtcpy = getRoot();
		return traverse(rtcpy, rtcpy.getLeft(), true).getKey();
	}

	/* getKeys 
    	in: nothing
    	return: an array of strings, containing just the keys from the tree cells
    	effect: the array of strings is produced in alphabetic order, small to large
      		note, this means do an in-order traversal to get the keys out of the BST sorted */
	@Override
	public String[] getKeys() {
		// TODO Auto-generated method stub
		return null;
	}

	//-------------------------------------------------------------
	// here down... you fill in the implementations for
	// the other interface methods
	//-------------------------------------------------------------
	//
	// remember to implement the required recursion as noted
	// in the interface definition
	//
	//-------------------------------------------------------------

	/*
	 *  if key is NOT in the structure
         add a new cell to the structure and put the key into it, the value into it
         and return null
      if key IS in the structure
         do not add a new cell
         instead, replace the Value object in the existing cell
                  with the new Value object
                  return the old Value object (the one being replaced)
	 * 	
	 */

	private Value put_r(TMCell orig, TMCell input) {
		if (getRoot() == null) {
			root = input;
			size++;
			return null;
		}
		if (orig.getKey().equals(input.getKey())) {
			Value temp = orig.getValue();
			orig.setValue((input.getValue()));
			return temp;

		} else if(isLeft(orig.getKey(), input.getKey())) {
			if(orig.getLeft() == null) {
				orig.setLeft(input);
				size++;
				return null;
			} else {
				return put_r(orig.getLeft(), input);
			}
		} else if (orig.getRight() == null) {
			orig.setRight(input);
			size++;
			return null;
		} else {
			return put_r(orig.getRight(), input);
		}
	}

	private boolean isLeft(String origKey, String inputKey) {
		if (origKey.compareTo(inputKey) > 0) {
			return true;
		} else {
			return false;
		}
	}
	private Value get_r(TMCell orig, String k) {
		if(orig == null || size == 0) {
			return null;
		}
		int diff = orig.getKey().compareTo(k);
		if (diff == 0) {
			return orig.getValue();
		} else if (diff > 0){
			return get_r(orig.getLeft(), k);
		} else {
			return get_r(orig.getRight(), k);
		}
	}

	private void remove_r(TMCell orig,  String k) {
		if(orig == null) {
			return;
		}
		int diff = orig.getKey().compareTo(k);
		if (diff == 0) {
			size--;
			if (orig.getLeft() != null) {
				TMCell temp = traverse(null, orig.getLeft(), false);
				root = temp;
				temp = null;
				return;
			} else {
				TMCell temp = traverse(null, orig.getRight(), true);
				orig = temp;
				temp = null;
				return;
			}
		} else if (diff > 0){
			remove_r(orig.getLeft(),k);
		} else {
			remove_r(orig.getRight(),k);
		}
	}

	private TMCell traverse(TMCell prev, TMCell current, boolean isMin) {
		if (current == null) {
			return prev;
		} else if (isMin) {
			traverse (current, current.getLeft(), true);
		} else {
			traverse(current, current.getRight(), false);
		}
		return null;
	}

	private TMCell searchKey(TMCell orig, String k) {
		if(orig == null) { 
			return null;
		}
		int x = orig.getKey().compareTo(k);
		if (x == 0) {
			return orig;
		} else {
			if (x > 0) {
				searchKey(orig.getLeft(), k);
			} else {
				searchKey(orig.getRight(), k);
			}
		}
		return null;
	}

}
