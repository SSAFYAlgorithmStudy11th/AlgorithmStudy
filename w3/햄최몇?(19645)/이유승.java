import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

public class Main {
	static class Case{
		int a;
		int b;
		Case(int a, int b) {
			this.a = a;
			this.b = b;
		}
		
		@Override
		public int hashCode() {
			return a*10000 + b;
		}
		@Override
		public boolean equals(Object obj) {
			return obj.hashCode() == this.hashCode();
		}
	}
	static int sum;
	static int cumsum;
	
	public static int solution(int[] arr) {
		HashSet<Case> predp = new HashSet<Case>();
		HashSet<Case> dp = null;
		predp.add(new Case(0, 0));
		for(int i=0; i<arr.length; i++) {
			int utility = arr[i];
			cumsum +=arr[i];
			dp = new HashSet<Case>();
			for(Case c : predp) {
				dp.add(new Case(c.a+utility, c.b));
				dp.add(new Case(c.a, c.b+utility));
				dp.add(new Case(c.a, c.b));
			}
			predp = dp;
		}
		int max = 0;

		for(Case c : dp) {
			int t = sum-(c.a+c.b);
			if(t <= c.a && t <= c.b && max < t)
				max = t;
		}
		return max;
	}
	
	public static boolean check(int a, int b) {
		int c = cumsum-(a+b);
		if(a >= c && b >= c)
			return true;
		return false;
	}
	
	public static int[] convert(String[] temp) {
		int[] result = new int[temp.length];
		for(int i=0; i<temp.length; i++) {
			result[i] = Integer.parseInt(temp[i]);
			sum += result[i];
		}
		return result;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br.readLine();
		int[] arr = convert(br.readLine().split(" "));
		Arrays.sort(arr);
		System.out.println(solution(arr));
	}
}
