import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	static class Node{
		public Node(int i, int j) {this.i = i; this.j = j;} 
		int i; 
		int j;
	}
	static char[][] matrix;
	static boolean[][] svisited;
	static boolean[][] wvisited;
	static int r;
	static int c;
	static int cnt;
	
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	
	public static int solution() {
		Node target = null;
		Queue<Node> sq = new ArrayDeque<Node>();
		Queue<Node> wq = new ArrayDeque<Node>();
		
		// 초기 큐 세팅
		for(int i=0; i<r; i++)
			for(int j=0; j<c; j++)
				// .이면 wq에 다 집어 넣음
				if(matrix[i][j] == '.') {
					wq.add(new Node(i, j));
					wvisited[i][j] = true;
				}
				// L도 물이므로 wq에 집어넣고 target 초기화
				else if(matrix[i][j] == 'L') {
					wq.add(new Node(i, j));
					target = new Node(i, j);
					wvisited[i][j] = true;
				}
		// swan queue에 target 집어넣음
		sq.add(target);
		svisited[target.i][target.j] = true;
		Node node = null;
		
		while(true) {
			Queue<Node> fsq = new ArrayDeque<Node>();
			Queue<Node> fwq = new ArrayDeque<Node>();
			// swan queue에서 bfs로 다음 부서질 얼음을 찾음
			while(!sq.isEmpty()) {
				node = sq.poll();
				int x = node.i;
				int y = node.j;
				for(int i=0; i<4; i++) {
					if(x+dx[i] <0 || x+dx[i] >= r|| y+dy[i] <0 || y+dy[i] >= c)
						continue;
					else {
						// 물일 경우 swan queue에 집어넣음
						if(matrix[x+dx[i]][y+dy[i]] == '.' && !svisited[x+dx[i]][y+dy[i]]) {
							svisited[x+dx[i]][y+dy[i]] = true;
							sq.add(new Node(x+dx[i], y+dy[i]));
						}
						// 얼음일 경우 future swan queue에 집어넣음
						else if(matrix[x+dx[i]][y+dy[i]] == 'X' && !svisited[x+dx[i]][y+dy[i]]) {
							svisited[x+dx[i]][y+dy[i]] = true;
							fsq.add(new Node(x+dx[i], y+dy[i]));
						}
						// 백조일 경우 횟수 리턴
						else if(matrix[x+dx[i]][y+dy[i]] == 'L' && !svisited[x+dx[i]][y+dy[i]]) {
							return cnt;
						}
					}
				}
			}
			cnt++;
			// water queue에서 bfs로 다음 부서질 얼음을 찾고 .로 바꿈
			while(!wq.isEmpty()) {
				node = wq.poll();
				int x = node.i;
				int y = node.j;
				
				for(int i=0; i<4; i++) {
					if(x+dx[i] < 0 || x+dx[i] >= r|| y+dy[i] < 0 || y+dy[i] >= c)
						continue;
					else {
						// 얼음일 경우 future water queue에 집어넣음
						if(matrix[x+dx[i]][y+dy[i]] == 'X' || !wvisited[x+dx[i]][y+dy[i]]) {
							wvisited[x+dx[i]][y+dy[i]] = true;
							matrix[x+dx[i]][y+dy[i]] = '.';
							fwq.add(new Node(x+dx[i], y+dy[i]));
						}
					}
				}
			}
			sq = fsq;
			wq = fwq;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		r = Integer.parseInt(temp[0]);
		c = Integer.parseInt(temp[1]);
		svisited= new boolean[r][c];
		wvisited= new boolean[r][c];
		matrix = new char[r][c];
		for(int i=0; i<r; i++)
			matrix[i] = br.readLine().toCharArray();
		System.out.println(solution());
	}
}
