import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static List<Integer>[] map;
	static int[] arr;
	
	static boolean[] check;
	static int MIN = Integer.MAX_VALUE;
	static int total = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new List[N+1];
		arr = new int[N+1];
		
		for(int i = 1; i <= N; i++) {
			map[i] = new ArrayList<>();
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			total+= arr[i];
		}

		check = new boolean[N+1];
		for(int i = 1 ; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken());
			while(st.hasMoreTokens()) {
				int v = Integer.parseInt(st.nextToken());
				map[i].add(v);
			}
		}
		
		pomutation(1, new ArrayList<>());
		System.out.println(MIN == Integer.MAX_VALUE ? -1 : MIN);
	}
	
	
	static void pomutation(int depth, List<Integer> l) {
		boolean flag = true;
		boolean flag2 = true;
		if(depth <= N) {
			//한 녀석이 인접하고 다른 녀석도 인접한지 확인해야 함. 한 녀석만 인접하면 됨.
				for(int j = 1; j <= N; j++) {
					if(!check[j]) {
						continue;
					}
					if(!bfs(l.get(0),j, l)) {
						flag = false;
						break;
					}
				}
			
			if(flag) {
				List<Integer> r = new ArrayList<>();
				for(int i = 1; i <= N; i++) {
					if(!check[i]) {
						r.add(i);
					}
				}
				for(int j = 1; j <= N; j++ ) {
					if(check[j]) {
						continue;
					}
					if(!bfs(r.get(0),j,r)) {
						flag2 = false;
						break;
					}
				}
				
				if(flag2) {
					int l1 = 0;
					for(int i = 1; i <= N; i++) {
						if(check[i]) {
							l1+= arr[i];
						}
					}
					int l2 = total - l1;
					
					if(l1 > l2 && l1 - l2 > MIN) {
						return;
					}

					MIN = Math.min(Math.abs(l1 - l2), MIN);
				}
			}
		}
		for(int i = depth; i <= N; i++) {
			if(check[i]) {
				continue;
			}
			check[i] = true;
			l.add(i);
			pomutation(i+1, l);
			l.remove(l.size() - 1);
			check[i] = false;
		}
	}
	
	
	
	
	static boolean bfs(int u, int v, List<Integer> list) {
		Queue<Integer> q = new ArrayDeque<>();
		q.add(u);

		boolean[] visited = new boolean[N+1];
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			if(visited[cur]) {
				continue;
			}
			
			if(cur == v) {
				return true;
			}
			
			visited[cur] = true;
			
			for(int vortex : map[cur]) {
				if(!visited[vortex] && list.contains(vortex))
					q.add(vortex);
				}
			}
		//자식 트리 탐색
		return false;
	}
}
