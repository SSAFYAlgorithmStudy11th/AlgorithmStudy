import java.io.*;
import java.util.*;

public class Main {
    static int[][] map;
    static Node[][] visitedCache;
    static final int ICE = 1;
    static final int WATER = 0;
    static Node[] Ls;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1 , 0, 0};
    static int R, C;
    static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        visitedCache = new Node[R][C];
        Ls = new Node[2];
        int LCount = 0;

        for(int i = 0 ; i < R; i++) {
            String str = br.readLine();
            for(int j = 0; j < C; j++){
                char c = str.charAt(j);
                visitedCache[i][j] = new Node(j,i, 0);
                switch(c){
                    case '.':
                        map[i][j] = WATER;
                        break;
                    case 'L':
                        map[i][j] = WATER;
                        Ls[LCount++] = visitedCache[i][j];
                        break;
                    case 'X':
                        map[i][j] = ICE;
                        break;
                }
            }
        }
        iceWeightBfs(iceBoundaryAdder());
        System.out.println(binarySearch());
    }

    static int binarySearch() {
        int l = 0;
        int r = max;
        while(l <= r) {
            int mid = (l + r) / 2;
            if(canGo(mid)) {
                r = mid - 1;
            }else {
                l = mid + 1;
            }
        }
        return l;
    }

    static boolean canGo(int canGoNum) {
        boolean[][] visited = new boolean[R][C];
        Queue<Node> q = new ArrayDeque<>();
        q.add(Ls[0]);

        while(!q.isEmpty()) {
            Node cur = q.poll();

            if(visited[cur.y][cur.x]) {
                continue;
            }

            visited[cur.y][cur.x] = true;
            for(int i = 0; i < 4; i++) {
                int curX = cur.x + dx[i];
                int curY = cur.y + dy[i];

                if(isCantGo(curX, curY)) {
                    continue;
                }
                if(visitedCache[curY][curX].weight > canGoNum) {
                    continue;
                }
                if(!visited[curY][curX]) {
                    q.add(visitedCache[curY][curX]);
                }

                if(curX == Ls[1].x && curY == Ls[1].y) {
                    return true;
                }
            }
        }
        return false;
    }


    static Queue<Node> iceBoundaryAdder() {
        Queue<Node> ices = new ArrayDeque<>();
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(map[i][j] == ICE) {
                    continue;
                }
                for(int k = 0; k < 4; k++) {
                    int curX = j + dx[k];
                    int curY = i + dy[k];
                    if(isCantGo(curX, curY)){
                        continue;
                    }

                    if(map[curY][curX] == ICE && map[i][j] == WATER) {
                        ices.add(visitedCache[curY][curX]);
                    }
                }
            }
        }
        return ices;
    }

    static void iceWeightBfs(Queue<Node> q) {
        Queue<Node> next = new ArrayDeque<>();
        max = 1;
        while(!q.isEmpty()) {
            Node cur = q.poll();

            if(cur.weight >= 1) {
                if(q.isEmpty()){
                    Queue<Node> temp = q;
                    q = next;
                    next = temp;
                    max++;
                }
                continue;
            }

            cur.weight = max;
            for(int i = 0; i < 4; i++) {
                int curX = cur.x + dx[i];
                int curY = cur.y + dy[i];
                if(isCantGo(curX, curY)) {
                    continue;
                }

                if(visitedCache[curY][curX].weight >= 1) {
                    continue;
                }

                if(visitedCache[curY][curX].weight == 0 && map[curY][curX] == ICE) {
                    next.add(visitedCache[curY][curX]);
                }
            }

            if(q.isEmpty()){
                Queue<Node> temp = q;
                q = next;
                next = temp;
                max++;
            }
        }

    }



    static boolean isCantGo(int x, int y) {
        return isBoundary(x, C) || isBoundary(y, R);
    }

    static boolean isBoundary(int cur, int bound) {
        return 0 > cur || cur >= bound;
    }

    static class Node {
        int x;
        int y;
        int weight;

        Node(int x, int y, int w) {
            this.x = x;
            this.y = y;
            weight = w;
        }

        public String toString() {
            return String.valueOf(weight);
        }
    }

}
