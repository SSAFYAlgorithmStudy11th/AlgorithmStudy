import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
 
public class Solution {
    static int N, M, C;
    static int[][] arr;
    static int[] first;
    static int[] second;
    static boolean[][] visited;
 
    static int MAX;
    public static void main(String[] args) throws IOException { 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        int test_case = 1;
        while(T --> 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            arr = new int[N][N];
            first = new int[M];
            second = new int[M];
            visited = new boolean[N][N];
            maxFindVisited = new boolean[M];
            MAX = Integer.MIN_VALUE;
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
             
            backtracking(0,first, false);
            sb.append('#');
            sb.append(test_case++);
            sb.append(' ');
            sb.append(MAX);
            sb.append('\n');
        }
        System.out.println(sb);
    }
     
    static int maxVisited = 0;
    static void backtracking(int cnt, int[] target, boolean last) {
        if(!last && cnt == 1) {
            backtracking(0, second, true);
            return;
        }
         
        if(last && cnt == 1) {
            maxVisited = 0;
            getMax(first, 0);
            int l1 = maxVisited;
            maxVisited = 0;
            getMax(second, 0);
            int l2 = maxVisited;
//          if(MAX < l1 + l2) {
//              System.out.println(Arrays.toString(first) + l1);
//              System.out.println(Arrays.toString(second) + l2);
//
//          }
            MAX = Math.max(l1 + l2, MAX);
            return;
        }
         
         
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N - M + 1; j++) {
                if(!canVisited(j,i)) {
                    continue;
                }
                setVisited(j,i,true);
//              for(int k = 0; k < N; k++) {
//                  System.out.println(Arrays.toString(visited[k]) + last + i + j);
//              }
//              System.out.println();
                setVal(target, j, i);
                backtracking(cnt+1, null, last);
                 
 
                setVisited(j,i,false);
            }
        }
    }
     
     
    static void setVisited(int x, int y, boolean setVal) {
        for(int i = x; i < x + M; i++) {
            visited[y][i] = setVal;
        }
    }
     
    static boolean canVisited(int x, int y) {
        for(int i = x; i < x + M; i++) {
            if(visited[y][i]) {
                return false;
            }
        }
        return true;
    }
     
    static void setVal(int[] target, int x, int y) {
        for(int i = 0; i < M; i++ ) {
            target[i] = arr[y][i+x];
        }
    }
     
 
    static boolean[] maxFindVisited;
    static void getMax(int[] target, int cnt) {
        if(cnt == M) {
            int sum = 0;
            for(int i = 0; i < M; i++) {
                if(maxFindVisited[i]) {
                    sum += target[i];
                }
            }
             
            if(sum > C) {
                return;
            }
             
            else {
                int val = 0;
                for(int i = 0; i < M; i++) {
                    if(maxFindVisited[i]) {
                        val += target[i] * target[i];
                    }
                }
                maxVisited = Math.max(val, maxVisited);
                return;
            }
        }
         
        maxFindVisited[cnt] = true;
        getMax(target, cnt+1);
 
 
        maxFindVisited[cnt] = false;
        getMax(target, cnt+1);
         
    }
}
