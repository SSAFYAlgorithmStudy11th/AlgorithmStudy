import java.io.*;
import java.util.*;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
	static int n, m, k, add;
    static int[][] arr;
    static ArrayList<ArrayList<int[]>> alist;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            arr = new int[1000][1000];
            alist = new ArrayList<>();

            for(int i = 0;i<311;i++) {
                alist.add(new ArrayList<>());
            }

            for (int i = 500; i < 500+n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 500; j < 500+m; j++) {
                    int num = Integer.parseInt(st.nextToken());
                    arr[i][j] = num;
                    if(num > 0)
                        alist.get(num+1).add(new int[]{i,j,num,0});
                }
            }

            answer = 0;
            for(int i = 1;i<=k;i++) {
                bfs(i);
            }



            sb.append("#").append(test_case).append(" ").append(answer).append("\n");
        }

        System.out.print(sb);

    }

    public static void bfs(int time) {
        PriorityQueue<int[]> q = new PriorityQueue<>((o1,o2) -> {
        	return -(o1[2] - o2[2]);
        });
        q.addAll(alist.get(time));

        while(!q.isEmpty()) {
            int[] out = q.poll();
            int x = out[0];
            int y = out[1];
            int t = out[2];
            int stat = out[3];
            if(stat == 1) {
            	//alist.get(time).remove(out);
            	continue;
            }
            if(t > 1 && stat == 0) {
            	alist.get(time+t-1).add(new int[] {x,y,t,1});
            }
            for(int i = 0;i<4;i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(arr[nx][ny] == 0) {
                    arr[nx][ny] = t;
                    alist.get(time+t+1).add(new int[]{nx,ny,t,0});
                }
            }
        }
        
        if(time == k) {
            for(int i = k+1; i<k+12;i++) {
                answer += alist.get(i).size();
            }
        }
        alist.get(time).clear();
        
    }


}

