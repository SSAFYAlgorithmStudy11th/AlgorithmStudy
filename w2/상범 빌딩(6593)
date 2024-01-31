import java.io.*;
import java.util.*;
public class Main {
    static int L, R, C;
    static int[][][] visited;
    static int[][][] map;
    static int[] dx = {0, 0, -1, 1, 0, 0};
    static int[] dy = {-1,1, 0, 0, 0, 0};
    static int[] dz = {0, 0, 0, 0, -1, 1};
    static int[] end;
    static int[] start;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        while(L != 0 || R != 0 || C != 0) {
            visited = new int[L][R][C];
            map = new int[L][R][C];
            for(int i = L-1; i >= 0; i--) {
                for(int j = 0; j < R; j++) {
                    String str = br.readLine();
                    for(int k = 0; k < C; k++) {
                        switch(str.charAt(k)) {
                            case 'S':
                                start = new int[]{i,j,k};
                                break;
                            case '#':
                                map[i][j][k] = 1;
                                break;
                            case 'E':
                                map[i][j][k] = 2;
                                end = new int[]{i,j,k};
                        }
                    }
                }
                br.readLine();
            }
            bfs();
            if(visited[end[0]][end[1]][end[2]] != 0) {
                bw.append(String.format("Escaped in %d minute(s).", visited[end[0]][end[1]][end[2]]));
                bw.newLine();
            }else {
                bw.append("Trapped!");
                bw.newLine();
            }

            st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
        }
        bw.flush();

    }

    static boolean bfs() {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(start[2],start[1],start[0]));

        while(!q.isEmpty()){
            Node cur = q.poll();

            for(int i = 0; i < 6; i++) {
                int curX = cur.x + dx[i];
                int curY = cur.y + dy[i];
                int curZ = cur.z + dz[i];

                if(isCantGo(curX, curY, curZ) || map[curZ][curY][curX] == 1) {
                    continue;
                }

                if(visited[curZ][curY][curX] == 0) {

                    q.add(new Node(curX, curY, curZ));
                    visited[curZ][curY][curX] = visited[cur.z][cur.y][cur.x] + 1;
                }
            }
        }

        return false;
    }



    static class Node {
        int x;
        int y;
        int z;

        public Node(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    static boolean isCantGo(int x, int y, int z) {
        return isNotBoundary(x, C) || isNotBoundary(y, R) || isNotBoundary(z, L);
    }

    static boolean isNotBoundary(int cur, int bound) {
        return 0 > cur || cur >= bound ;
    }
}
