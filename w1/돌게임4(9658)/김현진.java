import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static boolean[] dp;
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		dp = new boolean[N + 6];
		dp[0] = false;
		dp[1] = false;
		dp[2] = true;
		dp[3] = false;
		dp[4] = true;
		dp[5] = true;
		dp[6] = true;
		
		for(int i = 4; i < dp.length; i++) {
			dp[i] = !dp[i - 1] | !dp[i - 3] | !dp[i - 4] ;
		}
		
		System.out.println(dp[N] ? "SK" : "CY");
	}
}
