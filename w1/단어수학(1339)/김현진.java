import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {
	static int[] arr;
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		arr = new int[26];
		while(N --> 0) {
			String str = br.readLine();
			int pow = (int)Math.pow(10, str.length()-1);
			for(char c : str.toCharArray()) {
				arr[c-'A'] += pow;
				pow/=10;
			}
		}
		Arrays.sort(arr);
		
		long sum = 0;
		for(int i = arr.length - 1; i >= 17; i--) {
			sum += arr[i] * (i - 16);
		}
		System.out.println(sum);
	}
}
