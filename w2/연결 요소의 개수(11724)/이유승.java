import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	static boolean[] visited;
	static boolean[][] matrix;
	
	public static int solution(int n) {
		int cnt=0;
		Queue<Integer> queue = new ArrayDeque<Integer>();
		for(int i=0; i<n; i++) {
			if(visited[i] == false) {
				visited[i] = true;
				queue.add(i);
				while(!queue.isEmpty()) {
					int a = queue.poll();
					for(int j=0; j<n; j++) {
						if(matrix[a][j] && !visited[j]) {
							visited[j] = true;
							queue.add(j);
						}
					}
				}
				cnt++;
			}
		}
		return cnt;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		int n = Integer.parseInt(temp[0]);
		int m = Integer.parseInt(temp[1]);
		visited = new boolean[n];
		matrix = new boolean[n][n];
		for(int i=0; i<m; i++) {
			temp = br.readLine().split(" ");
			int x = Integer.parseInt(temp[0])-1;
			int y = Integer.parseInt(temp[1])-1;
			matrix[x][y] = true;
			matrix[y][x] = true;
		}
		System.out.println(solution(n));
	}
}
