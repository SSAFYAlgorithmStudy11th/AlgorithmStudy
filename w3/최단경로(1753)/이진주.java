import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static List<Node>[] list;
	static boolean[] visited;
	static int[] distance;
	static final int INF = 1000000000;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		list = new ArrayList[V + 1];
		for (int i = 1; i <= V; i++)
			list[i] = new ArrayList<>();

		visited = new boolean[V + 1];
		distance = new int[V + 1];

		int K = Integer.parseInt(br.readLine());
		Arrays.fill(distance, INF);

		for (int e = 0; e < E; e++) {

			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			list[start].add(new Node(end, weight));
		}

		dijkstra(K);

		for (int i = 1; i <= V; i++) {
			if (distance[i] == INF)
				sb.append("INF");
			else
				sb.append(distance[i]);
			sb.append("\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	public static void dijkstra(int vertex) {

		Queue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(vertex, 0));
		distance[vertex] = 0;

		while (!pq.isEmpty()) {

			Node now = pq.poll();

			if (visited[now.end])
				continue;
			visited[now.end] = true;

			for (Node n : list[now.end]) {

				if (!visited[n.end]) {
					if (distance[n.end] > distance[now.end] + n.weight) {
						distance[n.end] = distance[now.end] + n.weight;
						pq.offer(new Node(n.end, distance[n.end]));

					}

				}
			}

		}
	}
}

class Node implements Comparable<Node> {
	int end;
	int weight;

	Node(int end, int weight) {
		this.end = end;
		this.weight = weight;
	}

	@Override
	public int compareTo(Node o) {// 오름차순 정렬

		return this.weight - o.weight;
	}
}
