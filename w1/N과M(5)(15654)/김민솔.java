import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class No15654 {
	public static int N;
	public static int M;
	public static int[] arr;
	public static int[] res;
	public static boolean[] ch;
	
	public static void bt( int cnt) {
		if(cnt == M) {
			for(int i=0;i<M;i++) {
				System.out.print(res[i]+" ");
			}
			System.out.println("");
			return;
		}
		
		for(int i=0;i<N;i++) {
			if(!ch[i]) {
				res[cnt] = arr[i];
				ch[i] = true;
				bt(cnt+1);
				ch[i] = false;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		ch = new boolean[N];
		res = new int[M];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N ;i++) {
			arr[i]= Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		bt(0);

	}

}
