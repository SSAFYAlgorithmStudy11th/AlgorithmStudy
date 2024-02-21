//마법사 상어와 파이어스톰

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
	int row;
	int col;

	public Point(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}
}

public class Main {

	static int[][] map;
	static boolean[][] visited;
	static int[] l;

	static int N, Q, size, max;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		size = (int) Math.pow(2, N);

		// 입력
		map = new int[size][size];
		l = new int[Q];

		for (int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < size; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 단계
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < Q; i++)
			l[i] = Integer.parseInt(st.nextToken());

		for (int i = 0; i < Q; i++) {

			map = divide((int) Math.pow(2, l[i]));
			check();// 얼음 줄이기
		}

		visited = new boolean[size][size];
		int sum = 0;
		max = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				sum += map[i][j];

				if (map[i][j] > 0 && !visited[i][j])
					bfs(i, j);
			}
		}

		// 결과 출력
		bw.write(String.valueOf(sum) + "\n" + String.valueOf(max));
		bw.flush();
		bw.close();
		br.close();
	}

	public static int[][] divide(int d) {

		int[][] tmp = new int[size][size];

		for (int i = 0; i < size; i += d) {
			for (int j = 0; j < size; j += d) {
				rot(i, j, d, tmp);
			}
		}
		return tmp;
	}

	public static void rot(int row, int col, int d, int[][] tmp) {// 회전

		for (int i = 0; i < d; i++) {
			for (int j = 0; j < d; j++) {
				tmp[row + j][col + d - 1 - i] = map[row + i][col + j];
			}
		}
	}

	public static void check() {// 얼음 확인

		Queue<Point> queue = new ArrayDeque<>();

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {

				int cnt = 0;// 0인거 개수
				if(map[i][j]==0)
					continue;
				
				for (int d = 0; d < 4; d++) {
					int r = i + dr[d];
					int c = j + dc[d];

					if (r < 0 || r >= size || c < 0 || c >= size) {
						cnt++;
						continue;
					}

					if (map[r][c] == 0)
						cnt++;
				}
				if (cnt > 1)
					queue.add(new Point(i, j));
			}
		}

		while (!queue.isEmpty()) {

			Point ice = queue.poll();
			if (map[ice.row][ice.col] > 0)
				map[ice.row][ice.col]--;
		}

	}

	public static void bfs(int row, int col) {

		int cnt = 0;
		Queue<Point> queue = new ArrayDeque<>();
		
		queue.add(new Point(row, col));
		visited[row][col] = true;

		while (!queue.isEmpty()) {

			Point now = queue.poll();
			cnt++;

			for (int d = 0; d < 4; d++) {
				int r = now.row + dr[d];
				int c = now.col + dc[d];

				if (r < 0 || r >= size || c < 0 || c >= size) {
					continue;
				}

				if (map[r][c] > 0 && !visited[r][c]) {

					visited[r][c] = true;
					queue.offer(new Point(r, c));
				}
			}
		}
		max = Math.max(max, cnt);
	}
}
