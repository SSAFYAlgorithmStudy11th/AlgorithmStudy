import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class castle_defense {
	static int N; //행
	static int M; //열
	static int D; // 궁수 사거리
	static boolean [][] map; //적군 맵
	static int[] comb_num; // 조합하기 위한 배열
	static ArrayList<int[]> list;
	static int answer =0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		map = new boolean[N+1][M];
		list = new ArrayList<int[]>();
		comb_num = new int[M];
		answer = 0;
		
		for(int i = 0; i<M; i++) 
			comb_num[i] = i;
		
		
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<M; j++) {
				int num = Integer.parseInt(st.nextToken());
				if(num == 1)
					map[i][j] = true;
			}
		}
		
		comb(0,new int[3],0);
		

		// 궁수 경우의 수 조합마다 실행
		for (int[] archers : list) {
			
			boolean[][] copied_map = new boolean[N+1][M];
			for(int i = 0; i<N; i++) {
				for(int j = 0; j<M; j++) {
					copied_map[i][j] = map[i][j];
				}
			}
			
			int total_kill = 0;
			
			//행의 수만큼 반복
			for (int t = 0; t < N; t++) {
				
				
				// 궁수들의 y좌표는 N+1
				int[] ardt_arr = new int[3];
				Arrays.fill(ardt_arr, Integer.MAX_VALUE);
				
				// 궁수들의 목표 좌표
				int[] tgx_arr = new int[3];
				int[] tgy_arr = new int[3];
				Arrays.fill(tgy_arr, -1);
				Arrays.fill(tgx_arr, -1);
				int start =0;
				
				//사거리 보다 긴 거리는 탐색x
				if(N-1>=D)
					start = N-D-1;
				
				//공격 대상 지정
				for (int i = start; i < N; i++) {
					for (int j = 0; j < M; j++) {
						if (copied_map[i][j]) {
							for (int k = 0; k < 3; k++) {
								
								//거리 산정하여 가장 가까운 목표 선정
								int dt = Math.abs(N - i) + Math.abs(archers[k] - j);
								if (dt <= ardt_arr[k] && dt <= D) {
									//거리가 같다면 왼쪽 목표로
									if(dt == ardt_arr[k] &&  tgx_arr[k] < j)
										continue;
									ardt_arr[k] = dt;
									tgx_arr[k] = j;
									tgy_arr[k] = i;
								}
							}
						}
					}
				}
				
				//적군 제거
				for (int k = 0; k < 3; k++) {
					if (tgy_arr[k] != -1 && copied_map[tgy_arr[k]][tgx_arr[k]]) {
						copied_map[tgy_arr[k]][tgx_arr[k]] = false;
						total_kill++;
					}
				}


				// 줄 내리기
				for(int i = 0; i<M; i++) {
					copied_map[N-1][i] = false;
				}
				
				for(int i = N-2; i>=0; i--) {
					for(int j = 0; j<M; j++) {
						copied_map[i+1][j] = copied_map[i][j];
					}
				}
				
				for(int i = 0; i<M; i++) {
					copied_map[0][i] = false;
				}
				
				
				
			}
			answer = Math.max(answer, total_kill);

		}
		
		System.out.println(answer);

	}
	
	static void comb(int toselect, int[] selected,int startidx) {
		if(toselect == 3) {
			list.add(selected.clone());
			return;
		}
		
		for(int i = startidx; i<comb_num.length; i++) {
			selected[toselect] = comb_num[i];
			comb(toselect+1,selected,i+1);
		}

	}

}
