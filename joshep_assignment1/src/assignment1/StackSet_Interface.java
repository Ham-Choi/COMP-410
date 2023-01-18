/**
 * COMP 410
 *
 * Make your class and its methods public!
 * Don't modify this file!
 *
 * Your StackSet implementation will implement this interface.
 *
*/


package assignment1;
/*
  Interface: The StackSet will provide this collection of operations:

  push:
    in: double
    return: boolean (indicating success or not)
    effect:
      push will first of all ensure that at most one element with any specific integer 
      value is ever stored in the LIFOset... so we say the stack implements a set.

      push will also make sure that the size of the stack never exceeds the limit() limit
      established by the constructor

      if we do push(n) for some value n here is what happens:
        -- if n is already in the stack, return true 
             we move that existing element to be at the top;
             in this case the size of the stack does not change, but the state does... the element
             relative order will probably change (wont change is n is already the top)
        -- if n is not already in the stack, 
             then we have 2 subcases:
             .. if size()==limit() return false
                  we make no changes to the stack since adding the new element 
                  would make the stack too big
             .. if size() less than limit()  return true
                  there is room for the new element, so we put the new element at the top, 
                  the size increases by 1

  pop:
    in: nothing
    return: boolean... true is the top item is successfully removed
            false if no change is made to the stack
    effect: there are cases to consider
            if the stack is not empty, the the element at top is removed 
               and the size reduced by one (true returned)
            if the stack is empty, then no change is made (false returned)

  peek:  (just a different name for top)
    in: nothing
    return: double 
    effect: if the stack size is 1 or more, then return the value of the top element
            if the stack is empty, then return Double.NaN

  contains:
    in: double
    return: boolean indicating if the value is in the stack
    effect: no change is made in state of the data structure
            return true if the double given is an element in the stack
            return false if it is NOT in the stack (including if the stack is empty)

  size:
    in: nothing
    return: integer (the number of elements in the stack)
            this will be 0 or greater, and limit() or smaller
    effect: no change is made in state of the data structure

  limit:
    in: nothing
    returns: integer, telling the maximum number of elements that can ever
             be in the stack... this is defined as a parameter to the constructor
             it is also a constant, and 0 or greater.
    effect: no change is made in state of the data structure

  isEmpty:
    in: nothing
    return: boolean telling if the stack has no elements
    effect: shorthand for size() == 0
            no change is made in state of the data structure

  isFull:
    in: nothing
    return: boolean telling if the stack may have no more elements added to it
    effect: shorthand for size() == limit()
            no change is made in state of the data structure


*/

// ADT operations

public interface StackSet_Interface {
    public boolean push(double elt);
    public boolean pop();
    public double peek();
    public boolean contains(double elt);
    public int size();
    public int limit();
    public boolean isEmpty();
    public boolean isFull();
}