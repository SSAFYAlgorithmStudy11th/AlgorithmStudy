import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
 
 
public class Solution {
    static int N, M, K;
    static int[][] arr;
    static int MIN;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
         
        int test_case = 1;
        while(T --> 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
             
            arr = new int[N][M];
            MIN = Integer.MAX_VALUE;
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0 ; j < M; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
             
             
            backtracking(0, new int[N], 0);
            sb.append('#');
            sb.append(test_case++);
            sb.append(' ');
            sb.append(MIN);
            sb.append('\n');
        }
        System.out.println(sb);
    }
     
     
     
    static void backtracking(int depth, int[] arr, int cnt) {
        if(cnt > K ) {
            return;
        }
         
        if(MIN <= cnt ) {
            return;
        }
        if(depth == N) {
            // 체크하는 로직
            boolean[] check = new boolean[N];
 
            if(check(arr, check, 0)) {
                MIN = Math.min(cnt, MIN);
                 
            }
            return;
        }
        arr[depth] = -1;
        if(MIN < cnt) {
            return;
        }
        backtracking(depth+1, arr, cnt);
        arr[depth] = depth;
        backtracking(depth+1, arr, cnt + 1);
         
    }
     
     
    // 각각마다 또 A인지 B인지 파워셋으로 체크한다.
    static boolean check(int[] checkedNum, boolean[] visited, int depth) {
        if(depth == N) {
            // 진짜 체크하는 로직
            return press(checkedNum, visited);
        }
         
        if(checkedNum[depth] == -1) {
            return check(checkedNum, visited, depth+1);
        }
         
        boolean flag = false;
        visited[depth] = true;
        flag |= check(checkedNum, visited, depth+1);
        if(flag) {
            return true;
        }
        visited[depth] = false;
        flag |= check(checkedNum, visited, depth+1);
        if(flag) {
            return true;
        }
//      if(flag)  {
//          System.out.println(Arrays.toString(visited));
//          System.out.println(Arrays.toString(checkedNum));
//      }
        return flag;
    }
     
     
    static boolean press(int[] checkedNum, boolean[] aOrB) {
        for(int i = 0; i < M; i++) {
            int count = 0;
            boolean flag = false;
            int cursor = 1;
            if(checkedNum[0] == -1) {
                cursor = arr[0][i];
            }else {
                if(!aOrB[0]) {
                    cursor = 0;
                }else {
                    cursor = 1;
                }
            }
            for(int j = 1; j < N; j++) {         
                if(cursor == 0 && (checkedNum[j] != -1 ? !aOrB[j] : cursor == arr[j][i])) {
                    count++;
                } else if(cursor == 1 && (checkedNum[j] != -1 ? aOrB[j] : cursor == arr[j][i])) {
                    count++;
                }else {
                    count = 0;
                        if(checkedNum[j] == -1) {
                        cursor = arr[j][i];
                    }else {
                        if(!aOrB[j]) {
                            cursor = 0;
                        }else {
                            cursor = 1;
                        }
                    }
                }
                 
                if(count == K-1) {
                    flag = true;
                    break;
                }
            }
            if(!flag) {
                return false;
            }
        }
        return true;
    }
}
