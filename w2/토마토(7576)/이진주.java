import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static boolean[][] visited;
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };
	static int[][] array;
	static int N, M, day, totalTomato;
	static Queue<Tomato> queue;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		visited = new boolean[N][M];
		array = new int[N][M];
		queue = new LinkedList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());

				if (array[i][j] == 1) {
					queue.offer(new Tomato(i, j, 0));
					visited[i][j] = true;
				} else if (array[i][j] == 0)
					totalTomato++;
			}
		}

		bfs();
		bw.write(String.valueOf(day));
		bw.flush();
		bw.close();
	}

	public static void bfs() {
		int count = 0;

		if (totalTomato == 0) {
			day = 0;
			return;
		}

		while (!queue.isEmpty()) {
			Tomato m = queue.poll();

			for (int d = 0; d < 4; d++) {

				int nrow = m.row + dr[d];
				int ncol = m.col + dc[d];

				if (nrow > -1 && nrow < N && ncol > -1 && ncol < M && array[nrow][ncol] == 0) {

					if (!visited[nrow][ncol]) {
						queue.offer(new Tomato(nrow, ncol, m.day + 1));
						visited[nrow][ncol] = true;
						count++;
					}
				}

			}
			if (count == totalTomato) {
				day = m.day + 1;
				return;
			}
		}
		day = -1;
	}
}

class Tomato {
	int row;
	int col;
	int day;

	Tomato(int row, int col, int day) {
		this.row = row;
		this.col = col;
		this.day = day;
	}
}
