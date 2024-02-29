import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
 
 
public class Solution {
    static int N, K;
    static int[][] arr;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};
    static int MAX = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
         
        int test_case = 1;
        while(T --> 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            arr = new int[N][N];
            MAX = Integer.MIN_VALUE;
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0 ; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            List<int[]> maxList = getMaxList();
            boolean[][] visited = new boolean[N][N];
            for(int[] cur : maxList) {
                visited[cur[1]][cur[0]] = true;
                dfs(cur[0], cur[1], 1, 0, visited);
                visited[cur[1]][cur[0]] = false;
            }
 
            sb.append('#');
            sb.append(test_case++);
            sb.append(' ');
            sb.append(MAX);
            sb.append('\n');
        }
        System.out.println(sb);
    }
     
     
    static List<int[]> getMaxList() {
        List<int[]> list = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(list.isEmpty()) {
                    list.add(new int[] {j,i});
                    continue;
                }
                int[] cur = list.get(0);
                if(arr[cur[1]][cur[0]] < arr[i][j]) {
                    list.clear();
                    list.add(new int[] {j, i});
                }
                else if(arr[cur[1]][cur[0]] == arr[i][j]) {
                    list.add(new int[] {j, i});
                }
            }
        }
        return list;
    }
 
    static void dfs(int x, int y, int depth, int cnt, boolean[][] visited) {
        MAX = Math.max(depth, MAX);
        if(MAX == depth) {
             
        }
         
        for(int i = 0; i < 4; i++) {
            int curX = x + dx[i];
            int curY = y + dy[i];
            if(!isBoundary(curX, curY)) {
                continue;
            }
            if(visited[curY][curX]) {
                continue;
            }
            if(arr[curY][curX] < arr[y][x]) {
                visited[curY][curX] = true;
                dfs(curX, curY, depth+1, cnt, visited);
                visited[curY][curX] = false;
            }
            if(arr[curY][curX] >= arr[y][x] && cnt == 0) {
                for(int j = 1; j <= K; j++) {
                    if(arr[curY][curX] < j) {
                        break;
                    }
                    arr[curY][curX] -= j;
                    if(arr[curY][curX] < arr[y][x]) {
                        visited[curY][curX] = true;
                        dfs(curX, curY, depth+1, cnt+1, visited);
                        visited[curY][curX] = false;
                    }
                    arr[curY][curX] += j;
                }
            }
        }
    }
    static boolean isBoundary(int x, int y) {
        return 0 <= x && x < N && 0 <= y & y < N;
    }
}
