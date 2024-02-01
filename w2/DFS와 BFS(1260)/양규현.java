package D0119_bj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;


public class dfs_bfs {
	static ArrayList<Integer> bfs_answer = new ArrayList<>();
	static ArrayList<Integer> dfs_answer = new ArrayList<>();
 	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());
		
		Queue<Integer> q = new LinkedList<>();
		Stack<Integer> stack = new Stack<>();
		ArrayList<Integer>[] list = new ArrayList[N+1];
		boolean[] visited = new boolean[N+1];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int index = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			
			// 양방향 간선 리스트에 넣기 
			if(list[index] == null) {
				list[index]= new ArrayList<Integer>();
				list[index].add(num);
			}
			else
				list[index].add(num);
			if(list[num] == null) {
				list[num]= new ArrayList<Integer>();
				list[num].add(index);
			}
			else
				list[num].add(index);
		}
		
		// 작은 정점 번호로 순회하기 위해 정렬
		for(int i = 1; i< N+1; i++) {
			if(list[i] != null)
				list[i].sort(null);
		}

		// dfs 실행
		stack.add(V);
		dfs(stack, list,visited);
		

		//visited 재사용 후 bsf 실행
		visited = new boolean[N+1];
		q.offer(V);
		bfs(q,list,visited);
		
	}
	
	static void dfs(Stack<Integer> stack, ArrayList<Integer>[] list, boolean[] visited) throws IOException {

		while (!stack.isEmpty()) {
			int num = stack.pop();
			if (!visited[num]) {
				visited[num] = true;
				dfs_answer.add(num);
				
				//스택 특성상 역순으로 스택 삽입 
				if(list[num] != null) {
			for (int i = list[num].size()-1; i >= 0; i--) {
					stack.push(list[num].get(i));
			}
				}
			}
		}
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for(int i : dfs_answer) {
			bw.write(i+" ");
		}
		bw.write("\n");
		bw.flush();
		
	}

	static void bfs(Queue<Integer> q,ArrayList<Integer>[] list, boolean[] visited) throws IOException {
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		
		while(!q.isEmpty()) {
			int node = q.poll();
			if(visited[node])
				continue;
			else {
				visited[node] = true;
				bfs_answer.add(node);
				if(list[node] != null) {
				for(int i : list[node]) {
					q.add(i);
				}
				}
			}
		}
		
		
		for(int i : bfs_answer) {
			bw.write(i+" ");
		}
		bw.write("\n");
		bw.close();

	}
}