import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static ArrayList<Integer> []array;
	static boolean []visited;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st=new StringTokenizer(br.readLine());
		int N=Integer.parseInt(st.nextToken());
		int M=Integer.parseInt(st.nextToken());
		
		array=new ArrayList[N+1];
		visited=new boolean[N+1];
		for(int i=1;i<N+1;i++)
			array[i]=new ArrayList<>();
		
		for(int i=0;i<M;i++) {
			st=new StringTokenizer(br.readLine());
			int s=Integer.parseInt(st.nextToken());
			int e=Integer.parseInt(st.nextToken());
			
			array[s].add(e);
			array[e].add(s);
		}
		
		int count=0;
		for(int i=1;i<=N;i++) {
			
			if(!visited[i]) {
				count++;
				dfs(i);
			}
		}
		
		bw.write(String.valueOf(count));
		bw.flush();
		bw.close();
	}
	
	public static void dfs(int v) {
		
		if(visited[v])
			return;
		
		visited[v]=true;
		
		for(int i:array[v]) {
			if(!visited[i])
				dfs(i);
		}
	}
}
