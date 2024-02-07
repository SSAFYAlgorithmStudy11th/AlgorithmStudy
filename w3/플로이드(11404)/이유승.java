import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int n;
	static int m;
	static int[][] matrix;
	static int[][] result;
	static boolean[] visited;
	
	public static void solution(int start) {
		for(int i=0; i<n; i++)
			result[start][i] = matrix[start][i];
		visited[start] = true;
		while(true) {
			int v = getMinIdx(start);
			if(v == -1)
				break;
			visited[v] = true;
			for(int i=0; i<n; i++) {
				if(i==start)
					continue;
				result[start][i] = Math.min(result[start][i], result[start][v] + matrix[v][i]);
			}
		}
	}
	
	public static int getMinIdx(int start) {
		int minValue = Integer.MAX_VALUE/2;
		int minIdx = -1;
		for(int i=0; i<n; i++) {
			if(minValue > result[start][i] && !visited[i]) {
				minValue = result[start][i];
				minIdx = i;
			}
		}
		return (minValue != Integer.MAX_VALUE/2) ? minIdx : -1;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		String[] temp = null;
		matrix = new int[n][n];
		result = new int[n][n];
		visited = new boolean[n];
		for(int i=0; i<n; i++)
			Arrays.fill(matrix[i], Integer.MAX_VALUE/2);
		for(int i=0; i<m; i++) {
			temp = br.readLine().split(" ");
			int start = Integer.parseInt(temp[0])-1;
			int end = Integer.parseInt(temp[1])-1;
			int value = Integer.parseInt(temp[2]);
			matrix[start][end] = Math.min(matrix[start][end], value);
		}
		for(int i=0; i<n; i++) {
			Arrays.fill(visited, false);
			solution(i);
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(result[i][j] == Integer.MAX_VALUE/2)
					sb.append(0).append(" ");
				else
					sb.append(result[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
