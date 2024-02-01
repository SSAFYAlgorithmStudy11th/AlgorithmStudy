import java.io.*;
import java.util.*;

public class Main {

    static int n,m;
    static int[] parents;
    static class Node implements Comparable<Node> {
        int from;
        int to;
        public Node(int from, int to) {
            this.from = from;
            this.to =to;
        }

        public int compareTo(Node o) {
            return this.from - o.from;
        }
    }
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parents = new int[n+1];
        ArrayList<Node> nlist = new ArrayList<>();

        set();

        for(int i = 0;i<m;i++) {
            st = new StringTokenizer(br.readLine());
            int f = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            nlist.add(new Node(f,t));
        }

      
        for(Node nd : nlist) {
            union(nd.from,nd.to);
        }

        HashSet<Integer> hs = new HashSet<>();
        for(int i = 1;i<n+1;i++) {
            hs.add(find(parents[i]));
        }

        System.out.println(hs.size());


    }
    public static void set() {
        for(int i = 1;i<n+1;i++) {
            parents[i] = i;
        }
    }

    public static void union(int x, int y) {
        int px = find(x);
        int py = find(y);


        if(px < py)
            parents[py] = px;
        else
            parents[px] = py;

    }

    public static int find (int x) {
        if(parents[x] == x)
            return x;
        return parents[x] = find(parents[x]); //루트 탐색
    }




}
