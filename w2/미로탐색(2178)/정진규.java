import java.io.*;
import java.util.*;

public class Main {

    static int n,m;
    static boolean[][] arr;
    static boolean[][] visit;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new boolean[n][m];
        visit = new boolean[n][m];
        String str;
        for(int i = 0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            str = st.nextToken();
            for(int j = 0;j<m;j++) {
                if(str.charAt(j) == '1')
                    arr[i][j] = true;
            }
        }

        Queue<int[]> q = new LinkedList<>();
        visit[0][0] = true;
        q.offer(new int[] {0,0,1});
        int answer = n*m;
        while(!q.isEmpty()) {
            int[] out = q.poll();
            int x = out[0];
            int y = out[1];
            int cnt = out[2];
            if(x == n-1 && y == m-1) {
                answer= Math.min(answer,cnt);
            }
            for(int i = 0;i<4;i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx>=0 && nx<n && ny>=0 && ny<m && !visit[nx][ny] && arr[nx][ny]) {
                    visit[nx][ny] = true;
                    q.offer(new int[] {nx,ny,cnt+1});
                }
            }

        }
        System.out.println(answer);

    }




}
