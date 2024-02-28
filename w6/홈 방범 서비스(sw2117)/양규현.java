
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class home_security {
	static int answer;
	static int homes;
	static int N;
	static int M;
	static int max_size;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int Test = 1; Test<T+1; Test++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			boolean[][] map = new boolean[N][N];
			answer = 0; 
			homes = 0; // 존재하는 집들의 갯수
			boolean isdone = false;
			
			for(int i = 0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j<N; j++) {
					if(Integer.parseInt(st.nextToken()) == 1) {
						map[i][j] = true;
						homes++;
					}
				}
			}
			
			int max_revenue = homes*M;
			max_size = 1;
			//최대로 가능한 사이즈 체크 
			while(max_size*max_size+(max_size-1)*(max_size-1) < max_revenue) 
				max_size++;
			
			for(int i = 0; i<N; i++) {
				for (int j =0; j<N; j++) {
					// k = 1부터 최대로 가능한 사이즈까지 마름모 탐색
					for(int n = 0; n< max_size; n++){
						int cost = (n+1)*(n+1)+(n)*(n);
						if(answer >= cost)
							continue;
						int total = 0;
						int home =0;
						for(int k = -n; k<= n; k++ ) {
							for(int l = -n; l<=n; l++) {
								if(i+k >= 0 && i+k <N && j+l >= 0 && j+l <N && Math.abs(k)+Math.abs(l) <= n ) {
									if (map[i+k][j+l]) {
										total += M;
										home++;
									}
								}
							}
						}
						if(cost <= total) {
							//나올 수 있는 최댓값이면 탐색 종료
							if(home == homes)
								isdone =true;
							answer = Math.max(home, answer);
						}
					}
					if(isdone)
						break;
				}
				if(isdone)
					break;
			}
			bw.append("#").append(Integer.toString(Test)).append(" ").append(Integer.toString(answer)).append("\n");
		}
		bw.close();
	}
}
