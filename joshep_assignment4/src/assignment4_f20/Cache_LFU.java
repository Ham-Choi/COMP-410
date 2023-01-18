package assignment4_f20;

import java.util.HashMap;

public class Cache_LFU implements Cache {
  
  HashMap<String, CacheFrame> map; 
    // allocate from java collections lib
    // do it this way so we all start with default size and 
    // default lambda and default hash function for string keys
  MinBinHeap heap; // your own heap code above
  int limit;       // max num elts the cache can hold
  int size;        // current number elts in the cache
  
  Cache_LFU (int maxElts) {
    this.map = new HashMap<String, CacheFrame>();
    this.heap = new MinBinHeap(maxElts);
    this.limit = maxElts;
    this.size = 0;
  }
  // dont change this we need it for grading
  public MinBinHeap getHeap() { return this.heap; }
  public HashMap getHashMap() { return this.map; }
  // =========================================================
  //
  // you fill in code for the other ops in the interface
  //
  //==========================================================
  @Override
  public int size() {
  	// TODO Auto-generated method stub
  	return this.limit;
  }
/*
 * size:
      in: nothing
      return: an integer, the maximum number of frames the cache will hold 
      effect: no cache state change
 */
  @Override
  public int numElts() {
  	// TODO Auto-generated method stub
  	return this.size;
  }
/*
 *  numElts:
      in: nothing
      return: an integer, the number of frames currently stored in the cache 
      effect: no cache state change
 */
  @Override
  public boolean isFull() {
  	// TODO Auto-generated method stub
  	if (numElts() == size()) {
  		return true;
  	} else {
  		return false;
  	}
  }
/*
 *  isFull:
      in: nothing
      return: a boolean, telling is the number of items in the cache is the same as the max limit
      effect: no cache state change
 */
  @Override
  public boolean refer(String address) {
  	// TODO Auto-generated method stub
  	if (map.containsKey(address)) {
  		heap.incElt(map.get(address));
  		return true;
  	} else if (isFull()) {
  		String rmKey = heap.getMin().getValue();
  		heap.delMin();
  		map.remove(rmKey);
  		this.size--;
  		refer(address);
  	} else {
  		CacheFrame newFrame = new CacheFrame(address, 1);
  		heap.insert(newFrame);
  		map.put(address, newFrame);
  		this.size++;
  	}
  	return false;
  }
  /*
   *  refer:
      in: String, a hexadecimal value as a string
          assume this op will always be sent a string the looks like hex
          no need to check or verify
      return: boolean, telling true (we found the hex address in the cache)
                            or false (we did not find the address in cache)
      effect: cache state changes by possibly having a frame removed (if it was full)
              and by having a new frame added if the address is not in the cache.
              if the address IS in the cache, then all that changes in the frequency counter
              for that frame, which goes up one.
              Also the number of frames in the cache goes up one if we add a frame
   
Almost all the action in the cache is from this one operation, and it is 
just a bit complicated. However, if you properly use the interface ops 
of the HashMap and the MinBinHeap you will find that when the smoke clears, 
the code is about 15 lines (not counting comments).

There are two main possibilities when a call like " refer( '2A4C' ); " 
is done. First we check to see if the frame "2A4C" is in the cache. We do 
this by consulting the HashMap and find it either is there, or is not.

If "2A4C" is in the cache, we dont do much other than up the frequency 
count for that frame by 1. We do that by finding the frame object (HashMap) 
and then telling the MinBinHeap to do a incElt on the object.

If "2A4C" is not in the cache, we have a couple other choices. We have to see 
if the cache is full or not. If the cache is not full, then there is room for 
one more frame so we make a frame, put the frequency counter in that frame to 
1, and insert it into both HashMap and the MinBinHeap.

If the cache IS full, then we do the most work. First we have to toss out a frame to make room 
for the new one. This means asking the MinBinHeap to delMin(), removing the root frame (which 
has the lowest frequency count); we also must remove the (address,frame) pair (address from the 
tossed frame) from the HashMap since we tossed it out of the cache. Then we have to what we do if the 
cache is not full... since it is now not full... we have made room for the new frame.
   */
}