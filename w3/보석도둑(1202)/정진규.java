import java.io.*;
import java.util.*;

//보석도둑
public class Main {
	static class Product implements Comparable<Product> {

		int weight;
		int value;
		public Product(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}
		
		//무게순, 같다면 가치 역순
		@Override
		public int compareTo(Product o) {
			// TODO Auto-generated method stub
			if(this.weight == o.weight)
				return -(this.value - o.value);
			return this.weight - o.weight;
		}
		
	}
	
	static int n, k;

	public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        ArrayList<Product> jew = new ArrayList<>();
        ArrayList<Integer> bag = new ArrayList<>();
        
        for(int i = 0;i<n;i++) {
        	st = new StringTokenizer(br.readLine());
        	int w = Integer.parseInt(st.nextToken());
        	int v = Integer.parseInt(st.nextToken());
        	
        	jew.add(new Product(w, v));
        }
        
        Collections.sort(jew);
        
        for(int i = 0;i<k;i++) {
        	st = new StringTokenizer(br.readLine());
        	bag.add(Integer.parseInt(st.nextToken()));
        }
        
        Collections.sort(bag);
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        int j = 0;
        long answer =0;
        for(int i = 0;i<k;i++) {
        	//가방과 무게가 같거나 낮은 보석들을 다 넣어
        	while(j<n && bag.get(i) >= jew.get(j).weight) {
        		pq.add(jew.get(j).value);
        		j++;
        	}
        	
        	
        	if(!pq.isEmpty())
        		answer += pq.poll();
        }
        
        System.out.println(answer);
        
    }
	
	
}
