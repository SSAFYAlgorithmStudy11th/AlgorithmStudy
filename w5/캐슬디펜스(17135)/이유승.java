import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

// 캐슬 디펜스

public class Problem_17135 {
	
	public static boolean[][] matrix;
	public static boolean[][] visited;
	public static ArrayDeque<int[]> queue;
	public static ArrayList<int[]> delList;	
	public static ArrayList<int[]> backupList;
	
	public static int[] dx = {-1, 0, 1};
	public static int[] dy = {0, -1, 0};
	
	static int n;
	static int m;
	static int d;
	static int cnt;
	
	public static int solution() {
		int result = 0;
		// 조합, 결과 다 구해서 Max 찾기
		for(int i=0; i<m; i++)
			for(int j=i+1; j<m; j++)
				for(int k=j+1; k<m; k++)
					result = Math.max(result, simul(new int[] {i, j, k}));
		return result;
	}
	
	
	public static int simul(int[] point) {
		// 한줄씩 당김
		for(int i=n-1; i>=0; i--) {
			// 제일 가까운 target을 찾기
			for(int j=0; j<3; j++) {
				initVisited();
				queue.clear();
				queue.add(new int[] {i, point[j], 1});
				visited[i][point[j]] = true;
				int[] node;
				while(!queue.isEmpty()) {
					node = queue.poll();
					if(node[2] > d)
						break;
					if(matrix[node[0]][node[1]]) {
						// 바로 지우면 다음 궁수가 다른 타겟을 고르므로
						// delList를 두고 한번에 삭제
						delList.add(node);
						break;
					}
					for(int k=0; k<3; k++) {
						int x = node[1]+dx[k];
						int y = node[0]+dy[k];
						if(x<0||x>=m||y<0||y>=n)
							continue;
						queue.add(new int[] {y,x,node[2]+1});
						visited[y][x] = true;
					}
				}
			}
			for(int[] node : delList)
				matrix[node[0]][node[1]]=false;
			backupList.addAll(delList); 
			// 바꿨던거 다음 조합에서 다시 쓸려고 backupList에 다시 넣어둠
			// 썻던 객체 재활용해서 그대로 넣는거라 메모리 오버헤드는 적지 않나?;;
			delList.clear();
		}
		int changeCnt=0; // 전체 끝나고 남은 적 수 
		for(int i=0; i<n; i++)
			for(int j=0; j<m; j++)
				if(matrix[i][j])
					changeCnt++;
		
		for(int[] node : backupList) // 백업 해주기
			matrix[node[0]][node[1]]=true;
		
		backupList.clear();
		return cnt-changeCnt; // 죽인 적 수 리턴
	}
	
	public static void initVisited() {
		for(int i=0; i<n; i++)
			Arrays.fill(visited[i], false);
	}
	
	public static boolean[] convert(String[] temp) {
		boolean[] result = new boolean[temp.length];
		for(int i=0; i<temp.length; i++)
			result[i] = temp[i].equals("1") ? true : false;
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] temp = br.readLine().split(" ");
		n = Integer.parseInt(temp[0]);
		m = Integer.parseInt(temp[1]);
		d = Integer.parseInt(temp[2]);
		matrix = new boolean[n][m];
		visited = new boolean[n][m];
		queue = new ArrayDeque();
		delList = new ArrayList();
		backupList = new ArrayList();
		
		for(int i=0; i<n; i++)
			matrix[i] = convert(br.readLine().split(" "));
		
		for(int i=0; i<n; i++)
			for(int j=0; j<m; j++)
				if(matrix[i][j])
					cnt++;
		
		System.out.println(solution());
	}
}
