
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class protection_film {
	static int D;
	static int W;
	static int K;
	static int answer;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int Test = 1; Test<T+1; Test++) {
			
			StringTokenizer st =new StringTokenizer(br.readLine());
			
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			answer = Integer.MAX_VALUE;
			
			boolean[][] film  = new boolean[D][W];
			
			for(int i = 0; i<D; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j<W; j++) {
					if(Integer.parseInt(st.nextToken()) ==1)
						film[i][j] = true;
				}
			}
			bt(film, 0, new boolean[D],0);
			bw.append("#").append(Integer.toString(Test)).append(" ").append(Integer.toString(answer)).append("\n");
		}
		bw.close();
	}
	
	static void bt(boolean[][] film,int depth,boolean visited[],int start) {

		if(depth > K || depth >= answer)
			return;
		
		if(check(film)) {
			answer = Math.min(depth, answer);
			return;
		}
		
		
		
		boolean[] layer = new boolean[W];
		
		//시간초과나서 탐색 시작부분을 조절함 
		for(int i = start; i<D; i++) {

			if(!visited[i]) {
				//백트래킹을 위한 저장
				for(int j = 0; j<W; j++) {
					layer[j] = film[i][j];
				}
				visited[i] =true;
				Arrays.fill(film[i], true);
				bt(film,depth+1,visited,i);
				Arrays.fill(film[i], false);
				bt(film,depth+1,visited,i);
				visited[i] =false;

				//백트래킹을 위한 복구
				for(int j = 0; j<W; j++) {
					film[i][j] = layer[j];
				}
			}
		}
	}
	
	//필름 체크
	static boolean check(boolean[][] layer) {
	
		for(int i = 0; i<W; i++) {
			int count = 1;
			boolean previous = layer[0][i];
			for(int j = 1; j<D; j++) {
				if(previous == layer[j][i])
					count++;
				else
					count = 1;
				if(count == K)
					break;
				previous = layer[j][i];
			}
			if(count != K)
				return false;
		}
		return true;
		
	}

}
