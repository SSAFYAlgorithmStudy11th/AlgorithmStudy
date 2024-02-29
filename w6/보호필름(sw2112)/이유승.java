import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
 
public class Solution {
    static boolean[][] matrix;
    static int d;
    static int w;
    static int k;
    static boolean success;
    static boolean[] allA;
    static boolean[] allB;
     
    public static int solution() {
        int cnt = 0;
        if(k == 1)
            return 0;
        while(cnt<k) {
            dfs(0, 0, cnt);
            if(success)
                break;
            cnt++;
        }
        return cnt;
    }
     
    public static void dfs(int target, int step, int cnt) {
        if(success)
            return;
        if(step == cnt) {
            success = check();
            return;
        }
        if(target == d)
            return;
        boolean[] original = matrix[target];
        dfs(target+1, step, cnt);
        matrix[target] = allA;
        dfs(target+1, step+1, cnt);
        matrix[target] = allB;
        dfs(target+1, step+1, cnt);
        matrix[target] = original;
    }
     
    public static boolean check() {
        for(int i=0; i<w; i++) {
            int cnt = 1;
            boolean isSuccess = false;
            boolean flag = matrix[0][i];
            for(int j=1; j<d; j++) {
                if(matrix[j][i] == flag) {
                    cnt++;
                    if(cnt == k) {
                        isSuccess = true;
                        break;
                    }
                }
                else {
                    cnt = 1;
                    flag = matrix[j][i];
                }
            }
            if(!isSuccess)
                return false;
        }
        return true;
    }
     
    public static boolean[] convert(String[] temp) {
        boolean[] result = new boolean[temp.length];
        for(int i=0; i<temp.length; i++)
            result[i] = temp[i].equals("1");
        return result;
    }
     
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());
        for(int i=0; i<t; i++) {
            String[] temp = br.readLine().split(" ");
            d = Integer.parseInt(temp[0]);
            w = Integer.parseInt(temp[1]);
            k = Integer.parseInt(temp[2]);
            allA = new boolean[w];
            allB = new boolean[w];
            matrix = new boolean[d][];
            success = false;
            Arrays.fill(allB, true);
            for(int j=0; j<d; j++)
                matrix[j] = convert(br.readLine().split(" "));
            bw.append("#").append(String.valueOf(i+1)).append(" ").append(String.valueOf(solution())).append("\n");
        }
        bw.flush();
    }
}
