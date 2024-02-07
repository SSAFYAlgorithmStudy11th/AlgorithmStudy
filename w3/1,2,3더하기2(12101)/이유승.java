import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class Main {
	
	public static void solution(int n, int k) {
		StringBuilder sb = new StringBuilder();
		ArrayList<ArrayDeque<Integer>> dp = new ArrayList<ArrayDeque<Integer>>();
		dp.add(new ArrayDeque<Integer>());
		dp.get(0).add(1);
		int idx = 0;
		while(idx != n) {
			int size = dp.size();
			for(int i=0; i<size; i++) {
				ArrayDeque<Integer> numbers = dp.get(i);
				int first = numbers.peek();
				if(first+1 <= 3) {
					ArrayDeque<Integer> copy = new ArrayDeque<Integer>();
					copy.addAll(numbers);
					copy.addFirst(copy.poll()+1);
					dp.add(copy);
				}
				numbers.addFirst(1);
			}
			idx++;
		}
		ArrayDeque<Integer> result = null;
		try {
			result = dp.get(k);
			int size = result.size();
			for(int i=0; i<size-1; i++) {
				sb.append(result.poll());
				sb.append("+");
			}
			sb.append(result.poll());
			System.out.println(sb.toString());
		} catch (IndexOutOfBoundsException e) {
			System.out.println(-1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		int n = Integer.parseInt(temp[0])-1;
		int k = Integer.parseInt(temp[1])-1;
		solution(n, k);
	}
}
