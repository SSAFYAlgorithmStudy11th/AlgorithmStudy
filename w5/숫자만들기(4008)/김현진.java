import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int[] ops; // + - * /
	static Deque<Integer> arr;
	static Deque<Character> op;
	static int total;
	static int[] visited;
	static int MAX = Integer.MIN_VALUE;
	static int MIN = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		int test_case = 1;
		while(T --> 0) {
			sb.append('#');
			sb.append(test_case++);
			sb.append(' ');
			N = Integer.parseInt(br.readLine());
			ops = new int[4];
			
			op = new ArrayDeque<>();
			arr = new ArrayDeque<>();
			MAX = Integer.MIN_VALUE;
			MIN = Integer.MAX_VALUE;
			total = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 4; i++) {
				ops[i] = Integer.parseInt(st.nextToken());
				total += ops[i];
			}
			visited = new int[total];

			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				arr.add(Integer.parseInt(st.nextToken()));
			}
			
			backtracking(0);
			
			sb.append(MAX - MIN);
			sb.append('\n');

		}
		System.out.println(sb);
	}
	
	
	static void backtracking(int cnt) {
		if(cnt == total) {
			//계산공식
			//최대값, 최솟값 갱신
			int val = arr.poll();

			MAX = Math.max(val, MAX);
			MIN = Math.min(val, MIN);
			arr.add(val);
			return;
		}

			for(int j = 0; j < 4; j++) {
				
				if(ops[j] > 0){
					ops[j]--;
					int val1 = arr.pollFirst();
					int val2 = arr.pollFirst();
					arr.addFirst(op(val1, val2, j));
					backtracking(cnt+1);
					arr.pollFirst();
					arr.addFirst(val2);
					arr.addFirst(val1);
					ops[j]++;
				}
			}
	}
	
	
	static int op(int val1, int val2, int op) {
		switch(op) {
		case 0:
			return val1 + val2;
		case 1:
			return val1 - val2;
		case 2:
			return val1 * val2;
		case 3:
			return val1 / val2;
		}
		return -1;
	}
	
	static char op(int op) {
		switch(op) {
		case 0:
			return '+';
		case 1:
			return '-';
		case 2:
			return '*';
		case 3:
			return '/';
		}
		return '\0';
	}
}
