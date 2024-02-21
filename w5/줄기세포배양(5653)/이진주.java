import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
 
public class Solution {
 
    static class Cell implements Comparable<Cell> {
 
        int row;
        int col;
        int life;
        int start;// 활성화시간
        int end;// 죽는 시간
        int status;
 
        public Cell(int row, int col, int life, int start, int end, int status) {
            super();
            this.row = row;
            this.col = col;
            this.life = life;
            this.start = start;
            this.end = end;
            this.status = status;
        }
 
        @Override
        public int compareTo(Cell o) {
 
            if (this.status == ACTIVE && o.status == ACTIVE) {// 활성큐
 
                // 시간 오름차순
                return this.end - o.end;
 
            } else {// 비활성화큐
 
                // 생명력 내림차순
                return -(this.life - o.life);
            }
        }
    }
 
    static int[][] map;
    static boolean[][] visited;
 
    static int N, M, K;
 
    static PriorityQueue<Cell> inactive;
    static PriorityQueue<Cell> active;
    static PriorityQueue<Cell> tmp;
 
    static final int ACTIVE = 1, INACTIVE = 2, DEAD = 0;
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };
 
    public static void main(String[] args) throws NumberFormatException, IOException {
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
 
        int Tc = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= Tc; tc++) {
 
            sb.append("#" + tc + " ");
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());// 배양시간
 
            visited = new boolean[2 * K + N][2 * K + M];
            inactive = new PriorityQueue<>();
            active = new PriorityQueue<>();
            tmp = new PriorityQueue<>();
 
            // 세포 입력
            for (int i = 0; i < N; i++) {
 
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    int l = Integer.parseInt(st.nextToken());
                    if (l != 0) {
 
                        visited[K + i][K + j] = true;
                        inactive.offer(new Cell(K + i, K + j, l, l, 2 * l, INACTIVE));
                    }
                }
            }
 
            // 배양
            for (int k = 1; k <= K; k++) {
 
                int n = inactive.size();
 
                while (true) {
 
                    if (n == 0)
                        break;
 
                    Cell cell = inactive.poll();
 
                    if (cell.start + 1 == k) {// 활성화 Time
 
                        for (int d = 0; d < 4; d++) {
                            int r = cell.row + dr[d];
                            int c = cell.col + dc[d];
 
                            if (r < 0 || r >= 2 * K + N || c < 0 || c >= 2 * K + M)
                                continue;
 
                            if (!visited[r][c]) {// 새로운 세포
                                tmp.offer(new Cell(r, c, cell.life, k + cell.life, (k + 2 * cell.life), INACTIVE));
                                visited[r][c] = true;
                            }
                        }
 
                        cell.status = ACTIVE;
                        active.offer(cell);
                    }
 
                    else {// inactive하니까 다시 넣기
                        tmp.offer(cell);
                    }
 
                    n--;
                }
 
                while (!tmp.isEmpty()) {
                    inactive.offer(tmp.poll());
                }
 
                while (!active.isEmpty() && active.peek().end == k) {
                    active.poll();
                }
            }
 
            sb.append(inactive.size() + active.size()).append("\n");
 
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
 
}
