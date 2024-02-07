import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static List<Node>[] maps;
    static StringBuilder sb = new StringBuilder();
    static int[][] dists;

    static int INF = 1_000_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());


        maps = new List[N+1];
        for(int i = 0; i <= N; i++) {
            maps[i] = new ArrayList<>();
        }

        dists = new int[N+1][N+1];

        StringTokenizer st;
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            maps[u].add(new Node(v, w));
        }

        for(int i = 1; i <= N; i++) {
            dijkstra(i);
        }

        for(int i = 1 ; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(dists[i][j] == INF) {
                    dists[i][j] = 0;
                }
                sb.append(dists[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }


    static void dijkstra(int start) {
        Arrays.fill(dists[start], INF);
        dists[start][start] = 0;

        boolean[] visited = new boolean[N+1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        while(!pq.isEmpty()) {
            Node curNode = pq.poll();
            int cur = curNode.e;

            if(visited[cur]) {
                continue;
            }
            visited[cur] = true;

            for(Node v : maps[cur]) {
                if(dists[start][v.e] > dists[start][cur] + v.v) {
                    dists[start][v.e] = dists[start][cur] + v.v;
                    pq.add(new Node(v.e, dists[start][v.e]));
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        int e;
        int v;
        Node(int a, int b) {
            e = a; v = b;
        }

        @Override
        public int compareTo(Node o) {
            return v - o.v;
        }
    }
}
