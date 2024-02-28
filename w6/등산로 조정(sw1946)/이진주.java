import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
 
public class Solution {
 
    static int N, K;
    static int[][] map;
    static boolean[][] visited;
    static int maxH;
    static int max;
    static int[] dr = { -1, 0, 1, 0 };
    static int[] dc = { 0, 1, 0, -1 };
 
    public static void dfs(int row, int col, int h, int cut, int len) {
 
        // max값
        max = Math.max(max, len);
 
        for (int d = 0; d < 4; d++) {
 
            int nr = row + dr[d];
            int nc = col + dc[d];
 
            if (nr < 0 || nr >= N || nc < 0 || nc >= N)
                continue;
 
            if (visited[nr][nc])
                continue;
 
            if (map[nr][nc] >= h) {// 현재 높이보다 높다 -> 줄이자
 
                if (cut > 0 && h > (map[nr][nc] - K)) {// 깎았을때 현재보다 낮으면
 
                    visited[nr][nc] = true;
                    dfs(nr, nc, h - 1, 0, len + 1);
                    visited[nr][nc] = false;
                }
 
            } else {// 그냥 가면 됨
 
                visited[nr][nc] = true;
                dfs(nr, nc, map[nr][nc], cut, len + 1);
                visited[nr][nc] = false;
            }
        }
    }
 
    public static void main(String[] args) throws NumberFormatException, IOException {
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
 
        int Tc = Integer.parseInt(br.readLine());
 
        for (int tc = 1; tc <= Tc; tc++) {
 
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
 
            map = new int[N][N];
            visited = new boolean[N][N];
            maxH = Integer.MIN_VALUE;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (maxH < map[i][j])
                        maxH = map[i][j];
                }
            }
            // 입력끝
            max = Integer.MIN_VALUE;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
 
                    if (map[i][j] != maxH)
                        continue;
 
                    visited[i][j] = true;
                    dfs(i, j, maxH, 1, 1);
 
                    visited[i][j] = false;
                }
            }
 
            sb.append("#" + tc + " " + max + "\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
 
}
