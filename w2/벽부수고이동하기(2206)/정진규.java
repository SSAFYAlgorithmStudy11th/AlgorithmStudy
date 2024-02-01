import java.io.*;
import java.util.*;

public class Main {

	static int n, m;
	static boolean[][] visit1;
	static boolean[][] visit2;
	static int[][] arr;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static int answer;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
        if(n==1 && m== 1) {
			System.out.println(1);
			return;
		}
		visit1 = new boolean[n][m];
		visit2 = new boolean[n][m];
		arr = new int[n][m];
		answer = n * m + 1;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String str = st.nextToken();
			for (int j = 0; j < m; j++) {
				arr[i][j] = str.charAt(j)-'0';

			}
		}
		
		bfs(0,0);
		if(answer == n*m+1)
			System.out.println(-1);
		else {
			System.out.println(answer);
		}
		

	}

	public static void bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { x, y, 1, 0 });
		visit1[x][y] = true;
		visit2[x][y] = true;

		while (!q.isEmpty()) {
			int[] out = q.poll();
			int r = out[0];
			int c = out[1];
			int cnt = out[2];
			int b = out[3];

			for (int i = 0; i < 4; i++) {
				int nx = r + dx[i];
				int ny = c + dy[i];

				if (nx == n-1 && ny == m-1) {
					answer = Math.min(answer, cnt + 1);
					continue;
				}

				if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
					if (b == 0) {
						if (arr[nx][ny] == 0) {
							if (!visit1[nx][ny]) {
								q.offer(new int[] { nx, ny, cnt + 1, 0 });
								visit1[nx][ny] = true;
								visit2[nx][ny] = true;
							}
						} else {
							q.offer(new int[] { nx, ny, cnt + 1, 1 });
							visit2[nx][ny] = true;
						}
					} else {
						if (arr[nx][ny] == 0) {
							if (!visit2[nx][ny]) {
								q.offer(new int[] { nx, ny, cnt + 1, 1 });
								visit2[nx][ny] = true;
							}
						}
					}

				}
			}

		}

	}
}
