package assignment3_f20;

public class HashMap_imp implements HashMap { 
	HMCell[] tab;
	int nelts;
	String minKey = null;
	String maxKey = null;

	//-------------------------------------------------------------

	HashMap_imp (int num) { 
		this.tab = new HMCell[num];
		// for (int i=0; i<num; i++) { tab[i] = null; }
		// we can rely on the Java compiler to fill the table array with nulls
		// another way would be Array.fill()
		this.nelts = 0; 
	}

	//-------------------------------------------------------------

	public int hash (String key, int tabSize) {
		int hval = 7;
		for (int i=0; i<key.length(); i++) {
			hval = (hval*31) + key.charAt(i);
		}
		hval = hval % tabSize;
		if (hval<0) { hval += tabSize; }
		return hval;
	}
	/*
	 * hash
    in: the key (a String), and table size (an int)
    return: integer hash value for the given key and table size
    effect: no change to hash table state
	 */
	//-------------------------------------------------------------

	// dont change 
	@Override
	public HMCell[] getTable() { return this.tab; }

	@Override
	public Value put(String k, Value v) {
		// TODO Auto-generated method stub
		int hval = hash(k, getTable().length);
		if (hasKey(k)) {
			Value temp = getTable()[hval].getValue(); 
			getTable()[hval].setValue(v);
			return temp;
		} 
		getTable()[hval] = new HMCell_imp(k,v);
		nelts++;
		if(lambda() >= 1) {
			extend();
		}
		return null;

	}
	/*
	 *  put: 
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
	 * 
	 */
	/*
	 * Chaining:
Implement your hash table to handle collisions via chaining (not probing). 
  In the put operation, put a check on lambda and if lambda becomes 1 or greater 
  after any put, perform the extend operation automatically (before the put returns).
	 */

	@Override
	public Value get(String k) {
		// TODO Auto-generated method stub
		if (size() == 0 || !hasKey(k)) {
			return null;
		} else {
			for (int i = 0; i < getTable().length; i++) {
				if (getTable()[i] == null) {
					continue;
				}
				if (getTable()[i].getKey().compareTo(k) == 0) {
					return tab[i].getValue();
				}
			}
		}
		return null;
	}
	/*
	 * get: 
    in: a string (the key) 
    return: a Value object, or null
    effect: 
      if key is NOT in the structure
         return null (this includes when the structure is empty, size 0)
      if key IS in the structure
         return the Value object from the cell containing the key
	 */

	@Override
	public void remove(String k) {
		// TODO Auto-generated method stub
		if (getTable() == null) {
			return;
		}
		if(hasKey(k)) {
			int hval = hash(k, getTable().length);
			getTable()[hval] = null;
			nelts--;
			return;
		} else {
			return;
		}
	}
	/*
	 * remove: 
    in: a string (the key of the pair to be removed)
    return: nothing in all cases (return void)
    effect: 
      if the key IS in the structure
        take the whole cell out of the structure... the (key,value) pair will not be in the
        structure after
      if the key is NOT in the structure
        make no changes to the state of the structure 
	 */

	@Override
	public boolean hasKey(String k) {
		// TODO Auto-generated method stub
		if (getTable() == null) {
			return false;
		}
		for (int i = 0; i < getTable().length; i++) {
			if (getTable()[i] == null) {
				continue;
			}
			if (getTable()[i].getKey().compareTo(k) == 0) {
				return true;
			}
		}
		return false;
	}
	/* hasKey: 
    in: a string (the key)
    return: boolean
    effect: 
      if key IS in the structure (meaning there is a (key,value) pair for the key), 
        return true
      if key is NOT in the structure, return false
      in both cases, no change to the structure tate 
	 */

	@Override
	public int size() {return nelts;}
	/*
	 * size:
    in: nothing
    return: int, the number of (key,value) pairs stored in the map structure
    effect: no change to state of tree nodes
	 */

	@Override
	public String maxKey() {
		// TODO Auto-generated method stub
		if (getTable().length == 0) {
			return null;
		}
		for (int i = 0; i < getTable().length; i++) {
			if (getTable()[i] == null) {
				continue;
			}
			if (this.maxKey == null) {
				this.maxKey = getTable()[i].getKey();
			}
			if (getTable()[i].getKey().compareTo(this.maxKey) > 0) {
				this.maxKey = getTable()[i].getKey();
			}
		}
		return this.maxKey;
	}
	/* maxKey: 
    in: none
    return: string, the key from the hash table that is largest
    effect: no hash table state change
      if hash table size if 0, return null
	 */

	@Override
	public String minKey() {
		// TODO Auto-generated method stub
		if (getTable().length == 0) {
			return null;
		}
		for (int i = 0; i < getTable().length; i++) {
			if (getTable()[i] == null) {
				continue;
			}
			if (this.minKey == null) {
				this.minKey = getTable()[i].getKey();
			}
			if (getTable()[i].getKey().compareTo(this.minKey) < 0) {
				this.minKey = getTable()[i].getKey();
			}
		}
		return this.minKey;
	}

	/* minKey: 
    in: none
    return: string, the key from the hash table that is smallest
    effect: no hash table state change
      if hash table size if 0, return null
	 */


	@Override
	public String[] getKeys() {
		// TODO Auto-generated method stub
		String[] keyArr = new String[size()];
		int i = 0;
		int j = 0;
		while (i < getTable().length) {
			if (getTable()[i] != null) {
				keyArr[j] = getTable()[i].getKey();
				j++;
			}
			i++;
		} 
		return keyArr;
	}
	/* getKeys 
    in: nothing
    return: an array of strings, containing just the keys from the hash table cells
    effect: the array of strings is produced in unspecified, which means it is ok 
      to just go through the table subscript 0 up and pull keys from the cells

      // in random order
	 */

	@Override
	public double lambda() {return (double)size()/getTable().length;}
	/* lambda
    in: nothing
    returns: the lambda value for the table (a double)
    effect: no change in the state of the hash table
    // compute lambda load factor
	 */

	@Override
	public double extend() {
		// TODO Auto-generated method stub
		HMCell[] temp = new HMCell[getTable().length*2];
		for (int i = 0; i < getTable().length;i++) {
			if (getTable()[i] != null) {
				int hval = hash(getTable()[i].getKey(), getTable().length);
				int t_hval = hash(getTable()[i].getKey(), getTable().length*2);
				temp[t_hval] = getTable()[hval];
			}
		}
		this.tab = temp;
		return lambda();
	}
	/* extend
    in: nothing
    returns: a new lambda value, after the table array has been extended
    effect: the array that is the hash table is doubled in size, and the elements 
      in the old table are rehashed (using the new array size) and stored 
      in the new table array
      number of elements stored is unchanged, array is doubled in size 
      (and so lambda gets cut in half)
// double table size, rehash, return new lambda
      Extend:
Use normal, built-in, fixed length Java arrays for this implementation.
The extend function requires you to allocate a new array twice as long as 
  the one currently installed in the HashMap object. You must then go through 
  the old table array and find every cell (every key) and rehash the key using 
  the new twice-as-large array size, and link the cell with that key into the 
  new table array. If you do this by unlinking it from the old table and then 
  "moving it" to the new table you will not create a lot of garbage. However, 
  the method would also work by allocating new cells for the new table array 
  (and copying in the keys and the pointers to the value objects). I think it 
  is less complicated to unlink the cell from the old table and link into the new table.
When re-hashing into the new table array is complete, you must make the HashMap 
  object reference the new extended array as the table array in the object.
Also recall that since we already know all the keys (cells) in the old table 
  array are unique, there is no need to check for uniqueness when putting the 
  cells into the new table. You can just link them in at the head of whatever list 
  they need to go in.
Also note that the extend operation returns the new lambda value (which should 
  be 1/2 of the lambda value before the extend).
	 */

	/* String keys When comparing strings note that the String.compareTo 
  	method compares two strings lexically and should be used in this assignment
	 */
}