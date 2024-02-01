import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static boolean[][] matrix;
	static int w;
	static int h;
	static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
	public static int solution() {
		int cnt=0;
		for(int i=0; i<h; i++)
			for(int j=0; j<w; j++)
				if(matrix[i][j]) {
					dfs(i, j);
					cnt++;
				}
		return cnt;
	}
	
	public static void dfs(int i, int j) {
		matrix[i][j] = false;
		for(int k=0; k<8; k++) {
			if(i+dx[k] < 0 || i+dx[k] >= h || j+dy[k] < 0 || j+dy[k] >= w)
				continue;
			if(matrix[i+dx[k]][j+dy[k]])
				dfs(i+dx[k], j+dy[k]);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			String[] temp = br.readLine().split(" ");
			w = Integer.parseInt(temp[0]);
			h = Integer.parseInt(temp[1]);
			if(w == 0 && h == 0)
				return;
			matrix = new boolean[h][w];
			for(int i=0; i<h; i++) {
				temp = br.readLine().split(" ");
				for(int j=0; j<w; j++)
					matrix[i][j] = temp[j].equals("1") ? true : false;
			}
			System.out.println(solution());
		}
	}
}
