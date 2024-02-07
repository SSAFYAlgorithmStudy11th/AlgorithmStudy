import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N , K;
	static ArrayList<Integer[]> res;
	static ArrayList<Integer> arr;
	
	static void bt(int idx , int sum) {
		if(sum>N) {
			return;
		}
		if(sum==N) {
			Integer[] a = new Integer[arr.size()];
			a =arr.toArray(new Integer[arr.size()]); 
			res.add(a);
			return;
		}
		
		for(int i = 1 ; i<4;i++) {
			arr.add(i);
			bt(i,sum+i);
			arr.remove(arr.size()-1);
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb =new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		res = new ArrayList<Integer[]>();
		arr = new ArrayList<Integer>();

		for(int i=1;i<4;i++) {
			arr.add(i);
			bt(i,i);
			arr.remove(arr.size()-1);
		}

		if(res.size()>=K) {
			for(int i=0;i<res.get(K-1).length;i++) {
				// 출력
				if(i==0) {
					sb.append(res.get(K-1)[i]);
				}else {
					sb.append("+").append(res.get(K-1)[i]);
				}

			}
			System.out.println(sb);
		}else {
			System.out.println(-1);
		}
	}

}
