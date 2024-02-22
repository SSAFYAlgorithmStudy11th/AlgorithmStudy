package study4.No2382;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 미생물 
public class Solution {
	static int N, M , K;
	static int time;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static Cell[][] arr;
	
	static class Cell implements Comparable<Cell>{ // 미생물 
		int x;
		int y;
		int cnt; // 미생물 수 
		int d; // 이동 방향 
		
		public Cell(int x, int y , int cnt, int d) {
			super();
			this.x =x;
			this.y = y;
			this.cnt = cnt;
			this.d = d;
		}

		@Override
		public int compareTo(Cell o) {
			// TODO Auto-generated method stub
			return o.cnt- this.cnt;
		}
		
	}
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("sample_input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T= Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 맵 크기 
			M = Integer.parseInt(st.nextToken()); // 시간
			K = Integer.parseInt(st.nextToken()); // 군집 수 
			arr = new Cell[N][N];
			PriorityQueue<Cell> q = new PriorityQueue<>();
			time =0;
			int res =0;
			
			for(int i=0;i<K;i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int cnt = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				
				arr[x][y] = new Cell(x,y,cnt, d-1);
			}
			
			while(time<M) { // M 시간 까지 
				time++;
				
				// 이동해줄 세포 찾
				for(int i=0;i<N;i++) {
					for(int j=0;j<N;j++) {
						
						if(arr[i][j] != null) { // 셀 이 있는 곳이면 이동
							Cell c = arr[i][j];
							int nx = i + dx[c.d];
							int ny = j + dy[c.d];
							
							
							if(nx==0 || ny ==0 || nx ==N-1 || ny==N-1) { // 다음이 약있는 곳이면 
								c.cnt = c.cnt/2; // 세포수 반갈
								c.d = c.d%2 ==0? c.d+1 : c.d-1; // 방향 반대로 
							}
							if(c.cnt!=0) {
								q.add(new Cell(nx,ny,c.cnt,c.d));
							}
							
							arr[i][j] = null;
							
						}
					}
				}
				while(!q.isEmpty()) {
					Cell c = q.poll();
					
					if(arr[c.x][c.y]!= null) {
						arr[c.x][c.y].d = arr[c.x][c.y].cnt>c.cnt?arr[c.x][c.y].d:c.d;
						
						arr[c.x][c.y].cnt += c.cnt;
						
					}
					else {
						arr[c.x][c.y] =c; // 다음 칸으로 옮겨줌 
					}
				}
				
			}
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(arr[i][j] !=null) {
						res+= arr[i][j].cnt;
					}
				}
			}
			
			System.out.printf("#%d %d \n",test_case, res);
		}
	}

}
