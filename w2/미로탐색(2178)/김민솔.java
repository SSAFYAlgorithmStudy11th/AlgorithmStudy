import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int[][] arr;
	public static int[][] visited;
	public static int[] dx = {0,0,1,-1};
	public static int[] dy = {1,-1,0,0};
	public static int N,M;
	
	public static void bfs(int i, int j) {
		Queue<int[]> q = new LinkedList<>();
		visited[i][j]++;
		q.add(new int[] {i,j});
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			
			for(int d=0;d<4;d++) {
				int nx = now[0]+dx[d];
				int ny = now[1]+dy[d];
				
				if(nx>=0 &&nx<N && ny>=0 && ny<M && visited[nx][ny]==0 &&arr[nx][ny]==1) {
					visited[nx][ny] = visited[now[0]][now[1]]+1;
					q.add(new int[] {nx,ny});
				}
				
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		visited = new int[N][M];
		
		for(int i=0;i<N;i++) {
			String str = br.readLine();
			for(int j=0;j<M;j++) {
				arr[i][j] = str.charAt(j)-'0';
			}
		}
		
		bfs(0,0);
		
		System.out.println(visited[N-1][M-1]);
	}

}
