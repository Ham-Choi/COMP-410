package assignment4_f20;

public class MinBinHeap implements Heap {
  private CacheFrame[] array; // load this array
  private int size;      // how many items currently in the heap
  private int arraySize; // Everything in the array will initially
                         // be null. This is ok! Just build out
                         // from array[1]

  public MinBinHeap(int nelts) {
    this.array = new CacheFrame[nelts+1];  // remember we dont use slot 0
    this.arraySize = nelts+1;
    this.size = 0;
    this.array[0] = new CacheFrame(null, 0); // 0 not used, so this is arbitrary
  }

  // Please do not remove or modify this method! Used to test your entire Heap.
  @Override
  public CacheFrame[] getHeap() { return this.array; }
  //===============================================================
  //
  // here down you implement the ops in the interface
  //
  //===============================================================
  @Override
  public void insert(CacheFrame elt) {
  	// TODO Auto-generated method stub
  	if (size() >= this.arraySize) {
  		return;
  	}
  	if (size() == 0) {
  		getHeap()[1] = elt;
  		getHeap()[1].setSlot(1);
  		this.size++;
  	} else {
  		int currentNode = size() + 1;
  		getHeap()[currentNode] = elt;
  		getHeap()[currentNode].setSlot(currentNode);
  		this.size++;
  		bubbleUp(currentNode);
  		return;
  	}
  }//add to HashMap
/*
 * insert
      in: a CacheFrame object, containing String key (address), 
          the priority (frequency counter) and the array subscript (slot).
          We may have duplicate priorities being inserted 
      return: void
      effect: the heap size goes up one
      
      Ordering is done based on the integers as priorities. In the test 
      data we use, the integer priorities will not be unique -- there 
      may well be duplicate values. Every new frame object we put in 
      the heap has priority 1 (frequency 1 reflecting its first reference). 
      So most inserts will percolate upwards to near the root. Remember 
      when you move elements around in the heap array to go to the 
      frame object and update the "slot" field.
 */
  @Override
  public void delMin() {
  	// TODO Auto-generated method stub
  	if (size == 0) {
  		return;
  	}
  	getHeap()[1] = getHeap()[size()];
  	getHeap()[size()] = null;
  	this.size--;
  	bubbleDown(1);
  	return;
  }
/*
 *  delMin
      in: nothing
      return: nothing
      effect: the heap size goes down one, the element at the root is removed
      
      This operation removes the root element from the heap. The size of 
      the heap goes down by 1 (if the heap was not already empty). If 
      delMin is done on an empty heap, treat it as a no-op... i.e., do 
      nothing other than return void. Remember when you move elements around 
      in the heap array to go to the frame object and update the "slot" field.
 */
  @Override
  public CacheFrame getMin() {
  	// TODO Auto-generated method stub
  	if (size() == 0) {
  		return null;
  	}
  	incElt(getHeap()[1]);
	return getHeap()[1];
  }
/*
 * getMin
      in: nothing
      return: a CacheFrame object (the one in slot 1 on the array)
      effect: no change in heap state
      
      This operation returns a frame object. It does NOT alter the heap.
      The heap has the same elements in the same arrangement after as it 
      did before. If getMin is done on an empty heap, return null.
 */
  @Override
  public int size() {
  	// TODO Auto-generated method stub
  	return this.size;
  }
/*
 * size
      in: nothing
      return: integer 0 or greater, the count of the number of elements in the heap
      effect: no change in heap state
      
      The size operation simply returns the count of how many elements 
      are in the heap. It should be 0 if the heap is empty, and 
      always return a 0 or greater.
 */
  @Override
  public void incElt(CacheFrame elt) {
  	// TODO Auto-generated method stub
	  elt.setPriority(elt.getPriority()+1);
	  bubbleDown(elt.getSlot());
	  return;
  }
/*
 *  incELt
      in: a CacheFrame object
      return: nothing
      effect: frequency counter (priority) in the CacheFrame object is incremented by 1
              heap state is altered by perhaps the indicated element being bubbled down
              also, slot numbers in moved heap elements change to reflect new locations
              
              The incElt operation is a very active method in our assignment, as it
              will be called many times when the Cache has a refer operation. 
              The parameter is a pointer to a frame object, so you must access that 
              object and first add 1 to its priority (do a ++ on the frequency field). 
              We are not implementing a full "change by delta" operation like in 
              your text... we are just doing a "delta" of +1 (and -1 for decElt). 
              Once the frequency/priority is incremented, apply the bubble down (up) 
              activity to move the frame to the new proper place in the heap array to 
              maintain heap order.

			The thing to watch with these operations (and all operations 
			that move elements around in the heap array) is to make sure 
			when a heap elements moves to a different array slot, you update 
			the slot field in the frame object, to properly reflect where 
			that frame object now resides in the array.
 */
  @Override
  public void decElt(CacheFrame elt) {
  	// TODO Auto-generated method stub
	  if (elt.getPriority() == 1) {
		  return;
	  } else {
	  elt.setPriority(elt.getPriority()-1);
	  bubbleUp(elt.getSlot());
	  return;
	  }
  }
  /*
   *  decElt
      in: a CacheFrame object
      return: nothing
      effect: frequency counter (priority) in the CacheFrame object is decreased by 1
              heap state is altered by perhaps the indicated element being bubbled up
              also, slot numbers in moved heap elements change to reflect new locations
              error: do no frequency change is the CacheFrame object already has priority 1
              (all priorities must be 1 or greater).
              
              In decElt, watch out for decrementing a priority that is 
              already 1. We wont allow frequency counts to go below 1. If this 
              is attempted, just leave the frequency at 1.
   */
  private void bubbleUp(int index) {
	  while ((index / 2) > 0) {
			if (getHeap()[index].getPriority() < getHeap()[index/2].getPriority()) {
				CacheFrame temp = getHeap()[index / 2];
				getHeap()[index / 2] = getHeap()[index];
				getHeap()[index / 2].setSlot(index / 2);
				getHeap()[index] = temp;
				getHeap()[index].setSlot(index);
				index = index / 2;
			} else {
				return;
			}
		}
  }
  
  private void bubbleDown(int index) {
	  while (index * 2 <= size()) {
		  int child = leaf(index);  
		  if (getHeap()[index].getPriority() > getHeap()[child].getPriority()) {
			  CacheFrame temp = getHeap()[index];
			  getHeap()[index] = getHeap()[child];
			  getHeap()[index].setSlot(getHeap()[child].getSlot());
			  getHeap()[child] = temp;
			  getHeap()[child].setSlot(temp.getSlot());
			  index = child;
		  }
	  return;
	  }

  }
  private int leaf(int pIndex) {
	  if (pIndex * 2 + 1 > size()) {
		  return pIndex * 2;
	  } else if (getHeap()[pIndex * 2].getPriority() < getHeap()[pIndex * 2 + 1].getPriority()) {
			  return pIndex * 2;
	  } else {
		  return pIndex * 2 + 1;
	  }
  }
}