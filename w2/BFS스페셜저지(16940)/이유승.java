import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;

public class Main {

	static ArrayList<Integer>[] graph;
	static boolean[] visited;
	
	public static void solution(ArrayDeque<Integer> input) {
		Queue<Integer> queue = new ArrayDeque<>();
		HashSet<Integer> set = new HashSet<>();
		int init = input.poll();
		if(init != 0) {
			System.out.println(0);
			return;
		}
		queue.add(init);
		visited[init] = true;
		while(!queue.isEmpty()) {
			int t = queue.poll();
			if(graph[t] != null) {
				for(int i : graph[t])
					if(!visited[i])
						set.add(i);
				int size = set.size();
				for(int i=0; i<size; i++) {
					int k = input.poll();
					if(!set.contains(k)) {
						System.out.println(0);
						return;
					};
					queue.add(k);
					visited[k] = true;
				}
				set.clear();
			}
		}
		System.out.println(1);
	}
	
	public static ArrayDeque<Integer> convert(String[] temp) {
		ArrayDeque<Integer> result = new ArrayDeque<Integer>();
		for(String str : temp)
			result.add(Integer.parseInt(str)-1);
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		graph = new ArrayList[n];
		visited = new boolean[n];
		for(int i=0; i<n-1; i++) {
			String[] temp = br.readLine().split(" ");
			int a = Integer.parseInt(temp[0])-1;
			int b = Integer.parseInt(temp[1])-1;
			if(graph[a] == null) graph[a] = new ArrayList<Integer>();
			if(graph[b] == null) graph[b] = new ArrayList<Integer>();
			graph[a].add(b);
			graph[b].add(a);
		}
		ArrayDeque<Integer> input = convert(br.readLine().split(" "));
		solution(input);

	}
}
