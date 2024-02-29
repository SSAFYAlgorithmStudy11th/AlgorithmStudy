import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

    static int n,m;
    static int[][] arr;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static int max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc<=T;tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            arr = new int[n][n];

            for(int i = 0; i<n;i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j<n;j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            max = 0;
            for(int i = 0 ; i< n; i++) {
                for(int j = 0 ;j<n;j++) {
                    chk(i,j);
                }
            }

            sb.append("#").append(tc).append(" ").append(max).append("\n");

        }
        System.out.print(sb);
    }

    public static int cal(int k, int cnt) {
        int runCost = k*k + (k-1) * (k-1);
        int serviceCost = cnt * m;


        return runCost - serviceCost;
    }

    public static void chk(int x, int y) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visit = new boolean[n][n];


        q.add(new int[] {x,y});
        visit[x][y] = true;

        int cnt = 0;
        if(arr[x][y] == 1) {
            cnt++;
            if(max < cnt && cal(1,cnt) <= 0 )
                max = cnt;
        }

        for(int i = 1; i< n*2; i++) {
            Queue<int[]> tmp = new ArrayDeque<>();
            while(!q.isEmpty()) {
                int[] out = q.poll();
                int r = out[0];
                int c = out[1];


                for(int j = 0 ;j<4;j++) {
                    int nx = r + dx[j];
                    int ny = c + dy[j];

                    if(nx>=0 && nx<n && ny>=0 && ny<n && !visit[nx][ny]) {
                        visit[nx][ny] = true;
                        tmp.add(new int[] {nx,ny});
                        if(arr[nx][ny] == 1)
                            cnt++;
                    }
                }
            }
            if(cnt > max && cal(i+1,cnt) <= 0 )
                max = cnt;
            q = tmp;
        }


    }
}
