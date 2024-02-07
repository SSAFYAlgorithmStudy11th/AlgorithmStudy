import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int n;
	static int[] result;
	static int[] data;
	static StringBuilder sb = new StringBuilder();
	
	public static void oddSet() {
		for(int i=0; i<n; i++) {
			int k=1;
			while(i-k >= 0 && i+k < n) {
				if(data[i-k] == data[i+k])
					k++;
				else
					break;
			}
			result[2*i]=k-1;
		}
	}
	
	public static void evenSet() {
		for(int i=0; i<n; i++) {
			int k=0;
			while(i-k >= 0 && i+1+k < n) {
				if(data[i-k] == data[i+1+k])
					k++;
				else
					break;
			}
			result[2*i+1]=k-1;
		}
	}
	
	public static int[] convert(String[] temp) {
		int[] result = new int[temp.length];
		for(int i=0; i<temp.length; i++)
			result[i] = Integer.parseInt(temp[i]);
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		result = new int[2*n];	
		data = convert(br.readLine().split(" "));
		oddSet();
		evenSet();
		
		String[] temp;
		int m = Integer.parseInt(br.readLine());
		for(int i=0; i<m; i++) {
			temp =  br.readLine().split(" ");
			int s = Integer.parseInt(temp[0])-1;
			int e = Integer.parseInt(temp[1])-1;
			if(result[s+e] >= (e+s)/2-s)
				sb.append(1);
			else
				sb.append(0);
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
