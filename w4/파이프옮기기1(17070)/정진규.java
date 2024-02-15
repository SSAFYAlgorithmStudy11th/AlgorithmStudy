import java.io.*;
import java.util.StringTokenizer;


public class Main {

    static int n;
    static int[][] arr;
    static boolean[][] visit;
    static int[] dx = {0,1};
    static int[] dy = {1,1};
    static int[] dx1 = {1,1};
    static int[] dy1 = {0,1};
    static int[] dx2 = {1,0,1};
    static int[] dy2 = {0,1,1};
    static int answer;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        visit = new boolean[n][n];
        answer = 0;

        for(int i = 0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0;j<n;j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visit[0][0] = true;
        visit[0][1] = true;

        dfs(0,1,0);

        System.out.println(answer);


    }

    public static void dfs(int x, int y, int pos) {
        if(x == n-1 && y==n-1) {
            answer++;
            return;
        }
        if(pos == 2) {
            for(int i = 0; i< 3;i++) {
                int nx = x + dx2[i];
                int ny = y + dy2[i];

                if(nx>=0 && nx<n && ny>=0 && ny<n && !visit[nx][ny] && arr[nx][ny] ==0) {

                    if(i==0) {
                        visit[nx][ny] = true;
                        dfs(nx,ny,1);
                    }
                    else if(i==1) {
                        visit[nx][ny] = true;
                        dfs(nx,ny,0);
                    }
                    else {
                        if(arr[nx-1][ny] == 0 && arr[nx][ny-1] == 0) {
                            visit[nx][ny] = true;
                            dfs(nx,ny,2);
                        }

                    }
                    visit[nx][ny] = false;
                }
            }
        }
        else if(pos == 1){
            for(int i = 0; i< 2;i++) {
                int nx = x + dx1[i];
                int ny = y + dy1[i];

                if(nx>=0 && nx<n && ny>=0 && ny<n && !visit[nx][ny] && arr[nx][ny] ==0) {

                    if(i==0) {
                        visit[nx][ny] = true;
                        dfs(nx,ny,1);
                    }
                    else if(i==1) {
                        if(arr[nx-1][ny] == 0 && arr[nx][ny-1] == 0) {
                            visit[nx][ny] = true;
                            dfs(nx,ny,2);
                        }
                    }
                    visit[nx][ny] = false;
                }
            }
        }
        else {
            for(int i = 0; i< 2;i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx>=0 && nx<n && ny>=0 && ny<n && !visit[nx][ny] && arr[nx][ny] ==0) {

                    if(i==0) {
                        visit[nx][ny] = true;
                        dfs(nx,ny,0);
                    }
                    else if(i==1) {
                        if(arr[nx-1][ny] == 0 && arr[nx][ny-1] == 0) {
                            visit[nx][ny] = true;
                            dfs(nx,ny,2);
                        }
                    }
                    visit[nx][ny] = false;
                }
            }
        }

    }


}
