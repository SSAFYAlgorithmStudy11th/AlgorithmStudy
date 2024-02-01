import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
	public static int[] visited;
	public static int V;
	public static int E;
	public static String res;
	
	public static void bfs(int s) {
		Queue<Integer> q = new LinkedList<>();
		q.add(s);
		visited[s] = 1; //임의로 하는게 맞나,,
		
		while(!q.isEmpty()) {
			int x = q.poll();
			for(int i=0;i<arr.get(x).size();i++) {
				if(visited[arr.get(x).get(i)]==0) {
					int nc = visited[x]==1? 2:1;
					visited[arr.get(x).get(i)] = nc;
					q.add(arr.get(x).get(i));
				}
				else {
					if(visited[arr.get(x).get(i)]==visited[x]) {
						res = "NO";
						return;
					}
				}
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 0;t<T ; t++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken()); // 정점 수 
			E = Integer.parseInt(st.nextToken()); // 간선 수 
			visited = new int[V+1];
			res = "YES";
			arr = new ArrayList<>();
			for(int i=0;i<V+1 ; i++) {
				arr.add(new ArrayList<Integer>());
			}
			
			for(int i=0;i<E ;i++) {
				st= new StringTokenizer(br.readLine());
				
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				arr.get(a).add(b);
				arr.get(b).add(a);
			}
			
			for(int i=1; i<V+1 ; i++) {
				if(visited[i]==0) {
					bfs(i);
				}
			}
			System.out.println(res);
		}
		
	}

}
