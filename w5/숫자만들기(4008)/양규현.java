
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class making_number {
	
	static int N;
	static int max_answer;
	static int min_answer;
	static ArrayList<Integer> list;
	static int[] num_arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int Test = Integer.parseInt(br.readLine());
		
		for(int T = 1; T<Test+1; T++) {
			
			int N = Integer.parseInt(br.readLine());
			list = new ArrayList<>();
			num_arr = new int[N];
			max_answer = Integer.MIN_VALUE;
			min_answer = Integer.MAX_VALUE;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int i = 0; i<4; i++)
				list.add(0);
			// '+' 갯수
			int size = Integer.parseInt(st.nextToken());
			for(int i = 0; i<size; i++) 
				list.set(0, list.get(0)+1);
			// '-' 갯수
			size = Integer.parseInt(st.nextToken());
			for(int i = 0; i<size; i++) 
				list.set(1, list.get(1)+1);

			// '*' 갯수
			size = Integer.parseInt(st.nextToken());
			for(int i = 0; i<size; i++) 
				list.set(2, list.get(2)+1);

			// '/' 갯수
			size = Integer.parseInt(st.nextToken());
			for(int i = 0; i<size; i++) 
				list.set(3, list.get(3)+1);
			
			
			st= new StringTokenizer(br.readLine());
			
			//숫자 배열 초기화
			for(int i = 0; i<N; i++) 
				num_arr[i] = Integer.parseInt(st.nextToken());
			
			for(int i = 0; i<4; i++) {
				if(list.get(i)!= 0) {
					list.set(i, list.get(i)-1);
					char a =' ';
					switch(i) {
					case 0:
						a = '+';
						break;
					case 1:
						a = '-';
						break;
					case 2:
						a = '*';
						break;
					case 3:
						a = '/';
						break;
					}
					bt(1,num_arr[0],a);
					list.set(i, list.get(i)+1);
				}
			}
			System.out.println("max answer is " + max_answer);
			System.out.println("min answer is " + min_answer);
			bw.append("#").append(Integer.toString(T)).append(" ").append(Integer.toString(max_answer-min_answer)).append("\n");

		}
		bw.close();
		
	}
	
	//백트래킹으로 모든 연산자 경우의 수로 계산
	static void bt(int depth, int total,char operator) {
		switch(operator) {
			case '+':
				total += num_arr[depth];
				break;
			case '-':
				total -= num_arr[depth];
				break;
			case '*':
				total *= num_arr[depth];
				break;
			case '/':
				total /= num_arr[depth];
				break;
		}
		
		
		if(depth == N) {
			max_answer = Math.max(max_answer, total);
			min_answer = Math.min(min_answer, total);
			return;
		}
		
		for(int i = 0; i<4; i++) {
			if(list.get(i) > 0) {
				list.set(i, list.get(i)-1);
				
				char a =' ';
				switch(list.get(i)) {
				case 0:
					a = '+';
					break;
				case 1:
					a = '-';
					break;
				case 2:
					a = '*';
					break;
				case 3:
					a = '/';
					break;
				}
				
				bt(depth+1,total,a);
				list.set(i, list.get(i)+1);
				
			}
		}
	}

}
