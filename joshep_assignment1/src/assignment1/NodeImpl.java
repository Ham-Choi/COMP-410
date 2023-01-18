/**
 * COMP 410
 * Don't modify this file!
*/

package assignment1;

public class NodeImpl implements Node {
  private final double val;
  private Node next;
  
  public NodeImpl(double val) { this(val, null); }
  
  public NodeImpl(double val, Node next) {
    this.val = val;
    this.next = next;
  }
  
  @Override
  public double getValue() { return val; }
  
  @Override
  public void setNext(Node next) { this.next = next; }

  @Override
  public Node getNext() { return next; }
}