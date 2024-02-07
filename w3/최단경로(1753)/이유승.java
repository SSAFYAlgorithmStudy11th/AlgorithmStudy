import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {
	
	static class Node implements Comparable<Node>{
		Node(int v, int w) {this.v = v; this.w = w;}
		int v;
		int w;
		@Override
		public int compareTo(Node o) {
			return this.w - o.w;
		}

	}
	static int[] costs;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		int n = Integer.parseInt(temp[0]);
		int e = Integer.parseInt(temp[1]);
		int start = Integer.parseInt(br.readLine())-1;
		costs = new int[n];
		visited = new boolean[n];
		ArrayList<Node>[] graph = new ArrayList[n];
		
		for(int i = 0; i<e; i++) {
			temp = br.readLine().split(" ");
			int u = Integer.parseInt(temp[0])-1;
			int v = Integer.parseInt(temp[1])-1;
			int w = Integer.parseInt(temp[2]);
			
			if(graph[u] == null) graph[u] = new ArrayList<Node>();
			graph[u].add(new Node(v, w));
		}
		
		for(Node node : graph[start]) {
			if(costs[node.v] == 0)
				costs[node.v] = node.w;
			else
				costs[node.v] = Math.min(costs[node.v], node.w);
		}
		
		visited[start] = true;
		
		PriorityQueue<Node> pq = new PriorityQueue<>(graph[start]);
		Node node = null;
		while(!pq.isEmpty()) {
			node = pq.poll();
			if(graph[node.v] == null || visited[node.v])
				continue;
			visited[node.v] = true;
			for(Node next: graph[node.v]) {
				if(costs[next.v] == 0 || costs[next.v] > costs[node.v] + next.w) {
					costs[next.v] = costs[node.v] + next.w;
					next.w = costs[next.v];
					pq.add(next);
				}
			}
		}
		
		for(int i=0; i<n; i++) {
			if(i == start)
				System.out.println(0);
			else if(costs[i] == 0)
				System.out.println("INF");
			else
				System.out.println(costs[i]);
		}
	}
}
