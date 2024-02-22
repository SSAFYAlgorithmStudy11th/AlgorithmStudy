import java.io.*;
import java.util.*;

public class Main {

	static int n, q;
	static int[][] arr;
	static Queue<int[]> mainQ;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static int len;
	static boolean[][] visit;
	static int sum, max_area;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());
		len = (int) Math.pow(2, n);

		arr = new int[len][len];
		visit = new boolean[len][len];
		mainQ = new LinkedList<>();
		sum = 0;
		max_area = 0;
		for (int i = 0; i < len; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < len; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < q; i++) {
			int time = Integer.parseInt(st.nextToken());
			if(time != 0)
				rotate(time);
			
			melt();
			
		}

		
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				if (!visit[i][j] && arr[i][j] >0) {
					mainQ.offer(new int[] { i, j });
					bfs();
				}
			}
		}
		
		System.out.println(sum);
		System.out.println(max_area);

	}

	public static void bfs() {
		visit[mainQ.peek()[0]][mainQ.peek()[1]] = true;
		int area = 0;
		while (!mainQ.isEmpty()) {
			int[] out = mainQ.poll();
			int x = out[0];
			int y = out[1];
			area++;
			sum += arr[x][y];
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx >= 0 && nx < len && ny >= 0 && ny < len && !visit[nx][ny] && arr[nx][ny]>0) {
					visit[nx][ny] = true;
					mainQ.add(new int[] {nx,ny});
				}
			}
		}
		
		max_area = Math.max(area, max_area);
	}

	public static void melt() {
		boolean[][] tmp = new boolean[len][len];
		for(int i = 0;i<len;i++) {
			for(int j = 0;j<len;j++) {
				if(arr[i][j] > 0) {
					tmp[i][j] = true;
					mainQ.add(new int[] {i,j});
				}
			}
		}
		
		while(!mainQ.isEmpty()) {
			int[] out = mainQ.poll();
			int x = out[0];
			int y = out[1];
			int cnt = 0;
			for(int i = 0;i<4;i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx>=0 && nx<len && ny>=0 && ny<len) {
					if(arr[nx][ny] >0)
						cnt++;	
					if(arr[nx][ny] == 0 && tmp[nx][ny] == true) {
						cnt++;
					}
				}
			}
			if(cnt<3) {
				arr[x][y]--;
				if(arr[x][y] < 0) {
					arr[x][y] = 0;
				}
			}
		}
		

		
	}

	public static void rotate(int time) {
		int tmp = 0;
		int len1 = (int) Math.pow(2, time);
		int r = 0;
		int c = 0;
		while (r < arr.length) {
			int cnt = 1;
			for (int i = r; i < r + (len1 / 2); i++) {
				for (int j = c; j < c + len1; j++) {
					tmp = arr[i][j];
					arr[i][j] = arr[(r + len1) - cnt][j];
					arr[r + len1 - cnt][j] = tmp;
				}
				cnt++;
			}

			for (int i = r; i < r + len1; i++) {
				cnt = 1;
				for (int j = (i % len1) + 1 + c; j < c + len1; j++) {
					tmp = arr[i][j];
					arr[i][j] = arr[i + cnt][j - cnt];
					arr[i + cnt][j - cnt] = tmp;
					cnt++;
				}
			}

			c += len1;
			if (c == arr.length) {
				c = 0;
				r += len1;
			}

		}
		
		
		
		

	}

}
