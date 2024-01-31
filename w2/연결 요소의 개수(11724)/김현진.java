import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
	static int[][] map;
	static boolean[] visited;
	static int N, M;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		visited = new boolean[N];
		
		for(int i = 0 ; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()) - 1;
			int v = Integer.parseInt(st.nextToken()) - 1;
			map[u][v] = 1;
			map[v][u] = 1;
		}
		
		int count = 0;
		for(int i = 0; i < N; i++) {
			if(!visited[i]) {
				bfs(i);
				count++;
			}
		}
		
		System.out.println(count);
	}
	
	
	
	static void bfs(int startIdx) {
		Queue<Integer> q = new ArrayDeque<>();
		q.add(startIdx);
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			if(visited[cur]) {
				continue;
			}
			visited[cur] = true;
			for(int i = 0; i < N; i++) {
				if(!visited[i] && map[cur][i] > 0) {
					q.add(i);
				}
			}
		}
	}
}
