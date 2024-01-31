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
	static int N, M, c;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		visited = new boolean[N][M];
		array = new int[N][M];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				array[i][j] = str.charAt(j) - '0';
			}

		}
		c = 0;
		bfs(0, 0);

		bw.write(String.valueOf(c));
		bw.flush();
		bw.close();
	}

	public static void bfs(int i, int j) {

		Queue<Matrix> queue = new LinkedList<>();
		queue.offer(new Matrix(i, j, 1));

		visited[i][j] = true;

		while (!queue.isEmpty()) {
			Matrix m = queue.poll();

			for (int d = 0; d < 4; d++) {

				int nrow = m.row + dr[d];
				int ncol = m.col + dc[d];

				if (nrow == N - 1 && ncol == M - 1) {
					c=m.count+1;
					return;
				}

				if (nrow > -1 && nrow < N && ncol > -1 && ncol < M && array[nrow][ncol] == 1) {

					if (!visited[nrow][ncol]) {
						queue.offer(new Matrix(nrow, ncol,m.count+1));
						visited[nrow][ncol] = true;
					}
				}
			}
		}
	}
}

class Matrix {
	int row;
	int col;
	int count;

	Matrix(int row, int col, int count) {
		this.row = row;
		this.col = col;
		this.count = count;
	}
}
