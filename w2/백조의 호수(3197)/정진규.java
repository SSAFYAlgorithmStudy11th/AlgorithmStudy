
import java.io.*;
import java.util.*;

public class Main {

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static int r, c;
    static char[][] arr;

    static boolean[][] visit;
    static boolean chk;
    static int[][] swan;
    static Queue<int[]> mainQ ;
    static Queue<int[]> iceQ;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        arr = new char[r][c];
        visit = new boolean[r][c];
        swan = new int[2][2];
        mainQ = new LinkedList<>();
        iceQ = new LinkedList<>();
        String str;

        int sw = 0;
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            str = st.nextToken();
            for (int j = 0; j < c; j++) {
                char c = str.charAt(j);
                arr[i][j] = c;
                if (c == 'L') {
                    swan[sw][0] = i;
                    swan[sw][1] = j;
                    sw++;
                    iceQ.offer(new int[] {i,j});

                } else if(c== '.') {
                    iceQ.offer(new int[] {i,j});
                }

            }
        }

        mainQ.offer(new int[] {swan[0][0],swan[0][1]});
        visit[swan[0][0]][swan[0][1]] = true;

        int day = 0;

        while (true) {

            chk = false;
            bfs();
            if (chk) {
                System.out.println(day);
                return;
            } else {
                day++;
            }

        }


    }
    public static void melt() {
        Queue<int[]> q = new LinkedList<>();

        while(!iceQ.isEmpty()) {
            int[] out = iceQ.poll();
            int x = out[0];
            int y = out[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < r && ny >= 0 && ny < c) {
                    if (arr[nx][ny] == 'X') {
                        arr[nx][ny] = '.';
                        q.offer(new int[]{nx, ny});
                    }
                }
            }
        }
        iceQ = q;
    }
    public static void bfs() {
        Queue<int[]> q = new LinkedList<>();

        while (!mainQ.isEmpty()) {
            int[] out = mainQ.poll();
            int x = out[0];
            int y = out[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < r && ny >= 0 && ny < c && !visit[nx][ny]) {
                    if(nx == swan[1][0] && ny == swan[1][1] ) {
                        chk = true;
                        return;
                    }
                    if (arr[nx][ny] == 'X') {
                        visit[nx][ny] = true;
                        q.offer(new int[]{nx, ny});
                    }
                    else {
                        visit[nx][ny] = true;
                        mainQ.offer(new int[]{nx, ny});
                    }

                }
            }
        }

        if(!chk) {
            melt();
        }

        mainQ = q;
    }
}




