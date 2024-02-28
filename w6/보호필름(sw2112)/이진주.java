import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
 
public class Solution {
 
    static int D, W, K;
    static int[][] film;
    static int[] combi;
    static boolean flag;
 
    public static void dfs(int depth, int idx,int size) {
 
        if (depth == size) {
 
            if (check()) {
                flag = true;
            }
 
            return;
        }
 
        for (int i = idx; i < D; i++) {
            combi[i] = 1;
            dfs(depth + 1, i + 1, size);
            combi[i] = 2;
            dfs(depth + 1, i + 1,size);
            combi[i] = 0;
        }
    }
 
    public static int change(int row, int col) {
 
        if (combi[row] == 1)
            return 0;
        else if (combi[row] == 2)
            return 1;
        else
            return film[row][col];
    }
 
    public static boolean check() {
 
        // K개 연속으로 있는지 확인하는 메소드
        for (int i = 0; i < W; i++) {// 한줄씩 확인, col임
 
            int cnt = 1;
 
            int pre = change(0, i);
 
            for (int j = 1; j < D; j++) {// row임
 
                int now = change(j, i);
 
                if (now == pre) {
                    cnt++;
                } else {
                    cnt = 1;
                    pre = now;
                }
 
                if (cnt == K) {
                    break;// 옆줄 확인하러
                }
 
            }
            if (cnt < K)
                return false;
        }
 
        return true;
    }
 
    public static void main(String[] args) throws NumberFormatException, IOException {
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int Tc = Integer.parseInt(br.readLine());
 
        for (int tc = 1; tc <= Tc; tc++) {
            sb.append("#" + tc + " ");
            st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
 
            film = new int[D][W];
            flag = false;
 
            for (int i = 0; i < D; i++) {
 
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++)
                    film[i][j] = Integer.parseInt(st.nextToken());
            }
 
            combi = new int[D];
            for (int i = 0; i < K; i++) {
 
                dfs(0, 0, i);
                if (flag) {
                    sb.append(i + "\n");
                    break;
                }
                if (i == K - 1)
                    sb.append(K + "\n");
            }
 
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
 
}
