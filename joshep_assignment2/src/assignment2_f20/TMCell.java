//-------------------------------------------------------------------
// put this Interface and Implementation into your code as is
// the grader may need these methods to examine the structure
// that your code creates
//-------------------------------------------------------------------
// you may add methods and structure as you need to
// for example, you may want a toString to help with your debugging
// but to not remove what we have given here
//-------------------------------------------------------------------

package assignment2_f20;

public interface TMCell { // these will be used by the grader to 
                          // examine your data structure contents
  public void setKey(String newKey);
  public String getKey();
  public void setValue(Value newValue);
  public Value getValue();
  public void setLeft(TMCell newLeft);
  public TMCell getLeft();
  public void setRight(TMCell newRight);
  public TMCell getRight();
}