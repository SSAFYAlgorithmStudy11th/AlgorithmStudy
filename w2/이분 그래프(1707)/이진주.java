import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static ArrayList<Integer>[]graph;
	static int V,E;
	static int[]color;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb=new StringBuilder();
		
		int Tc=Integer.parseInt(br.readLine());
		for(int tc=0;tc<Tc;tc++) {
			
			st=new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			graph=new ArrayList[V+1];
			color=new int[V+1];
			for(int i=1;i<=V;i++)
				graph[i]=new ArrayList<>();
			
			for(int i=0;i<E;i++) {
				st=new StringTokenizer(br.readLine());
				
				int s=Integer.parseInt(st.nextToken());
				int e=Integer.parseInt(st.nextToken());
				
				graph[s].add(e);
				graph[e].add(s);
				
			}
			boolean res=true;
			for(int i=1;i<=V;i++) {
				
				if(color[i]==0) {
					color[i]=1;
					res=bfs(i);
				}
				if(!res) break;	
			}
			sb.append(res?"YES":"NO").append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	static boolean bfs(int v) {
		
		Queue<Integer>queue=new LinkedList<>();
		queue.offer(v);
		
		while(!queue.isEmpty()) {
			
			int now=queue.poll();
			
			for(int n:graph[now]) {
				
				if(color[now]==color[n])
					return false;
				if(color[n]==0) {
					color[n]=color[now]*-1;
					queue.add(n);
				}
			}
		}
		return true;
	}
}
