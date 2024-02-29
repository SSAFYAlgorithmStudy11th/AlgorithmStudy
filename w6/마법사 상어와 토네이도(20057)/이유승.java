import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[][] matrix;
	static int n;
	static int sum;
	
	static final int LEFT = 0;
	static final int DOWN = 1;
	static final int RIGHT = 2;
	static final int UP = 3;
	
	static int[][] sdx = new int[][] {
		{-2, -1, -1, -1, 0, 0, 0, 0, 1, 1},
		{0, 1, 0, -1, 2, 1, -1, -2, 1, -1},
		{2, 1, 1, 1, 0, 0, 0, 0, -1, -1},
		{0, -1, 0, 1, -2, -1, 1, 2, -1, 1}
	};
	static int[][] sdy = new int[][] {
		{0, -1, 0, 1, -2, -1, 1, 2, -1, 1},
		{2, 1, 1, 1, 0, 0, 0, 0, -1, -1},
		{0, -1, 0, 1, -2, -1, 1, 2, -1, 1},
		{-2, -1, -1, -1, 0, 0, 0, 0, 1, 1}
	};
	
	static int[] dx = new int[] {-1, 0, 1, 0};
	static int[] dy = new int[] {0, 1, 0, -1};
	
	static int[] cost = new int[] {5, 10, 0, 10, 2, 7, 7, 2, 1, 1};
	
	public static int solution() {
		int posX = n/2;
		int posY = n/2;
		int direction = LEFT;
		int step = 1;
		int cnt = 0;
		
		while(true) {
			if(cnt == 2) {
				cnt = 0;
				step++;
			}
			for(int i=0; i<step; i++) {
				posX += dx[direction];
				posY += dy[direction];
				scatter(posY, posX, direction);
				if((posX ==0 && posY==0))
					return sum;
			}
			direction = (direction+1)%4;
			cnt++;
		}
	}
	public static void scatter(int y, int x, int direction) {
		int pre = matrix[y][x];
		int cur = matrix[y][x];
		for(int i=0; i<10; i++) {
			if(i==2) continue;
			int cy = y+sdy[direction][i];
			int cx = x+sdx[direction][i];
			if(cy<0 || cy>=n || cx<0 || cx>=n) {
				sum += pre*cost[i]/100;
				cur -= pre*cost[i]/100;
				continue;
			}
			matrix[cy][cx] += pre*cost[i]/100;
			cur -= pre*cost[i]/100;
		}
		
		int cy = y+sdy[direction][2];
		int cx = x+sdx[direction][2];
		
		if(cy<0 || cy>=n || cx<0 || cx>=n)
			sum += cur;
		else
			matrix[cy][cx] += cur;
		
		matrix[y][x] = 0;
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
		matrix = new int[n][];
		for(int i=0; i<n; i++)
			matrix[i] = convert(br.readLine().split(" "));
		System.out.println(solution());
	}
}
