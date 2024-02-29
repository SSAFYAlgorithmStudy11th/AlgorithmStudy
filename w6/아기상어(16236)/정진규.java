import java.io.*;
import java.util.*;


public class Main {

    static int n;
    static int[][] arr;
    static boolean[][] visit;
    static Queue<int[]> q;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static int answer;
    static int sx, sy;
    static int count,size,time;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        q = new LinkedList<>();
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        visit = new boolean[n][n];
        int tmp;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                tmp = Integer.parseInt(st.nextToken());
                arr[i][j] = tmp;
                if (tmp == 9) {
                    sx = i;
                    sy = j;
                    q.offer(new int[]{sx, sy, 0});
                    visit[i][j] = true;
                }
            }
        }
        size = 2;
        count = 0;
        time = 0;
        // bfs

        while (bfs()){

        }
        System.out.println(time);
    }


    public static boolean bfs() {
        Queue<int[]> fish = new LinkedList<>();

        while (!q.isEmpty()) {
            int[] out = q.poll();
            int x = out[0];
            int y = out[1];
            int t = out[2];


            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n && !visit[nx][ny] && arr[nx][ny] <= size) {
                    if (arr[nx][ny] > 0 && arr[nx][ny] < size) {
                        fish.add(new int[]{nx, ny, t + 1});
                    } else {
                        visit[nx][ny] = true;
                        q.offer(new int[]{nx, ny, t + 1});
                    }

                }
            }
        }

        if (fish.isEmpty())
            return false;
        else {
            int nx = Integer.MAX_VALUE;
            int ny = Integer.MAX_VALUE;
            int dist = Integer.MAX_VALUE;
            while (!fish.isEmpty()) {
                int[] out = fish.poll();
                if (out[2] <= dist) {
                    if (nx > out[0]) {
                        nx = out[0];
                        ny = out[1];
                        dist = out[2];
                    }
                    if (nx == out[0]) {
                        if (ny > out[1]) {
                            nx = out[0];
                            ny = out[1];
                            dist = out[2];
                        }
                    }
                }

            }
            count++;
            if (count == size) {
                size++;
                count = 0;
            }
            arr[sx][sy] = 0;
            sx = nx;
            sy = ny;
            arr[nx][ny] = 9;
            q = new LinkedList<>();
            time = dist;
            q.offer(new int[]{nx, ny, time});
            visit = new boolean[n][n];
            visit[nx][ny] = true;
        }

        return true;
    }
}
