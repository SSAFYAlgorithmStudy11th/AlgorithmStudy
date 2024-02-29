import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Solution {
    static int k;
    static boolean isPossible;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc<=T;tc++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            int[][] arr = new int[d][w];

            for(int i = 0;i<d;i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0;j<w;j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            if(k==1) {
                sb.append("#").append(tc).append(" ").append(0).append("\n");
                continue;
            }
            isPossible = false;
            answer = Integer.MAX_VALUE;
            make(arr,d,w,0,0);
            if(answer == Integer.MAX_VALUE) {
                sb.append("#").append(tc).append(" ").append(0).append("\n");
            }
            else {
                sb.append("#").append(tc).append(" ").append(answer).append("\n");
            }
        }
        System.out.print(sb);

    }
    public static int[][] copyArr(int[][] arr, int d ,int w) {
        int[][] tmp = new int[d][w];

        for(int i = 0; i<d;i++) {
            tmp[i] = Arrays.copyOf(arr[i],w);
        }
        return tmp;
    }
    public static boolean chk(int[][] arr, int d, int w) {
        //각 열별로 k개 이어지는지 확인
        for(int i = 0;i<w;i++) {
            int now = arr[0][i];
            int cnt = 1;
            boolean flag = false;
            for(int j = 1;j<d;j++) {
                if(now == arr[j][i]) {
                    cnt++;
                }
                else {
                    now = arr[j][i];
                    cnt = 1;
                }
                if(cnt == k) {
                    flag = true;
                    break;
                }
            }

            if(!flag)
                return false;
        }
        return true;
    }
    public static void make(int[][] arr, int d, int w, int idx,int cnt) {
        if(cnt > k || cnt >= answer)
            return;
        if(chk(arr,d,w)) {
            answer = Math.min(cnt,answer);
            return;
        }
        int[][] copiedArr = copyArr(arr,d,w);
        for(int i = idx;i<d;i++) {
            Arrays.fill(copiedArr[i],0);
           make(copiedArr,d,w,i+1,cnt+1);
           Arrays.fill(copiedArr[i],1);
           make(copiedArr,d,w,i+1,cnt+1);
           copiedArr[i] = Arrays.copyOf(arr[i],w);
        }
    }


}






