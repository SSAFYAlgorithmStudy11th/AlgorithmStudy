import java.io.*;
import java.util.*;
public class Main {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int MIN = Integer.MAX_VALUE;
    static int MAX = Integer.MIN_VALUE;
    static int[] dx = {0,0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        StringTokenizer st;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                MIN = Math.min(MIN, map[i][j]);
                MAX = Math.max(MAX, map[i][j]);
            }
        }
        System.out.println(binarySearch());
    }

    static int binarySearch() {
        int l = 0;
        int r = MAX - MIN;
        while(l <= r) {
            int mid = (r + l)/2;
            boolean flag = false;
            for(int i = MIN ; mid + i <= MAX; i++) { //i = 최솟값, mid = 가중치
            	if(flag = bfs(mid, i)) {
            		break;
            	}
            }
            if(flag) {
            	r = mid - 1;
            }else {
            	l = mid + 1;
            }
        }
        return l;
    }
    
    
    static boolean bfs(int weight, int min) {
        Queue<Node> q = new ArrayDeque<>();
        if(min <= map[0][0] && map[0][0] <= min + weight) {
            visited = new boolean[N][N];
        	q.add(new Node(0, 0));

        }
        while(!q.isEmpty()) {
            Node cur = q.poll();

            if(cur.y == N - 1 && cur.x == N - 1) {
            	return true;
            }
            
            if(visited[cur.y][cur.x]) {
            	continue;
            }
           
            visited[cur.y][cur.x] = true;
            
            for(int i = 0; i < 4; i++) {
                int curX = cur.x + dx[i];
                int curY = cur.y + dy[i];

                if (isNotBoundary(curX) || isNotBoundary(curY)) {
                    continue;
                }
                if(!visited[curY][curX] && min <= map[curY][curX] && map[curY][curX] <= min + weight) {
                	q.add(new Node(curX, curY));
                }
            }
        }
        return false;
    }

    static boolean isNotBoundary(int x) {
        return 0 > x || x >= N;
    }

    static class Node {
        int x;
        int y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
