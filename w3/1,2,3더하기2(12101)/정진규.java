import java.io.*;
import java.util.*;

public class Main {

	static int n,m;
	static int count;
	static int[][] dp;
	static String answer;
	static boolean chk;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st= new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		dp = new int[n+1][4];

		if(n>0) {
			dp[1][1] = 1;
			dp[1][2] = 1;
			dp[1][3] = 1;
		}
		if(n>1) {
			dp[2][1] = 1;
			dp[2][2] = 2;
			dp[2][3] = 2;
		}
		if(n>2) {
			dp[3][1] = 2;
			dp[3][2] = 3;
			dp[3][3] = 4;
		}
		if(n>3) {
			for(int i = 4;i<n+1;i++) {
				dp[i][1] = dp[i-1][3];
				dp[i][2] = dp[i][1] + dp[i-2][3];
				dp[i][3] = dp[i][2] + dp[i-3][3];

			}
		}

	
		
		
		
		
		if(dp[n][3] < m) {
			System.out.println(-1);
		}
		else {
			answer = "";
			chk =false;
			if(m > dp[n][2]) {
				count = dp[n][2];
				find("3",3);
			}
			else if (m > dp[n][1]) {
				count = dp[n][1];
				find("2",2);
			}else {
				count = 0;
				find("1",1);
			}
			System.out.println(answer);
		}
		
		
	}

	public static void find(String str, int sum) {
		if(sum>n) {
			return;
		}
		if(sum==n) {
			answer = str;
			count++;
			if(count==m) {
				chk = true;
				return;
			}
		}
		for(int i = 1;i<4;i++) {
			if(chk) {
				return;
			}
			find(str+"+"+i,sum+i);
		}
	}

}
