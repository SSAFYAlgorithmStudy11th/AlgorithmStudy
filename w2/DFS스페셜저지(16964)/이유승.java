import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;

public class Main {
	static HashSet<Integer>[] graph;
	static ArrayDeque<Integer> input;
	static boolean[] visited;
	
	public static void solution() {
		int temp = input.poll();
		if(temp != 0) {
			System.out.println(0);
			return;
		}
		dfs(temp);
		if(input.isEmpty()) 
			System.out.println(1);
		else
			System.out.println(0);
	}
	
	public static void dfs(int v) {
		while(!input.isEmpty() && !visited[input.peek()] && graph[v].contains(input.peek())) {
			visited[input.peek()] = true;
			dfs(input.poll());
		}
	}
	
	public static ArrayDeque<Integer> convert(String[] temp) {
		ArrayDeque<Integer> result = new ArrayDeque<Integer>();
		for(String str : temp)
			result.add(Integer.parseInt(str)-1);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		graph = new HashSet[n];
		visited = new boolean[n];
		for(int i=0; i<n-1; i++) {
			String[] temp = br.readLine().split(" ");
			int a = Integer.parseInt(temp[0])-1;
			int b = Integer.parseInt(temp[1])-1;
			if(graph[a] == null) graph[a] = new HashSet<>();
			if(graph[b] == null) graph[b] = new HashSet<>();
			graph[a].add(b);
			graph[b].add(a);
		}
		input = convert(br.readLine().split(" "));
		solution();
	}
}
