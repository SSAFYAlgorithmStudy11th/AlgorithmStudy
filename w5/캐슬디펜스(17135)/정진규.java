import java.io.*;
import java.util.*;

public class Main {
    static int n, m,d;
    static int[][] arr;
    static boolean[] archer;
    static int[] dx = {0,1,-1,0,0};
    static int[] dy= {0,0,0,1,-1};
    static int enemy = 0;
    static Queue<int[]> mainQ;
    static Queue<int[]> deleteQ;
    static int kill, max;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        archer = new boolean[m];
        for(int i = 0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0;j<m;j++) {
                int num = Integer.parseInt(st.nextToken());
                arr[i][j] = num;
                if(num == 1) {
                    enemy++;
                }
            }
        }
        mainQ = new ArrayDeque<>();
        deleteQ = new ArrayDeque<>();
        max = 0;
        setArcher(0,0);
        System.out.println(max);
    }
    public static void find(int[][] map) {
        boolean[][] visit = new boolean[n][m];
        boolean find = false;
        Queue<int[]> q = new ArrayDeque<>(); // 적 선택 위한 큐
        while(!mainQ.isEmpty()) {
            int[] out = mainQ.poll();
            int x = out[0];
            int y = out[1];
            int count = out[2];
            if(count > d) {
                continue;
            }

            for(int i = 0;i<5;i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx>=0 && nx<n && ny>=0 && ny<m && !visit[nx][ny]) {
                    if(!find && map[nx][ny] == 0) {
                        visit[nx][ny] = true;
                        mainQ.add(new int[]{nx,ny,count+1});
                    }

                    if(map[nx][ny] > 0) {
                        find = true;
                        visit[nx][ny] = true;
                        if(i==0) {
                            q.add(new int[] {nx,ny,count});
                        }
                        else {
                            q.add(new int[] {nx,ny,count+1});
                        }
                    }

                }
            }
        }
        // 조건에 맞는 적 탐색
        int mr = 0;
        int mc = Integer.MAX_VALUE;
        int mcount = Integer.MAX_VALUE;
        while(!q.isEmpty()) {
            int[] out = q.poll();
            int r = out[0];
            int c = out[1];
            int count = out[2];
            if(count>d)
                continue;
            if(mcount > count) {
                mcount = count;
                mc = c;
                mr = r;

            }
            else if(mcount == count) {
                if(mc > c) {
                    mr = r;
                    mc = c;
                }


            }
        }
        if(mc != Integer.MAX_VALUE && mcount != Integer.MAX_VALUE)
            deleteQ.add(new int[]{mr,mc});


    }
    public static int delete(int cnt,int[][] map) {
        int result = cnt;
        while(!deleteQ.isEmpty()) {
            int[] out = deleteQ.poll();
            if(map[out[0]][out[1]] > 0) {
                map[out[0]][out[1]] = 0;
                result--;
                kill++;
            }

        }
        return result;
    }
    public static int move(int cnt,int[][] map) {
        //끝 줄 이동
        int result = cnt;
        for(int j = 0;j<m;j++) {
            if(map[n-1][j] > 0) {
                map[n-1][j] = 0;
                result--;
            }
        }
        for(int i = n-2;i>=0;i--) {
            for(int j = 0;j<m;j++) {
                map[i+1][j] = map[i][j];
            }
        }
        for(int j = 0;j<m;j++) {
            map[0][j] = 0;
        }
        return result;

    }

    public static int shoot(int cnt, int[][] map) {
        int tmp = cnt;
        for(int i = 0;i<m;i++) {
            if(archer[i]) {
                // 궁수 윗 칸 부터 출발
                mainQ.add(new int[] {n-1,i,1});
                find(map);
            }
        }
        tmp = delete(tmp,map);
        tmp = move(tmp,map);

        return tmp;
    }


    public static void setArcher(int cnt,int idx) {
        if(cnt == 3) {
            kill=0;
            int[][] map = new int[n][m];
            for(int i = 0 ; i<n;i++) {
                map[i] = Arrays.copyOf(arr[i],m);
            }
            int remain = enemy;

            while(remain>0) {
                remain = shoot(remain, map);
            }
            max = Math.max(max,kill);
            return;
        }

        for(int i = idx;i<m;i++) {
            if(!archer[i]) {
                archer[i] = true;
                setArcher(cnt+1,i+1);
                archer[i] = false;
            }
        }
    }
}
