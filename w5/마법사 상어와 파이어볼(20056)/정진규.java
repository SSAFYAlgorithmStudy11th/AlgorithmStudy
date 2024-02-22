import java.io.*;
import java.util.*;


public class Main {

    static class FireBall {

        int m;
        int d;
        int s;
        boolean isMove;

        public FireBall(int m, int s, int d,boolean isMove) {
            this.m = m;
            this.d = d;
            this.s = s;
            this.isMove = isMove;
        }
    }

    static int n, m, k;
    static ArrayList<FireBall>[][] map;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    static Queue<int[]> mainQ;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        mainQ = new ArrayDeque<>();
        map = new ArrayList[n + 1][n + 1];

        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                map[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            map[r][c].add(new FireBall(m, s, d,false));
            mainQ.add(new int[] {r,c});
        }

        //todo 1. 이동시키기 -> d방향으로 s만큼 이동
        //todo 2. 갯수 확인 -> 방향 모두 홀,짝이면 0,2,4,6 , 아니면 1,3,5,7
        // todo 3. 질량 합치기
        for(int i = 0;i<k;i++) {
            move();
        }

        System.out.println(cal());
    }
    public static int cal() {
        int sum = 0;
        while(!mainQ.isEmpty()) {
            int[] out = mainQ.poll();
            int x = out[0];
            int y = out[1];

            for(FireBall fb : map[x][y]) {
                sum+= fb.m;
            }
        }
        return sum;
    }

    public static void move() {
        Queue<int[]> next = new ArrayDeque<>();
        while(!mainQ.isEmpty()) {
            ArrayList<FireBall> tmp = new ArrayList<>();
            int[] out = mainQ.poll();
            int x = out[0];
            int y = out[1];

            //리스트 갯수만큼 반복
            for(int i = 0;i<map[x][y].size();i++) {
                FireBall fb = map[x][y].get(i);
                if(fb.isMove) {
                    tmp.add(fb);
                    continue;
                }
                int nx = x + dx[fb.d] * fb.s;
                int ny = y + dy[fb.d] * fb.s;

                if(nx > n) {
                    nx = nx % n;
                }
                if(nx < 1) {
                    nx = n - Math.abs(nx) % n;
                }
                if(ny > n) {
                    ny = ny % n;
                }
                if(ny < 1) {
                    ny = n - Math.abs(ny) % n;
                }
                fb.isMove = true;
                map[nx][ny].add(fb);
                next.add(new int[] {nx,ny});

            }
            map[x][y] = tmp;

        }
        mainQ = next;
        divide();
    }
    public static void divide() {
        Queue<int[]> next = new ArrayDeque<>();
        boolean[][] visit = new boolean[n+1][n+1];

        while(!mainQ.isEmpty()) {
            int[] out = mainQ.poll();
            int x = out[0];
            int y = out[1];

            if(visit[x][y])
                continue;
            visit[x][y] = true;
            if(map[x][y].size() == 1) {
                map[x][y].get(0).isMove = false;
                next.add(new int[]{x,y});
                continue;
            }

            int chk = map[x][y].get(0).d % 2;
            boolean check = true;
            int mass = map[x][y].get(0).m;
            int speed=  map[x][y].get(0).s;
            for(int i = 1;i<map[x][y].size();i++) {
                FireBall fb = map[x][y].get(i);
                if(fb.d % 2 != chk)
                    check = false;
                mass += fb.m;
                speed += fb.s;
            }
            int nMass = mass / 5;
            int nSpeed = speed / map[x][y].size();
            map[x][y].clear();
            if(nMass == 0)
                continue;
            //모두 홀짝이면
            if(check) {
                map[x][y].add(new FireBall(nMass,nSpeed,0,false));
                map[x][y].add(new FireBall(nMass,nSpeed,2,false));
                map[x][y].add(new FireBall(nMass,nSpeed,4,false));
                map[x][y].add(new FireBall(nMass,nSpeed,6,false));
            }
            else {
                map[x][y].add(new FireBall(nMass,nSpeed,1,false));
                map[x][y].add(new FireBall(nMass,nSpeed,3,false));
                map[x][y].add(new FireBall(nMass,nSpeed,5,false));
                map[x][y].add(new FireBall(nMass,nSpeed,7,false));
            }
            next.add(new int[]{x,y});
        }

        mainQ = next;
    }
}

