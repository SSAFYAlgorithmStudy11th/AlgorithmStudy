import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

public class No2407 {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		BigInteger [][] dp = new BigInteger[101][101];
		
		for(int i=0;i<101;i++) {
			Arrays.fill(dp[i], BigInteger.ONE);
		}
		
		for(int i =2;i<101;i++) {
			for(int j = 1;j<i;j++) {
				dp[i][j] = dp[i-1][j].add(dp[i-1][j-1]);
			}
		}
		System.out.println(dp[N][M]);

	}

}
