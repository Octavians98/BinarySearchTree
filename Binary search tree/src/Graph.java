import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

//ex6
public class Graph {
	
	public class Edge implements Comparable{
		int n1, n2;
		double weight;
		
		public Edge(int n1, int n2){
			this.n1=n1;
			this.n2=n2;
			Point a = nodes.get(n1);
			Point b = nodes.get(n2);
			weight=Math.sqrt( (double) (a.x-b.x)*(a.x-b.x) + (a.y-b.y)*(a.y-b.y) );
		}
		
		public boolean check(int id1, int id2){
			if( (n1 == id1 && n2 == id2) || (n1 == id2 && n2 == id1) )
				return true;
			else
				return false;
		}

		@Override
		public int compareTo(Object o) {
			double d = this.weight - ((Edge) o).weight;
			if(d>0)return 1;
			else if(d<0)return -1;
			else return 0;
		}
	}
	
	ArrayList<Point> nodes;
	ArrayList<Edge> edges;
	
	public Graph(){
		nodes=new ArrayList<Point>();
		edges=new ArrayList<Edge>();
	}
	
	public Point getPosition(int id){
		if(id>=nodes.size())return null;
		return nodes.get(id);
	}
	
	public boolean isEdge(int id1, int id2){
		for(Edge e: edges)
			if(e.check(id1, id2))
				return true;
		return false;
	}
	
	public Edge edge(int id1, int id2){
		for(Edge e: edges)
			if(e.check(id1, id2))
				return e;
		return null;
	}
	
	public double weight(int id1, int id2){
		for(Edge e: edges)
			if(e.check(id1, id2))
				return e.weight;
		return -1;
	}
	
	public ArrayList<Integer> neighbours(int n){
		ArrayList<Integer> ngb = new ArrayList<Integer>();
		for(Edge e: edges)
			if(e.n1 == n)ngb.add(e.n2);
			else if(e.n2 == n) ngb.add(e.n1);
		return ngb;
	}
	
	//ex7
	public void randomGraph(int n, double p){
		Random r = new Random();
		for(int i=0; i<n; ++i)
			nodes.add(new Point(r.nextInt(1000), r.nextInt(1000)));
		
		for(int i=0; i<n; ++i)
			for(int j=i+1; j<n; ++j)
				if (p>=r.nextDouble())
					edges.add(new Edge(i,j));
		
	}
	
	//ex8
	public ArrayList<Edge> prim(){
		//distance to the tree
		double d[] = new double[nodes.size()+1];
		for(int i=0; i<nodes.size(); i++)d[i]=Double.MAX_VALUE	;
		//the solution
		ArrayList<Edge>sol = new ArrayList<Edge>();
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		//starting from a random node
		int n = (int) Math.random()*nodes.size();
		for(int i=0; i<nodes.size()-1; ++i){
			d[n]=0;
			//optimize the distance to the neighbours
			for(int k: neighbours(n)){
				if(d[k]>weight(k, n)){
					d[k]=weight(k, n);
					pq.add(edge(n, k));
				}
			}
			//selecting the next edge to be added to sol
			Edge e = pq.poll();
			while(d[e.n1]==0 && d[e.n2]==0)e=pq.poll();
			sol.add(e);
			//selecting the next node to check
			n=d[e.n1]>d[e.n2]?e.n1:e.n2;
		}
		return sol;
	}
	
	//ex9
	public ArrayList<Edge> kruscal(){
		//the solution
		ArrayList<Edge>sol = new ArrayList<Edge>();
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		//adding all edges to the pq
		pq.addAll(edges);
		Edge e;
		//the object which will be used for union-find
		DisjointSets ds = new DisjointSets(nodes.size());
		int n=0;
		while(n<nodes.size()-1){
			e=pq.poll();
			int finda=ds.find(e.n1);
			int findb=ds.find(e.n2);
			//check if the edge forms cycles	
			if(finda != findb){
				sol.add(e);
				n++;
				ds.union(finda, findb);
			}
		}
		return sol;
	}
}
