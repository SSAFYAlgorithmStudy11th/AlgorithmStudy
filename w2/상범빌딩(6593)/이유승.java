import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	static class Node {
		int x, y, z, h;
		Node(int x, int y, int z, int h){
			this.x = x;
			this.y = y;
			this.z = z;
			this.h = h;
		}
	}
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int l;
	static int r;
	static int c;
	static char[][][] matrix;
	static boolean[][][] visited;
	static int[] start;
	static int[] dx = {1, -1, 0, 0, 0, 0};
	static int[] dy = {0, 0, 1, -1, 0, 0};
	static int[] dz = {0, 0, 0, 0, 1, -1};
	
	public static void solution() throws IOException {
		Queue<Node> queue = new ArrayDeque<>();
		queue.add(new Node(start[0] , start[1], start[2], 0));
		visited[start[2]][start[1]][start[0]] = true;
		Node node = null;
		while(!queue.isEmpty()) {
			node = queue.poll();
			for(int i=0; i<6; i++) {
				int z = node.z + dz[i];
				int y = node.y + dy[i];
				int x = node.x + dx[i];
				if(x<0 || x>=c || y<0 || y>=r || z<0 || z>=l)
					continue;
				if(!visited[z][y][x] && matrix[z][y][x] == '.') {
					visited[z][y][x] = true;
					queue.add(new Node(x,y,z,node.h+1));
				} else if(matrix[z][y][x] =='E') {
					bw.append(String.format("Escaped in %d minute(s).\n", node.h+1));
					return;
				}
			}
		}
		bw.append("Trapped!\n");
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			String[] temp = br.readLine().split(" ");
			l = Integer.parseInt(temp[0]);
			r = Integer.parseInt(temp[1]);
			c = Integer.parseInt(temp[2]);
			if(l+r+c == 0)
				break;
			visited = new boolean[l][r][c];
			matrix = new char[l][r][];
			start = new int[3];
			for(int i=0; i<l; i++) {
				for(int j=0; j<r; j++) {
					String str = br.readLine();
					matrix[i][j] = str.toCharArray();
					if(str.indexOf('S') != -1) {
						start[0] = str.indexOf('S');
						start[1] = j;
						start[2] = i;
					}
				}
				br.readLine();
			}
			solution();
		}
		bw.flush();
	}
}
