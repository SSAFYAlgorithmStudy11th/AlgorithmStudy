import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Main {
    static boolean[] visited;
    static int[][] map;
    static int N;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        visited = new boolean[N];
        StringTokenizer st;
        for(int i = 0; i < N; i++) {
            String[] str = br.readLine().split(" ");
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(str[j]);
            }
        }

        List<Integer> list = new ArrayList<>();
        for(int i = 0 ; i < N; i++) {
            backtracking(i, i, list);
        }
        System.out.println(min);
        System.gc();
    }

    static void backtracking(int start, int now, List<Integer> list) {
        if(list.size() == N - 1 && map[now][start] != 0) {
            int sum = 0;
            for(int i = 0 ; i < list.size(); i++) {
                sum += list.get(i);
            }
            sum += map[now][start];
            min = Math.min(sum, min);
            return;
        }

        visited[now] = true;
        for(int i = 0; i < N; i++) {
            if(!visited[i] && map[now][i] != 0) {
                list.add(map[now][i]);
                backtracking(start, i, list);
                list.remove(list.size() -1);
            }
        }
        visited[now] = false;
    }
}
