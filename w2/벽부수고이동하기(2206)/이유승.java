import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

class Node {
	Node(int i, int j, int h, boolean d) {
		this.i = i;
		this.j = j;
		this.h = h;
		this.d = d;
	}
	int i, j, h;
	boolean d;
}

public class Main {
	static int nrow;
	static int ncol;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	static boolean[][] visited;
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
				if(outOfCheck(curr.i+dy[a],curr.j+dx[a]))
					if(curr.d) {
						if(matrix[curr.i+dy[a]][curr.j+dx[a]] && dvisitedCheck(curr.i+dy[a],curr.j+dx[a])) {
							dvisited[curr.i+dy[a]][curr.j+dx[a]] = true;
							queue.add(new Node(curr.i+dy[a], curr.j+dx[a], curr.h+1, true));
						}
					} else {
						if(matrix[curr.i+dy[a]][curr.j+dx[a]] && visitedCheck(curr.i+dy[a],curr.j+dx[a])) {
							visited[curr.i+dy[a]][curr.j+dx[a]] = true;
							queue.add(new Node(curr.i+dy[a], curr.j+dx[a], curr.h+1, false));
						} 
						else if (!matrix[curr.i+dy[a]][curr.j+dx[a]] && dvisitedCheck(curr.i+dy[a],curr.j+dx[a])){
							dvisited[curr.i+dy[a]][curr.j+dx[a]] = true;
							queue.add(new Node(curr.i+dy[a], curr.j+dx[a], curr.h+1, true));
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
