import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
 
class Main {
	static int N;
	static int[][] cost;
	static int[][] dp;
	static final int INF = 1000000000;
 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		cost = new int[N][N];
		dp = new int[N][1<<N];
 
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
 
		for(int i=0; i<N; i++) {
			Arrays.fill(dp[i], INF);
		}
 
		System.out.println(travel(0,1));
	}
 
	static int travel(int cur, int visit) {
		//dp가 갱신되어 있을 경우
		if(dp[cur][visit] != INF)
			return dp[cur][visit];
 
		//base case: 모든 마을을 방문하였을 경우 시작점으로 돌아간다.
		if(visit == (1<<N)-1)
			return dp[cur][visit] = cost[cur][0] != 0 ? cost[cur][0] : INF;
 
		//substructure
		for(int i=0; i<N; i++) {
			//이동할 수 없거나, 이미 방문한 경우
			if(cost[cur][i] == 0 || (visit & (1<<i)) > 0)
				continue;
			dp[cur][visit] = Math.min(dp[cur][visit], cost[cur][i] + travel(i, visit | (1<<i)));
		}
 
		return dp[cur][visit];
	}
}
