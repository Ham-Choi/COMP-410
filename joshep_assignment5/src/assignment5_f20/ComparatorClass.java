package assignment5_f20;
import java.util.*;
public class ComparatorClass implements Comparator<Node> {
	@Override
	public int compare(Node arg0, Node arg1) {
		// TODO Auto-generated method stub
		if (arg0.distance < arg1.distance) {
			return -1;
		} else if (arg0.distance > arg1.distance) {
			return 1;
		} else
			return 0;
	}
}
