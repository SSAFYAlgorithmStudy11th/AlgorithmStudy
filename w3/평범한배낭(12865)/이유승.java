import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	static class Stuff implements Comparable<Stuff>{
		Stuff(int weight, int value){
			this.weight = weight;
			this.value = value;
		}
		int weight;
		int value;
		@Override
		public int compareTo(Stuff o) {
			if(this.weight - o.weight == 0)
				return o.value - this.value;
			return this.weight - o.weight;
		}
	}
	static int n;
	static int k;
	static ArrayList<Stuff> stuffs;
	static int[] prevalues;
	static int[] values;
	
	public static int solution() {
		for(Stuff stuff : stuffs) {
			for(int i=0; i<=k; i++)
				if(stuff.weight+i<= k)
					values[stuff.weight+i] = Math.max(prevalues[stuff.weight+i], stuff.value+prevalues[i]);
			prevalues = values.clone();
		}
		return values[k];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		n = Integer.parseInt(temp[0]);
		k = Integer.parseInt(temp[1]);
		prevalues = new int[k+1];
		values = new int[k+1];
		stuffs = new ArrayList<Stuff>();
		
		for(int i=0; i<n; i++) {
			temp = br.readLine().split(" ");
			int weight = Integer.parseInt(temp[0]);
			int value = Integer.parseInt(temp[1]);
			stuffs.add(new Stuff(weight, value));
		}
		
		stuffs.sort(null);
		System.out.println(solution());
	}
}
