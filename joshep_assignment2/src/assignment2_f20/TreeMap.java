package assignment2_f20;

/* Behavior: A TreeMap will provide Map operations, and also these:

  minKey: 
    in: none
    return: string, the key from the tree that is smallest
    effect: no tree state change
      if tree size if 0, return null

  maxKey: 
    in: none
    return: string, the key from the tree that is largest
    effect: no tree state change
      if tree size if 0, return null

  getKeys 
    in: nothing
    return: an array of strings, containing just the keys from the tree cells
    effect: the array of strings is produced in alphabetic order, small to large
      note, this means do an in-order traversal to get the keys out of the BST sorted

*/

// ADT operations

public interface TreeMap extends Map {
  public String maxKey();  // recursive
  public String minKey();  // recursive
  public String[] getKeys(); 
  public int height();  // recursive, given as an example

  // leave this in... for grader use
  // also specific to tree structure
  public TMCell getRoot();
}