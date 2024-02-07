import java.io.*;
import java.util.*;

//플로이드
public class Main {
	

	static int n, m;
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
	
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		int INF = 100000 * 100;
		
		int[][] floyd = new int[n+1][n+1];
		
		for(int i = 1;i<n+1;i++) {
			for(int j = 1; j<n+1;j++) {
				if(i==j)
					floyd[i][j] = 0;
				else {
					floyd[i][j] = INF;
				}
			}
		}
		
		for(int i = 0; i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int f = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			//기존 입력에서 최단경로로 설정
			floyd[f][t] = Math.min(floyd[f][t], w); 
		}
		
		// 거치는 노드 수 k
		for(int k = 1; k<n+1;k++) {
			// i to k 이동 경우
			for(int i = 1;i<n+1;i++) {
				for(int j =1;j<n+1;j++) {
					floyd[i][j] = Math.min(floyd[i][j], floyd[i][k] + floyd[k][j]);
				}
			}
		}
		
		for(int i = 1;i<n+1;i++) {
			for(int j = 1;j<n+1;j++) {
				if(floyd[i][j] == INF)
					sb.append(0).append(" ");
				else {
					sb.append(floyd[i][j]).append(" ");
				}
			}
			sb.append("\n");
		}
		
		
		System.out.println(sb);
	}
	
	

}
