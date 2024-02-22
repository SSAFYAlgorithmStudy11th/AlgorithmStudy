import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] arr;
	static int N;
	static int Q;
	static int L;
	static int answer;
	static int answer_size = 0;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Queue<Integer> q= new ArrayDeque<>();
		Queue<int[]> bfs_q = new ArrayDeque<>();
		
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		int size = 1<<N;
		
		arr = new int[size][size];
		
		
		
		for(int i = 0; i<size; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<size; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i<Q; i++) 
			q.add(Integer.parseInt(st.nextToken()));
		
		
		while(!q.isEmpty()) {
			boolean[][] visited = new boolean[size][size];
			L = q.poll();
			int pivot = 1<<L;
			bt(0,0,pivot,visited);

//			for(int[] a: arr) {
//				for(int b : a) {
//					System.out.print(b);
//				}
//				System.out.println();
//			}
//			System.out.println("===========");
//			
			arr = search();
//			
//			for(int[] a: arr) {
//				for(int b : a) {
//					System.out.print(b);
//				}
//				System.out.println();
//			}
//			System.out.println("************");
//			
		}
		
		boolean[][] visited = new boolean[size][size];
		
		for(int i = 0; i<size; i++) {
			for(int j = 0; j<size; j++) {
				if(arr[i][j] != 0) {
					bfs_q.add(new int[] {i,j});
					
					int total_size = 0;
					while(!bfs_q.isEmpty()) {
						int[] pos = bfs_q.poll();
						int x = pos[1];
						int y = pos[0];
						
						if(!visited[y][x]) {
							total_size++;
							visited[y][x] = true;
							
							for(int k = 0; k<4; k++) {
								int nx = x+dx[k];
								int ny = y+dy[k];
						
								if(nx < size && nx>=0 && ny<size && ny>= 0&&!visited[ny][nx] && arr[ny][nx] != 0){
									bfs_q.add(new int[] {ny,nx});
								}
			
						}
					
					}
				}
					answer_size = Math.max(total_size, answer_size);
			}
				
				
		}
		}
		

		for(int[] a: arr) {
			for(int b : a) {
				answer+=b;
			}
		}
		System.out.println(answer);
		System.out.println(answer_size);

	}
	// 3개이상 인접해있지 않은 칸 사방탐색하여 감소시키는 매서드
	static int[][] search() {
		
		int size = 1<<N;
		int[][] copy_arr = new int[size][size];
		
		for(int i = 0; i <size; i++) {
			for(int j = 0; j<size; j++) {
				copy_arr[i][j] = arr[i][j];
			}
		}
		
		for(int i = 0; i<1<<N; i++) {
			for(int j = 0; j<1<<N; j++) {
				
				int count = 0;
				
				for(int k = 0; k<4; k++) {
					int ny = i + dy[k];
					int nx = j + dx[k];
					
					if(ny < size && ny >=0 &&nx < size && nx >= 0) {
						if(arr[ny][nx] > 0)
							count++;
					}
				}
				if(count < 3 && arr[i][j] >0)
					copy_arr[i][j]--;
			}
		}
		
		return copy_arr;
		
	}
	
	//회전 백트래킹
	static void bt(int start_x, int start_y,int pivot,boolean[][] visited) {
		int size = 1<<N;
		visited[start_y][start_x] = true;
		
		spin(start_x,start_y,pivot);
		
		if(start_x+pivot <size &&!visited[start_y][start_x+pivot])
			bt(start_x+pivot,start_y,pivot,visited);
		if(start_y+pivot <size &&!visited[start_y+pivot][start_x])
			bt(start_x,start_y+pivot,pivot,visited);
		if(start_y+pivot <size && start_x +pivot<size &&!visited[start_y+pivot][start_x+pivot])
			bt(start_x+pivot,start_y+pivot,pivot,visited);
		
	}
	
	//회전 매서드
	static void spin(int start_x, int start_y,int pivot) {
		int[][] copy_arr = new int[pivot][pivot];
		
		
		for(int i = 0; i <pivot; i++) {
			for(int j = 0; j<pivot; j++) {
				copy_arr[i][j] = arr[i+start_y][j+start_x];
			}
		}
		
		for(int i = 0; i <pivot; i++) {
			for(int j = 0; j<pivot; j++) {
				arr[j+start_y][start_x+pivot-i-1] = copy_arr[i][j];
			}
		}

	}

}
