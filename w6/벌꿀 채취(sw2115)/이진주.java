import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
 
public class Solution {
 
    static int N, M, C;
    static int[][] map;
    static boolean visited;
    static int[] selected;
    static int ans;
 
    public static void dfs(int idx, int depth) {
 
        if (depth == 2) {// A,B각자 하나씩 고르고 뒤에 나머지 선택
 
            if (selected[0] < selected[1] && selected[0] + M > selected[1])// 겹침
                return;
            // 같은 행에 있는가
            if (selected[0] / N != (selected[0] + M - 1) / N)
                return;
            if (selected[1] / N != (selected[1] + M - 1) / N)
                return;
 
            ans = Math.max(ans, getHoney(selected[0], selected[1]));
            return;
        }
        for (int i = idx; i < N * N; i++) {
            selected[depth] = i;
            dfs(i + 1, depth + 1);
        }
    }
 
    public static int getHoney(int x, int y) {
 
        int s1m = 0, s2m = 0;
 
        int n = (1 << M);// 2개면 11->3
        for (int i = 1; i < n; i++) {
            int s1 = 0;
            int cnt = 0;
            for (int j = 0; j < M; j++) {
 
                if ((i & (1 << j)) != 0) {
                     
                    cnt += map[selected[0] / N][selected[0] % N + j];
                    s1 += (int) Math.pow(map[selected[0] / N][selected[0] % N + j], 2);
                }
            }
            if (cnt <= C)
                s1m = Math.max(s1m, s1);
        }
 
        for (int i = 1; i < n; i++) {
            int s2 = 0;
            int cnt = 0;
            for (int j = 0; j < M; j++) {
 
                if ((i & (1 << j)) != 0) {
                    cnt += map[selected[1] / N][selected[1] % N + j];
                    s2 += (int) Math.pow(map[selected[1] / N][selected[1] % N + j], 2);
                }
            }
            if (cnt <= C)
                s2m = Math.max(s2m, s2);
        }
 
        return s1m + s2m;
    }
 
    public static void main(String[] args) throws NumberFormatException, IOException {
 
//      System.setIn(new FileInputStream("sample_input.txt"));
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
 
        int Tc = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= Tc; tc++) {
 
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
 
            map = new int[N][N];
            selected = new int[2];
 
            ans = Integer.MIN_VALUE;
 
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            dfs(0, 0);
            sb.append("#" + tc + " " + ans + "\n");
        }
 
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
