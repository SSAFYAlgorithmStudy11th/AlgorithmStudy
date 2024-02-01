import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {
	
	public static void solution(List<Integer>[] lists, int v, int e) {
		// visited와 반전 여부
		Boolean[] value_matrix = new Boolean[v];
		Queue<Integer> queue = new ArrayDeque<>();
		// 0부터 v 개수 만큼 탐색
		for(int i=0; i<v; i++) {
			// 방문한적 없다면 초기값 setting
			if(value_matrix[i] == null) {
				value_matrix[i] = true;
				queue.add(i);
				while(!queue.isEmpty()) {
					int vertex = queue.poll();
					for(int j : lists[vertex])
						// 방문한적 없다면 큐에 삽입, 값은 toggle
						if(value_matrix[j] == null) {
							queue.add(j);
							value_matrix[j] = !value_matrix[vertex];
						}
						// 이전에 방문한적 있다면 값 비교해서 같으면 NO 출력
						else
							if(value_matrix[j] == value_matrix[vertex]) {
								System.out.println("NO");
								return;
							}
				}
			}
		}
		System.out.println("YES");
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		int[] v = new int[t];
		int[] e = new int[t];
		
		List<Integer>[][] lists = new ArrayList[t][];
		
		for(int i=0; i<t; i++) {
			String[] temp = br.readLine().split(" ");
			v[i] = Integer.parseInt(temp[0]);
			e[i] = Integer.parseInt(temp[1]);
			lists[i] = new ArrayList[v[i]];
			for(int j=0; j<v[i]; j++)
				lists[i][j] = new ArrayList<>();
			for(int j=0; j<e[i]; j++) {
				temp = br.readLine().split(" ");
				int x = Integer.parseInt(temp[0])-1;
				int y = Integer.parseInt(temp[1])-1;
				lists[i][x].add(y);
				lists[i][y].add(x);
			}
		}
		for(int i=0; i<t; i++)
			solution(lists[i], v[i], e[i]);
	}
}
