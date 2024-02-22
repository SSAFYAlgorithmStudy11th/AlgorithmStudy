import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static ArrayList<Integer>[] list;
	static int[] peoples;
	static int[] group;
	static boolean[]visited;
	static int min;
	static int N;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());

		list = new ArrayList[N + 1];
		peoples = new int[N + 1];

		// list 생성
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}

		// 인구
		for (int i = 1; i <= N; i++) {
			peoples[i] = Integer.parseInt(st.nextToken());
		}

		// 인접구역
		for (int i = 1; i <= N; i++) {

			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			for (int j = 0; j < n; j++)
				list[i].add(Integer.parseInt(st.nextToken()));
		}
		// 입력끝

		int n = (int) Math.pow(2, N);// 경우의 수 2^N조합
		int min = Integer.MAX_VALUE;
		for (int i = 1; i < n; i++) {

			group = new int[N + 1];
			for (int k = 0; k < N; k++) {
				if ((i & (1 << k)) > 0) {
					group[k + 1] = 1;
				}
			}
			// 그룹 나눔 0,1로 나눴고 index1부터 시작임

			min = Math.min(min, check());
		}
		
		if(min==Integer.MAX_VALUE)
			min=-1;
		
		bw.write(String.valueOf(min));
		bw.flush();
		bw.close();
		br.close();
	}

	public static int check() {// 선거구 가능한지 확인

		// 불가능하면 Integer.max 보내장
		ArrayList<Integer> red = new ArrayList<>();// 0
		ArrayList<Integer> blue = new ArrayList<>();// 1

		int rcnt = 0;
		int bcnt = 0;// 인구 수
		// 그룹 나누기
		for (int i = 1; i <= N; i++) {
			if (group[i] == 0) {

				red.add(i);
				rcnt += peoples[i];
			} else {

				blue.add(i);
				bcnt += peoples[i];
			}
		}
		if (red.size() == 0 || blue.size() == 0 || !isConnected(red) || !isConnected(blue))
			return Integer.MAX_VALUE;
		
		
		return Math.abs(rcnt - bcnt);

	}

	public static boolean isConnected(ArrayList<Integer> g) {

		//이어져 있는지 확인
		//bfs돌리자
		
		Queue<Integer>queue=new ArrayDeque<>();
		visited=new boolean[N+1];
		
		queue.add(g.get(0));
		visited[g.get(0)]=true;
		
		int cnt=0;
		while(!queue.isEmpty()) {
			int v=queue.poll();
			
			cnt++;
			for(int to:list[v]) {
				if(g.contains(to)&&!visited[to]) {
					queue.offer(to);
					visited[to]=true;
				}
			}
		}
		if(cnt==g.size())
			return true;
		return false;
	}
}
