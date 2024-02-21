import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 게리 맨더링

public class Problem_17471 {
	public static boolean[][] matrix;
	public static boolean[] visited;
	public static int[] people;
	public static int n;
	public static int min = Integer.MAX_VALUE;
	public static int cnt; 
	public static int sum;
	public static int totalSum;
	
	public static int solution() {
		
		int subSetcnt = 0;			// 몇개의 서로소 집합인지?
		for(int i=0; i<n; i++) {
			if(!visited[i]) {
				visited[i] = true;
				dfs(i, visited);	//dfs 돌려서 확인
				subSetcnt++;
			}
		}
		Arrays.fill(visited, false);
		
		if(subSetcnt > 2)			// 2개보다 크다면 분할 못함
			return -1;
		
		if(subSetcnt == 2) {		// 2개라면 그 자체가 최적 분할
			visited[0] = true;
			dfs(0, visited);		// dfs 한번 돌림
			int sum=0;
			for(int i=0; i<n; i++)
				if(visited[i])		// 1개의 서로소 집합에서 합을 구함
					sum += people[i];
			return Math.abs(totalSum-2*sum);	//전체에서 빼주면 끝
			// totalSum - sum => 다른 서로소 집합의 합
			// sum => 내 서로소 집합의 합
			// (totalSum - sum) - sum =  totalSum-2*sum 
		}
		// 서로소 집합이 1개일때 쪼개보기
		// 집합을 두등분으로 쪼개므로 i는 몇개쪼갤지?
		// 8이라면 1/7 2/6 ... 에서 i는 1, 2 ... 4까지
		for(int i=1; i<=n/2; i++)
			rec(0, 0, 0, i);
		return min;
	}
	
	public static void rec(int idx, int step, int sum, int selectCnt) {
    // 두 등분으로 쪼갠 그래프가 각각 연결되어 있는지 체크크
		if(step == selectCnt) {
			// visited를 toggle해서 선택된 원소들을 false로 만듬
			// 선택된 원소로 만들어진 그래프가 서로소인지도 체크해야됨
			// dfs를 돌려서 visited가 모두 true이면
			// 선택된 원소들은 연결되어있음을 확인
			// 하나라도 false이면 연결되어있지 않음
			
			boolean[] toggled_copy = toggle(visited);
			for(int i=0; i<n; i++)
				if(!toggled_copy[i]) {
					toggled_copy[i] = true;
					dfs(i, toggled_copy);
					break;
				}	
			if(!check(toggled_copy))
				return;
			
			
			// 선택되지 않은 원소들 끼리도 완전 연결되어있는지 확인
			boolean[] copy = visited.clone();
			for(int i=0; i<n; i++)
				if(!copy[i]) {
					copy[i] = true;
					dfs(i, copy);
					break;
				}
			if(!check(copy))
				return;
			min = Math.min(min, Math.abs(totalSum-2*sum));
			return;
		}
		
		if(idx >= n)
			return;
		
		// 백트래킹
		
		visited[idx] = true;
		rec(idx+1, step+1, sum+people[idx], selectCnt); 
		visited[idx] = false;
		rec(idx+1, step, sum, selectCnt);
	}
	
	// 배열이 모두 true인지 검사하는 함수
	public static boolean check(boolean[] arr) {
		for(int i=0; i<n; i++)
			if(!arr[i])
				return false;
		return true;
	}
	
	// 단순 dfs
	public static void dfs(int vertex, boolean[] visited) {
		for(int i=0; i<n; i++)
			if(matrix[vertex][i] && !visited[i]) {
				visited[i] = true;
				dfs(i, visited);
			}
	}
	
	// 배열의 값을 토글하는 함수
	public static boolean[] toggle(boolean[] arr) {
		boolean[] result = arr.clone();
		for(int i=0; i<arr.length; i++)
			result[i] = !result[i];
		return result;
	}
	
	public static int[] convert(String[] temp) {
		int[] result = new int[temp.length];
		for(int i=0; i<temp.length; i++)
			result[i] = Integer.parseInt(temp[i]);
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		people = convert(br.readLine().split(" "));
		for(int p : people)
			totalSum += p;
		matrix = new boolean[n][n];
		visited = new boolean[n];
		int[] input;
		for(int i=0; i<n; i++) {
			input = convert(br.readLine().split(" "));
			for(int j=1; j<=input[0]; j++)
				matrix[i][input[j]-1] = true;
		}
		System.out.println(solution());
	}
}
