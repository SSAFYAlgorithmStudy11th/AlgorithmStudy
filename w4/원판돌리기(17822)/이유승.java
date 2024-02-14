import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	static final int RIGHT = 0;
	static final int LEFT = 1;
	static int n;
	static int m;
	static int t;
	static int r[][];
	static int[][] matrix;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static ArrayList<int[]> deleteList;
	
	public static int solution() {
		int result = 0;
		for(int[] rv : r) {
			int x = rv[0];
			int d = rv[1];
			int k = rv[2];
			for(int i=0; i<k; i++)
				rotate(x, d);
			if(!delete(x))
				setup();
		}
		for(int i=0; i<n; i++)
			for(int j=0; j<m; j++)
				result += matrix[i][j];
		return result;
	}
	
	public static boolean delete(int x) {
		deleteList.clear();
		boolean flag = false;
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++)
				for(int k=0; k<4; k++) {
					int r = i + dy[k];
					int c = j + dx[k];
					if(r < 0 || r >= n)
						continue;
					if(c >= 0 && c < m) {
						if(matrix[i][j] !=0 && matrix[i][j] == matrix[r][c]) {
							flag = true;
							deleteList.add(new int[] {i,j});
							deleteList.add(new int[] {r,c});
						}
					}
					else if(c < 0) {
						if(matrix[i][j] !=0 && matrix[i][j] == matrix[r][m-1]) {
							flag = true;
							deleteList.add(new int[] {i,j});
							deleteList.add(new int[] {r,m-1});
						}
					}
					else if(c >= m) {
						if(matrix[i][j] !=0 && matrix[i][j] == matrix[r][0]) {
							flag = true;
							deleteList.add(new int[] {i,j});
							deleteList.add(new int[] {r,0});
						}
					}
				}
		}
		for(int[] c : deleteList)
			matrix[c[0]][c[1]] = 0;
		return flag;
	}
	
	public static void setup() {
		int cnt = n*m;
		int sum = 0;
		for(int i=0; i<n; i++)
			for(int j=0; j<m; j++) {
				if(matrix[i][j] == 0) {
					cnt--;
					continue;
				}
				sum += matrix[i][j];
			}
		double avg = (double)sum/cnt;
		for(int i=0; i<n; i++)
			for(int j=0; j<m; j++) {
				if(matrix[i][j] != 0 && matrix[i][j] > avg)
					matrix[i][j]--;
				else if(matrix[i][j] != 0 && matrix[i][j] < avg)
					matrix[i][j]++;
			}
	}
	
	public static void rotate(int x, int direction) {
		int save = 0;
		for(int i=x-1; i<n; i+=x) {
			switch(direction) {
			case RIGHT :
				save = matrix[i][m-1];
				for(int j=m-1; j>0; j--)
					matrix[i][j] = matrix[i][j-1];
				matrix[i][0] = save;
				break;
			case LEFT :
				save = matrix[i][0];
				for(int j=0; j<m-1; j++)
					matrix[i][j] = matrix[i][j+1];
				matrix[i][m-1] = save;
				break;
			}
		}
	}
	
	public static int[] convert(String[] temp) {
		int[] result = new int[temp.length];
		for(int i=0; i<temp.length; i++)
			result[i] = Integer.parseInt(temp[i]);
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		n = Integer.parseInt(temp[0]);
		m = Integer.parseInt(temp[1]);
		t = Integer.parseInt(temp[2]);
		
		matrix = new int[n][];
		r = new int[t][];
		deleteList = new ArrayList<>();
		
		for(int i=0; i<n; i++)
			matrix[i] = convert(br.readLine().split(" "));
		
		for(int i=0; i<t; i++)
			r[i] = convert(br.readLine().split(" "));
		
		System.out.println(solution());
	}
}
