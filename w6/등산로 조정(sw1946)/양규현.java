
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class hiking_road {
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};
	static int N;
	static int K;
	static int answer;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int Test = 1; Test<T+1; Test++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			int best_height = 0; //맵의 최대 높이
			answer = 0;
			
			int[][] map = new int[N][N];
			for(int i = 0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j<N; j++) {
					int num = Integer.parseInt(st.nextToken());
					map[i][j]  = num;
					best_height = Math.max(best_height, num);
				}
			}
			
			//최대높이에서 dfs 시작
			for(int i = 0; i<N; i++) {
				for(int j = 0; j<N; j++) {
					if(map[i][j] == best_height) {
						int temp = map[i][j];
						map[i][j] = -9999;
						dfs(i,j,best_height,1,true,map);
						map[i][j] = temp;
					}
				}
			}
			bw.append("#").append(Integer.toString(Test)).append(" ").append(Integer.toString(answer)).append("\n");
		}
		bw.close();
	}
	
	
	static void dfs(int y, int x,int height, int depth,boolean check,int[][] map) {
		
		answer = Math.max(depth, answer);
		
			for(int i = 0; i<4; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(nx < N && nx >= 0 && ny < N && ny >= 0) {
					//높이가 높은경우 공사
					if(map[ny][nx] >= height && map[ny][nx] - K < height && check && map[ny][nx] != -9999) {
						int temp = map[ny][nx];
						check =false;
						map[ny][nx] = -9999;
						dfs(ny,nx,height-1,depth+1,check,map);
						map[ny][nx] = temp;
						check = true;
					}
					//아닌경우
					if (map[ny][nx] < height && map[ny][nx] != -9999) {
						int temp = map[ny][nx];
						map[ny][nx] = -9999;
						dfs(ny,nx,temp,depth+1,check,map);
						map[ny][nx] = temp;
						
					}
				}
					
			}
			
	}
}
