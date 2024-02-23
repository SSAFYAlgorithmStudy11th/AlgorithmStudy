package W0219;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int M;
	static int C;
	static int[] worker_A;
	static int[] worker_B;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		for(int Test = 1; Test< T+1; Test++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			int[][] honey = new int[N][N];
			int answer =0;
			boolean check;
			
			worker_A = new int[3]; // 0 : y  ,  1 : x  , 2 : 최대 수확량
			worker_B = new int[3];
			int[] nums= new int[M];
			
			for(int i = 0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j<N; j++) 
					honey[i][j] = Integer.parseInt(st.nextToken());
			}
			
			//1번 일꾼
			for(int i = 0; i<N; i++) {
				for(int j = 0; j<=N-M; j++) {
					for(int n = j; n<j+M; n++) {
						nums[n-j] = honey[i][n];
					}
					worker_A = subset(nums,worker_A,i,j);
				}
			}
			
			
			//최댓값이 확정 됐으면 선택된 값 지우기
			for(int n = worker_A[1]; n<worker_A[1]+M; n++)
				honey[worker_A[0]][n] = -1;
			
			// 2번 일꾼 
			for(int i = 0; i<N; i++) {
				for(int j = 0; j<=N-M; j++) {
					check = true;
					for(int n = j; n<j+M; n++) {
						//이미 선택된 값을 고려
						if(honey[i][n] == -1) {
							check = false;
							break;
						}
						nums[n-j] = honey[i][n];
					}
					if(check)
						worker_B = subset(nums,worker_B,i,j);
					
				}
			}
			
			answer = worker_A[2]+worker_B[2];
			
			bw.append("#").append(Integer.toString(Test)).append(" ").append(Integer.toString(answer)).append("\n");
			
		}
		bw.close();
		
		
	}
	
	//비트마스킹을 이용한 부분집합
	static int[] subset(int[] nums,int[] worker,int y, int x) {
		int total = 0;
		int total_value = 0;
		for(int i = 1; i < (1 << M); i++) {
			total = 0; 
			total_value =0;
			for(int j = 0; j<M; j++) {
				if((i & (1<<j)) != 0) {
					if(nums[j] < 0) {
						total = 0;
						total_value = 0;
						break;
					}
					total += nums[j];
					total_value += nums[j]*nums[j];
				}
			}
			if(worker[2] < total_value && total <= C) {
				worker[0] = y;
				worker[1] = x;
				worker[2] = total_value;
			}
		}
		
		return worker;

	}

}
