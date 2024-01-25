import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No6603 {
	public static int K;
	public static int[] arr;
	public static int[] res;
	public static boolean[] visited;
	
	public static void bt(int idx ,int cnt) {
		if(cnt==6) {
			for(int i=0;i<6;i++) {
				System.out.print(res[i]+" ");
			}
			System.out.println("");
			return;
		}
		
		for(int i=idx;i<K;i++) {
			if(!visited[i]) {
				visited[i] = true;
				res[cnt] = arr[i];
				bt(i,cnt+1);
				visited[i]=false;
				
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		while(true) {
			st = new StringTokenizer(br.readLine(), " ");
			
			K=Integer.parseInt(st.nextToken());
			if(K==0) break;
			
			arr = new int[K];
			visited = new boolean[K];
			res = new int[6];
			
			for(int i=0;i<K;i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			bt(0,0);
			System.out.println("");
		}
		
	}
}
