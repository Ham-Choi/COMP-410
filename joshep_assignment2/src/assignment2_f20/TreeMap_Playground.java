package assignment2_f20;

public class TreeMap_Playground {

	/*
	 * you will test your own TreeMap implementation in here
	 *
	 * we will replace this with our own when grading, and will
	 * do what you should do in here... create TreeMap objects,
	 * put data into them, take data out, look for values stored
	 * in it, checking size, and looking at the TMCells to see if they 
	 * are all linked up correctly as a BST
	 * 
	*/
	  
	  public static void main(String[] args) {
	    // you should test your TreeMap implementation in here
	    // sample tests are shown

	    // it is up to you to test it thoroughly and make sure
	    // the methods behave as requested above in the interface
	  
	    // do not simple depend on the oracle test we will give
	    // use the oracle tests as a way of checking AFTER you have done
	    // your own testing

	    // one thing you might find useful for debugging is a print tree method
	    // feel free to use the one we have here ... basic and quick, or write one 
	    // you like better, one that shows you the tree structure more clearly
	    // the one we have here only shows keys, you may wish to add 
	    // value fields to the printing

	    TreeMap tm = new TreeMap_imp();
	    Value v1 = new Value_imp(12345, 87.3, 21);
	    Value v2 = new Value_imp(23456, 75.54, 25);
	    Value v3 = new Value_imp(34567, 99.013, 19);
	    Value v4 = new Value_imp(45678, 55.70, 35);
	    
	    tm.put("kappa", v1); tm.put("beta",v2); 
	    tm.put("sigma",v3); tm.put("alpha",v4);
	    System.out.println(tm.get("sigma")); // assumes a toString for a Value onject
	    System.out.println(tm.hasKey("gamma"));
	    prTree(tm.getRoot(),0); 

	    // etc...

	  }

	  public static void prTree (TMCell c, int off) {
	    if (c==null) return;
	        
	    prTree(c.getRight(), off+2);
	    
	    for (int i=0; i<off; i++) System.out.print(" ");
	    System.out.println(c.getKey());
	    
	    prTree(c.getLeft(), off+2);
	  }

}
