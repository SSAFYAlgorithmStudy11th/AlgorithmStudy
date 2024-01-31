import java.io.*;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int[][] maps;
    static int[][] dp;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maps = new int[N][M];
        dp = new int[N][M];

        for(int i = 0; i < N; i++) {
            String input = br.readLine();
            for(int j = 0; j < M; j++) {
                maps[i][j] = input.charAt(j) - '0';
            }
        }
        bfs();
        System.out.println(dp[N-1][M-1] + 1);
    }

    static void bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{0,0});

        while(!q.isEmpty()){
            int[] cur = q.poll();

            for(int i = 0 ; i < 4; i++) {
                int curX = cur[1] + dx[i];
                int curY = cur[0] + dy[i];
                if(!isBoundary(curY, N) || !isBoundary(curX, M)) {
                    continue;
                }

                if(maps[curY][curX] > 0 && dp[curY][curX] == 0) {
                    q.add(new int[]{curY, curX});
                    dp[curY][curX] = dp[cur[0]][cur[1]] + 1;
                }
            }
        }
    }


    static boolean isBoundary(int cur, int bound){
        return 0 <= cur && cur < bound;
    }
}
