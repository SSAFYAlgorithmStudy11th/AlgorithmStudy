import java.io.*;
import java.util.*;

public class Main {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // - 기준으로 나눠  -> 55-50+40 이면 55 , 50+40 으로 분리
        StringTokenizer st = new StringTokenizer(br.readLine(),"-");
        int sum = Integer.MAX_VALUE;
        while(st.hasMoreTokens()) {
            int sub_sum = 0;
            // + 기준으로 나눠서 읽음 -> 55은 유지, 50 + 40 을 빼줘
            // +만 있다면 더하기만
            StringTokenizer m = new StringTokenizer(st.nextToken(),"+");

            while(m.hasMoreTokens()) {
                sub_sum += Integer.parseInt(m.nextToken());
            }
            if(sum == Integer.MAX_VALUE) {
                sum = sub_sum;
            }
            else {
                sum -= sub_sum;
            }
        }
        System.out.println(sum);
    }


}
