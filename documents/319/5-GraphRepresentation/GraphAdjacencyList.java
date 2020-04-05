import java.util.LinkedList;
import java.util.ListIterator;
import java.util.ArrayList;

public class GraphAdjacencyList {
	
	class EdgeAdjacencyList{
		public int destinationVertexIndex;
		public double weight;
		
		EdgeAdjacencyList(int destinationVertexIndex, double weight){
			
			this.destinationVertexIndex = destinationVertexIndex;
			this.weight = weight;
			
		}
		
	}
	
	ArrayList<LinkedList<EdgeAdjacencyList>> adjacencyList = new ArrayList<LinkedList<EdgeAdjacencyList>>();
	
	
	GraphAdjacencyList(int n){
		
		for(int i = 0; i < n; i++) {
			
			adjacencyList.add(new LinkedList<EdgeAdjacencyList>());
			
		}
		
	}
	private EdgeAdjacencyList findNeighbour(int v0, int v1, boolean remove) {
		
		EdgeAdjacencyList result;
		
		ListIterator<EdgeAdjacencyList> listIterator = adjacencyList.get(v0).listIterator();
		while (listIterator.hasNext()) {
			result = listIterator.next();
			if(result.destinationVertexIndex == v1) {
				if( remove )listIterator.remove();
				return result;
			}
		}
		return null;
	}
	
	public void addEdge(int v0, int v1, double weight) {
		
		EdgeAdjacencyList edgePointer;
		edgePointer =  findNeighbour(v0,v1,false);
		
		if(edgePointer == null) {
			
			adjacencyList.get(v0).add(new EdgeAdjacencyList(v1,weight));
			adjacencyList.get(v1).add(new EdgeAdjacencyList(v0,weight));
			
		}else {
			edgePointer.weight = weight;
		}
		
		
		
		
	}

	public void removeEdge(int v0, int v1) {
		
		
		ListIterator<EdgeAdjacencyList> temp;
		
		findNeighbour(v0,v1,true);
		
		findNeighbour(v1,v0,true);
		
	}
	
	public double getWeight(int v0, int v1) throws Exception {
		EdgeAdjacencyList edgePointer;
		edgePointer = findNeighbour(v0,v1,false);
		
		if(edgePointer == null) {
			
			throw new Exception("Edge does not exist");
			
		}else {
			return edgePointer.weight;
		}
		
		
	}
	
	public void print() {
		
		for (int i = 0 ; i < adjacencyList.size() ; i++) {
			if( adjacencyList.get(i).size() != 0 ) {
				
				String Str = "V" + i + " is connected to";
				ListIterator<EdgeAdjacencyList> listIterator = adjacencyList.get(i).listIterator();
				while (listIterator.hasNext()) {
					EdgeAdjacencyList element = listIterator.next();
					Str = Str + " V" + element.destinationVertexIndex+ "("+ element.weight + ")";
					
				}
				
				System.out.println(Str);
			}
			
		}
		System.out.println("");
	}
	

	public void addVertex() {	
		//To do
	}
	
	public static void main(String[] args) {
		
		GraphAdjacencyList G1 = new GraphAdjacencyList(3);  
		
		G1.addEdge(0,2,0.5);
		G1.addEdge(0,1,1.5);
		G1.addEdge(1,2,1.3);
		
		
		G1.print();
		G1.addEdge(1,2,1.4);
		G1.removeEdge(0, 1);
		
		G1.print();
		
		try {
			System.out.println("Edge weight between v1 and v2 is:" + G1.getWeight(1,2));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
