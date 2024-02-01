
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class island {
	static boolean[][] visited;
	static int answer = 0;
	static int unsearched_land = 0;

	static int[] dx = { -1, 0, 1, 0,1,1,-1,-1 };
	static int[] dy = { 0, 1, 0, -1,1,-1,1,-1 };
	static Queue<int[]> q = new LinkedList<>();
	static int w;
	static int h;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		while (true) {
			answer = 0;
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			if(w==0&&h==0)
				break;
			visited = new boolean[h][w];

			//맨 처음 탐색된 땅을 큐에 추가 
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					if (Integer.parseInt(st.nextToken()) == 0) {
						visited[i][j] = true;
					}
					else {
						unsearched_land++;
						}
					}
				}
			
			// 첫 bfs후 섬 탐색
			while (unsearched_land != 0) {
				for (int i = 0; i < h; i++) {
					for (int j = 0; j < w; j++) {
						if (!visited[i][j] && q.isEmpty()) {
							q.add(new int[] { i, j });
							answer++;
							break;
						}
					}
					if(!q.isEmpty())
						break;
				}
				bfs();
			}
		
		System.out.println(answer);
		}

	}

	static void bfs() {
		while (!q.isEmpty()) {
			int[] point = q.poll();			
			int y = point[0];
			int x = point[1];
			if(!visited[y][x]) {
				visited[y][x] = true;
				unsearched_land--;
				//땅 주변의 땅 탐색하여 방문
			for (int i = 0; i < 8; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx >= 0 && nx < w && ny >= 0 && ny < h && !visited[ny][nx]) {
	                q.add(new int[] {ny,nx});
	            }
			}
			}
		}
	}
	
	
	
}
