package D0129;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class maze_search {
    static int N;
    static int M;
    static int[][] maze;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        maze = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            char[] mazeLine = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                maze[i][j] = mazeLine[j] - '0';
            }
        }

        int answer = bfs(0, 0);
        bw.write(Integer.toString(answer));
        bw.close();
    }

    static int bfs(int startX, int startY) {
        Queue<int[]> queue = new LinkedList<>();
        //큐에 x좌표, y좌표, 길이 정보를 담은 int배열 추가
        queue.offer(new int[]{startX, startY, 1}); 
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int len = current[2];

            if (x == N - 1 && y == M - 1) {
                return len;
            }

            if (!visited[x][y]) {
                visited[x][y] = true;

                // 아래
                if (x < N - 1 && maze[x + 1][y] == 1) {
                    queue.offer(new int[]{x + 1, y, len + 1});
                }

                // 오른쪽
                if (y < M - 1 && maze[x][y + 1] == 1) {
                    queue.offer(new int[]{x, y + 1, len + 1});
                }

                // 위
                if (x > 0 && maze[x - 1][y] == 1) {
                    queue.offer(new int[]{x - 1, y, len + 1});
                }

                // 왼쪽
                if (y > 0 && maze[x][y - 1] == 1) {
                    queue.offer(new int[]{x, y - 1, len + 1});
                }
            }
        }
        return -1;
    }
}

