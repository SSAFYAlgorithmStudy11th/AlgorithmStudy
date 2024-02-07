import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] arr;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N][N];
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());

        oddPalindrome();
        evenPalindrome();

        StringBuilder sb = new StringBuilder();

        while( M --> 0 ) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken()) - 1;
            int E = Integer.parseInt(st.nextToken()) - 1;
            if(S == E) {
                sb.append(1);
                sb.append('\n');
                continue;
            }
            if(dp[S][E] == 1) {
                sb.append(1);

            }else {
                sb.append(0);
            }
            sb.append('\n');
        }
        System.out.println(sb.toString());

    }



    private static void oddPalindrome() {
        for(int i = 0 ; i < N-1; i++) {
            for(int j = 0 ; i+j < N && i -j >= 0; j++) {
                if(arr[i-j] != arr[i+j]) {
                    break;
                }

                dp[i-j][i+j] = 1;
            }
        }
    }

    private static void evenPalindrome() {
        for(int i = 0 ; i < N-1; i++) {
            for(int j = 0 ; i+j+1< N && i - j >= 0; j++) {
                if(arr[i-j] != arr[i+j+1]) {
                    break;
                }
                dp[i-j][i+j+1] = 1;
            }
        }
    }

}
