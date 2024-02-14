import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	
	static int[][] matrix;
	static ArrayList<int[]> list;
	static int n;
	static int m;
	static int h;
	
	public static int solution() {
		int cnt = 0;
		int[] result = new int[n+1];
		
		int[] first = null;
		int[] second = null;
		int[] third = null;
		
		while(cnt != 4) {
			if(cnt == 0)
				if(isMatch(result, first, second, third))
					return cnt;
			if(cnt == 1)
				for(int i=0; i<list.size(); i++) {
					first = list.get(i);
					if(isMatch(result, first, second, third))
						return cnt;
				}
			if(cnt == 2)
				for(int i=0; i<list.size(); i++) {
					first = list.get(i);
					for(int j=i+1; j<list.size(); j++) {
						second = list.get(j);
						if(first[0] == second[0] && first[1] == second[1]-1)
							continue;
						if(isMatch(result, first, second, third))
							return cnt;
					}
				}
			if(cnt == 3)
				for(int i=0; i<list.size(); i++) {
					first = list.get(i);
					for(int j=i+1; j<list.size(); j++) {
						second = list.get(j);
						if(first[0] == second[0] && first[1] == second[1]-1)
							continue;
						for(int k=j+1; k<list.size(); k++) {
							third = list.get(k);
							if(second[0] == third[0] && second[1] == third[1]-1)
								continue;
							if(isMatch(result, first, second, third))
								return cnt;
						}
					}
				}
			cnt++;
		}
		return -1;
	}
	
	public static void init(int[] input) {
		for(int i=1; i<=n; i++)
			input[i] = i;
	}
	
	public static void start(int[] input) {
		for(int i=1; i<=h; i++)
			for(int j=1; j<n; j++)
				if(matrix[i][j]==1)
					swap(input, j, j+1);
	}
	
	public static boolean isMatch(int[] input, int[] first, int[] second, int[] third) {
		if(first != null) matrix[first[0]][first[1]] = 1;
		if(second != null) matrix[second[0]][second[1]] = 1;
		if(third != null) matrix[third[0]][third[1]] = 1;
		
		init(input);
		start(input);
		
		if(first != null) matrix[first[0]][first[1]] = 0;
		if(second != null) matrix[second[0]][second[1]] = 0;
		if(third != null) matrix[third[0]][third[1]] = 0;
		
		for(int i=1; i<=n; i++)
			if(input[i] != i)
				return false;
		
		return true;
	}
	
	public static void swap(int[] result, int i, int j) {
		int idxI = 0;
		int idxJ = 0;
		for(int k=0; k<result.length; k++) {
			if(result[k] == i) idxI = k;
			if(result[k] == j) idxJ = k;
		}
		int temp = result[idxI];
		result[idxI] = result[idxJ];
		result[idxJ] = temp;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		list = new ArrayList<int[]>();
		String[] temp = br.readLine().split(" ");
		n = Integer.parseInt(temp[0]);
		m = Integer.parseInt(temp[1]);
		h = Integer.parseInt(temp[2]);
		matrix = new int[h+1][n+1];
		
		for(int i=0; i<m; i++) {
			temp = br.readLine().split(" ");
			int y = Integer.parseInt(temp[0]);
			int x = Integer.parseInt(temp[1]);
			matrix[y][x-1] = 2;
			matrix[y][x] = 1;
			matrix[y][x+1] = 2;
		}
		
		for(int i=1; i<=h; i++)
			for(int j=1; j<n; j++)
				if(matrix[i][j]==0)
					list.add(new int[] {i,j});
		
		System.out.println(solution());
	}
}
