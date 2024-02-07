import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static Integer[] arr;
    static int K;
    static boolean[][] dp;
    static int sum;
    static int MAX = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new Integer[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        sum = 0;

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
        }

        dp = new boolean[sum + 1][sum + 1];
        dp[0][0] = true;
        for(int i = 0; i < N; i++) {
            for(int j = sum; j >= 0; j--) {
                for(int k = sum; k >= 0; k--) {
                    if(arr[i] <= j)
                        dp[j][k] |= dp[j-arr[i]][k];
                    if(arr[i] <= k)
                        dp[j][k] |= dp[j][k-arr[i]];
                }
            }
        }

        for(int i = 0; i < sum + 1; i++ ) {
            for(int j = 0; j <= i; j++ ){
                int last = sum - i - j;
                if(dp[i][j] && last <= j) {
                    MAX = Math.max(last, MAX);
                }
            }
        }
        System.out.println(MAX);
    }
}
