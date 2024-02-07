import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer minus=new StringTokenizer(br.readLine(),"-");
		
		int sum=Integer.MAX_VALUE;
		while(minus.hasMoreTokens()) {
			
			StringTokenizer plus=new StringTokenizer(minus.nextToken(),"+");
			int tmp=0;
			while(plus.hasMoreTokens()) {
				tmp+=Integer.parseInt(plus.nextToken());
			}
			
			if(sum==Integer.MAX_VALUE)
				sum=tmp;
			else
				sum-=tmp;
		}
		
		bw.write(String.valueOf(sum));
		bw.flush();
		bw.close();
		br.close();
	}

}
