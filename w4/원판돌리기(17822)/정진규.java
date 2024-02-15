import java.io.*;
import java.util.*;

public class Main {

	static int n, m, t;
	static int[][] arr;
	static int x, d, k;
	static boolean chk;
	static int OUT = Integer.MIN_VALUE;
	

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());

		arr = new int[n + 1][m + 1];

		for (int i = 1; i < n + 1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < m + 1; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < t; i++) {
			st = new StringTokenizer(br.readLine());
			// x는 원판 고르기, d 0은 시계, 1은 반시계
			// k는 돌리는 횟수
			x = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			chk = false;
			rotate();
			// todo : 돌린 모양에서 같은 행 동일 숫자 지우고, 열 확인하며 다른 숫자 만날때까지 탐색
			for (int j = 1; j < n + 1; j++) {
				for (int q = 1; q < m + 1; q++) {
					if (arr[j][q] != OUT) {
						find(j, q);
					}
				}
			}
			// todo : 지워진 숫자가 없다면 평균 구해서 평균보다 크면 +1, 작으면 -1 수행
			if (!chk) {
				update();
			}
		}
		// todo : 반복 끝나면 남은 수 합 구해서 출력
		System.out.println(sum());

	}

	

	public static int sum() {
		int sum = 0;
		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < m + 1; j++) {
				if (arr[i][j] != OUT)
					sum += arr[i][j];
			}
		}
		return sum;
	}

	// 동일 숫자가 없으면 평균 구해서 갱신
	public static void update() {
		double sum = 0;
		int cnt = 0;
		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < m + 1; j++) {
				if (arr[i][j] != OUT) {
					sum += arr[i][j];
					cnt++;
				}
			}
		}
		if (cnt == 0) {
			return;
		}
		double avg = sum / cnt;
		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < m + 1; j++) {
				if (arr[i][j] == OUT)
					continue;
				if (arr[i][j] > avg) {
					arr[i][j]--;
				} else if (arr[i][j] < avg) {
					arr[i][j]++;
				}
			}
		}

	}

	public static void find(int r, int c) {
		Queue<int[]> q = new LinkedList<>();
		boolean up = false;
		q.add(new int[] { r, c });
		int target = arr[r][c];
		while (!q.isEmpty()) {
			int out[] = q.poll();

			// 왼쪽 확인
			int nc = out[1] - 1;
			if (nc < 1) {
				nc = m;
			}
			if (arr[out[0]][nc] == target) {
				arr[out[0]][nc] = OUT;
				q.offer(new int[] { out[0], nc });
				chk = true;
				up = true;
			}

			// 오른쪽 확인
			nc = out[1] + 1;
			if (nc > m) {
				nc = 1;
			}
			if (arr[out[0]][nc] == target) {
				arr[out[0]][nc] = OUT;
				q.offer(new int[] { out[0], nc });
				chk = true;
				up = true;
			}

			// 위로 확인
			int nr = out[0] + 1;
			if (nr > n) {
				continue;
			}
			if (arr[nr][out[1]] == target) {
				arr[nr][out[1]] = OUT;
				q.offer(new int[] { nr, out[1] });
				chk = true;
				up = true;
			}
			
			// 아래로 확인
			nr = out[0] - 1;
			if (nr < 1) {
				continue;
			}
			if (arr[nr][out[1]] == target) {
				arr[nr][out[1]] = OUT;
				q.offer(new int[] { nr, out[1] });
				chk = true;
				up = true;
			}

		}

		if (up) {
			arr[r][c] = OUT;
		}

	}

	public static void rotate() {
		for (int i = 1; i < (n / x) + 1; i++) {
			if (d == 0) {
				for (int j = 0; j < k; j++) {
					int tmp = arr[i * x][m];
					for (int p = m - 1; p >= 1; p--) {
						arr[i * x][p + 1] = arr[i * x][p];
					}
					arr[i * x][1] = tmp;
				}
			} else {
				for (int j = 0; j < k; j++) {
					int tmp = arr[i * x][1];
					for (int p = 2; p < m + 1; p++) {
						arr[i * x][p - 1] = arr[i * x][p];
					}
					arr[i * x][m] = tmp;
				}
			}
		}
	}

}
