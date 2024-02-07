package D0202;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class floyd {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int[][] city;
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		//
		
		city = new int[N+1][N+1];
		for(int i = 0 ; i <N+1; i++) {
			Arrays.fill(city[i], 1_000_000_000);
			city[i][i] = 0;
		}
		
		
		
		
		for(int i = 0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			//도시를 연결하는 노선이 하나가 아닐 수 있으므로 최소 비용으로 선정
			city[a][b] = Math.min(c, city[a][b]);
				
		}
		
//		for(int[] a : city) {
//			for(int b : a) {
//				System.out.print(b+" ");
//			}
//			System.out.println();
//		}

		//플로이드 워셜
		for(int m = 1; m< N+1; m++) {
			for(int s = 1; s<N+1; s++) {
				for(int e = 1; e<N+1; e++) {
					if(city[s][e] > city[s][m]+city[m][e])
						city[s][e] = city[s][m]+city[m][e];
				}
			}
		}
		
		for(int i = 1; i<N+1; i++) {
			for(int j = 1; j<N+1; j++) {
				if(city[i][j] == 1_000_000_000)
					bw.write(Integer.toString(0));
				else 
					bw.write(Integer.toString(city[i][j]));
				bw.write(" ");
			}
			bw.write("\n");
		}
		bw.close();
	}

}
