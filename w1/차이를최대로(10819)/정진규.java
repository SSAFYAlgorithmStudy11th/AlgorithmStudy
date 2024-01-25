import java.io.*;
import java.util.*;

class Main {
    static int t;
    static int[] arr;
    static boolean[] visit;
    static int answer;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        t = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[t];
        visit = new boolean[t];
        for(int i = 0;i<t;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        answer = 0;

        find(0,0,0);
        System.out.println(answer);

    }
    public static void find(int cnt, int before, int sum) {
        if(cnt== t) {
            answer = Math.max(answer,sum);
            return;
        }
        for(int i = 0;i<t;i++) {
            if(!visit[i]) {
                visit[i] = true;
                if(cnt==0) {
                    find(cnt+1,arr[i],0);
                }
                else {
                    int cal = Math.abs(arr[i] - before);
                    find(cnt+1,arr[i],sum+cal);
                }
                visit[i] = false;
            }
        }
    }

}
