import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 색종이 붙히기

public class Problem_17136 {
	static boolean[][] matrix;
	static int[] papers; // 종이 개수 저장 배열
	static int totalCnt; // 총 1의 개수
	static int min = Integer.MAX_VALUE;
	
	public static int solution() {
		rec(0,0);
		if(min == Integer.MAX_VALUE)
			return -1;
		return min;
	}
	
	public static void rec(int y, int x) {
		int j=x;
		for(int i=y; i<10; i++) {
			while(j<10) {
				if(matrix[i][j]) {
					int m = vaildMax(i, j); // 해당 좌표에서 덮을 수 있는 최대 크기
					for(int k=m; k>=0; k--) {
						if(papers[k]==0)	// 사용할 수 있는 종이가 없다면 종료
							return;
						setting(i, j, k, false); // 있으면 사용했다고 하고
						totalCnt -= k*k;		 // 처리해야할 전체 개수 감소
						papers[k]--;			 // 종이 개수 감소
						if(j==9)				 
							rec(i+1, 0);		 // x가 값 벗어난 경우 i를 증가
						else
							rec(i, j+1);
						setting(i, j, k, true);	// backtracking
						papers[k]++;
						totalCnt += k*k;
					}
				}
				j++;
			}
			j=0;
		}
		if(totalCnt == 0)
			min = Math.min(min, usePaperCnt());
	}
	
	
	// 해당 좌표에서 최대 얼마 크기의 색종이를 붙힐 수 있는가
	// 좀 기괴하긴한데 1~4까지 모든 셀을 한번만 검색함(늘어난 범위 만큼만)
	// O 		XO		XXO		XXXO
	//			OO		XXO		XXXO
	//				  	OOO		XXXO
	//					    		OOOO
	// i=0	i=1		i=2		i=3
	// 현진이형 ★ 고마워 ★
	public static int vaildMax(int y, int x) {
		int i;
		for(i=0; i<=4; i++) {
			if(y+i>=10 || x+i>=10)
				return i;
			for(int j=y; j<=y+i; j++)
				if(!matrix[j][x+i])
					return i;
			for(int j=x; j<=x+i; j++)
				if(!matrix[y+i][j])
					return i;
		}
		return i;
	}
	
	// 해당좌표에서 size만큼의 네모 구역을 setValue(true/false)로 변경 
	public static void setting(int y, int x, int size, boolean setValue) {
		for(int i=y; i<y+size; i++)
			for(int j=x; j<x+size; j++)
				matrix[i][j] = setValue;
	}
	
	// 사용한 종이 개수 구하기
	public static int usePaperCnt() {
		int sum = 0;
		for(int paper: papers)
			sum += paper;
		return 25-sum;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		matrix = new boolean[10][10];
		for(int i=0; i<10; i++) {
			String[] temp = br.readLine().split(" ");
			for(int j=0; j<10; j++) {
				if(temp[j].equals("1")) {
					matrix[i][j] = true;
					totalCnt++;
				}
			}
		}
		papers = new int[6];
		for(int i=1; i<6; i++)
			papers[i] = 5;
		System.out.println(solution());
	}
}
