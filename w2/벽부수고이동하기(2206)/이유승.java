import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	static class Node {
		Node(int i, int j, int h, boolean d) {
			this.i = i;
			this.j = j;
			this.h = h;
			this.d = d;
		}
		int i, j, h;
		boolean d;
	}
	static int nrow;
	static int ncol;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	// 일반 visited
	static boolean[][] visited;
	// 벽을 뚫고 지나갔을경우 dvisited
	static boolean[][] dvisited;
	static boolean[][] matrix;
	
	public static int solution() {
		Queue<Node> queue = new ArrayDeque<Node>();
		queue.add(new Node(0,0,1,false));
		while(!queue.isEmpty()) {
			Node curr = queue.poll();
			if(curr.i == nrow-1 && curr.j == ncol-1)
				return curr.h;
			for(int a=0; a<4; a++) {
				if(outOfCheck(curr.i+dy[a],curr.j+dx[a])) {
					int x = curr.j+dx[a];
					int y = curr.i+dy[a];
					int h = curr.h;
					// 이전 노드가 벽을 뚫었을 경우
					if(curr.d) {
						// 길이 있고, 방문하지 않았을 경우
						if(matrix[y][x] && dvisitedCheck(y,x)) {
							dvisited[y][x] = true;
							queue.add(new Node(y, x, h+1, true));
						}
					} else {
						// 이전 노드가 벽을 뚫지 않았을 경우
						// 길이 있고, 방문하지 않았을 경우
						if(matrix[y][x] && visitedCheck(y,x)) {
							visited[y][x] = true;
							queue.add(new Node(y, x, h+1, false));
						} 
						// 길은 없지만, 방문하지 않았을 경우(dvisited)
						else if (!matrix[y][x] && dvisitedCheck(y,x)){
							dvisited[y][x] = true;
							queue.add(new Node(y, x, h+1, true));
						}
					}
				}
			}	
		}
		return -1;
	}
	
	public static boolean outOfCheck(int i, int j) {
		if(i>=nrow || i<0 || j>=ncol || j<0)
			return false;
		return true;
	}
	
	public static boolean visitedCheck(int i, int j) {
		return !visited[i][j];
	}
	
	public static boolean dvisitedCheck(int i, int j) {
		return !dvisited[i][j];
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		nrow = Integer.parseInt(temp[0]);
		ncol = Integer.parseInt(temp[1]);
		visited = new boolean[nrow][ncol];
		dvisited = new boolean[nrow][ncol];
		matrix = new boolean[nrow][ncol];
		for(int i=0; i<nrow; i++) {
			String line = br.readLine();
			for(int j=0; j<ncol; j++)
				if(line.charAt(j) == '0')
					matrix[i][j] = true;
		}
		System.out.println(solution());
	}
}
