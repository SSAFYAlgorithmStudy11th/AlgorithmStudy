import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, Q;
    static int[][] arr;
    static int size;
    static int[] dx = {0,0, -1, 1};
    static int[] dy = {1,-1,0,0};
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        size = (int)Math.pow(2, N);
        arr = new int[size][size];
        visited = new boolean[size][size];
        for(int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < size; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < Q; i++) {
            int val = Integer.parseInt(st.nextToken());
            if(val != 0) {
                rotate(0,0,size, (int) Math.pow(2, val));
            }
            melt();

        }

        int sum = allSum();
        int max = 0;
        for(int i = 0; i < size; i++) {
            for(int j = 0 ; j < size; j++) {
                if(!visited[i][j] && arr[i][j] != 0) {
                    max = Math.max(max, maxSize(j,i));
                }
            }
        }
        System.out.println(sum +"\n" +max);
    }


    static void rotate(int x, int y, int distance, int size) {
        if(distance == size) {
            //회전하는 메서드
            int[][] temp = new int[distance][distance];
            for(int i = y; i < y+distance; i++) {
                for(int j = x; j < x+distance; j++) {
                    temp[i-y][j-x] = arr[i][j];
                }
            }

            for(int i = 0; i < distance; i++) {
                for(int j = 0; j < distance; j++) {
                    arr[i+y][j+x] = temp[distance-j-1][i];
                }
            }
            return;
        }

        int next = distance / 2;
        rotate(x,y,next, size);
        rotate(x+next,y,next, size);
        rotate(x,y+next,next, size);
        rotate(x+next,y+next,next, size);
    }

    static void melt() {
        List<int[]> list = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                int count = 0;
                if(arr[i][j] == 0) {
                    continue;
                }
                for(int d = 0; d < 4; d++) {
                    int curX = j + dx[d];
                    int curY = i + dy[d];
                    if(!isBoundary(curX, curY)) {
                        continue;
                    }

                    if(arr[curY][curX] != 0) {
                        count++;
                    }
                }

                if(count < 3) {
                    list.add(new int[]{j,i});
                }
            }
        }

        for(int[] cur : list) {
            int x = cur[0];
            int y = cur[1];
            arr[y][x] --;
        }
    }

    static int allSum() {
        int sum = 0;
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                sum += arr[i][j];
            }
        }
        return sum;
    }

    static int maxSize(int x, int y) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{x,y});

        int count = 0;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int cx = cur[0];
            int cy = cur[1];

            if(visited[cy][cx]) {
                continue;
            }

            visited[cy][cx] = true;
            count++;

            for(int i = 0; i < 4; i++) {
                int curX = cx + dx[i];
                int curY = cy + dy[i];
                if(!isBoundary(curX, curY)) {
                    continue;
                }

                if(!visited[curY][curX] && arr[curY][curX] != 0) {
                    q.add(new int[]{curX, curY});
                }
            }
        }

        return count;
    }


    static boolean isBoundary(int x, int y) {
        return 0 <= x && x < size && 0 <= y && y < size;
    }
}
