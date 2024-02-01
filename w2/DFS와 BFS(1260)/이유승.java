import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;

public class Main {
	static int n, m, v;
	static boolean[][] matrix;
	static HashSet<Integer> dfsVisited = new HashSet<Integer>();
	static HashSet<Integer> bfsVisited;
	static StringBuilder sb = new StringBuilder();
	static Queue<Integer> queue = new ArrayDeque<Integer>();
	
	private static void dfs(int v) {
		dfsVisited.remove(v);
		sb.append(v+1);
		sb.append(" ");
		if(dfsVisited.isEmpty()) {
			System.out.println(sb.toString());
			return;
		}
		boolean flag = false;
		for(int i=0; i<n; i++) {
			flag |= matrix[v][i]; 
			if(matrix[v][i] && dfsVisited.contains(i))
				dfs(i);
		}
		if(flag == false) {
			System.out.println(sb.toString());
			return;
		}
	}
	
	private static void bfs(int start) {
		queue.add(start);
		sb.append(start+1);
		sb.append(" ");
		bfsVisited.remove(start);
		
		while(!bfsVisited.isEmpty() && !queue.isEmpty()) {
			int v = queue.poll();
			for(int i=0; i<n; i++)
				if(matrix[v][i] && bfsVisited.contains(i)) {
					queue.add(i);
					sb.append(i+1);
					sb.append(" ");
					bfsVisited.remove(i);
				}
		}
		System.out.println(sb.toString());
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		n = Integer.parseInt(temp[0]);
		m = Integer.parseInt(temp[1]);
		v = Integer.parseInt(temp[2])-1;
		matrix = new boolean[n][n];
		for(int i=0; i<m; i++) {
			temp = br.readLine().split(" ");
			int x = Integer.parseInt(temp[0])-1;
			int y = Integer.parseInt(temp[1])-1;
			matrix[x][y] = true;
			matrix[y][x] = true;
			dfsVisited.add(x);
			dfsVisited.add(y);
		}
		bfsVisited = new HashSet<Integer>(dfsVisited);
		dfs(v);
		sb.setLength(0);
		bfs(v);
	}
}
