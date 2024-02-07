import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	
	public static int solution(Queue<Integer> nqueue, Queue<Character> oqueue) {
		int sum = nqueue.poll();
		char operator;
		while(!oqueue.isEmpty()) {
			operator = oqueue.poll();
			if(operator == '+')
				sum += nqueue.poll();
			else {
				while(!oqueue.isEmpty() && oqueue.peek() != '-') {
					sum -= nqueue.poll();
					oqueue.poll();
				}
				sum -= nqueue.poll();
			}
		}
		return sum;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Queue<Integer> nqueue = new ArrayDeque<Integer>();
		Queue<Character> oqueue = new ArrayDeque<Character>();
		
		for(char a : br.readLine().toCharArray()) {
			switch(a) {
			case '+', '-':
				nqueue.add(Integer.parseInt(sb.toString()));
				sb.setLength(0);
				oqueue.add(a);
				break;
			default:
				sb.append(a);
			}
		}
		nqueue.add(Integer.parseInt(sb.toString()));
		
		System.out.println(solution(nqueue, oqueue));
	}
}
