import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {
	
	public static void solution(List<Integer>[] lists, int v, int e) {
		Integer[] value_matrix = new Integer[v];
		Queue<Integer> queue = new ArrayDeque<>();
		for(int i=0; i<v; i++) {
			if(value_matrix[i] == null) {
				value_matrix[i] = 1;
				queue.add(i);
				while(!queue.isEmpty()) {
					int vertex = queue.poll();
					for(int j : lists[vertex])
						if(value_matrix[j] == null) {
							queue.add(j);
							value_matrix[j] = value_matrix[vertex] * -1;
						}
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
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());

		
		List<Integer>[] list = null;
		
		for(int i=0; i<t; i++) {
			String[] temp = br.readLine().split(" ");
			int v = Integer.parseInt(temp[0]);
			int e = Integer.parseInt(temp[1]);
			list = new ArrayList[v];
			for(int j=0; j<v; j++)
				list[j] = new ArrayList<>();
			for(int j=0; j<e; j++) {
				temp = br.readLine().split(" ");
				int x = Integer.parseInt(temp[0])-1;
				int y = Integer.parseInt(temp[1])-1;
				list[x].add(y);
				list[y].add(x);
			}
			solution(list, v, e);
		}
	}
}
