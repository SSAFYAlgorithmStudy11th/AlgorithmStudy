import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static boolean[][] map;
    static int N, M;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int[][][] visited;
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new boolean[N][M];
        visited = new int[2][N][M];

        String str;
        for(int i = 0 ; i < N; i++) {
            str = br.readLine();
            for(int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) == '1' ? false : true;
            }
        }
        bfs();
//        for(int i = 0 ; i < 2; i++){
//            for(int j = 0 ; j < N; j++) {
//                System.out.println(Arrays.toString(visited[i][j]));
//            }
//            System.out.println();
//        }
        if(visited[0][N-1][M-1] == 0) {
            if(visited[1][N-1][M-1] != 0){
                System.out.println(visited[1][N-1][M-1]);
            }else{
                System.out.println(-1);
            }
        }else{
            if(visited[1][N-1][M-1] != 0) {
                System.out.println(Math.min(visited[1][N-1][M-1], visited[0][N-1][M-1]));
            }else {
                System.out.println(visited[0][N-1][M-1]);
            }
        }

    }



    static void bfs() {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(0,0,1,false));

        int curX;
        int curY;
        Node cur;
        while(!q.isEmpty()) {
            cur = q.poll();

            if(visited[cur.isBroken ? 1 : 0][cur.y][cur.x] > 0) {
                continue;
            }
            visited[cur.isBroken ? 1 : 0][cur.y][cur.x] = cur.weight;

            for(int i = 0 ; i < 4; i++) {
                curX = cur.x + dx[i];
                curY = cur.y + dy[i];

                if(!isBoundary(curX, curY)) {
                    continue;
                }

                if(visited[cur.isBroken ? 1 : 0][curY][curX] > 0) {
                    continue;
                }

                if(cur.isBroken && !map[curY][curX]) {
                    continue;
                }

                if(!map[curY][curX] && !cur.isBroken) {
                    q.add(new Node(curX, curY, cur.weight+1, true));
                    continue;
                }

                q.add(new Node(curX, curY, cur.weight+1, cur.isBroken));
            }
        }
    }

    static boolean isBoundary(int x, int y) {
        return 0 <= x && x < M && 0 <= y && y < N;
    }


    static class Node {
        int x;
        int y;
        int weight;
        boolean isBroken;

        Node(int x, int y, int w, boolean b) {
            this.x = x;
            this.y = y;
            weight = w;
            isBroken = b;
        }
    }
}
