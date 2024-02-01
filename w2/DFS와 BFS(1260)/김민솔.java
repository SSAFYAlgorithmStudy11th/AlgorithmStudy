import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N,M,V;
	static ArrayList<ArrayList<Integer>> arr;
	static boolean[] visited;

	static void bfs(int v) {
		Queue<Integer> q = new LinkedList<>();
		visited[v] = true;
		q.add(v);
		
		while(!q.isEmpty()) {
			int n = q.poll();
			System.out.print(n+" ");

			for(int i=0;i<arr.get(n).size();i++) {
				if(!visited[arr.get(n).get(i)] ) {
					visited[arr.get(n).get(i)]= true;
					q.add(arr.get(n).get(i));
				}
			}
		}
		
	}
	
	static void dfs(int v) {
		visited[v] = true;
		System.out.print(v+" ");
		
		for(int i=0;i<arr.get(v).size();i++) {
			if(!visited[arr.get(v).get(i)] ) {
				dfs(arr.get(v).get(i));
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		visited = new boolean[N+1];
		arr =new ArrayList<>();
		
		for(int i=0;i<N+1;i++) {
			arr.add(new ArrayList<Integer>());
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			arr.get(a).add(b);
			arr.get(b).add(a);
		}
		
		for(int i=0;i<N+1;i++) {
			Collections.sort(arr.get(i)); 

		}

		dfs(V);
		System.out.println("");

		visited = new boolean[N+1];
		
		bfs(V);
		
	}

}
