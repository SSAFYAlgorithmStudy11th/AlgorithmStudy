import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Main {
	static ArrayList[] li;
	static int[] dist;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken()); //정점 수 
		int E = Integer.parseInt(st.nextToken()); //간선 수 
		int K = Integer.parseInt(br.readLine()); // 시작 노드 번호 
		
		li = new ArrayList[V+1];
		dist = new int[V+1];
		
		for(int i=0;i<V+1 ;i++) {
			li[i] = new ArrayList<Node>();
			dist[i] = Integer.MAX_VALUE;
		}
		
		for(int i=0;i<E ; i++) {
			st = new StringTokenizer(br.readLine());
			
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			li[u].add(new Node(v,w));
		}
		
		di(K);
		
		for(int i=1;i<V+1;i++) {
			if(dist[i] == Integer.MAX_VALUE) {
				System.out.println("INF");
			}else {
				System.out.println(dist[i]);
			}
		}

	}
	
	static void di(int s) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		dist[s] = 0;
		pq.add(new Node(s,0));
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			
			int len = li[now.v].size();
			for(int i=0;i<len;i++) {
				Node next = (Node) li[now.v].get(i);
				
				if(dist[next.v] > now.w + next.w) {
					dist[next.v] = now.w + next.w;
					pq.add(new Node(next.v , dist[next.v]));
				}
			}
		}
	}
	
}


class Node implements Comparable<Node>{
	int v,w;
	
	public Node(int v, int w) {
		this.v = v;
		this.w = w;
	}

	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		return this.w - o.w;
	}
	
	
}
