import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No10819 {
	public static int N;
	public static int res;
	public static int[] arr;
	public static int[] arr2;
	public static boolean[] visited;
	
	public static void bt(int cnt) {
		if(cnt==N) {
			int sum =0;
			
			for(int i=0;i<cnt-1;i++) {
				sum+= Math.abs(arr2[i]-arr2[i+1]);
			}
			res = (res>sum)?res:sum;
			return;
		}
		
		for(int i =0 ;i<N;i++) {
			if(!visited[i]) {
				visited[i]= true;
				arr2[cnt] = arr[i];
				bt(cnt+1);
				visited[i]=false;
			}
		}
		
	}	

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		arr2 = new int[N];
		visited = new boolean[N];
		res = 0;
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		bt(0);
		
		System.out.println(res);
		
		

	}
}
