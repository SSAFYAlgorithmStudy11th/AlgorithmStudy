import java.io.*;
import java.util.*;


/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
	
	static class Cell implements Comparable<Cell> {
		int r;
		int c;
		long size;
		int dir;

		public Cell(int r, int c, long size, int dir) {
			super();
			this.r = r;
			this.c = c;
			this.size = size;
			this.dir = dir;
		}

		@Override
		public int compareTo(Cell o) {
			// TODO Auto-generated method stub
			return Long.compare(o.size, this.size);
		}

	}

	static int n, m, k;
	static Cell[][] graph;
	static PriorityQueue<Cell> mainQ;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken()); // 크기
			m = Integer.parseInt(st.nextToken()); // 격리시간
			k = Integer.parseInt(st.nextToken()); // 미생물 수
			graph = new Cell[n][n];
			mainQ = new PriorityQueue<>();
			for (int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int size = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());

				graph[r][c] = new Cell(r, c, size, dir);
				mainQ.add(graph[r][c]);
			}

			for (int i = 0; i < m; i++) {
				move();
			}

			int sum = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (graph[i][j] != null) {
						sum += graph[i][j].size;
					}
				}
			}
			sb.append("#").append(tc).append(" ").append(sum).append("\n");
		}

		System.out.println(sb);
	}

	public static void move() {
		ArrayList<Cell> tmpList = new ArrayList<>();
		Cell[][] tmp = new Cell[n][n];
		while (!mainQ.isEmpty()) {
			Cell out = mainQ.poll();
			// 이동시키기
			// 가장자리라면 크기 1/2 , 방향 반대로
			// 여러 세포 모이면 사이즈는 합쳐지고, 방향은 가장 큰 세포로
			// 1 상, 2 하 , 3 좌, 4 우
			if (out.dir == 1) {
				out.r -= 1;
				if (out.r == 0) {
					out.size /= 2;
					out.dir = 2;
					if (out.size == 0) {
						continue;
					}
				}
				if (tmp[out.r][out.c] != null) {
					
					tmp[out.r][out.c].size += out.size;
					
				} else {
					tmp[out.r][out.c] = out;
					tmpList.add(out);
				}

			} else if (out.dir == 2) {
				out.r += 1;
				if (out.r == n - 1) {
					out.size /= 2;
					out.dir = 1;
					if (out.size == 0) {
						continue;
					}
				}
				if (tmp[out.r][out.c] != null) {
					tmp[out.r][out.c].size += out.size;
				} else {
					tmp[out.r][out.c] = out;
					tmpList.add(out);
				}
			} else if (out.dir == 3) {
				out.c -= 1;
				if (out.c == 0) {
					out.size /= 2;
					out.dir = 4;
					if (out.size == 0) {
						continue;
					}
				}
				if (tmp[out.r][out.c] != null) {
					tmp[out.r][out.c].size += out.size;
				} else {
					tmp[out.r][out.c] = out;
					tmpList.add(out);
				}
			} else if (out.dir == 4) {
				out.c += 1;
				if (out.c == n - 1) {
					out.size /= 2;
					out.dir = 3;
					if (out.size == 0) {
						continue;
					}
				}
				if (tmp[out.r][out.c] != null) {
					tmp[out.r][out.c].size += out.size;
				} else {
					tmp[out.r][out.c] = out;
					tmpList.add(out);
				}
			}

		}
		
		graph = tmp;
		for(Cell c : tmpList) {
			mainQ.add(c);
		}
	}

}
