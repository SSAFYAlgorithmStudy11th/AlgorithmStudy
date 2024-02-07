import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
	static Deque<Integer> nq = new LinkedList<>();
	static boolean isnegative = false;
	static int answer = 0; 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (char a : br.readLine().toCharArray()) {
			if (a == '+' || a == '-') {
				operation();
			} else //연산자가 아니면 dequeue에 삽입 
				nq.addFirst(a - 48);
			if (a == '-')
				isnegative = true;
		}
		
		//마지막 숫자 연산
		operation();
		System.out.println(answer);

	}
	
	static void operation() {
		int flag = 1;
		//한번이라도 -가 나왔다면 무조건 빼기 연산
		if (isnegative) {
			while (!nq.isEmpty()) {
				//
				answer -= nq.pop() * flag;
				flag *= 10;
			}
		} else {
			while (!nq.isEmpty()) {
				answer += nq.pop() * flag;
				flag *= 10;
			}
		}
		
	}
}
