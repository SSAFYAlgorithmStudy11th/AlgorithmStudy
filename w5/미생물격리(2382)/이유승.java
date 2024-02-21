import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;

// 미생물 격리
// 배열로 풀려고 했는데 이전에 줄기세포 풀어서, 더 빠를 것 같아서 아래 처럼 품

public class Problem_2382 {
	static ArrayList<Creature> list;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static final int UP = 1;
	static final int DOWN = 2;
	static final int LEFT = 3;
	static final int RIGHT = 4;
	static int n;
	static int m;
	static int k; 
	// Creature 클래스
	static class Creature implements Comparable<Creature> {
		Creature(int y, int x, int cnt, int direction) {
			this.y = y;
			this.x = x;
			this.cnt = cnt;
			this.direction = direction;
		}
		int y;			//좌표
		int x;
		int cnt;		// 미생물 개수
		int direction;	// direction : 방향 UP DOWN LEFT RIGHT)
		
		// 나중에 정렬할껀데, 정렬해서 좌표 순으로 정렬을 함
		// 좌표 순으로 정렬하고, 이후 좌표가 같다면 cnt가 큰 순서대로 정렬
		@Override
		public int compareTo(Creature o){
			if(this.y!=o.y)
				return this.y - o.y;
			if(this.x!=o.x)
				return this.x - o.x;
			return o.cnt - this.cnt;
		}
	}
	
	public static void solution(int t) throws IOException {
		int time = 0;
		while(time++ < m) {
			move();				// 세포 이동시키기
			changeDirection();	// 약품에 있는 경우 감소시키고 방향바꾸기
			combine();			// 같은 셀에 있는 경우 결합하기
		}
		
		int sum=0;
		for(Creature obj : list)
			sum += obj.cnt;
		
		bw.append("#"+t+" "+sum+"\n");
	}
	
	// 리스트 순회하면서 방향에 따른 obj 좌표값 변경
	public static void move() {	
		for(Creature obj : list) {
			switch(obj.direction) {
			case UP: obj.y--; break;
			case DOWN: obj.y++; break;
			case LEFT: obj.x--; break;
			case RIGHT: obj.x++; break;
			}
		}
	}
	
	// 약품에 있는 경우 감소시키고 방향바꾸기
	// 알겠지만 foreach 사용해서 list에서 직접 지우면 예외 발생 (Concurrentmodificationexception)
	// Iterator 사용해서 삭제하면 됨, HashMap이나 이런것도 가능
	public static void changeDirection() {
		Iterator<Creature> iter = list.iterator();
		while(iter.hasNext()) {
			Creature obj = iter.next();
			// 약품에 있는 경우
			if(obj.x == 0 || obj.x == n-1 || obj.y == 0 || obj.y == n-1) {
				obj.direction += (obj.direction%2 == 0) ? -1 : 1; // 방향 바꾸기
				obj.cnt /= 2; // 절반 감소
				if(obj.cnt == 0) 
					iter.remove();	// 0이면 삭제	
			}
		}
	}
	
	// 같은 셀에 있는 미생물 결합
	public static void combine() {
		// 정렬하면 다음 원소는 x와 y가 같거나 클 수 밖에 없음
		list.sort(null);
		Creature pre = null;
		Iterator<Creature> iter = list.iterator();
		while(iter.hasNext()) {
			Creature obj = iter.next();
			// 초깃값 설정, 
			if(pre == null) {
				pre = obj;
				continue;
			}
			// 같은 셀에 있는 세포의 경우
			if(obj.x == pre.x && obj.y == pre.y) {
				// 좌표가 같다면 cnt가 큰 순으로 정렬했으므로
				// 첫번째 있는 미생물의 cnt가 가장 큼
				// 따라서 direction 안바꿔줘도 됨
				pre.cnt += obj.cnt; // 미생물 개체수 계속 더하기
				iter.remove(); // 더했던 미생물은 삭제
			} else
				pre = obj; 
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		list = new ArrayList<Creature>();
		for(int i=0; i<t; i++) {
			list.clear();
			String[] temp = br.readLine().split(" ");
			n = Integer.parseInt(temp[0]);
			m = Integer.parseInt(temp[1]);
			k = Integer.parseInt(temp[2]);
			for(int j=0; j<k; j++) {
				temp = br.readLine().split(" ");
				int y = Integer.parseInt(temp[0]);
				int x = Integer.parseInt(temp[1]);
				int cnt = Integer.parseInt(temp[2]);
				int direction = Integer.parseInt(temp[3]);
				list.add(new Creature(y, x, cnt, direction));
			}
			solution(i+1);
		}
		bw.flush();
	}
}
