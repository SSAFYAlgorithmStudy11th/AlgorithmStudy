package study4.No20058;
//상어 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] arr;
	static int[][] temp;
	static boolean[][] visited;
	static int N;
	static int Q;
	static int res;
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};
	static Queue<int[]> q2;
	
	static void r(int l) {
		int L = (int)Math.pow(2, l);
		temp = new int[L][L];
		int c = N/L;
		
		for(int a=0;a<c;a++) {
			for(int b= 0; b<c;b++) {
				
				for(int i =0;i<L;i++) {
					for(int j=0;j<L;j++) {
						temp[i][j] = arr[(L-j-1)+L*a][i+L*b];
						
					}
				}
				
				for(int i =0;i<L;i++){
					for(int j=0;j<L;j++) {
						arr[L*a+i][L*b+j] = temp[i][j];
					}
				}
				
			}
		}
	}
	
	static void ice(int x, int y) {
		
		int c=0;
		if( arr[x][y]==0) {
			return;
		}
		for(int d = 0;d<4;d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if(nx>=0 && nx<N && ny>=0 && ny<N) {
				if(arr[nx][ny] !=0) {
					c++;
				}
			}
		}
		if(c<3) {
			q2.add(new int[] {x,y});
		}
	}

	static int bfs(int i, int j) {
		Queue<int []> q = new LinkedList<>();
		q.add(new int[] {i,j});
		visited[i][j] = true;
		int c=1;
		while(!q.isEmpty()) {
			int[] now = q.poll();
			int x = now[0];
			int y = now[1];
			
			for(int d =0; d<4;d++) {
				int nx = x+dx[d];
				int ny = y+dy[d];
				
				if(nx>=0 && nx<N && ny>=0 && ny<N && arr[nx][ny]!=0 && !visited[nx][ny]) {
					c++;
					visited[nx][ny] = true;
					q.add(new int[] {nx,ny});
				}
			}
		}
		
		return c;
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());// 격자 크기 
		Q = Integer.parseInt(st.nextToken()); // 파이어스톰 횟수 
		N = (int)Math.pow(2, N); // 2n 
		arr = new int[N][N];
		visited = new boolean[N][N];
		int total = 0; // 얼음의 합
		res = 0; // 덩어리 크기 
		q2 = new LinkedList<>();
		
		for(int i=0;i<N ;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int q=0;q<Q;q++) {
			int L = Integer.parseInt(st.nextToken()); // 단계 
			r(L);// 배열 돌리기 
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					ice(i,j); // 얼음 녹이기 
				}
			}
			
			while(!q2.isEmpty()) {
				int[] now = q2.poll();
				arr[now[0]][now[1]] --;
			}
			
		}

		for(int i=0;i<N ; i++) {
			for(int j=0;j<N ;j++) {
				total += arr[i][j];
			}
		}
		for(int i=0;i<N ; i++) {
			for(int j=0;j<N ;j++) {
				if(!visited[i][j]&& arr[i][j] !=0)
					res = Math.max(res, bfs(i,j));
			}
		}
		
		System.out.println(total);
		System.out.println(res);
	}

}
