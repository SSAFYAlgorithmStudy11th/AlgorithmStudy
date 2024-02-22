
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class gerrymandering {
	static int N;
	static int answer;
	static int[] population; //도시 인구수 배열
	static int[] area; //조합에서 도시를 나누기위해 사용하는 배열
	static ArrayList<Integer>[] list; //간선 정보를 저장하는 리스트 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		list = new ArrayList[N+1];
		population = new int[N+1]; 
		answer = Integer.MAX_VALUE;
		
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i<N+1; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i<N+1; i++) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			list[i] = new ArrayList<Integer>();
			
			for(int j =1; j<time+1; j++) {
				list[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		area = new int[N+1];
		for(int i = 1; i<N+1; i++)
			area[i] = i;
		
		combination(new int[N+1], 0,1);
		
		if(answer == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(answer);
		
		
		
		
	}
	
	// 정점 경우의 수를 나눠서 나눠지면 bfs 실행
	static void combination(int[] comb, int toselect, int startidx) {
		//원소가 하나라도 있으면 
		if(comb[0] != 0) {
			
			//선택된 원소는 도시 a, 선택되지 않은 원소는 도시 b로 넣음
			ArrayList<Integer> city_a = new ArrayList<>();
			ArrayList<Integer> city_b = new ArrayList<>();
			
			for(int a : area) {
				if(a!=0)
					city_b.add(a);
			}
			
			for(int a : comb) {
				if(a==0)
					break;
				city_a.add(a);
				city_b.remove(Integer.valueOf(a));
			}
			
			//bfs해서 각 선거구의 인구수 리턴
			int pop_a = bfs(city_a);
			int pop_b = bfs(city_b);
			
			if(pop_a != -1 && pop_b != -1) 
				answer = Math.min(Math.abs(pop_a-pop_b),answer);
			
			
		}
		//절반만 해도 조합 경우의수 다됨 
		if(toselect == N/2)
			return;
		
		for(int i = startidx; i<area.length; i++) {
			comb[toselect] = area[i];
			int[] copied_comb= comb.clone();
			combination(copied_comb,toselect+1,i+1);
		}
	}
	
	
	
	
	static int bfs(ArrayList<Integer> city) {
		boolean visited[] = new boolean[N+1];
		Queue<Integer> q = new ArrayDeque<>();
		q.add(city.get(0));
		int total_pop =0;
		
		//연결된 도시를 모두 방문후 인구수 더함
		while(!q.isEmpty()) {
			int city_num = q.poll();
			if(!visited[city_num]) {
				total_pop += population[city_num];
				visited[city_num] =true;
				for(int a: list[city_num]) {
					for(int b : city) {
						if(!visited[a] && a==b) {
							q.add(a);
						}
					}

				}
			}

		}
		
		//연결되지 않은 선거구가 있으면 -1 리턴
		for(int a : city) {
			if(!visited[a])
				return -1;
		}
		return total_pop;
	}
}
