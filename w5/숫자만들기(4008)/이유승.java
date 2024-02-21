import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// 숫자 만들기

public class Problem_4008 {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int[] nums; // 숫자
	static int[] ops; // 연산자 개수 저장한 배열
	static int numCnt; // 전체 숫자 개수
	static int max;
	static int min;
	public static void solution(int t) throws IOException {
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		rec(0, nums[0]); // 재귀 (백 트래킹?) 
		bw.append("#"+t+" "+(max-min)+"\n");
	}
	
	// 중복 순열
	public static void rec(int step, int result) {
		if(step == numCnt-1) {
			max = Math.max(max, result);
			min = Math.min(min, result);
			return;
		}
		for(int i=0; i<4; i++) {
			if(ops[i] > 0) { // 사용할 연산자가 있는 경우
				ops[i]--; // 사용 처리
				switch(i) {
				case 0: rec(step+1, result+nums[step+1]); break; // 더하기
				case 1: rec(step+1, result-nums[step+1]); break; // 빼기
				case 2: rec(step+1, result*nums[step+1]); break; // 곱하기
				case 3:
					if(nums[step+1] != 0) // 나누는 수가 0일 경우 처리
						rec(step+1, result/nums[step+1]); break; // 나누기
				}
				ops[i]++; // 다시 복구
			}
		}
	}
	
	public static int[] convert(String[] temp) {
		int[] result = new int[temp.length];
		for(int i=0; i<temp.length; i++)
			result[i] = Integer.parseInt(temp[i]);
		return result;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for(int i=0; i<t; i++) {
			numCnt = Integer.parseInt(br.readLine());
			ops = convert(br.readLine().split(" "));
			nums = convert(br.readLine().split(" "));
			solution(i+1);
		}
		bw.flush();
	}
}
