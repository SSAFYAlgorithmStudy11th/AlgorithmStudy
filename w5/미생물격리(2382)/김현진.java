import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int N, M, K;
	static List<Cluster>[][] map;
	static List<Cluster> list;
	static int[] dx = {0,0,-1,1,0};
	static int[] dy = {0,-1,0,0,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		int test_case = 1;
		while(T --> 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new List[N][N];
			for(int i = 0; i < N; i++) {
				for(int j = 0 ; j < N; j++) {
					map[i][j] = new ArrayList<>();
				}
			}
			list = new ArrayList<>();
			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				int val = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				
				switch(d) {
				case 1:
					d = 1;
					break;
				case 2:
					d = 4;
					break;
				case 3:
					d = 2;
					break;
				case 4:
					d = 3;
					break;
				}
				Cluster c = new Cluster(x,y,val,d);
				list.add(c);
				map[c.y][c.x].add(c);
			}
			
			while(M --> 0) {
				for(int i = list.size() - 1; i >= 0; i--) {
					Cluster c = list.get(i);
					map[c.y][c.x].remove(c);
					c.x += dx[c.direct];
					c.y += dy[c.direct];
					
					if(c.x == 0 || c.x == N-1 || c.y == N-1 || c.y == 0) {
						c.direct = 5 - c.direct;
						c.val = c.val / 2;
						if(c.val == 0) {
							list.remove(i);
							continue;
						}
					}
					map[c.y][c.x].add(c);
					
				}
				
				for(int i = 0; i < N; i++) {
					for(int j = 0; j < N; j++) {
						List<Cluster> cur = map[i][j]; 
						if(cur.size() <= 1) {
							continue;
						}
						
						int maxIdx = 0;
						int max = 0;
						for(int k = 0; k < cur.size(); k++) {
							if(max < cur.get(k).val) {
								maxIdx = k;
								max = cur.get(k).val;
							}
						}
						
						int val = 0;
						for(int k = cur.size() - 1; k >= 0; k--) {
							val+=cur.get(k).val;
							if(k != maxIdx) {
								list.remove(cur.get(k));
								cur.remove(cur.get(k));
							}
						}
						cur.get(0).val = val;
					}
				}
			}
			int sum = 0;
			for(Cluster c : list) {
				sum += c.val;
			}

			sb.append('#');
			sb.append(test_case++);
			sb.append(' ');
			sb.append(sum);
			sb.append('\n');
		}
		System.out.println(sb);
	}
	
	static class Cluster {
		int x;
		int y;
		int val;
		int direct;
		
		Cluster(int a, int b, int c, int d) {
			x = a; y = b; val = c; direct = d;
		}
		
		public String toString() {
			return val + ", ";
		}
	}
}
