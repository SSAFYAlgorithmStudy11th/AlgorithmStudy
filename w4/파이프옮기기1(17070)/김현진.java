import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[][] map;
    static int count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        backtracking(1, 0, 0);
        System.out.println(count);
    }


    static void backtracking(int x, int y, int radio) {
        if(x == N - 1 && y == N - 1) {
            count++;
            return;
        }


        if(y < N && x+1 < N && map[y][x+1] == 0 && (radio == 0 || radio == 2)) {
            backtracking(x+1, y, 0);
        }

        if(y+1 < N && x < N && map[y+1][x] == 0 && (radio == 1 || radio == 2)) {
            backtracking(x, y+1, 1);
        }

        if(y+1 < N && x+1 < N && map[y+1][x+1] == 0 && map[y+1][x] == 0 && map[y][x+1] == 0) {
            backtracking(x+1, y+1, 2);
        }
    }
}
