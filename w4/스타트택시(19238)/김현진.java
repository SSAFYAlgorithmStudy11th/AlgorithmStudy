import java.util.*;
import java.io.*;

public class Main {
    static int N, M, K;
    static int[][] map;
    static int curX;
    static int curY;
    static List<int[]> passenger;
    static List<int[]> destination;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        passenger = new ArrayList<>();
        destination = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        curY = Integer.parseInt(st.nextToken()) - 1;
        curX = Integer.parseInt(st.nextToken()) - 1;

        for(int i = 0; i < M ; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int dY = Integer.parseInt(st.nextToken()) - 1;
            int dX = Integer.parseInt(st.nextToken()) - 1;
            passenger.add(new int[] {x, y});
            destination.add(new int[] {dX, dY});
        }

        while(!passenger.isEmpty()) {
            int[] cur = findPassenger();
            if(cur != null && cur[1] <= K) {
                //System.out.println(K + " " + cur[1]);
                K -= cur[1];
                passenger.remove(cur[0]);
            }else {
                //System.out.println(K + " " + cur[1]);
                System.out.println(-1);
                System.exit(0);
            }

            int fromToFuel = findDestination(cur[0]);
            if(fromToFuel != -1 && fromToFuel <= K) {
                K -= fromToFuel;
                K += fromToFuel * 2;
                destination.remove(cur[0]);
            }else {
                System.out.println(-1);
                System.exit(0);
            }
        }
        System.out.println(K);
    }


    static int[] findPassenger() {
        PriorityQueue<int[]> q = new PriorityQueue<>((i1, i2) -> {
            if(i1[2] != i2[2]) {
                return i1[2] - i2[2];
            }

            if(i1[1] != i2[1]) {
                return i1[1] - i2[1];
            }

            return i1[0] - i2[0];

        });
//        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];
        q.add(new int[]{curX, curY, 0});

        int[] copy = new int[2];
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            curX = cur[0];
            curY = cur[1];

            copy[0] = cur[0];
            copy[1] = cur[1];
            for(int i = 0; i < passenger.size(); i++) {
                if(Arrays.equals(passenger.get(i), copy)) {

                    return new int[]{i, cur[2]};
                }
            }

            if(visited[curY][curX]) {
                continue;
            }

            visited[curY][curX] = true;

            for(int i = 0; i < 4; i++) {
                int dx = Main.dx[i] + curX;
                int dy = Main.dy[i] + curY;
                if(!isBoundary(dx, dy)) {
                    continue;
                }

                if(map[dy][dx] == 0) {
                    q.add(new int[]{dx, dy, cur[2] + 1});
                }
            }
        }
        return null;
    }

    static int findDestination(int idx) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{curX, curY, 0});
        boolean[][] visited = new boolean[N][N];

        int[] copy = new int[2];
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            curX = cur[0];
            curY = cur[1];

            copy[0] = curX;
            copy[1] = curY;

            if(Arrays.equals(destination.get(idx), copy)) {
                return cur[2];
            }

            if(visited[curY][curX]) {
                continue;
            }

            visited[curY][curX] = true;

            for(int i = 0; i < 4; i++) {
                int dx = Main.dx[i] + curX;
                int dy = Main.dy[i] + curY;
                if(!isBoundary(dx, dy)) {
                    continue;
                }

                if(map[dy][dx] == 0) {
                    q.add(new int[]{dx, dy, cur[2] + 1});
                }
            }
        }

        return -1;
    }

    static boolean isBoundary(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}
