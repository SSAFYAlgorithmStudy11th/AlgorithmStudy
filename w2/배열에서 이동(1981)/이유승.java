import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	static class Node {
		Node(int i, int j) {this.i = i; this.j = j;}
		int i;
		int j;
	}
	static int[][] matrix;
	static boolean[][] visited;
	static int n;
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	public static int solution() {
		// 시작 지점, 종료 지점의 차가 차이의 최솟값
		int start = Math.abs(matrix[0][0]-matrix[n-1][n-1]);
		// 행렬에서 최댓값과 최솟값의 차이가 차이의 최댓값
		int end = max - min;
		// 이분 탐색
		while(start <= end) {
			int mid = (start+end) / 2;
			if(bfs(mid))
				end = mid-1;
			else
				start = mid+1;
		}
		return start;
	}
	
	public static boolean bfs(int k) {
		// sliding window 방식으로 bfs Scan
		for(int i=min; i+k<= max; i++) {
			Queue<Node> queue = new ArrayDeque<>();
			visited = new boolean[n][n];
			if(matrix[0][0] >= i && matrix[0][0] <= i+k) {
				queue.add(new Node(0, 0));
				visited[0][0] = true;
			}
			Node node = null;
			
			while(!queue.isEmpty()) {
				node = queue.poll();
				for(int j=0; j<4; j++){
					int x = node.i + dx[j];
					int y = node.j + dy[j];
					if(x<0 || x>=n || y<0 || y>=n)
						continue;
					int value = matrix[x][y];
					if(!visited[x][y] && value >= i && value <= i+k) {
						// 하나라도 도착하는 방법이 있다면 true return
						if(x == n-1 && y == n-1)
							return true;
						visited[x][y] = true;
						queue.add(new Node(x, y));
					}
				}
			}
		}
		return false;
	}
	
	public static int[] convert(String[] temp) {
		int[] result = new int[temp.length];
		for(int i=0; i<temp.length; i++) {
			result[i] = Integer.parseInt(temp[i]);
			max = Math.max(max, result[i]);
			min = Math.min(min, result[i]);
		}
		return result;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		matrix = new int[n][n];
		for(int i=0; i<n; i++)
			matrix[i] = convert(br.readLine().split(" "));
		System.out.println(solution());
	}
}
