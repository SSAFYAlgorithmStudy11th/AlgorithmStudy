import java.io.*;
import java.util.*;

public class Main {

    static int n,m;
    static int[][] arr;

    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        Queue<int[]> q = new LinkedList<>();
        int t_cnt = 0;
        int n_cnt = 0;
        for(int i = 0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0;j<m;j++) {
                int num =  Integer.parseInt(st.nextToken());
                arr[i][j] = num;
                if(num==1) {
                    q.offer(new int[] {i,j});
                    t_cnt++;
                }
                else if(num==-1) {
                    n_cnt++;
                }

            }

        }
        int max = 1;

        while(!q.isEmpty()) {
            int[] out = q.poll();
            int x = out[0];
            int y = out[1];

            for(int i = 0;i<4;i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx>=0 && nx<n && ny>=0 && ny<m ) {
                    if(arr[nx][ny] == -1) {
                        continue;
                    }
                    else if(arr[nx][ny] == 0) {
                        arr[nx][ny] = arr[x][y] + 1;
                        q.offer(new int[] {nx,ny});
                        t_cnt++;
                        max = Math.max(max,arr[x][y] + 1);
                    }
                    else if (arr[nx][ny] > 1) {
                        arr[nx][ny] = Math.min(arr[x][y]+1, arr[nx][ny]);
                    }

                }
            }

        }

        if(n*m-t_cnt == n_cnt) {
            System.out.println(max-1);
        }
        else System.out.println(-1);
    }



}
