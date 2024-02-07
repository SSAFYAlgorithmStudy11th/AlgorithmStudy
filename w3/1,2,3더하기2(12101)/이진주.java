import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	static int n,k;
	static int count;
	static int []res;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		sb=new StringBuilder();
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		res=new int[11];//1~10
		
		dfs(0,0);
		
		if(count<k)
			sb.append(-1);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	public static void dfs(int c,int sum) {
		
		if(sum>n)
			return;
		if(sum==n) {
			count++;
			if(count==k) {
				for(int i=0;i<c-1;i++)
					sb.append(res[i]).append("+");
				sb.append(res[c-1]);
			}
			
			return;
		}
		
		for(int i=1;i<=3;i++) {
			res[c]=i;
			dfs(c+1,sum+i);
		}	
	}
}
