
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baby_shark {
	static int N;
	static int answer;
	static int[] dx = { 0, -1, 1, 0 };
	static int[] dy = { -1, 0, 0, 1 };
	static int shark_size;
	static int shark_hungry;
	static int total_fish;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		int[][] map = new int[N][N];
		answer = 0;
		shark_size = 2;
		shark_hungry = 2;
		total_fish = 0;

		int shark_x = 0;
		int shark_y = 0;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				if (num != 9 && num != 0)
					total_fish++;

				if (num == 9) {
					shark_x = j;
					shark_y = i;
				} else
					map[i][j] = num;

			}
		}

		bfs(shark_x, shark_y, new boolean[N][N], map);
		System.out.println(answer);

	}

	static void bfs(int x, int y, boolean[][] visited, int[][] map) {

		//사방 탐색으로 넣었는데 탐색 순서가 좀 이상해져서 우선순위큐 사용
		PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				int result = 0;
				if (o1[2] != o2[2])
					result = Integer.compare(o1[2], o2[2]);
				else {
					if (result == 0) {
						result = Integer.compare(o1[0], o2[0]);
						if (result == 0)
							result = Integer.compare(o1[1], o2[1]);
					}

				}
				return result;
			}
		});
		
		//다먹었으면 끝
		if (total_fish == 0)
			return;

		q.add(new int[] { y, x, 0 }); // y 좌표, x 좌표, 거리(second) 

		//기본적으로 bfs 돌리면서, 가까운 물고기 찾으면, 좌표이동시키고 큐 비움, 그러고 다시 탐색
		while (!q.isEmpty()) {
			int[] pos = q.poll();
			y = pos[0];
			x = pos[1];
			int dt = pos[2];

			if (!visited[pos[0]][pos[1]]) {
				visited[pos[0]][pos[1]] = true;

				if (y < N && y >= 0 && x < N && x >= 0 && map[y][x] <= shark_size) {
					if (map[y][x] < shark_size && map[y][x] != 0) {
						total_fish--;
						shark_hungry--;
						if (shark_hungry == 0) {
							shark_size++;
							shark_hungry = shark_size;
						}
						map[y][x] = 0;
						answer += pos[2];

						visited = new boolean[N][N];
						dt = 0;
						q.clear();
					}
				}

				for (int i = 0; i < 4; i++) {
					int ny = y + dy[i];
					int nx = x + dx[i];
					if (ny < N && ny >= 0 && nx < N && nx >= 0 && map[ny][nx] <= shark_size) {
						q.add(new int[] { ny, nx, dt + 1 });
					}
				}
			}
		}
	}
}
