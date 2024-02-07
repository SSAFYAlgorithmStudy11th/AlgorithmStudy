import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Node implements Comparable<Node>{
	int to;
	int value;
	
	public Node(int to, int value) {
		this.to = to;
		this.value = value;
	}

	@Override
	public int compareTo(Node o) {
		return this.value - o.value;
	}
}
public class Main {

	static int res;
	static List<Node>[] list;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
                      
		list = new ArrayList[V+1];
		visited = new boolean[V+1];
		for(int i=1; i<V+1; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
            
			list[a].add(new Node(b,w));
			list[b].add(new Node(a,w));
		}
		
		prim(1);
		System.out.println(res);
	}
	
	static void prim(int start) {
		Queue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start,0));
        
		while(!pq.isEmpty()) {
			Node p = pq.poll();
			int node = p.to;
			int weight = p.value;
			
			if(visited[node]) continue;
			visited[node]= true;
			res += weight;
			
			for(Node next : list[node]) {
				if(!visited[next.to]) {
					pq.add(next);
				}
			}
		}
		
	}
}
