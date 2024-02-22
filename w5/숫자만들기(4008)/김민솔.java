import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int max;
	static int min;
	static int num[];
	static int ch[];
	
	static void dfs(int c,int cnt, int res) {
		if(cnt==N-1) {
			if(c == 0) {
				res+=num[cnt];
			}
			else if(c == 1) {
				res-=num[cnt];
			}
			else if(c == 2) {
				res*=num[cnt];
			}
			else if(c == 3) {
				res/=num[cnt];
			}
			max = Math.max(max,res);
			min = Math.min(min, res);
			return;
		}
		
		for(int j=0;j<4 ; j++) {
			if(ch[j]!=0) {
				ch[j]--;
				if(c == 0) {
					dfs(j, cnt+1, res+num[cnt] );
				}
				else if(c == 1) {
					dfs(j, cnt+1, res-num[cnt] );
				}
				else if(c == 2) {
					dfs(j, cnt+1, res*num[cnt] );
				}
				else if(c == 3) {
					dfs(j, cnt+1, res/num[cnt] );
				}
				ch[j]++;
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		//System.setIn(new FileInputStream("sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ; 
		
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
			N = Integer.parseInt(br.readLine());
			num = new int[N];
			ch = new int[4];
			min = 100000001;
			max = -100000001;
			
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<4;i++) {
				ch[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) {
				num[i] = Integer.parseInt(st.nextToken());
			}
			
			dfs(0, 0, 0);
			
			System.out.printf("#%d %d\n",test_case,max - min);
		}

	}

}
