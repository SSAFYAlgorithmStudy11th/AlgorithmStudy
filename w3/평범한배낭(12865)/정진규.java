import java.io.*;
import java.util.*;


public class Main {
    static int n,k;
    static int[][] arr;
    static ArrayList<Product> parr;

    static class Product implements Comparable<Product>{
        int weight;
        int value;

        public Product(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        public int compareTo(Product o) {
            return this.weight - o.weight;
        }
    }
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[n+1][k+1];
        parr = new ArrayList<>();
        parr.add(new Product(0,0));
        for(int i = 1;i<n+1;i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            parr.add(new Product(w,v));
        }

        Collections.sort(parr);

       for(int i = 1;i<n+1;i++) {
           int w = parr.get(i).weight;
           int v = parr.get(i).value;
           for(int j = 1;j<k+1;j++) {
               if(j < w)
                   arr[i][j] = arr[i-1][j];
               if(j>=w) {
                   arr[i][j] = Math.max(arr[i-1][j], arr[i-1][j-w] + v);
               }
           }
       }

        System.out.println(arr[n][k]);

    }


}
