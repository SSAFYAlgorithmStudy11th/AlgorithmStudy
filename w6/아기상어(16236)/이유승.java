import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

public class Main {
	static class Fish implements Comparable<Fish>{
		public Fish(int y, int x, int d) {
			this.y = y;
			this.x = x;
			this.d = d;
		}
		int y;
		int x;
		int d;
		@Override
		public int compareTo(Fish o) {
			if(this.y == o.y)
				return this.x - o.x;
			return this.y - o.y;
		}
	}
	
	static int n;
	static int[][] matrix;
	static boolean[][] visited;
	static int posX;
	static int posY;
	static int size = 2;
	static int time;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	public static int solution() {
		Queue<int[]> queue = new ArrayDeque<>();
		ArrayList<Fish> list = new ArrayList<Fish>();
		int cnt = 0;
		while(true) {
			queue.add(new int[] {posY, posX, 0});
			visited[posY][posX] = true;
			while(!queue.isEmpty()) {
				int[] space = queue.poll();
				if(!list.isEmpty() && list.get(0).d == space[2])
					break;
				for(int i=0; i<4; i++) {
					int y = space[0] + dy[i];
					int x = space[1] + dx[i];
					if(y<0 || y>=n || x<0 || x>=n)
						continue;
					if(!visited[y][x] && size >= matrix[y][x]) {
						if(size != matrix[y][x] && matrix[y][x] != 0)
							list.add(new Fish(y, x, space[2]+1));
						queue.add(new int[] {y, x, space[2]+1});
						visited[y][x] = true;
					}
				}
			}
			if(list.isEmpty())
				return time;
			list.sort(null);
			
			matrix[posY][posX] = 0;
			posY = list.get(0).y;
			posX = list.get(0).x;
			time += list.get(0).d;
			cnt++;
			if(cnt == size) {
				cnt = 0;
				size++;
			}
			matrix[posY][posX] = 0;
			
			list.clear();
			queue.clear();
			initVisited();
		}
	}
	public static void initVisited() {
		for(int i=0; i<n; i++)
		 	Arrays.fill(visited[i], false);
	}
	
	public static int[] convert(int row, String[] temp) {
		int[] result = new int[temp.length];
		for(int i=0; i<temp.length; i++) {
			result[i] = Integer.parseInt(temp[i]);
			if(result[i] == 9) {
				posX = i;
				posY = row;
			}
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		matrix = new int[n][];
		visited = new boolean[n][n];
		for(int i=0; i<n; i++)
			matrix[i] = convert(i, br.readLine().split(" "));
		System.out.println(solution());
	}
}
