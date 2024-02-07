import java.io.*;
import java.util.*;


public class Main {

    static class Node implements Comparable<Node> {
        int next;
        int weight;

        public Node(int next, int weight) {
            this.next = next;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight-o.weight;
        }
    }

    static ArrayList<Node>[] graph;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];

        for(int i = 0;i<n+1;i++) {
            graph[i] = new ArrayList<>();
        }

        int start = Integer.parseInt(br.readLine());

        for(int i =0;i<m;i++) {
            st = new StringTokenizer(br.readLine());
            int f = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[f].add(new Node(t,w));
        }
        Dijkstra(n,start);
    }

    public static void Dijkstra(int n, int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visit = new boolean[n+1];
        int[] distance = new int[n+1];
        int INF = Integer.MAX_VALUE;

        Arrays.fill(distance,INF);
        distance[start] = 0;
        pq.offer(new Node(start,0));

        while(!pq.isEmpty()) {
            int now = pq.poll().next;

            if(visit[now])
                continue;
            else visit[now] = true;

            for(Node nd : graph[now]) {
                if(distance[nd.next] > distance[now] + nd.weight)
                    distance[nd.next] = distance[now] + nd.weight;

                pq.offer(new Node(nd.next,distance[nd.next]));
            }
        }

        for(int i = 1;i<n+1;i++) {
            if(distance[i] == INF)
                System.out.println("INF");
            else System.out.println(distance[i]);
        }
    }
}
