import java.io.*;
import java.util.*;

public class Main {

    static int n, m, v;
    static boolean[] visit;
    static boolean[][] arr;


    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());

        arr = new boolean[n + 1][n + 1];
        visit = new boolean[n + 1];


        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            arr[r][c] = true;
            arr[c][r] = true;

        }
        visit[v] = true;
        dfs(v);
        System.out.println();

        visit = new boolean[n+1];
        bfs(v);
        System.out.println();
    }

    public static void bfs(int idx) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(idx);
        visit[idx] = true;

        while(!q.isEmpty()) {
            int num = q.poll();
            System.out.print(num + " ");
            for(int i = 1;i<n+1;i++) {
                if (arr[num][i] && !visit[i]) {
                    visit[i] = true;
                    q.offer(i);
                }
            }
        }
    }

    public static void dfs(int idx) {
        System.out.print(idx + " ");
        for (int i = 1; i < n + 1; i++) {
            if (arr[idx][i] && !visit[i]) {
                visit[i] = true;
                dfs(i);
            }
        }



    }

}
