import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int stoi(String s) {
        return Integer.parseInt(s);
    }
    static int w, h;
    static int[][] map;
    static boolean[][] visit;

    static int[] dx = {0, 0, -1, 1, 1, -1, -1, 1}; //상,하,좌,우, 우상, 좌상, 좌하, 우하
    static int[] dy = {1, -1, 0, 0, 1, 1, -1, -1};

    public static void main(String[] args) throws IOException {
        StringTokenizer st;
        do {
            st = new StringTokenizer(br.readLine());
            w = stoi(st.nextToken());
            h = stoi(st.nextToken());

            if(w == 0 && h == 0) {
                break;
            }

            setMap();
            findIsland();

        } while (w != 0 && h != 0);
    }

    static void setMap() throws IOException{
        map = new int[h][w];
        visit = new boolean[h][w];
        StringTokenizer st;
        for(int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < w; j++) {
                map[i][j] = stoi(st.nextToken());
            }
        }
    }

    static void findIsland(){
        int count = 0;
        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                if(map[i][j] == 1 && !visit[i][j]) {
                    DFS(i, j);
                    count++;
                }
            }
        }

        System.out.println(count);
    }

    static void soutMap() {
        for(int i = 0; i < h; i++) {
            for(int j = 0 ; j < w; j++) {
                System.out.print(!visit[i][j]? '0' : '1');
            }
            System.out.println();
        }
        System.out.println();
    }

    static void DFS(int y, int x) {
        visit[y][x] = true;
        for(int i = 0; i < 8; i++) {
            int dX = x + dx[i];
            int dY = y + dy[i];
            if(dX < 0 || dX >= w || dY < 0 || dY >= h) {
                continue;
            }
            if(map[dY][dX] == 1 && !visit[dY][dX]) {
                DFS(dY, dX);
            }
        }
    }
}
