import java.io.*;
import java.util.*;

//플로이드
public class Main {
	static class Node implements Comparable<Node> {

		int index;
		int weight;
		
		public Node(int index, int weight) {
			this.index = index;
			this.weight = weight;
		}


		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.weight - o.weight;
		}
		
	}

	static int n, m;
	static ArrayList<Node>[] graph;
	static StringBuilder sb;
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
	
		sb = new StringBuilder();

		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		graph = new ArrayList[n+1];
		for(int i = 0;i<n+1;i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int f = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph[f].add(new Node(t,w));
		}
		
		for(int i = 1;i<n+1;i++) {
			Dijkstra(i);
			sb.append("\n");
		}
		System.out.println(sb);
		
	}
	
	public static void Dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[] distance = new int[n+1];
		int INF = 100000*100+1;
		Arrays.fill(distance, INF);
		pq.offer(new Node(start,0));
		distance[start] = 0;
		
		while(!pq.isEmpty()) {
			Node out = pq.poll();
			int now = out.index;
			int weight = out.weight;
			
			
			if( distance[now] < weight)
				continue;
			
			
			for(Node next : graph[now]) {
				if(distance[next.index] > distance[ now] + next.weight) {
					distance[next.index] = distance[now] + next.weight;
					pq.add(new Node(next.index, distance[next.index]));
				}
				
				
			}
		}
		
		for(int i = 1;i<n+1;i++) {
			if(distance[i] == INF) {
				sb.append(0).append(" ");
			}
			else {
				sb.append(distance[i]).append(" ");
				
			}
		}
		
	}

}
