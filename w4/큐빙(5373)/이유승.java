import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	static final int UP = 0;
	static final int DOWN = 1;
	static final int FRONT = 2;
	static final int BACK = 3;
	static final int LEFT = 4;
	static final int RIGHT = 5;
	
	static final int N = 0;
	static final int E = 1;
	static final int S = 2;
	static final int W = 3;
	
	public static final boolean RTURN = true;
	public static final boolean LTURN = false;

	static int[][] rs = new int[6][];
	static int[][][] rsn = new int[6][][];
	static char[] color = {'w', 'y', 'r', 'o', 'g', 'b'};
	static BufferedWriter bw;
	
	static class Cell {
		char value;
		Cell(char c){
			this.value = c;
		}
	}
	
	static class Plane {
		Cell[] owns;
		Cell[][] sides;
		Plane(char c){
			this.owns = new Cell[9];
			for(int i=0; i<9; i++)
				owns[i] = new Cell(c);
			this.sides = new Cell[4][3];
		}
		void rotate(boolean turn) {
			char[] clone = new char[9];
			for(int i=0; i<9; i++)
				clone[i] = owns[i].value;
			for(int i=0; i<3; i++) {
				owns[i].value = turn ? clone[6-3*i] : clone[2+3*i];
				owns[3+i].value = turn ? clone[7-3*i] : clone[1+3*i];
				owns[6+i].value = turn ? clone[8-3*i] : clone[3*i];
			}
			
			char t1, t2, t3 = 0;
			
			if(turn) {
				t1 = sides[W][0].value;
				t2 = sides[W][1].value;
				t3 = sides[W][2].value;
				for(int i=3; i>0; i--) {
					sides[i][0].value = sides[i-1][0].value;
					sides[i][1].value = sides[i-1][1].value;
					sides[i][2].value = sides[i-1][2].value;
				}
				sides[0][0].value = t1;
				sides[0][1].value = t2;
				sides[0][2].value = t3;
			}
			else {
				t1 = sides[0][0].value;
				t2 = sides[0][1].value;
				t3 = sides[0][2].value;
				for(int i=0; i<3; i++) {
					sides[i][0].value = sides[i+1][0].value;
					sides[i][1].value = sides[i+1][1].value;
					sides[i][2].value = sides[i+1][2].value;
				}
				sides[W][0].value = t1;
				sides[W][1].value = t2;
				sides[W][2].value = t3;
			}
		}
	}
	
	public static void solution(String[] steps) throws IOException {
		Plane[] cube = new Plane[6];
		for(int i=0; i<6; i++)
			cube[i] = new Plane(color[i]);
		
		for(int i=UP; i<=RIGHT; i++)
			for(int j=N; j<=W; j++)
				cube[i].sides[j] = new Cell[] {
						cube[rs[i][j]].owns[rsn[i][j][0]],
						cube[rs[i][j]].owns[rsn[i][j][1]],
						cube[rs[i][j]].owns[rsn[i][j][2]]
				};
		
		for(String step : steps) {
			switch(step) {
			case "U+": cube[UP].rotate(RTURN); break;
			case "U-": cube[UP].rotate(LTURN); break;
			case "D+": cube[DOWN].rotate(RTURN); break;
			case "D-": cube[DOWN].rotate(LTURN); break;
			case "F+": cube[FRONT].rotate(RTURN); break;
			case "F-": cube[FRONT].rotate(LTURN); break;
			case "B+": cube[BACK].rotate(RTURN); break;
			case "B-": cube[BACK].rotate(LTURN); break;
			case "L+": cube[LEFT].rotate(RTURN); break;
			case "L-": cube[LEFT].rotate(LTURN); break;
			case "R+": cube[RIGHT].rotate(RTURN); break;
			case "R-": cube[RIGHT].rotate(LTURN); break;
			}
		}
		
		Cell[] plane = cube[UP].owns;
		for(int i=0; i<9; i++) {
			bw.append(plane[i].value);
			if(i%3 == 2)
				bw.append("\n");
		}
	}
	
	public static void setup() {
		rs = new int[6][];
		rsn = new int[6][][];
		
		rs[UP] = new int[]{BACK, RIGHT, FRONT, LEFT};
		rs[DOWN] = new int[]{FRONT, RIGHT, BACK, LEFT};
		rs[FRONT] = new int[]{UP, RIGHT, DOWN, LEFT};
		rs[BACK] = new int[]{UP, LEFT, DOWN, RIGHT};
		rs[LEFT] = new int[]{UP, FRONT, DOWN, BACK};
		rs[RIGHT] = new int[]{UP, BACK, DOWN, FRONT};
		
		rsn[UP] = new int[][] {{0, 1, 2},{0, 1, 2},{0, 1, 2},{0, 1, 2}};
		rsn[DOWN] = new int[][] {{6, 7, 8},{6, 7, 8},{6, 7, 8},{6, 7, 8}};
		rsn[FRONT] = new int[][] {{6, 7, 8},{0, 3, 6},{2, 1, 0},{8, 5, 2}};
		rsn[BACK] = new int[][] {{2, 1, 0},{0, 3, 6},{6, 7, 8},{8, 5, 2}};
		rsn[LEFT] = new int[][] {{0, 3, 6},{0, 3, 6},{0, 3, 6},{8, 5, 2}};
		rsn[RIGHT] = new int[][] {{8, 5, 2},{0, 3, 6},{8, 5, 2},{8, 5, 2}};
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		setup();
		int t = Integer.parseInt(br.readLine());
		for(int i=0; i<t; i++) {
			br.readLine();
			solution(br.readLine().split(" "));
		}
		bw.flush();
	}
}
