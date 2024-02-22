
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class magicalshark_fireball {
	static int N;
	static int M;
	static int K;
	
	static int[] dy = {-1,-1,0,1,1,1,0,-1};
	static int[] dx = {0,1,1,1,0,-1,-1,-1};
	
	static class Fireball{
		int x;
		int y;
		
		int massive;
		int speed; 
		int direction;
		int merged; //합쳐진 파이어볼의 갯수
		
		public Fireball( int y,int x, int massive, int speed, int direction,int merged) {
			super();
			this.y = y;
			this.x = x;
			this.massive = massive;
			this.speed = speed;
			this.direction = direction;
			this.merged = merged;
		}

		@Override
		public String toString() {
			return "Fireball [x=" + x + ", y=" + y + ", massive=" + massive + ", speed=" + speed + ", direction="
					+ direction + ", merged=" + merged + "]";
		}
		
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Queue<Fireball> q = new ArrayDeque<>();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int answer =0;
		

		for(int i = 0; i<M; i++) {
			st= new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int massive = Integer.parseInt(st.nextToken());
			int speed = Integer.parseInt(st.nextToken());
			int direction = Integer.parseInt(st.nextToken());
			Fireball fb =new Fireball(y-1,x-1,massive,speed,direction,1);
			q.add(fb);
			

		}
		
		
		Fireball[][] arr = new Fireball[N][N];
		
		for(int t = 0; t<K; t++) {
			
			//파이어볼의 이동 
			while(!q.isEmpty()) {
				
				Fireball fb = q.poll();
				
				fb.x += dx[fb.direction]*fb.speed;
				fb.y += dy[fb.direction]*fb.speed;
				
				//격자 바깥 좌표면 조정
				while(fb.x < 0)
					fb.x += N;
				while(fb.y < 0)
					fb.y += N;
				if(fb.x >= N)
					fb.x %= N;
				if(fb.y >= N)
					fb.y %= N;
				
				//이미 있는 파이어볼이 있다면, 질량 속도 합치기, 방향도 고려 
				if(arr[fb.y][fb.x] != null) {
					arr[fb.y][fb.x].massive += fb.massive;
					arr[fb.y][fb.x].speed += fb.speed;
					arr[fb.y][fb.x].merged += 1;
					
					if(arr[fb.y][fb.x].direction % 2 != fb.direction % 2)
						arr[fb.y][fb.x].direction = -1;
				
				}
				else 
					arr[fb.y][fb.x] = fb;
			}
			
			
			for(int i = 0; i<N; i++) {
				for(int j = 0; j<N; j++) {
					if(arr[i][j] == null)
						continue;
					
					//파이어볼이 겹쳐져있다면
					if(arr[i][j].merged > 1) {
						int massive = arr[i][j].massive/5;
						int speed = arr[i][j].speed/arr[i][j].merged;
						if(massive > 0) {
							for(int k = 0; k<4; k++) {
								if(arr[i][j].direction != -1)
									q.add(new Fireball(i,j,massive,speed,k*2,1));
								else
									q.add(new Fireball(i,j,massive,speed,k*2+1,1));
							}
						}
					}
					else 
						q.add(arr[i][j]);		
				}
			}
			arr = new Fireball[N][N];

		}
		
		while(!q.isEmpty()) {
			Fireball fb = q.poll();
			answer += fb.massive;
		}

		System.out.println(answer);
	}

}
