import java.io.*;
import java.util.*;

class Main {
    static int n;
    static int[] arr;
    static boolean[] visit;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            if(n==0) {
                break;
            }
            arr = new int[n];
            visit = new boolean[n];
            for(int i = 0;i<n;i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(arr);
            find(0,0,"");
            System.out.println();
        }

    }

    public static void find(int cnt, int idx, String str) {
        if(cnt==6) {
            System.out.println(str);
            return;
        }

        for(int i = idx;i<n;i++) {
            if(!visit[i]) {
                visit[i] = true;
                find(cnt+1,i,str+arr[i]+" ");
                visit[i] = false;
            }
        }
    }

}
