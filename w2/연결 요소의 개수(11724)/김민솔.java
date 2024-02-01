import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N, V;
	static boolean[] visited;
	static ArrayList<ArrayList<Integer>> arr;
	static int res;
	
	static void dfs(int s) {
		visited[s]= true;
		
		for(int i=0;i<arr.get(s).size();i++) {
			if(!visited[arr.get(s).get(i)]) {
				dfs(arr.get(s).get(i));
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());
		
		res =0;
		visited = new boolean[N+1];
		arr = new ArrayList<>();
		
		for(int i =0;i<N+1;i++) {
			arr.add(new ArrayList<Integer>());
		}
		
		for(int i=0;i<V;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			arr.get(a).add(b);
			arr.get(b).add(a);
		}
		
		for(int i=1;i<N+1;i++) {
			if(!visited[i]) {
				res++;
				dfs(i);
			}
		}
		
		System.out.println(res);
		
		
	}

}
