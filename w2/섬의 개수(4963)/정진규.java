import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] arr;
    static boolean[][] visit;
    static int[] dx = {1, -1, 0, 0, 1, 1, -1, -1};
    static int[] dy = {0, 0, 1, -1, 1, -1, -1, 1};

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            if(n==0 && m ==0){
                return;
            }

            arr = new int[n][m];
            visit = new boolean[n][m];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int num = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (arr[i][j] == 1 && !visit[i][j]) {
                        bfs(i, j);
                        num++;
                    }
                }
            }

            System.out.println(num);
        }

    }

    public static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        visit[x][y] = true;

        while (!q.isEmpty()) {
            int[] out = q.poll();
            int r = out[0];
            int c = out[1];
            for (int i = 0; i < 8; i++) {
                int nx = r + dx[i];
                int ny = c + dy[i];

                if(nx>=0 && nx< n && ny >=0 && ny<m && !visit[nx][ny] && arr[nx][ny] == 1) {
                    visit[nx][ny] = true;
                    q.offer(new int[] {nx,ny});
                }
            }
        }
    }
}
