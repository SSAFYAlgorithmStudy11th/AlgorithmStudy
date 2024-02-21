import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
 
class Cluster implements Comparable<Cluster> {
    int row;
    int col;
    int cnt;
    int dir;
 
    public Cluster(int row, int col, int cnt, int dir) {
        super();
        this.row = row;
        this.col = col;
        this.cnt = cnt;
        this.dir = dir;
    }
 
    @Override
    public int compareTo(Cluster o) {
 
        // 개수 내림차순
        return -(this.cnt - o.cnt);
    }
 
    @Override
    public String toString() {
        return "Cluster [row=" + row + ", col=" + col + ", cnt=" + cnt + ", dir=" + dir + "]";
    }
 
}
 
public class Solution {
 
    static int N, M, K;
    static PriorityQueue<Cluster> clusters;
    static int[][] map;
    static int[] dr = { -1, 0, 0, 1 };// 상우좌하
    static int[] dc = { 0, 1, -1, 0 };
 
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
 
        int Tc = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= Tc; tc++) {
 
            sb.append("#" + tc + " ");
 
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());// N*N행렬
            M = Integer.parseInt(st.nextToken());// 격리시간
            K = Integer.parseInt(st.nextToken());// 군집의 개수
 
            clusters = new PriorityQueue<>();
 
            // 미생물 저장
            for (int i = 0; i < K; i++) {
 
                st = new StringTokenizer(br.readLine());
                int row = Integer.parseInt(st.nextToken());
                int col = Integer.parseInt(st.nextToken());
                int cnt = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());
 
                // dr은 상우좌하 순으로 저장되어 있음
                switch (dir) {
                case 1:// 상
                    dir = 0;
                    break;
                case 2:// 하
                    dir = 3;
                    break;
                case 3:// 좌
                    dir = 2;
                    break;
                case 4:// 우
                    dir = 1;
                    break;
                }
 
                clusters.offer(new Cluster(row, col, cnt, dir));
            }
 
            // 시간
            for (int t = 0; t < M; t++) {
 
                map = new int[N][N];
 
                // 미생물 수가 많은 것 부터 나옴 내림차순
                Queue<Cluster> tmp = new ArrayDeque<>();
 
                // 군집 각자 이동
                while (!clusters.isEmpty()) {
 
                    Cluster cs = clusters.poll();
                    cs.row += dr[cs.dir];
                    cs.col += dc[cs.dir];
 
                    if (cs.row == 0 || cs.row == N - 1 || cs.col == 0 || cs.col == N - 1) {// 약품
 
                        cs.cnt /= 2;// 절반
                        cs.dir = 3 - cs.dir;// 반대 방향으로
                        if (cs.cnt == 0)// 없어지면 그냥 삭제
                            continue;
                    }
 
                    if (map[cs.row][cs.col] > 0) {// 겹칠 때
 
                        // 미생물 수가 많은 것부터 저장되었기 때문에 방향은 그대로 유지됨
                        cs.cnt += map[cs.row][cs.col];
                        map[cs.row][cs.col] = cs.cnt;
 
                        // 원래 있던거 cnt 변경
                        int n = tmp.size();
                        for (int k = 0; k < n; k++) {
                            Cluster cl = tmp.poll();
                            if (cl.row == cs.row && cl.col == cs.col) {
 
                                cl.cnt = cs.cnt;
                            }
                            tmp.offer(cl);
                        }
                    }
 
                    else {
                        map[cs.row][cs.col] = cs.cnt;//저장
                        tmp.offer(cs);
                    }
 
                }
 
                while (!tmp.isEmpty())
                    clusters.offer(tmp.poll());
 
            }
 
            // 이동 끝 출력
            int sum = 0;
            while (!clusters.isEmpty())
                sum += clusters.poll().cnt;
 
            sb.append(sum).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
 
}
