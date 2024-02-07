import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	static List<Jewel> jewels;
	static List<Integer> backpacks;
	static int MIN = Integer.MAX_VALUE;
	static int backpackMAX = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		jewels = new ArrayList<>();
		backpacks = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			jewels.add(new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		for(int i = 0; i < K; i++) {
			backpacks.add(Integer.parseInt(br.readLine()));
			backpackMAX = Math.max(backpacks.get(backpacks.size() - 1), backpackMAX);
		}
		Collections.sort(backpacks);
		Collections.sort(jewels);
		long sum = 0;
		
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>((i1, i2) -> i2-i1);
		int j = 0;
		for(int i = 0; i < backpacks.size(); i++) {
			while(true) {
				if(j >= jewels.size()) {
					break;
				}
				Jewel cur = jewels.get(j);
				
				if(backpacks.get(i) < jewels.get(j).weight) break; // 종료조건
				pq.add(cur.val);
				j++;
			}
			
			if(!pq.isEmpty()) {
				sum += pq.poll();
			}
		}
		System.out.println(sum);
	}

	static class Jewel implements Comparable<Jewel>{
		int weight;
		int val;
		
		Jewel(int w, int v) {
			weight = w;
			val = v;
		}

		@Override
		public int compareTo(Main.Jewel o) {
			return this.weight - o.weight;
		}
	}
}
