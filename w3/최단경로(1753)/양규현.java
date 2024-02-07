import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int V;
	static int E;
	static int K;

	public static void main(String[] args) throws IOException, InterruptedException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		// 간선의 가중치를 기준으로 오름차순으로 정렬하는 우선순위 큐
		Comparator<int[]> mincost = Comparator.comparingInt(arr -> arr[1]);
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>(mincost);
		ArrayList<int[]>[] list;
		boolean visited[];

		int[] dt; // 정점에 도달하는 최소 비용

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		list = new ArrayList[V + 1];
		dt = new int[V + 1];
		visited = new boolean[V+1];
		
		// 최소비용들의 초깃값
		Arrays.fill(dt, 1_000_000_000);
		dt[K] = 0;

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			if (list[u] == null) 
				list[u] = new ArrayList<int[]>();
				list[u].add(new int[] {v, w});
		}

		// 정점 K에서 출발
		if(list[K] != null) 
			pq.addAll(list[K]);
		
		int min_point = K;
		int end = 0;
		int cost = 0;
	
		while (!pq.isEmpty()) {

			int[] line = pq.poll();
			end = line[0];
			cost = line[1];
			
			 
			//최소거리라면 업데이트
			if (dt[end] > dt[min_point] + cost) 
				dt[end] = dt[min_point] + cost;
			
//			System.out.println(start+" "+ end+" "+cost+" "+dt[end]);
			
			
			while(pq.isEmpty()) {
				visited[min_point] = true;
				int min_dis = 1_000_000_000;
				min_point = 0;

				
				//방문하지 않은 정점중에 거리가 최소인 정점 선정 
				for(int i = 1; i <visited.length;i++) {
					if(!visited[i]) {
						if(dt[i] < min_dis) {
							min_dis = Math.min(dt[i],min_dis);
							min_point = i;
						}
					}
				}
				
				//정점과 연결된 간선을 우선순위 큐에 넣기
				if(list[min_point] != null)
					pq.addAll(list[min_point]);
				
				//정점에 간선이 없다면 방문처리
				else 
					visited[min_point] = true;
				
				
				//정점을 모두 방문했다면 종료
				if(min_point == 0)
					break;
			}
			
		}

		for(int i = 1; i<V+1; i++) {
			if(dt[i] == 1_000_000_000) {
				bw.write("INF");
				bw.write("\n");
			}
			else {
				bw.write(Integer.toString(dt[i]));
				bw.write("\n");
			}	
		}
		bw.close();
	}
}
