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
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int N;
    static int M;
    static List<int[]> houses;
    static int[][] map;
    static int MAX;
    public static void main(String[] args) throws IOException { 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        int test_case = 1;
        while(T --> 0) {
            int answer = 0;
             
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            houses = new ArrayList<>();
            MAX = Integer.MIN_VALUE;
            for(int i = 0 ; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0 ; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j] == 1) {
                        houses.add(new int[] {j,i});
                    }
                }
            }
             
            boolean[][] visited = new boolean[N][N];
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    findMAX(j,i, visited);
                }
            }
            answer = MAX;
            sb.append('#');
            sb.append(test_case++);
            sb.append(' ');
            sb.append(answer);
            sb.append('\n');
        }
        System.out.println(sb);
    }
     
     
    static void findMAX(int x, int y, boolean[][] visited) {
        for(int i = 0; i < N; i++) {
            Arrays.fill(visited[i], false);
        }
         
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {x,y});
        int homeCount = 0;
        visited[y][x] = true;
        int count = 1;
        while(count != N+2) {
            int size = q.size();
            while(size --> 0) {
                int[] cur = q.poll();
                int cx = cur[0];
                int cy = cur[1];
                if(map[cy][cx] == 1) {
                    homeCount++;
                }
                for(int i = 0; i < 4; i++) {
                    int curX = cx + dx[i];
                    int curY = cy + dy[i];
                    if(!isBoundary(curX, curY)) {
                        continue;
                    }
                     
                    if(!visited[curY][curX]) {
                        q.add(new int[] {curX,curY});
                        visited[curY][curX] = true;
                    }
                }
            }
            if(count * count + (count - 1 ) * (count - 1) <= homeCount * M) {
                MAX = Math.max(MAX, homeCount);
            }
            count++;
        }
    }
 
     
    static boolean isBoundary(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}
