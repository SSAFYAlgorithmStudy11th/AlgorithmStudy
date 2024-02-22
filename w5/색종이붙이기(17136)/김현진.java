import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main{
	static int[][] arr;
	static boolean[][] visited;
	static int[] papers;
	static int MIN = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arr = new int[10][10];
		visited = new boolean[10][10];
		papers = new int[6];
		
		for(int i = 1; i <= 5; i++) {
			papers[i] = 5;
		}
		
		for(int i = 0; i < 10; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 10; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		backtracking(0, 0);
		System.out.println(MIN == Integer.MAX_VALUE ? -1 : MIN);
	}
	
	static void backtracking(int depth, int cnt) {
		if(depth == 100) {
			MIN = Math.min(cnt, MIN);
			return;
		}
		int curY = depth/10;
		int curX = depth%10;
		if(arr[curY][curX] == 0) {
			backtracking(depth+1, cnt);
			return;
		}
		
		if(visited[curY][curX]) {
			backtracking(depth+1, cnt);
			return;
		}
		
		for(int i = 5; i >= 1; i--) {
			if(papers[i] > 0 && canUse(curX, curY, i)) {
				papers[i] --;
				setVal(curX, curY, i, true);
				backtracking(depth+1, cnt+1);
				setVal(curX, curY, i, false);
				papers[i]++;
			}
		}
	
	}
	
	
	static boolean canUse(int x, int y, int v) {
		if(!(0 <= x + v && x + v < 11 && 0 <= y + v && y + v < 11) ) {
			return false;
		}
		for(int i = y; i < y + v; i++) {
			for(int j = x; j < x + v; j++) {
				if(visited[i][j] || arr[i][j] == 0) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	static void setVal(int x, int y, int size, boolean b) {
		for(int i = y; i < y + size; i++) {
			for(int j = x; j < x + size; j++) {
				visited[i][j] = b;
			}
		}
	}

}
