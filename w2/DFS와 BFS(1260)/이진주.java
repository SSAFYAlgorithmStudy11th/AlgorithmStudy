import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static ArrayList<Integer>[] array;
	static boolean []visited;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		
		
		StringTokenizer st=new StringTokenizer(br.readLine());
		int N=Integer.parseInt(st.nextToken());
		int M=Integer.parseInt(st.nextToken());
		int V=Integer.parseInt(st.nextToken());
		
		sb=new StringBuilder("");
		
		array = new ArrayList[N+1];
		for(int i=1;i<=N;i++) {
			array[i]=new ArrayList<>();
		}
		
		
		
		for(int i=0;i<M;i++) {
			
			st=new StringTokenizer(br.readLine());
			int s=Integer.parseInt(st.nextToken());
			int e=Integer.parseInt(st.nextToken());
			
			array[s].add(e);
			array[e].add(s);
		}
		
		
		for(int i=1;i<=N;i++)
			Collections.sort(array[i]);
		
		visited=new boolean[N+1];
		dfs(V);
		sb.append("\n");
		
		visited=new boolean[N+1];
		bfs(V);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	
	public static void dfs(int v) {
		
		if(visited[v]) return;
		
		visited[v]=true;
		sb.append(v).append(" ");
		for(int i:array[v]) {
			
			if(!visited[i])
				dfs(i);
		}
	}
	
	public static void bfs(int v) {
	
		Queue<Integer> queue=new LinkedList<>();
		
		queue.offer(v);
		visited[v]=true;
		
		
		while(!queue.isEmpty()) {
			
			int node=queue.poll();
			sb.append(node).append(" ");
			
			for(int i:array[node]) {
				
				if(!visited[i]) {
					visited[i]=true;
					queue.add(i);
				}
			}
		}
	}
}
