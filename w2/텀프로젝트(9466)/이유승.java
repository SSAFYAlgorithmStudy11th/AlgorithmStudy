import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;

public class Main {
	
	public static boolean[] visited;
	public static LinkedHashSet<Integer> stack;
	public static int cnt;
	public static int solution(int[] arr) {
		cnt = 0;
		int n = arr.length;
		visited = new boolean[n];
		for(int i=0; i<n; i++) {
      // 자기 자신을 가리키는지 확인
			if(i == arr[i])
				visited[i] = true; 
			if(!visited[i]) {
				stack.add(i);
				dfs(arr, arr[i]);
			}
		}
		return cnt;
	}
  
	public static void dfs(int[] arr, int current) {
    // stack에 이미 현재 값을 포함하고 있는 경우
		if(stack.contains((Integer)current)) {
      // cycle 발생 시점 check
			boolean flag = false;
			for(int a : (Integer[])stack.toArray(new Integer[1])) {
				visited[a] = true;
				if(a == current)
					flag = true;
        // cycle이 아닌 node들에 대해서 값 증가
				if(!flag)
					cnt++;
			}
			stack.clear();
			return;
		};
		// 이미 방문한 노드를 만났다면
		if(visited[current]) {
      // cycle이 없으므로 모두 값 증가
			for(int a : (Integer[])stack.toArray(new Integer[1])) {
				visited[a] = true;
				cnt++;
			}
			stack.clear();
			return;
		}
		stack.add(current);
		dfs(arr, arr[current]);
	}
	
	public static int[] convert(String[] temp) {
		int[] result = new int[temp.length];
		for(int i=0; i<temp.length; i++)
			result[i] = Integer.parseInt(temp[i])-1;
		return result;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		stack = new LinkedHashSet<Integer>();
		int[][] arrs = new int[n][];
		for(int i=0; i<n; i++)	{
			br.readLine();
			arrs[i] = convert(br.readLine().split(" "));
		}
		for(int i=0; i<n; i++)
			System.out.println(solution(arrs[i]));
	}
}
