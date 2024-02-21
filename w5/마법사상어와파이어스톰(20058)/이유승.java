import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

// 마법사 상어와 파이어스톰
// 1. 부분 행렬에 대해 오른쪽으로 돌리기
// 2. 돌린다음 녹는지 확인하고 녹이기
// 3. 입력받은 주문 수 만큼 반복
// 4. BFS로 탐색하면서 섬에서 최대와 전체 최대를 구해 반환

// + 배열돌리기
//		이거 그냥 돌리는 것보다, 전치행렬한 다음 행렬 상하 뒤집으면 쉽게 가능
//		1 2 3 4				  8 4 8 4		  		5 1 5 1
//		5 6 7 8		왼쪽	7 3 7 3		상하	6 2 6 2
//		1 2 3 4		전치	6 2 6 2		반전	7 3 7 3
//		5 6 7 8				  5 1 5 1	  			8 4 8 4
// 		왼쪽 대각선(/) 기준으로 전치행렬해서 뒤집으면 오른쪽으로 배열 회전한 결과 
//		오른쪽 대각선(\) 기준으로 전치행렬해서 뒤집으면 왼쪽으로 배열 회전한 결과

public class Problem_20058 {
	public static boolean[][] visited;
	public static int[][] matrix;
	public static int[] spells;
	public static int size;
	public static ArrayDeque<int[]> queue;
	
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	
	public static void solution() {
		for(int spell : spells) {
			rotate(spell);
			melt();
		}
			
		int totalSum = 0;
		int MaxCnt=0;
		
		// BFS : 섬중에 최댓값도 구해야 하므로
		for(int i=0; i<size; i++)
			for(int j=0; j<size; j++) {
				if(matrix[i][j] == 0 || visited[i][j])
					continue;
				int cnt = 0;
				int sum = 0;
				queue.add(new int[]{i, j});
				visited[i][j] = true;
				while(!queue.isEmpty()) {
					int[] node= queue.poll();
					cnt++;
					sum += matrix[node[0]][node[1]];
					for(int k=0; k<4; k++) {
						int x = node[1]+dx[k];
						int y = node[0]+dy[k];
						if(x<0 || x>=size || y<0 || y>=size || matrix[y][x] == 0 || visited[y][x])
							continue;
						visited[y][x] = true;
						queue.add(new int[] {y, x});
					}
				}
				MaxCnt = Math.max(MaxCnt, cnt);
				totalSum += sum;
			}
		System.out.println(totalSum);
		System.out.println(MaxCnt);
	}
	
	// 잘린 각 행렬의 시작점을 찾아 srotate 실행
	public static void rotate(int l) {
		int step = (int)Math.pow(2, l);
		for(int i=0; i<size; i+= step)
			for(int j=0; j<size; j+= step)
				srotate(i, j, step);
	}
	
	// 오른쪽으로 행렬을 돌리는 함수
	public static void srotate(int y, int x, int step) {
		int n = step;
		
		// 전치 행렬
		for(int i=y; i<y+step; i++) {
			for(int j=x; j<x+n; j++) {
				int temp = matrix[i][j];
				matrix[i][j] = matrix[y+(step-1)-(j-x)][x+(step-1)-(i-y)];
				matrix[y+(step-1)-(j-x)][x+(step-1)-(i-y)] = temp;
			}
			n--;
		}
		
		// 위아래 뒤집기
		for(int i=y; i<y+step/2; i++)
			for(int j=x; j<x+step; j++) {
				int temp = matrix[i][j];
				matrix[i][j] = matrix[y+(step-1)-(i-y)][j];
				matrix[y+(step-1)-(i-y)][j] = temp;
			}
	}
	
	// 얼음이고, check 함수로 녹는지 판단한 후, 녹으면 큐에 넣어서
	// 마지막에 하나씩 빼서 녹이는 함수
	public static void melt() {
		queue.clear();
		for(int i=0; i<size; i++)
			for(int j=0; j<size; j++)
				if(matrix[i][j] != 0 && check(i,j))
					queue.add(new int[] {i, j});
		int[] element;
		while(!queue.isEmpty()) {
			element = queue.poll();
			matrix[element[0]][element[1]]--;
		}
	}
	
	// 4방 탐색해서 녹는지 안녹는지 반환하는 함수
	public static boolean check(int i, int j) {
		int cnt = 0;
		for(int k=0; k<4; k++) {
			int y = i + dy[k];
			int x = j + dx[k];
			if(x<0 || x>=size || y<0 || y>= size || matrix[y][x] == 0)
				continue;
			cnt++;
		}
		if(cnt >= 3)
			return false;
		return true;
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
		int n = Integer.parseInt(temp[0]);
		size = (int)Math.pow(2, n);
		matrix = new int[size][];
		visited = new boolean[size][size];
		queue = new ArrayDeque<>();
		for(int i=0; i<size; i++)
			matrix[i] = convert(br.readLine().split(" "));
		spells = convert(br.readLine().split(" "));
		solution();
	}
}
