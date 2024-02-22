import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	static int N,M,K, time;
	static int[][] arr;
	static PriorityQueue<Cell> q;
	static PriorityQueue<Cell> actCell;
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};
	static int cnt;
	
	static class Cell implements Comparable<Cell>{
		int x;
		int y;
		int life;
		int atime;
		int dtime;
		boolean act;
		
		Cell(int x, int y, int life, int atime, int dtime, boolean act){
			this.x = x;
			this.y = y;
			this.life = life;
			this.atime = atime;
			this.dtime = dtime;
			this.act = act;
		}

		@Override
		public int compareTo(Cell o) {
			// TODO Auto-generated method stub
			
			if(this.act && o.act) { // 둘 다 활성화 세포 
				return this.dtime - o.dtime;
			}
			else if(!this.act && !o.act) { /// 둘다 비활성화 세포
				if(this.atime == o.atime) {
					return o.life - this.life;
				}
				return this.atime - o.atime;
			}
			return 0;
		}
		
	}
	
	static void bfs() {
		while(time<= K) {

			time++;
			cnt =0;
			while(q.peek().atime == time) {
				Cell c = q.poll();
				for(int d = 0; d<4;d++) {
					int nx = c.x + dx[d];
					int ny = c.y + dy[d];
					if(nx>=0 && nx<2*K+N &&ny>=0 && ny <2*K+M ) {
						if(arr[nx][ny] == 0) {
							arr[nx][ny] = c.life;
							q.add(new Cell(nx, ny, c.life, time+c.life+1, time+c.life+c.life+1, false ));
							cnt++;
						}
					}
				}
				c.act = true;
				actCell.add(c);
			}
			
			
			while(!actCell.isEmpty() && actCell.peek().dtime == time) {
				actCell.poll();
			}
		}
	}
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		//System.setIn(new FileInputStream("sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T= Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			arr = new int[K*2+N][K*2+M];
			time = 0;
			q = new PriorityQueue<>(); // 활성화 되기 전 세포만 있는 큐 
			actCell = new PriorityQueue<>(); // 활성화 된 세포만 있는 큐
			
			for(int i=0;i<N ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<M;j++) {
					int s = Integer.parseInt(st.nextToken());
					if(s != 0) {
						arr[K+i][K+j] = s;
						q.add(new Cell(i+K,j+K,s,time+s+1 , time+s+s+1, false));
					}
				}
			}
			
			bfs();
			
			System.out.printf("#%d %d\n",test_case,actCell.size()+q.size()-cnt);
		}

	}

}
