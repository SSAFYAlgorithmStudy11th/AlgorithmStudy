import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;



public class Main {
	
	//사이클 체크를 위한 유니온 파인드 구현
	public static class UnionFind {
		int[] parent;
		int[] rank;
		
		
		UnionFind(int size){
			parent = new int[size+1];
			rank = new int[size+1];
			for(int i = 1; i <size+1;i++) {
				parent[i] = i;
				rank[i] = 0;
			}
		}
		
		int find(int x) {
			if(parent[x] != x) {
				parent[x] = find(parent[x]);
			}
			return parent[x];
		}
		
		public void union(int x, int y) {
			int rootX = find(x);
			int rootY = find(y);
			
			if(rootX != rootY) {
				if(rank[rootX] > rank[rootY]) {
					parent[rootY] = rootX;
				}
				else if(rank[rootY] > rank[rootX]) {
					parent[rootX] = rootY;
				}
				else {
					parent[rootY] = rootX;
					rank[rootX]++;
				}
			}
		}
		
		//필요 없는듯?
		public boolean isConnected(int x, int y) {
			return find(x)==find(y);
		}
		
		
		
	}
	static int V; //정점 갯수
	static int E; //간선 갯수
	static int answer = 0;
	static int counter =0;
	static ArrayList<int[]> list = new ArrayList<int[]>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		E = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i <E; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			list.add(new int[] {A,B,C});
		}
		
		UnionFind uf = new UnionFind(V);
		
		
		//크루스칼 알고리즘을 위해 가중치를 기준으로 오름차순 정렬
		Collections.sort(list,new Comparator<int[]>(){
			
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[2],o2[2]);
			}
			
		});
		
		for(int[] arr: list) {
			//루트노드가 같지않다면(같다면 사이클 형성) 
			if(uf.find(arr[0]) != uf.find(arr[1])) {
				uf.union(arr[0], arr[1]);
				counter++;
				answer += arr[2];
				//이어진 간선의 갯수가 정점갯수-1 개 (최소 스패닝 트리의 간선 갯수)이라면 종료 
				if(counter == V-1) {
					break;
				}
			}
		}
		System.out.println(answer);

		}
	
}
