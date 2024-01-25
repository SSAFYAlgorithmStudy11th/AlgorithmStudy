import java.io.*;
import java.math.BigInteger;
import java.util.*;

class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        BigInteger sum  = new BigInteger("1");

        for(int i = 0 ; i<m;i++) {
            sum = sum.multiply(BigInteger.valueOf(n-i));

        }
        BigInteger div  = new BigInteger("1");
        for(int i = 1;i<m+1;i++) {
           div = div.multiply(BigInteger.valueOf(i));
        }

        System.out.println(sum.divide(div).toString());
    }



}
