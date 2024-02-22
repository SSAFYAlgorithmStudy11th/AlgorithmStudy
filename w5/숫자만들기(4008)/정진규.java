import java.io.*;
import java.util.*;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
	static int n;
	static int[] num;
	static int[] count;
	static ArrayList<Integer> ari;
	static int max;
	static int min;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			n = Integer.parseInt(br.readLine());
			num = new int[n];
			count = new int[4];
			ari = new ArrayList<>();
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				count[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				num[i] = Integer.parseInt(st.nextToken());
			}
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			select(0);
			sb.append("#").append(tc).append(" ").append(max-min).append("\n");
		}
		System.out.println(sb);

	}

	public static void cal() {

		int sum = num[0];
		for(int i = 0;i<n-1;i++) {
			if(ari.get(i) == 0) {
				sum += num[i+1];
			}
			if(ari.get(i) == 1) {
				sum -= num[i+1];
			}
			if(ari.get(i) == 2) {
				sum *= num[i+1];
			}
			if(ari.get(i) == 3) {
				sum /= num[i+1];
			}
		}
		
		max = Math.max(max, sum);
		min = Math.min(min, sum);
	}

	public static void select(int cnt) {
		if (cnt == n - 1) {
			cal();
			return;
		}
		for (int i = 0; i < 4; i++) {
			if (count[i] > 0) {
				count[i]--;
				ari.add(i);
				select(cnt + 1);
				ari.remove(ari.size() - 1);
				count[i]++;
			}
		}
	}

}
