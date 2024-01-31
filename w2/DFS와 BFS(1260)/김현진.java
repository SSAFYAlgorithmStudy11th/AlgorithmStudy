import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M, V;
    static int [][] arr;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        arr = new int[N+1][N+1];

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            arr[start][end] = 1;
            arr[end][start] = 1;
        }
        visited = new boolean[N+1];
        dfs(V);
        bw.newLine();
        bw.flush();
        visited = new boolean[N+1];
        bfs(V);
        bw.flush();
    }


    static void dfs(int start) throws IOException {
        if(start == -1) {
            return;
        }
        if(visited[start]) {
            return;
        }
        visited[start] = true;
        bw.append(Integer.toString(start)).append(" ");
        for(int i = 1; i <= N; i++) {
            if(canGo(start, i)) {
                dfs(i);
            }
        }
    }

    static void bfs(int start) throws IOException {
        Queue<Integer> q = new ArrayDeque<>();

        q.add(start);

        while(!q.isEmpty()) {
            int cur = q.poll();
            if(visited[cur]){
                continue;
            }
            visited[cur] = true;
            bw.append(Integer.toString(cur)).append(" ");
            for(int i = 1 ; i <= N; i++) {
                if(canGo(cur, i)) {
                    q.add(i);
                }
            }
        }

    }

    static int findFirstIdx(int start) {
        int target = -1;
        for(int i = 1 ; i <= N; i++) {
            if(canGo(start, i)) {
                target = i;
                break;
            }
        }

        return target;
    }

    static boolean canGo(int start, int end) {
        return !visited[end] && arr[start][end] > 0;
    }
}
