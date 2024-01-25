import java.io.*;
import java.util.*;

public class Main {
    
static int n,m;
static int[] arr;
static boolean[] visit;
static StringBuilder sb;
    
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        arr = new int[n];
        visit = new boolean[n];
        st = new StringTokenizer(br.readLine());
        
        for(int i = 0;i<n;i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        dfs("",0);
        System.out.println(sb);
    }

    public static void dfs(String str, int cnt) {
        if(cnt == m) {

        	sb.append(str + "\n");
            return;
        }
        for(int i = 0;i<n;i++) {
           if(!visit[i]) {
        	   visit[i] = true;
        	   dfs(str+arr[i]+" ", cnt+1);
        	   visit[i] =false;
           }
        }
    }

}
   
   
