import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
 
public class Main {
	static int T;
	static char[][][] cube;
	static char[] color = {'w', 'r', 'b', 'o', 'g', 'y'};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while(T --> 0) {
			cube = new char[6][3][3];
			for(int i = 0; i < 6; i++) {
				for(int j = 0; j < 3; j++) {
					for(int k = 0; k < 3; k++) {
						cube[i][j][k] = color[i];
					}
				}
			}
			int rounds = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < rounds; i++) {
				String round = st.nextToken();
				int num = 0;
				
				switch(round.charAt(0)) {
				case 'U' : num = 0;
				break;
				case 'D' : num = 5;
				break;
				case 'F' : num = 1;
				break;
				case 'B' : num = 3;
				break;
				case 'L' : num = 4;
				break;
				case 'R' : num = 2;
				break;
				}
				rotate(num, round.charAt(1) == '-');
			}
			for(int i = 0 ; i < 3; i++) {
				sb.append(cube[0][i]);
				sb.append('\n');
			}

		}
		System.out.println(sb);
	}
	
	
	
	
	static void rotate(int mainIdx, boolean left) {
		char[][] temp1 = new char[3][3];
		if(left) {
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					temp1[i][j] = cube[mainIdx][j][2-i];
				}
			}
		}
		if(!left) {
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					temp1[i][j] = cube[mainIdx][2-j][i];
				}
			}
		}
		cube[mainIdx] = temp1;
		
		//메인칸이 0이면 1,2,3,4의 윗칸 회전
		switch(mainIdx) {
		case 0:
			if(!left) {
				char[] temp = cube[1][0];
				cube[1][0] = cube[2][0];
				cube[2][0] = cube[3][0];
				cube[3][0] = cube[4][0];
				cube[4][0] = temp;
			}else {
				char[] temp = cube[4][0];
				cube[4][0] = cube[3][0];
				cube[3][0] = cube[2][0];
				cube[2][0] = cube[1][0];
				cube[1][0] = temp;
			}
			break;
			//메인칸이 1이면 0의 아래쪽, 2의 왼쪽, 4의 오른쪽, 5의 아랫쪽 회전 
		case 1:
			if(left) {
				char[] temp = new char[] {cube[0][2][0], cube[0][2][1], cube[0][2][2]};
				for(int i = 0; i < 3; i++) {
					cube[0][2][i] = cube[2][i][0];
					cube[2][i][0] = cube[5][2][i];
					cube[5][2][i] = cube[4][2-i][2];
					cube[4][2-i][2] = temp[i];
				}
			}
			if(!left) {
				char[] temp = new char[] {cube[0][2][0], cube[0][2][1], cube[0][2][2]};
				for(int i = 0; i < 3; i++) {
					cube[0][2][i] = cube[4][2-i][2];
					cube[4][2-i][2] = cube[5][2][i];
					cube[5][2][i] = cube[2][i][0];
					cube[2][i][0] = temp[i];
				}
			}
			break;
			//메인칸이 2이면 0의 오른쪽, 1의 오른쪽, 5의 왼쪽, 3의 왼쪽 회전
		case 2:
			if(left) {
				char[] temp = new char[] {cube[0][0][2], cube[0][1][2], cube[0][2][2]};
				for(int i = 0 ; i < 3; i++) {
					cube[0][i][2] = cube[3][2-i][0];
					cube[3][2-i][0] = cube[5][2-i][0];
					cube[5][2-i][0] = cube[1][i][2];
					cube[1][i][2] = temp[i];
				}
			}
			
			if(!left) {
				char[] temp = new char[] {cube[0][0][2], cube[0][1][2], cube[0][2][2]};
				for(int i = 0 ; i < 3; i++) {
					cube[0][i][2] = cube[1][i][2];
					cube[1][i][2] = cube[5][2-i][0];
					cube[5][2-i][0] = cube[3][2-i][0];
					cube[3][2-i][0] = temp[i]; 
				}
			}
			break;
			//메인칸이 3이면 0의 위쪽, 4의 왼쪽, 5의 위쪽, 2의 오른쪽 회전
		case 3:
			if(left) {
				char[] temp = new char[] {cube[0][0][0], cube[0][0][1], cube[0][0][2]};
				for(int i= 0; i < 3; i++) {
					cube[0][0][i] = cube[4][2-i][0];
					cube[4][2-i][0] = cube[5][0][i];
					cube[5][0][i] = cube[2][i][2];
					cube[2][i][2] = temp[i];
				}
			}
			
			if(!left) {
				char[] temp = new char[] {cube[0][0][0], cube[0][0][1], cube[0][0][2]};
				for(int i= 0; i < 3; i++) {
					cube[0][0][i] = cube[2][i][2];
					cube[2][i][2] = cube[5][0][i];
					cube[5][0][i] = cube[4][2-i][0];
					cube[4][2-i][0] = temp[i];
				}
			}
			break;
			//메인칸이 4이면 0의 왼쪽, 1의 왼쪽, 5의 오른쪽, 3의 오른쪽 회전
		case 4:
			if(left) {
				char[] temp = new char[] {cube[0][0][0],cube[0][1][0],cube[0][2][0]};
				for(int i = 0; i < 3; i++) {
					cube[0][i][0] = cube[1][i][0];
					cube[1][i][0] = cube[5][2-i][2];
					cube[5][2-i][2] = cube[3][2-i][2];
					cube[3][2-i][2] = temp[i];
				}
			}
			
			if(!left) {
				char[] temp = new char[] {cube[0][0][0],cube[0][1][0],cube[0][2][0]};
				for(int i = 0; i < 3; i++) {
					cube[0][i][0] = cube[3][2-i][2];
					cube[3][2-i][2] = cube[5][2-i][2];
					cube[5][2-i][2] = cube[1][i][0];
					cube[1][i][0] = temp[i];
				}
			}
			break;			
			//메인칸이 5이면 1,2,3,4의 아랫쪽 회전
		case 5:
			if(left) {
				char[] temp = cube[1][2];
				cube[1][2] = cube[2][2];
				cube[2][2] = cube[3][2];
				cube[3][2] = cube[4][2];
				cube[4][2] = temp;
			}else {
				char[] temp = cube[4][2];
				cube[4][2] = cube[3][2];
				cube[3][2] = cube[2][2];
				cube[2][2] = cube[1][2];
				cube[1][2] = temp;
			}
			break;
		}	
	}
	
}
