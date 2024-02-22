
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
	
	static int N;
	static int M;
	static int K;
	static int answer;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		for(int Test = 1; Test<T+1; Test++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			answer = 0;
			
			ArrayList<int[]> list = new ArrayList<>();
			
			
			for(int i = 0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				int[] germs = new int[4];
				
				for(int j = 0; j<4; j++)
					germs[j] = Integer.parseInt(st.nextToken());
				list.add(germs);
			}
			
			for(int i = 0; i<M; i++) {
				ArrayList<int[]> [][] copied_arr = new ArrayList[N][N];
				
				for(int j = 0; j<list.size(); j++) {
					int[] germs = list.get(j);
					if(germs[2] > 0) {
						int germ_y = germs[0];
						int germ_x = germs[1];
						int num = germs[2];
						int direction = germs[3];
						
						switch(direction) {
						
							case 1:
								germ_y -= 1;
								break;
							case 2:
								germ_y += 1;
								break;
							case 3:
								germ_x -= 1;
								break;
							case 4:
								germ_x += 1;
								break;
						}
						
						if(germ_y == 0 || germ_y == N-1 || germ_x ==0 || germ_x == N-1) {
							num /= 2;
							if(direction == 1)
								direction =2;
							else if(direction == 2)
								direction =1;
							else if(direction == 3)
								direction = 4;
							else if(direction == 4)
								direction = 3;
						}
						
						
						if(copied_arr[germ_y][germ_x] == null) 
							copied_arr[germ_y][germ_x] = new ArrayList<int[]>();
						
						germs[0] = germ_y;
						germs[1] = germ_x;
						germs[2] = num;
						germs[3] = direction;
						
						copied_arr[germ_y][germ_x].add(germs);
						
					
					}
				}
				
//				for(int j = 0; j<N; j++) {
//					for(int k = 0; k<N; k++) {
//						if(copied_arr[j][k] != null)
//							for(int [] germ : copied_arr[j][k]) {
//								System.out.print("germ : " +germ[0]+" "+germ[1]+" "+germ[2]+" "+germ[3]);
//								System.out.println();
//							}
//					}
//				}
				
				list.clear();
				
				for(int k = 0; k<N; k++) {
					for(int l =0; l<N; l++) {
						if(copied_arr[k][l] == null)
							continue;
						
						if(copied_arr[k][l].size() > 1) {
							int num = 0;
							int total= 0;
							int direction = 0;

							//미생물 병합 
							for(int[] germ : copied_arr[k][l]) {
								if(num < germ[2]) {
									num = germ[2];
									direction = germ[3];
								}
								total+= germ[2];
							}
							
							list.add(new int[] {k,l,total,direction});
						}
						else {
							list.add(copied_arr[k][l].get(0));
						}
					}
				}
	
			}
			for(int[] germ : list)
				answer += germ[2];
			bw.append("#").append(Integer.toString(Test)).append(" ").append(Integer.toString(answer)).append("\n");
			
		}
		bw.close();
		
		
		
	}

}
