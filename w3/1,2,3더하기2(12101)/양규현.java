import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int k;
	static ArrayList<String>[] list = new ArrayList[11];
	public static void main(String[] args) throws IOException, InterruptedException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		
		
		// 리스트 초기화
		for(int i = 0; i <11; i++) {
			list[i] = new ArrayList<String>();
		}
		
		//dp 전 초기값
		list[1].add("1");
		list[2].add("1+1");
		list[2].add("2");
		list[3].add("1+1+1");
		list[3].add("1+2");
		list[3].add("2+1");
		list[3].add("3");
		
		
		

		//n번쨰의 리스트의 사이즈보다 k가 크면 -1출력
		if(dp(n).size()< k) {
			System.out.println(-1);
			return;
		}
		else {
			Collections.sort(list[n]);
			System.out.println(list[n].get(k-1));
		}
		
		
		
	}
	
	static ArrayList<String> dp(int num) {
		if(list[num].size() == 0) {
			for(int i = 1; i < 4; i++) {
				//dp로 foreach할 리스트를 호출, 없으면 재귀
				for(String str : dp(num-i)) {
					list[num].add(str+"+"+Integer.toString(i));
				}
			}
			return list[num];
		}
		else
			return list[num];
		
	}

}
