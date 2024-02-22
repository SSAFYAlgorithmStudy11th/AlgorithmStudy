import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution{
    static int N,M,K;
    static Cell[][] map;
    static Queue<Cell> q;
    static Queue<Cell> nextQ;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    static int[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        int test_case = 1;
        while(T --> 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            q = new ArrayDeque<>();

            map = new Cell[350][350];
            visited = new int[350][350];
            for(int i = 150; i < N + 150; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 150; j < M + 150; j++) {
                    int val = Integer.parseInt(st.nextToken());
                    if(val >= 1) {
                        map[i][j] = new Cell(val, j, i);
                        q.add(map[i][j]);
                        visited[i][j] = 1;
                    }
                }
            }

            bfs();
/*
            for(int i = 280; i < 330; i++) {
                for(int j = 280; j < 330; j++) {
                    if(map[i][j] == null) {
                        System.out.print(0 + " ");
                    }else {
                        System.out.print(map[i][j].life + " ");
                    }
                }
                System.out.println();
            }
*/
            int count = 0;
            for(int i = 0; i < 350; i++) {
                for(int j = 0 ; j < 350; j++) {
                    if(map[i][j] != null && map[i][j].time > -map[i][j].life){
                        count++;
                    }
                }
            }
            sb.append('#');
            sb.append(test_case++);
            sb.append(' ');
            sb.append(count);
            sb.append('\n');
        }
        System.out.println(sb);
    }

    static void bfs() {

        int count = 2;
        int time = K;

        while(time --> 0) {
            nextQ = new ArrayDeque<>();
            boolean[][] visited1 = new boolean[350][350];
            while(!q.isEmpty()) {
                Cell cur = q.poll();

                if(visited1[cur.y][cur.x]) {
                    continue;
                }

                //죽었으면 캔슬
                if(cur.time == -cur.life) {
                    continue;
                }

                visited1[cur.y][cur.x] = true;

                //활동 기간이라면
                if( 0 >= cur.time ) {
                    for(int i = 0 ; i < 4; i++) {
                        int curX = cur.x + dx[i];
                        int curY = cur.y + dy[i];

                        if(map[curY][curX] != null && visited[curY][curX] == count && map[curY][curX].life < cur.life) {
                            map[curY][curX].life = cur.life;
                            map[curY][curX].time = cur.life;
                            nextQ.add(map[curY][curX]);
                        }

                        if(visited1[curY][curX]) {
                            continue;
                        }

                        if(map[curY][curX] == null) {
                            map[curY][curX] = new Cell(cur.life, curX, curY);
                            visited[curY][curX] = count;
                            nextQ.add(map[curY][curX]);
                        }

                    }
                //활동기간이 아니라면
                }
                    //활동시간 빼기
                    cur.time--;
                    nextQ.add(cur);

            }
            q = nextQ;
            count++;
        }
    }

    static class Cell {
        int x;
        int y;
        int life;
        int time;

        Cell(int l, int a, int b) {
            life = l; time = l;x = a; y = b;
        }
    }
}
