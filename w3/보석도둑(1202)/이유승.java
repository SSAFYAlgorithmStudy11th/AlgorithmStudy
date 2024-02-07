import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.TreeMap;

public class Main {
	static int n;
	static int k;
	static Jewel[] jewels;
	static TreeMap<Integer, Integer> bags;
	
	static class Jewel implements Comparable<Jewel>{
		Jewel(int weight, int value){
			this.weight = weight;
			this.value = value;
		}
		int weight;
		int value;
		@Override
		public int compareTo(Jewel o) {
			return o.value - this.value;
		}
	}
	
	public static long solution() {
		long totalValue=0;
		Arrays.sort(jewels);
		int idx = 0;
		while(!bags.isEmpty() && idx != jewels.length) {
			Jewel jewel = jewels[idx++];
			Integer bagWeight = bags.ceilingKey(jewel.weight);
			if(bagWeight != null) {
				int temp = bags.get(bagWeight);
				if(temp == 1)
					bags.remove(bagWeight);
				else
					bags.put(bagWeight, temp-1);
				totalValue += jewel.value;
			}
		}
		return totalValue;
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		n = Integer.parseInt(temp[0]);
		k = Integer.parseInt(temp[1]);
		
		jewels = new Jewel[n];
		bags = new TreeMap<Integer, Integer>();
		
		for(int i=0; i<n; i++) {
			temp = br.readLine().split(" ");
			int weight = Integer.parseInt(temp[0]);
			int value = Integer.parseInt(temp[1]);
			jewels[i] = new Jewel(weight, value);
		}
		for(int i=0; i<k; i++) {
			int weight = Integer.parseInt(br.readLine());
			bags.putIfAbsent(weight, 0);
			bags.put(weight, bags.get(weight)+1);
		}
		System.out.println(solution());
	}
}
