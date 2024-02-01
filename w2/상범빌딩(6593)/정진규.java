import java.io.*;
import java.util.*;

public class Main {

    static int l,r,c;
    static int[] dx = {1,-1,0,0,0,0};
    static int[] dy = {0,0,1,-1,0,0};
    static int[] dz = {0,0,0,0,1,-1};
    static char[][][] arr;
    static boolean[][][] visit;
    static int sl,sr,sc,el,er,ec;
    static int answer;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            l = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            if(l==0 && r==0 && c==0) {
                return;
            }
            answer = l*r*c+1;
            arr = new char[l][r][c];
            visit = new boolean[l][r][c];

            for(int i = 0;i<l;i++) {
                for(int j = 0;j<r;j++) {
                    st = new StringTokenizer(br.readLine());
                    String str = st.nextToken();
                    for(int k=0;k<c;k++){
                        char ch = str.charAt(k);
                        arr[i][j][k] = ch;
                        if(ch == 'S') {
                            sl = i;
                            sr = j;
                            sc = k;
                        }
                        if(ch == 'E') {
                            el = i;
                            er = j;
                            ec = k;
                        }
                    }
                }
                br.readLine();
            }
            bfs();
            if(answer == c*r*l+1) {
                System.out.println("Trapped!");
            }else {
                System.out.println("Escaped in " + answer + " minute(s).");
            }


        }

    }

    public static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        visit[sl][sr][sc] = true;
        q.offer(new int[] {sl,sr,sc,0});

        while(!q.isEmpty()) {
            int[] out = q.poll();
            int z = out[0];
            int x = out[1];
            int y = out[2];
            int time = out[3];

            for(int i = 0;i<6;i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                int nz = z + dz[i];

                if(nx>=0 && nx< r && ny>=0 && ny<c && nz>=0 && nz<l
                        && !visit[nz][nx][ny] && arr[nz][nx][ny] != '#') {
                    if(nz == el && nx == er && ny == ec) {
                        answer = Math.min(answer,time+1);
                    }
                    else {
                        visit[nz][nx][ny] = true;
                        q.offer(new int[] {nz,nx,ny,time+1});
                    }

                }
            }
        }
    }


}
