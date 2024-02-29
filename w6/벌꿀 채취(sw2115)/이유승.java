import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {
	static int n;
	static int m;
	static int c;
	static int[][] matrix;
	static int max;
	static int profit;
	public static int solution() {
		for(int i=0; i<n; i++)
			for(int j=0; j<n; j++) {
				int l = j;
				for(int k=i; k<n; k++) {
					for(; l<n; l++)
						simul(i, j, k, l);
					l=0;
				}
			}
		return max;
	}
	
	public static void simul(int y1, int x1, int y2, int x2) {
		if(x1+(m-1) >= n || x2+(m-1) >= n)
			return;
		if(y1 == y2 && x1+(m-1) >= x2)
			return;
		max = Math.max(max, cal(y1, x1) + cal(y2, x2));
		return;
	}
	
	public static int cal(int y, int x) {
		profit=0;
		dfs(y, x, 0, 0, 0);
		return profit;
	}
	
	public static void dfs(int y, int x, int sum, int powsum, int step) {
		if(step == m && sum<=c) {
			profit = Math.max(profit, powsum);
			return;
		}
		if(sum>c)
			return;
		dfs(y, x+1, sum, powsum, step+1);
		dfs(y, x+1, sum+matrix[y][x], powsum+matrix[y][x]*matrix[y][x], step+1);
	}
	
	public static int[] convert(String[] temp) {
		int[] result = new int[temp.length];
		for(int i=0; i<temp.length; i++)
			result[i] = Integer.parseInt(temp[i]);
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int t = Integer.parseInt(br.readLine());
		for(int i=0; i<t; i++) {
			int[] temp = convert(br.readLine().split(" "));
			n = temp[0];
			m = temp[1];
			c = temp[2];
			max = 0;
			matrix = new int[n][];
			for(int j=0; j<n; j++)
				matrix[j] = convert(br.readLine().split(" "));
			bw.append(String.valueOf("#"+(i+1)+" "+solution())+"\n"); 
		}
		bw.flush();
	}
}
