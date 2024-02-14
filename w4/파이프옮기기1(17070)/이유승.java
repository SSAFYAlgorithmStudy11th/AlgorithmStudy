import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static boolean[][] matrix;
	static int cnt;
	static int n;
	static final int RIGHT = 0;
	static final int DOWN = 1;
	static final int CROSS = 2;
	
	public static boolean[] convert(String[] temp) {
		boolean[] result = new boolean[temp.length];
		for(int i=0; i<temp.length; i++)
			result[i] = temp[i].equals("0") ? true : false;
		return result;
	}
	
	public static int solution() {
		rec(0, 1, RIGHT);
		return cnt;
	}
	
	public static void rec(int i, int j, int direction) {
		if(i == n-1 && j == n-1) {
			cnt++;
			return;
		}
		
		switch(direction) {
		case RIGHT:
			if(j+1<n && matrix[i][j+1]) rec(i,j+1,RIGHT);
			break;
		case DOWN:
			if(i+1<n && matrix[i+1][j]) rec(i+1,j,DOWN);
			break;
		case CROSS:
			if(j+1<n && matrix[i][j+1]) rec(i,j+1,RIGHT);
			if(i+1<n && matrix[i+1][j]) rec(i+1,j,DOWN);
		}
		if(j+1<n && i+1<n && matrix[i][j+1] && matrix[i+1][j] && matrix[i+1][j+1]) rec(i+1,j+1,CROSS);
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		matrix = new boolean[n][];
		for(int i=0; i<n; i++)
			matrix[i] = convert(br.readLine().split(" "));
		System.out.println(solution());
	}
}
