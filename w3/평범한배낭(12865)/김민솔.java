import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[]w = new int[N];
		int[]v = new int[N];
		
		long[][] dp = new long[N][K+1];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			w[i] = Integer.parseInt(st.nextToken());
			v[i] = Integer.parseInt(st.nextToken());
		}
		
		if(w[0]<=K) {
			dp[0][w[0]] = v[0];
		}
		
		for(int i=1;i<N ;i++) {
			for(int j =0;j<=K;j++) {
				if(dp[i-1][j]!=0) {
					dp[i][j] = Math.max(dp[i][j] , dp[i-1][j]);
				}
				if(w[i] + j <=K) {
					dp[i][w[i] + j] = Math.max(dp[i][w[i] + j],dp[i-1][j]+v[i] );
				}
			}
		}
		
		Arrays.sort(dp[N-1]);
		System.out.println(dp[N-1][K]);
	}

}
