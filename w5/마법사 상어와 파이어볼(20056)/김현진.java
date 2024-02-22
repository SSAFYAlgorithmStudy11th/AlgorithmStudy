import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static Set<Ball>[][] map;
	static int N, M, K;
	static Queue<Ball> q;
	static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		q = new ArrayDeque<>();
		
		map = new Set[N][N];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = new HashSet<>();
			}
		}
		
		for(int i = 0 ; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			Ball target = new Ball(c-1, r-1, d, s, m);
			q.add(target);
			map[r-1][c-1].add(target);
		}
		
		turn();
		int sum = 0;
		while(!q.isEmpty()) {
			Ball cur = q.poll();

			sum += cur.m;
		}
		System.out.println(sum);
	}
	
	static void turn() {

		while(K --> 0) {
			int size = q.size();
			while(size --> 0) {
				Ball cur = q.poll();
				
				map[cur.y][cur.x].remove(cur);
				cur.move();
				map[cur.y][cur.x].add(cur);
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j].size() == 1) {
						for(Ball b : map[i][j]) {
							q.add(b);
						}
						continue;
					}
					if(map[i][j].size() > 1) {
						boolean isDirection = true;
						int mSum = 0;
						int sSum = 0;
						boolean isAllOdd = true;
						boolean isAllEven = true;
						for(Ball b : map[i][j]) {
							if(b.direction % 2 != 0) {
								isAllEven = false;
							}
							if(b.direction % 2 == 0) {
								isAllOdd = false;
							}
							mSum += b.m;
							sSum += b.s;
						}
						isDirection = isAllEven || isAllOdd;
						sSum = sSum/map[i][j].size();
						mSum = mSum/5;
						map[i][j].clear();
						if(mSum == 0) {
							continue;
						}
						for(int k = 0; k < 4; k++) {
							Ball newBall;
							if(isDirection) {
								newBall = new Ball(j,i,k*2, sSum, mSum);
							}else {
								newBall = new Ball(j,i,k*2+1, sSum, mSum);
							}
							map[i][j].add(newBall);
							q.add(newBall);
						}
					}
				}
			}

		}
	}
	
	
	
	static class Ball {
		int x;
		int y;
		int direction; //방향
		int s; //속도
		int m; //질량
		
		Ball(int a, int b, int c, int d, int f) {
			x = a; y = b; direction = c; s = d; m = f;
		}
		
		void move() {
			x = (x + s * dx[direction]) % N;
			if(x < 0) {
				x += N;
			}
			y = (y + s * dy[direction]) % N;
			if(y < 0) {
				y += N;
			}
		}

	}
}
