import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {
	static class Edge implements Comparable<Edge>{
		public Edge(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}
		int u;
		int v;
		int w;
		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}
	}
	static boolean[] selected;
	static int result;
	static int cnt = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		int vn = Integer.parseInt(temp[0]);
		int en = Integer.parseInt(temp[1]);
		PriorityQueue<Edge> pq = new PriorityQueue<>(en);
		boolean[] selected = new boolean[vn];
		for(int i=0; i<en; i++) {
			temp = br.readLine().split(" ");
			int u = Integer.parseInt(temp[0])-1;
			int v = Integer.parseInt(temp[1])-1;
			int w = Integer.parseInt(temp[2]);
			pq.add(new Edge(u,v,w));
		}
		ArrayList<Edge> list = new ArrayList<Edge>();
		Edge edge = pq.poll();
		selected[edge.u] = true;
		selected[edge.v] = true;
		result += edge.w;
		cnt=2;
		while(cnt != vn) {
			edge = pq.poll();
			int u = edge.u;
			int v = edge.v;
			if(selected[u] && selected[v])
				continue;
			if(selected[u] ^ selected[v]) {
				if(!selected[u]) selected[u] = true;
				if(!selected[v]) selected[v] = true;
				result += edge.w;
				cnt++;
				pq.addAll(list);
				list.clear();
			}
			else
				list.add(edge);
		}
		System.out.println(result);
	}
}
