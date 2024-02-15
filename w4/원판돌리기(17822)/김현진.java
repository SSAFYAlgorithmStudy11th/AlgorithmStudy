import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class Main {
	static int N, M, T;
	static int[][] arr;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {1, -1, 0, 0};
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		arr = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(T --> 0) {
			visited = new boolean[N][M];
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			for(int i = n; i <= N; i+=n) {
				arr[i-1] = rotate(arr[i-1], d, k);
			}
			boolean flag = false;
			for(int i = 0; i < N; i++) {
				for(int j = 0 ; j < M; j++) {
					if(!visited[i][j])
						flag |= removeDuplicate(j, i);
				}
			}
			
//			for(int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(arr[i]));
//			}
			
			if(!flag) {
				setAvg();
			}
		}
		System.out.println(sum());
		
	}

	static int[] rotate(int[] arr, int d, int k) {
		//d == 0 시계방향
		if(d == 1) {
			k = arr.length - k; 
		}
		
		int[] newArr = new int[M];
		System.arraycopy(arr, arr.length-k, newArr, 0, k);
//		System.out.println(Arrays.toString(newArr));
		System.arraycopy(arr, 0, newArr, k, arr.length - k);
//		System.out.println(Arrays.toString(newArr));
		return newArr;
	}
	
	static boolean removeDuplicate(int x, int y) {
		if(arr[y][x] == 0) {
			return false;
		}
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {x, y});

		int count = 0;
		
		visited[y][x] = true;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int curX = cur[0];
			int curY = cur[1];
			
			for(int i = 0; i < 4; i++) {
				int dx = Main.dx[i] + curX;
				int dy = Main.dy[i] + curY;
				
				if(!isBoundary(dy)) {
					continue;
				}
				
				if(dx == -1) {
					dx = M - 1;
				}
				if(dx == M) {
					dx = 0;
				}
				
				
				if(!visited[dy][dx] && arr[dy][dx] == arr[y][x]) {
					q.add(new int[] {dx,dy});
					visited[dy][dx] = true;
					arr[dy][dx] = 0;
					count++;
				}
			}
		}
		
		if(count > 0) {
			arr[y][x] = 0;
			return true;
		}
		return false;
		
	}
	
	static void setAvg() {
		int count = 0;
		int sum = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(arr[i][j] != 0) {
					count++;
					sum += arr[i][j];
				}
			}
		}
		double pivot = (double) sum / count;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(arr[i][j] != 0) {
					if(arr[i][j] < pivot) {
						++arr[i][j];
					}
					else if(arr[i][j] > pivot) {
						--arr[i][j];
					}
				}
			}
		}
	}
	
	static int sum() {
		int sum = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(arr[i][j] != 0) {
					sum += arr[i][j];
				}
			}
		}
		
		return sum;
	}

	
	static boolean isBoundary(int y) {
		return 0 <= y && y < N;
	}
}
