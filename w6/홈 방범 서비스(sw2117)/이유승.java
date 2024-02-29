import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {
	
	static int n;
	static int m;
	static boolean[][] matrix;
	
	public static boolean[] convert(String[] temp) {
		boolean[] result = new boolean[temp.length];
		for(int i=0; i<temp.length; i++)
			result[i] = temp[i].equals("1") ? true : false;
		return result;
	}
	
	public static int solution() {
		int k = 1;
		int maxCnt = 0;
		while(k <= n+1) {
			int cost = k*k+(k-1)*(k-1);
			for(int i=0; i<n; i++)
				for(int j=0; j<n; j++) {
					int cnt = getHomeCnt(i, j, k-1);
					if(cnt*m-cost >= 0)
						maxCnt = Math.max(maxCnt, cnt);
				}
			k++;
		}
		return maxCnt;
	}
	
	public static int getHomeCnt(int y, int x, int k) {
		int cnt=0;
		for(int i= -k; i<=k; i++)
			for(int j = -k; j<=k; j++) {
				if(k<(Math.abs(i)+Math.abs(j)))
					continue;
				if(y+i<0 || y+i>=n || x+j<0 || x+j>=n)
					continue;
				if(matrix[y+i][x+j])
					cnt++;
			}
		return cnt;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int t = Integer.parseInt(br.readLine());
		for(int i=0; i<t; i++) {
			String[] temp = br.readLine().split(" ");
			n = Integer.parseInt(temp[0]);
			m = Integer.parseInt(temp[1]);
			matrix = new boolean[n][];
			for(int j=0; j<n; j++)
				matrix[j] = convert(br.readLine().split(" "));
			bw.append("#"+(i+1)+" "+solution()+"\n");
		}
		bw.flush();
	}
}
