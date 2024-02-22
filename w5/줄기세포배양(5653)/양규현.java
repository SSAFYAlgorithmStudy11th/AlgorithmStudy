
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class stemcell_grow {
	static int N;
	static int M;
	static int K;
	static int max_y;
	static int min_y;
	static int min_x;
	static int max_x;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	static int answer;
	
	public static class Cell{
		public int maxlife;
		public int life;
		public boolean active;
		public boolean isdead;
		
		public Cell(int maxlife, int life, boolean active, boolean isdead) {
			super();
			this.maxlife = maxlife;
			this.life = life;
			this.active = active;
			this.isdead = isdead;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int Test = 1; Test<T+1; Test++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			answer =0;
			
			//문제 조건에 맞춰 격자 생성
			int size = 650;
			Cell[][] arr = new Cell[size][size];
			
			int start_point_y = (size/2) -(N/2);
			int start_point_x = (size/2) -(M/2);
			
			//분열 할때 마다 탐색범위를 늘리는 식 
			max_y = size/2 +(N/2)+5;
			min_y = size/2 -(N/2)-5;
			max_x = size/2 +(M/2)+5;
			min_x = size/2 -(M/2)-5;
			
			for(int i = 0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j<M; j++) {
					int life = Integer.parseInt(st.nextToken());
					if(life == 0)
						continue;
					arr[start_point_y+i][start_point_x+j] = new Cell(life,life,false,false);
				}
			}
			
			
			for(int t = 0; t<K; t++) {
				
				for(int i = min_y; i<max_y;i++) {
					for(int j = min_x; j<max_x; j++) {
						if(arr[i][j] == null)
							continue;
						else if(arr[i][j].isdead)
							continue;
						else {
							//생명력 깎고 0이라면 활성화, 생명력도 다시 채움
							--arr[i][j].life;
							if(arr[i][j].life == 0 && !arr[i][j].active) {
								arr[i][j].active = true;
								arr[i][j].life = arr[i][j].maxlife;
							}
							
						}
						
					}
				}
				
				for(int i = min_y; i<max_y;i++) {
					for(int j = min_x; j<max_x; j++) {
						if(arr[i][j] == null)
							continue;
						else if(arr[i][j].isdead)
							continue;
						//활성화 상태이고 최대 생명력에서 1만 깎인 상태라면 분열
						else if(arr[i][j].active && (arr[i][j].maxlife - arr[i][j].life) == 1) {
							for(int k =0; k<4; k++) {
								int ny = i+dy[k];
								int nx = j+dx[k];
								if(arr[ny][nx] == null)
									arr[ny][nx] = new Cell(arr[i][j].maxlife,arr[i][j].maxlife,false,false);
								if(arr[ny][nx].isdead)
									continue;
								//활성화가 아닌데 최대 생명력과 생명력이 같다면 방금 분열한 세포이므로, 비교후 더 높은 생명력으로 교체 
								else if(arr[ny][nx].maxlife == arr[ny][nx].life && arr[i][j].maxlife > arr[ny][nx].maxlife && !arr[ny][nx].active) {
									arr[ny][nx].maxlife = arr[i][j].maxlife;
									arr[ny][nx].life = arr[i][j].maxlife;
								}
							}
						}
						
						//활성화 상태이고 생명력 0 이면 사망처리
						if(arr[i][j].active && arr[i][j].life == 0)
							arr[i][j].isdead =true;
					}
					
				}

				--min_y;
				++max_y;
				--min_x;
				++max_x;	
			}

			for(int i = min_y; i<max_y;i++) {
				for(int j = min_x; j<max_x; j++) {
					if(arr[i][j] == null)
						continue;
					if(arr[i][j].isdead)
						continue; 
					++answer;
				}
			}

			bw.append("#").append(Integer.toString(Test)).append(" ").append(Integer.toString(answer)).append("\n");
	
		}
		bw.close();
	}

}
