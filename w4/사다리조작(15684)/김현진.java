import java.util.*;
import java.io.*;

public class Main {
    static int N, M, H;
    static boolean[][][] ladder;
    static int min = 4;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        ladder = new boolean[H][N][N];

        for(int  i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken()) - 1;
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = from + 1;


            ladder[h][to][from] = true;
            ladder[h][from][to] = true;
        }
        backtracking(0, 0);
        System.out.println(min != 4 ? min : -1);
    }


    static void backtracking(int cnt, int y) {
        if(cnt>= min) {
            return;
        }

        boolean flag = true;

        for(int i = 0; i < N; i++) {
            if(i != findEnd(i)) {
                flag = false;
                break;
            }
        }

        if(flag) {
            min = Integer.min(cnt, min);
            return;
        }

        for(int i = y; i < H; i++) {
            for(int j = 0; j < N-1; j++) {
                if(ladder[i][j][j+1]) { // 이미 값이 있다면 패스
                    continue;
                }
                if(j >= 1 && ladder[i][j][j-1]) { //만약 이전 위치에 값이 있다면 패스
                    continue;
                }

                if(j+2 < N && ladder[i][j+1][j+2]) { // 만약 다음 칸에 값이 있다면 패스
                    continue;
                }

                ladder[i][j][j+1] = true;
                ladder[i][j+1][j] = true;
                backtracking(cnt+1, i);
                ladder[i][j][j+1] = false;
                ladder[i][j+1][j] = false;
            }
        }
    }

    static int findEnd(int start) {
        for(int i = 0; i < H; i++) {
            if(start +1 < N && ladder[i][start][start+1]) {
                start+=1;
            } else if(start -1 >= 0 && ladder[i][start-1][start]) {
                start-=1;
            }
        }
        return start;
    }
}
