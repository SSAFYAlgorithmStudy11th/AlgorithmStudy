import java.io.*;
import java.util.*;


class Solution
{
	static int n,k;
    static int[][] arr;
    static boolean[][] visit;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            arr = new int[n][n];
            Queue<int[]> q = new ArrayDeque<>();
            int max = 0;
            for(int i = 0;i<n;i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0;j<n;j++) {
                    int h = Integer.parseInt(st.nextToken());
                    arr[i][j] = h;
                    if(h>max) {
                        q.clear();
                        max =h;
                    }
                    if(h==max) {
                        q.add(new int[] {i,j});
                    }
                }
            }
            visit = new boolean[n][n];
            answer = 0;

            while(!q.isEmpty()) {
                int[] out = q.poll();
                int x = out[0];
                int y = out[1];

                visit[x][y] = true;
                dfs(x,y,arr[x][y],1,0);
                visit[x][y] = false;
            }

            sb.append("#").append(test_case).append(" ").append(answer).append("\n");
        }

        System.out.print(sb);

    }

    public static void dfs(int x, int y, int h, int cnt,int isBreak) {

        for(int i = 0;i<4;i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx>= 0 && nx<n && ny>=0 && ny<n && !visit[nx][ny]) {
                if(arr[nx][ny] >= h && arr[nx][ny]-k < h && isBreak == 0) {
                    visit[nx][ny] = true;
                   dfs(nx,ny,arr[nx][ny]-(arr[nx][ny] - h) - 1,cnt+1, 1);
                }

                if(arr[nx][ny] < h) {
                    visit[nx][ny] = true;
                    dfs(nx,ny,arr[nx][ny],cnt+1,isBreak);
                }

                visit[nx][ny] = false;
            }
        }

        answer = Math.max(answer,cnt);
    }

}
