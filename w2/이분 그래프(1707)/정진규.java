import java.io.*;
import java.util.*;

public class Main {

    static int k,v,e;
    static ArrayList<ArrayList<Integer>> arr;
    static int[] visit;
    static boolean chk;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int r,c;
        k = Integer.parseInt(br.readLine());
        for(int i = 0;i<k;i++) {
            arr = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            v = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());

            for(int j = 0;j<v+1;j++) {
                arr.add(new ArrayList<>());
            }
            visit = new int[v+1];
            for(int j = 0;j<e;j++) {
                st = new StringTokenizer(br.readLine());
                r = Integer.parseInt(st.nextToken());
                c = Integer.parseInt(st.nextToken());

                arr.get(r).add(c);
                arr.get(c).add(r);
            }
            chk = false;
            for(int k = 1;k<v+1;k++) {
                if(chk) {
                    break;
                }
                if(visit[k]==0) {
                    bfs(k);
                }
            }
            if(chk) {
                System.out.println("NO");
            }
            else System.out.println("YES");


        }




    }

    public static void bfs(int start) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {start, 1});
        visit[start] = 1;


        while(!q.isEmpty()) {
           int[] out = q.poll();
           int node = out[0];
           int depth = out[1];
           int newDepth = 0;

           for(Integer nd : arr.get(node)) {
               if(visit[nd] == depth) {
                  chk = true;
                  return;
               }
               if(visit[nd] == 0) {
                   if(depth == 1) {
                       newDepth = 2;
                   }
                   else newDepth = 1;
                   visit[nd] = newDepth;
                   q.offer(new int[] {nd,newDepth});
               }
           }
        }

    }





}
