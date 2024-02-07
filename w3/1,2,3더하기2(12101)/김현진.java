import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int count = 0;
    static int N, K;

    static int[] canUse = { 1, 2, 3};
    static int[] answer;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        Scanner sc =new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();

        answer = new int[N];

        backtracking(0, 0);
        System.out.println(sb.toString().isEmpty() ? -1 : sb.toString());
    }

    static boolean backtracking(int sum, int depth) {
        if(sum > N) {
            return false;
        }
        if(sum == N) {
            count++;
            if(count == K) {
                for(int i = 0; i < depth; i++) {
                    if(answer[i] == 0) {
                        break;
                    }
                    sb.append(answer[i]);

                    if(i + 1 != depth) {
                        sb.append("+");
                    }
                }
                return true;
            }
            return false;
        }
        boolean flag = false;
        for(int i = 0; i < canUse.length; i++) {
            answer[depth] = canUse[i];
            if(flag = backtracking(sum + answer[depth], depth+1)){
                break;
            }
        }

        return flag;
    }
}
