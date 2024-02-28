import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class Solution {
 
    static class Node {
        int row;
        int col;
 
        public Node(int row, int col) {
            super();
            this.row = row;
            this.col = col;
        }
    }
 
    public static int getCost(int K) {
 
        return (K * K) + (K - 1) * (K - 1);
    }
 
    public static void bfs(int row, int col) {
 
        queue.offer(new Node(row, col));
        visited[row][col] = true;
 
        int K = 1;
        int cnt = map[row][col] == 1 ? 1 : 0;
 
        if (getCost(K) <= cnt * M)
            max = Math.max(max, K);
         
        while (!queue.isEmpty()) {
 
            int size = queue.size();
            K++;
 
            for (int i = 0; i < size; i++) {
 
                Node now = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int nr = now.row + dr[d];
                    int nc = now.col + dc[d];
 
                    if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc])
                        continue;
 
                    if (map[nr][nc] == 1)
                        cnt++;
 
                    queue.offer(new Node(nr, nc));
                    visited[nr][nc] = true;
                }
            }
            // 비용계산
            if (getCost(K) <= cnt * M)
                max = Math.max(max, cnt);
        }
    }
 
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = { -1, 0, 1, 0 };
    static int[] dc = { 0, 1, 0, -1 };
    static int max;
    static Queue<Node> queue;
 
    public static void main(String[] args) throws NumberFormatException, IOException {
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
 
        int Tc = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= Tc; tc++) {
 
            sb.append("#" + tc + " ");
 
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());// 하나의 집이 지불할 수 있는 비용
 
            map = new int[N][N];
            max = Integer.MIN_VALUE;
 
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
 
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
 
                    visited = new boolean[N][N];
                    queue = new ArrayDeque<>();
                    bfs(i, j);
                }
            }
            sb.append(max + "\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
 
}
