
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class magical_shark_tornado {
	static int N;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	static int[][] move ={{-2,0},{-1,-1},{-1,0},{-1,1},{0,-2},{0,-1},{1,-1},{1,0},{1,1},{2,0}};	//토네이도에 영향받는 위치
	static int[][] rate ={{1,50},{1,10},{7,100},{1,100},{1,20},{0,1},{1,10},{7,100},{1,100},{1,50}}; //영향 받는 위치에 따른 비율 
	static int count;
	static int length;
	static int direction;
	static int answer;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N+1][N+1];
		
		for(int  i = 1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 1; j<=N; j++) 
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		answer =0;
		direction = 0;
		length = 1;
		count = 2;

		int start_x = N/2+1;
		int start_y = N/2+1;
		
		while(true) {
			
			//회전 로직 
			for(int i = 0; i<length; i++) {
				start_x += dx[direction];
				start_y += dy[direction];
				int sand = arr[start_y][start_x];
				arr[start_y][start_x] = 0;
				tornado(start_x,start_y,sand,direction,arr);
				
				if(start_x == 1 && start_y == 1)
					break;
			}
			
			count--;
			direction ++;
			direction %= 4;

			if(count == 0) {
				length++;
				count = 2;
			}
				
			
			if(start_x == 1 && start_y == 1)
				break;

		}
		System.out.println(answer);
		
	}
	static void tornado(int x, int y,int sand,int direction,int[][] arr) {
		int total_sand = sand;

		for(int i = 0; i<rate.length; i++) {
			int mx = move[i][1];
			int my = move[i][0];
			
			//방향에 따라 영향 받는 위치 다르게
			switch(direction) {
				case 0:
					break;
				case 1:
					mx = move[i][0]*-1;
					my = move[i][1]*-1;
					break;
				case 2:
					mx = move[i][1]*-1;
					my = move[i][0];
					break;
				case 3:
					mx = move[i][0]*-1;
					my = move[i][1];
					break;
			}
			
			
			int ny = y+my;
			int nx = x+mx;
			int moved_sand = (sand*rate[i][0])/rate[i][1];

			total_sand -= moved_sand;
			if(ny > N || ny <= 0 || nx>N || nx <= 0) {
				answer += moved_sand;
			}
			else {
				arr[ny][nx] += moved_sand;
			}
		}
		if(y+dy[direction] > N || y+dy[direction] <= 0 || x+dx[direction]>N || x+dx[direction] <= 0) {
			answer += total_sand;
		}
		else
			arr[y+dy[direction]][x+dx[direction]] += total_sand;
	}

}
