import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	static int[][] matrix;
	static int m;
	static int n;
	static int cnt;
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	static class Node {
		public Node(int i, int j, int h) { this.i = i; this.j = j; this.h = h;}
		int i;
		int j;
		int h;
	}
	
	public static int[] convert(String[] temp) {
		int[] result = new int[temp.length];
		for(int i=0; i<temp.length; i++)
			result[i] = Integer.parseInt(temp[i]);
		return result;
	}
	
	public static int solution() {
		Queue<Node> queue = new ArrayDeque<>();
		for(int i=0; i<n; i++)
			for(int j=0; j<m; j++)
				if(matrix[i][j] == 1)
					queue.add(new Node(i, j, 0));
					
		Node node = null;
		while(!queue.isEmpty()) {
			node = queue.poll();
			int x = node.i;
			int y = node.j;
			int h = node.h;
			for(int i=0; i<4; i++) {
				if(x+dx[i] < 0 || x+dx[i] >= n || y+dy[i] < 0 || y+dy[i] >= m)
					continue;
				if(matrix[x+dx[i]][y+dy[i]] == 0) {
					matrix[x+dx[i]][y+dy[i]] = 1;
					queue.add(new Node(x+dx[i], y+dy[i], h+1));
				}
			}
		}
		
		for(int i=0; i<n; i++)
			for(int j=0; j<m; j++)
				if(matrix[i][j] == 0)
					return -1;
		
		return node.h;
	};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		m = Integer.parseInt(temp[0]);
		n = Integer.parseInt(temp[1]);
		matrix = new int[n][m];
		for(int i=0; i<n; i++)
			matrix[i] = convert(br.readLine().split(" "));
		System.out.println(solution());
	}
}
