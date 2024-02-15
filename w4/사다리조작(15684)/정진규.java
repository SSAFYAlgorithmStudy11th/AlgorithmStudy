import java.io.*;
import java.util.*;


public class Main {

    static int n, m,h;
    static int[][] arr;
    static boolean isPossible;
    static boolean finish;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        arr = new int[h+1][n+1];
        finish = false;

        for(int i = 0;i<m;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            arr[a][b] = b+1;
            arr[a][b+1] = b;
        }

        for(int i = 0; i<=3;i++) {
            ladder(0,i);
            if(finish) {
                System.out.println(i);
                return;
            }
        }
        System.out.println(-1);

    }

    //다리 놓기
    public static void ladder(int cnt, int size) {
        if(cnt==size) {
            isPossible = true;
            go();
            // 가능하다면
            if(isPossible) {
                finish = true;
            }
            return;
        }

        for(int i = 1;i<h+1;i++) {
            for(int j = 1 ; j<n;j++) {
                //답이 나왔으면 나가
                if(finish) {
                    return;
                }
                //현재 자리에 다리가 없음
                if(arr[i][j] == 0) {
                    //오른쪽에 다리가 없으면 다리 놔
                    if(arr[i][j+1] == 0) {
                        arr[i][j] = j+1;
                        arr[i][j+1] = j;
                        ladder(cnt+1,size);
                        arr[i][j] = 0;
                        arr[i][j+1] = 0;
                    }
                }
            }
        }
    }
    // 사다리 타기
    public static void go() {
        boolean flag = true;
        for(int i = 1;i<n+1;i++) {
            if(!flag) {
                isPossible = false;
                return;
            }
            int idx = 1;
            int now = i;
            while(true) {
                if(arr[idx][now] > 0) {
                    now = arr[idx][now];
                }
                idx++;
                if(idx>h) {
                    if(now != i) {
                        flag = false;
                    }
                    break;
                }
            }

        }
    }


}
