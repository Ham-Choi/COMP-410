package assignment5_f20;
import java.util.*;

public class DiGraph implements DiGraphInterface{
	int numNode = 0;
	int numEdge = 0;
	HashMap <String, Node> nodes = new HashMap<>(); //keeps track of nodes and s and d nodes of edges
	HashMap <Long, Node> IDs = new HashMap<>(); // to check ids of nodes to keep unique
	HashMap <Long, Edge> edgeIDs = new HashMap<>(); //puts edge IDs to keep track of the edges in map
	PriorityQueue <Node> PQ;

	public DiGraph() {
	}
	@Override
	public boolean addNode(long idNum, String label) {
		// TODO Auto-generated method stub
		if(nodes.containsKey(label) || label == null || IDs.containsKey(idNum) || idNum < 0) { 
			return false; //if node already in or if input is null or if id number is not unique or < 0
		}
		Node newN = new Node(idNum,label);
		nodes.put(label, newN);
		IDs.put(idNum, newN);
		numNode++;
		return true;
	}
	/*
	 *  addNode
      in: unique id number of the node (0 or greater)
          string for name
            you might want to generate the unique number automatically
            but this operation allows you to specify any integer
            both id number and label must be unique
      return: boolean
                returns false if node number is not unique, or less than 0
                returns false if label is not unique (or is null)
                returns true if node is successfully added 
	 */

	@Override
	public boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
		// TODO Auto-generated method stub
		if (edgeIDs.containsKey(idNum) || idNum < 0) {
			return false; //if id is not unique or < 0
		}
		if (!nodes.containsKey(sLabel) || !nodes.containsKey(dLabel)) {
			return false; //if source or destn node isnt present
		}
		if (nodes.get(sLabel).sEdg.containsKey(dLabel) && nodes.get(dLabel).dEdg.containsKey(sLabel)) {
			return false; //if edge exists already
		}
		Long weightCheck;
		if (weight < 1) {
			weightCheck = (long) 1; //check to see if (weight is <1) { default to 1}
		} else { weightCheck = weight;}
		Edge newEdg = new Edge(weightCheck, idNum, nodes.get(sLabel), nodes.get(dLabel), eLabel);
		edgeIDs.put(idNum, newEdg);
		nodes.get(sLabel).addSEdge(nodes.get(dLabel), newEdg); //source node gets destn node edge
		nodes.get(dLabel).addDEdge(nodes.get(sLabel), newEdg); // destn node gets source node edge
		numEdge++;
		return true;
	}
	/*  addEdge
      in: unique id number for the new edge, 
          label of source node,
          label of destination node,
          weight for new edge (use 1 by default)
          label for the new edge (allow null)
      return: boolean
                returns false if edge number is not unique or less than 0
                returns false if source node is not in graph
                returns false if destination node is not in graph
                returns false is there already is an edge between these 2 nodes
                returns true if edge is successfully added 
	 */

	@Override
	public boolean delNode(String label) {
		// TODO Auto-generated method stub
		if (!nodes.containsKey(label)) {
			return false; //if node not in map
		} else {
			int edgRmCount = 0; //make a counter to count the number of edges deleted
			for (Edge edg: nodes.get(label).dEdg.values()) {
				edg.source.dEdg.remove(label);
				edgRmCount++; //iterate thru to delete all d edges 

			}
			for (Edge edg: nodes.get(label).sEdg.values()) {
				edg.destn.sEdg.remove(label);
				edgRmCount++; //iterate thru to delete all s edges 
			} //remove edges in edgeIDmap??
			IDs.remove(nodes.get(label).ID); //remove from ID map
			nodes.remove(label); 
			numNode--;
			numEdge -= edgRmCount;
			return true;

		}
	}
	/*
	 *   delNode
      in: string 
            label for the node to remove
      out: boolean
             return false if the node does not exist
             return true if the node is found and successfully removed
	 */

	@Override
	public boolean delEdge(String sLabel, String dLabel) {
		// TODO Auto-generated method stub
		if (!nodes.containsKey(sLabel) || !nodes.containsKey(dLabel)) {
			return false; //checks for s and d nodes
		}
		if (!nodes.get(sLabel).sEdg.containsKey(dLabel) || !nodes.get(dLabel).dEdg.containsKey(sLabel)) {
			return false; //check for s and d edge
		}
		edgeIDs.remove(nodes.get(sLabel).sEdg.get(dLabel).edgeID);
		nodes.get(sLabel).sEdg.remove(dLabel);
		nodes.get(dLabel).dEdg.remove(sLabel);
		numEdge--;
		return true;
	}
	/*
	 *   delEdge
      in: string label for source node
          string label for destination node
      out: boolean
             return false if the edge does not exist
             return true if the edge is found and successfully removed
	 */

	@Override
	public long numNodes() {
		// TODO Auto-generated method stub
		return numNode;
	}
	/*
	 * numNodes
      in: nothing
      return: integer 0 or greater
                reports how many nodes are in the graph
	 */

	@Override
	public long numEdges() {
		// TODO Auto-generated method stub
		return numEdge;
	}
	/*
	 *     numEdges
      in: nothing
      return: integer 0 or greater
                reports how many edges are in the graph
	 */

	@Override
	public ShortestPathInfo[] shortestPath(String label) {
		// TODO Auto-generated method stub
		ShortestPathInfo[] shortestInfo = new ShortestPathInfo[(int) numNodes()];
		for(Node node : nodes.values()){ //iterate thru nodes
			node.distance = Long.MAX_VALUE; //2^63-1 setting arbitrary distance
			node.knownDist = false;
		}
		Node nodeMod = nodes.get(label);
		nodeMod.distance = (long) 0;
		PQ = new PriorityQueue<Node>(numNode, new ComparatorClass());
		PQ.add(nodeMod);
		while(!PQ.isEmpty()) {
			Node revisedNode = PQ.remove(); //remove head function
			for(Edge edg : revisedNode.sEdg.values()) { //iterate thru all source edges
				Node node = edg.destn; 
				if(!node.knownDist) {	//checking destn node distance
					long distance = revisedNode.distance + edg.weight;
					if(distance < node.distance) { // small number compared to 2^63-1
						node.distance = distance;
						PQ.add(node);
					}
				}	
			}
			revisedNode.knownDist = true; //now known
		}
		int i = 0; //counter
		for(String s : nodes.keySet()) { // iterate thru nodes keys
			Node node = nodes.get(s);
			if(node.distance == Long.MAX_VALUE) {
				node.distance = (long) -1;
			}
			// shortest path constructor:  public ShortestPathInfo(String dest, long totalWeight)
			ShortestPathInfo sPath = new ShortestPathInfo(node.Name, node.distance);
			shortestInfo[i] = sPath;
			i++;
		}
		return shortestInfo;
	}
	/*
	 * shortestPath:
      in: string label for start vertex
      return: array of ShortestPathInfo objects (ShortestPathInfo)
              length of this array should be numNodes (as you will put in all shortest 
              paths including from source to itself)
              See ShortestPathInfo class for what each field of this object should contain
	 */
}