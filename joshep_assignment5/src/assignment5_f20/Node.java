package assignment5_f20;
import java.util.*;
public class Node {
	String Name;
	Long ID;
	Long  distance;
	boolean knownDist;
	HashMap <String, Edge> sEdg = new HashMap<>();
	HashMap <String, Edge> dEdg = new HashMap<>();
	
	public Node(Long id, String name) {
		Name = name;
		ID = id;
	}
	
	public void addDEdge (Node n, Edge e) {
		dEdg.put(n.Name, e);
	}
	public void addSEdge (Node n, Edge e) {
		sEdg.put(n.Name, e);
	}
}
