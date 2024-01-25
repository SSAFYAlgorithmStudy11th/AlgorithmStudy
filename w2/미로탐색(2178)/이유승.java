import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

class Node {
	public Node(int i, int j, int h) {
		this.i = i;
		this.j = j;
		this.h = h;
	}
	int i;
	int j;
	int h;
}

public class Main {
	static int nrow;
	static int ncol;
	
	static boolean[][] matrix;
	static boolean[][] visited;
	
	public static int solution() {
		Queue<Node> queue = new ArrayDeque<Node>();
		queue.add(new Node(0,0,1));
		while(!queue.isEmpty()) {
			Node curr = queue.poll();
			int i = curr.i;
			int j = curr.j;
			int h = curr.h;
			if(i==nrow-1 && j==ncol-1) return h;
			
			if(check(i-1, j)) queue.add(new Node(i-1, j, h+1));
			if(check(i+1, j)) queue.add(new Node(i+1, j, h+1));
			if(check(i, j-1)) queue.add(new Node(i, j-1, h+1));
			if(check(i, j+1)) queue.add(new Node(i, j+1, h+1));
		}
		return -1;
	}
	
	public static boolean check(int i, int j) {
		if(i >= nrow || i < 0 || j >= ncol || j < 0)
			return false;
		if(matrix[i][j])
			return false;
		boolean result = !visited[i][j];
		visited[i][j] = true;
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		nrow = Integer.parseInt(temp[0]);
		ncol = Integer.parseInt(temp[1]);
		visited = new boolean[nrow][ncol];
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
