import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
 
public class Main {
	static int N, M, D;
	static int[][] map;
	static int[][] mapClone;
	static int totalEnemy;
	static int[] dx = {0,-1,1};
	static int[] dy = {-1,0,0};
	static int MAX = Integer.MIN_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][M];
		mapClone = new int[N+1][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				mapClone[i][j] = map[i][j];
				if(map[i][j] == 1) {
					totalEnemy++;
				}
			}
		}
		backtracking(0, 0);
		System.out.println(MAX);
	}
	
	static int simulator() {
		//먼저 궁수 3자리까지 배치하고
		//시뮬레이션 돌려서 궁수가 처치한 적 count;
		//MAX 값 저장
		int totalEn = totalEnemy;
		int killVal = 0;
		int moveKill = 0;
		List<int[]> list = new ArrayList<>();
		while(totalEn != 0) { 
			for(int i = 0; i < M; i++) {
				if(mapClone[N][i] == 2) {
					int[] val = findMinEnemy(i,N);
					if(val != null) {
						list.add(val);
					}
				}
			}
			int kill = killEnemy(list);
			totalEn -= kill;
			killVal += kill;
			list.clear();
			totalEn -= (moveKill = move());

		}
		
		return killVal;
	}
	
	static void backtracking(int cnt, int depth) {
		if(cnt == 3) {
			mapClone();
			MAX = Math.max(MAX, simulator());
			return;
		}
		
		
		for(int i = depth; i < M; i++) {
			if(mapClone[N][i] != 0) continue;
			mapClone[N][i] = 2;
			
			backtracking(cnt+1, i+1);
			mapClone[N][i] = 0;
		}
	}
	
	static int killEnemy(List<int[]> list) {
		int killCount = 0;
		for(int[] cur : list) {
			if(mapClone[cur[1]][cur[0]] == 1) {
				mapClone[cur[1]][cur[0]] = 0;
				killCount++;
			}

		}
		return killCount;
	}
	
	static int[] findMinEnemy(int x, int y) {
		PriorityQueue<int[]> q = new PriorityQueue<>((i1, i2) ->{
			if(i1[2] != i2[2]) {
				return i1[2] - i2[2];
			}
			if(i1[0] != i2[0]) {
				return i1[0] - i2[0];
			}
			else {
				return i1[1] - i2[1];
			}
		});
		boolean[][] visited = new boolean[N+1][M];
		q.add(new int[] {x,y,0});
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int cx = cur[0];
			int cy = cur[1];
			
			if(visited[cy][cx]) {
				continue;
			}
			
			if(mapClone[cy][cx] == 1) {
				return new int[] {cx, cy};
			}
			
			visited[cy][cx] = true;
			for(int i = 0 ; i < 3; i++) {
				int curX = cx + dx[i];
				int curY = cy + dy[i];
				if(!isBoundary(curX, curY)) {
					continue;
				}
				
				if(!visited[curY][curX] && cur[2] + 1 <= D) {
					q.add(new int[] {curX, curY, cur[2]+1});
				}
			}
		}
		return null;
	}
	
	static boolean isBoundary(int x, int y) {
		return 0 <= x && x < M && 0 <= y && y < N;
	}
	
	static void mapClone() {
		for(int i = 0; i < N; i++) {
			System.arraycopy(map[i], 0, mapClone[i], 0, M);
		}
	}
	
	static int move() {
		int removeCount = 0;
		for(int i = N-1; i >= 0; i--) {
			for(int j = 0 ; j < M; j++) {
				if(mapClone[i][j] >= 1) {
					mapClone[i][j]--;
					if(i+1 != N) {
						mapClone[i+1][j]++;
					}else {
						removeCount++;
					}
				}
			}
		}
		
		return removeCount;
	}
	
}
