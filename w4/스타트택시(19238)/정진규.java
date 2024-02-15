import java.io.*;
import java.util.*;


public class Main {
    static int n, m, gas;
    static int[][] arr;
    static boolean[][] visit;
    static ArrayList<Integer>[] customer;
    static Queue<int[]> mainQ;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int pcount;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        mainQ = new ArrayDeque<>();

        // 배열 초기화
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        gas = Integer.parseInt(st.nextToken());

        pcount = 0;
        arr = new int[n + 1][n + 1];
        visit = new boolean[n + 1][n + 1];
        customer = new ArrayList[m + 2];

        for (int i = 0; i < m + 2; i++) {
            customer[i] = new ArrayList<>();
        }

        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < n + 1; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //출발지
        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        mainQ.offer(new int[]{r, c, 0});


        // 손님과 도착지
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int hr = Integer.parseInt(st.nextToken());
            int hc = Integer.parseInt(st.nextToken());
            int tr = Integer.parseInt(st.nextToken());
            int tc = Integer.parseInt(st.nextToken());

            //벽과 겹치지 않도록 2부터 손님 번호 시작
            //목적지가 겹칠 수 있기에, 해당 번호에 따른 목적지를 배열에 담아줘
            arr[hr][hc] = 2 + cnt;
            customer[2 + cnt].add(tr);
            customer[2 + cnt].add(tc);
            cnt++;
        }

        while (true) {
            //손님을 모두 태웠거나, 가스가 없다면 종료
            if (pcount >= m || gas == -1) {
                break;
            }
            bfs();
        }


        System.out.println(gas);


    }

    public static void bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        //만약 택시 출발지와 손님 위치가 동일하다면 바로 손님 Q에 넣어줘
        if (arr[mainQ.peek()[0]][mainQ.peek()[1]] > 1) {
            int[] out = mainQ.poll();
            q.add(new int[]{out[0], out[1], 0});
        } else {
            while (!mainQ.isEmpty()) {
                int[] out = mainQ.poll();
                int x = out[0];
                int y = out[1];
                int count = out[2];


                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if (nx >= 1 && nx < n + 1 && ny >= 1 && ny < n + 1 && !visit[nx][ny] && arr[nx][ny] != 1) {
                        if (arr[nx][ny] > 1) {
                            //손님을 찾으면 손님 Q에 넣어줘
                            visit[nx][ny] = true;
                            q.offer(new int[]{nx, ny, count + 1});
                        } else {
                            //손님 Q에 들어갔다면 탐색 중지
                            if (q.isEmpty()) {
                                visit[nx][ny] = true;
                                mainQ.offer(new int[]{nx, ny, count + 1});
                            }
                        }
                    }
                }
            }
        }


        // 거리가 가장 짧은 손님 찾아
        int min = Integer.MAX_VALUE;
        int r = 0;
        int c = 0;
        while (!q.isEmpty()) {
            int[] out = q.poll();

            if (out[2] == min) {
                if (r == out[0]) {
                    if (c > out[1]) {
                        r = out[0];
                        c = out[1];
                        min = out[2];
                    }
                } else {
                    if (r > out[0]) {
                        r = out[0];
                        c = out[1];
                        min = out[2];
                    }
                }
            } else {
                if (out[2] < min) {
                    r = out[0];
                    c = out[1];
                    min = out[2];
                }
            }
        }
        if (gas < min) {
            gas = -1;
            return;
        } else {
            gas -= min;
        }
        // 목적지까지 경로 찾기
        find_target(r, c, customer[arr[r][c]].get(0), customer[arr[r][c]].get(1));
        arr[r][c] = 0;

    }

    public static void find_target(int r, int c, int targetR, int targetC) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visit1 = new boolean[n + 1][n + 1];
        q.offer(new int[]{r, c, 0});
        visit1[r][c] = true;

        while (!q.isEmpty()) {
            int[] out = q.poll();
            int x = out[0];
            int y = out[1];
            int count = out[2];
            if (x == targetR && y == targetC) {
                if (gas >= count) {
                    gas += count;
                    pcount++;
                    visit = new boolean[n + 1][n + 1];
                    mainQ.offer(new int[]{x, y, 0});
                } else {
                    gas = -1;
                }
                return;
            }
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];


                if (nx >= 1 && nx < n + 1 && ny >= 1 && ny < n + 1 && !visit1[nx][ny] && arr[nx][ny] != 1) {
                    visit1[nx][ny] = true;
                    q.offer(new int[]{nx, ny, count + 1});
                }
            }
        }
        //목적지에 도착하지 못한다면 연료 없음 표시
        gas = -1;

    }


}
