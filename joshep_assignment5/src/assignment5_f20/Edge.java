package assignment5_f20;

public class Edge {
	Long weight;
	long edgeID;
	Node source;
	Node destn;
	String eLabel;
	
	public Edge(Long weight, Long id, Node sourceN, Node destN, String edgLabel) {
		this.weight = weight;
		this.edgeID = id;
		this.source = sourceN;
		this.destn = destN;
		this.eLabel = edgLabel;
	}
}
