import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] arr;
	static int[][] visited;
	static int N,M;
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	static int res;
	static Queue<int[]> q;
	
	static void bfs(int i, int j) {
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			
			for(int d=0;d<4;d++) {
				int nx = now[0]+dx[d];
				int ny = now[1]+dy[d];
				
				if(nx>=0&& ny>=0&& nx<N && ny<M && arr[nx][ny]== 0 && visited[nx][ny]==-1) {
					visited[nx][ny]=visited[now[0]][now[1]]+1;
					q.add(new int[] {nx,ny});
					
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		M= Integer.parseInt(st.nextToken());
		N= Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		visited = new int[N][M];
		q = new LinkedList<int[]>();
		res=0;
		
		for(int i=0;i<N;i++) {
			Arrays.fill(visited[i],-1);
		}
		
		int cnt=0; // 안 익은 토마토 수
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M ; j++) {
				int n =Integer.parseInt(st.nextToken());
				arr[i][j] = n;
				
				if(n==0) {
					cnt++;
				}else if(n==1) {
					q.add(new int[] {i,j});
					visited[i][j]++;
				}
			}
		}

		
		if(cnt==0) { // 다 익은 경우
			System.out.println(0);
		}else {
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<M ;j++) {
					if(arr[i][j]==1) {
						bfs(i,j);
					}
				}
			}
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<M ;j++) {
					if(arr[i][j]==0 && visited[i][j]==-1) { // 안익은 토마토 존재
						res =-1;
					}
					if(res!=-1 && visited[i][j]>res) {
						res = visited[i][j];
					}
				}
			}
			
			System.out.println(res);
			
		}
		
		
	}

}
