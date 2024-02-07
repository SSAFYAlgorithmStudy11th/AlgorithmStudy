import java.io.*;
import java.util.*;

//mst
public class Main {

	static class Node implements Comparable<Node> {
		int from;
		int to;
		int weight;
		public Node(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		@Override
		public int compareTo(Node o) {
			
			return this.weight - o.weight;
		}
		
	}
	static int n,m;
	static ArrayList<Node> nlist;
	static int[] parents;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		nlist = new ArrayList<>();
		parents = new int[n+1];
		for(int i = 0; i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int f = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			nlist.add(new Node(f,t,w));
		}
		
		Collections.sort(nlist);
		
		long sum = 0;
		set();
		for(Node nd : nlist) {
			if(union(nd.from,nd.to)) {
				sum += nd.weight;
			}
		}
		
		System.out.println(sum);
		
	}
	
	public static void set() {
		for(int i = 1;i<n+1;i++) {
			parents[i] = i;
		}
	}
	
	public static int find(int x) {
		if(parents[x] == x)
			return x;
		
		return parents[x] = find(parents[x]);
	}
	
	public static boolean union(int x, int y) {
		
		int rx = find(x);
		int ry =find(y);
		
		if(rx == ry)
			return false;
		if(rx > ry) {
			parents[rx] = ry;
		}
		else {
			parents[ry] = rx;
		}
		return true;
	}
	

}
