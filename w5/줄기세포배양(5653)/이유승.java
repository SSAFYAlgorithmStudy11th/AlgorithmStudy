import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Objects;

// 줄기세포배양 ;;;;

// 배열 사이즈가 정해져 있지 않아서 HashMap 사용
// Coor 클래스
// 		단순 x, y 좌표값 지정, equals와 hashcode 오버라이딩해서 같은 객체인지 확인하게 함
// Node 클래스
//		value : 생명력
//		remain : 활성화까지 남은 카운트
//		life : 사망까지 남은 카운트

// 전체 로직
//		1. inActivate에 비활성 세포들이 있음 

//		2. runActivate()로 비활성 세포들의 remain을 줄임

//		3. 근데 remain이 0이 된 세포들을 activate로 만듬
//			-> 바로 activate로 만들면 복제해야되는지 안해야되는지 모르니까
//			-> waitActivate에 넣음, 다음 턴되면 복제하고 activate에 넣음

//		4. runClone()으로 waitActivate에 있는 세포들 가져와서 복제
//			-> 바로 inActivate에 넣으면, 바로 활성화 될 수 있음
//			-> waitClone에 넣고, 다음 턴되면 inactivate에 넣음

//		5. runDead()로 life 감소시키면서 life가 0이된 세포들 dead에 넣음


// 이 소스코드 디버깅 해준 규현이에게 사랑을 바칩니다.. ♡


public class Problem_5653 {
	public static HashMap<Coor, Node> dead;	// 죽은 세포들
	public static HashMap<Coor, Node> activate;	// 활성 세포들
	public static HashMap<Coor, Node> inActivate;	// 비활성 세포들
	public static HashMap<Coor, Node> waitActivate;	// 분열할 세포들(비활성 -> 활성 대기)  
	public static HashMap<Coor, Node> waitClone; // 분열된 세포들(분열 -> 비활성 대기)
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static int time = 0;
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	
	// 좌표 저장 클래스
	public static class Coor{
		public Coor(int y, int x) {
			 this.y = y; this.x = x;
		}
		int y;
		int x;
		
		// equals와 hashCode 오버라이딩 해서 Key가 중복이 되게함
		// 둘다 재정의해야 중복 가능
		@Override
		public boolean equals(Object obj) {
			return this.x == ((Coor)obj).x && this.y == ((Coor)obj).y;
		}
		
		@Override
		public int hashCode() {
			// 이거 쓰면 두 값으로 해시코드 만들어낼 수 있다고 함
			return Objects.hash(x, y);
		}
	}
	public static class Node{
		Node(int value){
			this.value = value;
			this.remain = value;
			this.life = value;
		}
		int value;	// 생명력
		int remain;	// 활성화까지 남은 카운트
		int life;	// 사망까지 남은 카운트
	}
	
	public static void solution(int t) throws IOException {
		int currentTime=0;
		
		while(currentTime++ < time) {
			activate.putAll(waitActivate);
			runDead();		// 죽이기
			runClone();		// 복제하기
			runActivate();	// 활성화시키기
			inActivate.putAll(waitClone);
		}
		
		bw.append("#"+t+" "+(activate.size() + inActivate.size() + waitActivate.size())+"\n");
	}
	
	public static void runActivate() {
		waitActivate.clear();
		// 비활성화 상태의 세포들을 다 가져옴
		Iterator<Entry<Coor, Node>> iter = inActivate.entrySet().iterator();
		while(iter.hasNext()) {
			Entry<Coor, Node> pairs = iter.next();
			// remain 감소하고 검사 후, 0이면 waitActivate에 삽입
			if(--pairs.getValue().remain == 0) {
				waitActivate.put(pairs.getKey(), pairs.getValue());
				iter.remove();
			}
		}
	}
	
	public static void runDead() {
		// 활성화 상태의 세포들을 다 가져옴
		Iterator<Entry<Coor, Node>> iter = activate.entrySet().iterator();
		while(iter.hasNext()) {
			Entry<Coor, Node> pairs = iter.next();
			// life 감소하고 검사 후, 0이면 dead에 삽입
			if(--pairs.getValue().life == 0) {
				dead.put(pairs.getKey(), pairs.getValue());
				iter.remove();
			}
		}
	}
	
	public static void runClone() {
		waitClone.clear();
		// 방금 activate된 세포들, waitActivate에서 세포를 가져옴
		Iterator<Entry<Coor, Node>> iter = waitActivate.entrySet().iterator();
		while(iter.hasNext()) {
			Entry<Coor, Node> pairs = iter.next();
			for(int i=0; i<4; i++) {
				// 4방 탐색
				int x = pairs.getKey().x + dx[i];
				int y = pairs.getKey().y + dy[i];
				Coor coor = new Coor(y, x);
				// 이미 죽어있거나, 활성화되있거나, 비활성 세포가 자리를 먹고 있는 경우 pass
				if(dead.containsKey(coor) || activate.containsKey(coor) || inActivate.containsKey(coor))
					continue;
				// 새로 생길 자린데 다른 세포에 의해 자리가 차지해 있을 경우
				if(waitClone.containsKey(coor)) {
					Node node = waitClone.get(coor);
					// 값 비교해서 큰 생명력을 가진 세포로 덮어씌움
					node.value = (pairs.getValue().value > node.value) ? pairs.getValue().value : node.value;
					// 규현아 고마워 ㅠㅠ
					node.remain = node.value; // remain이랑 life로 바꿔줘야 함
					node.life = node.value;
				}
				// 새로 생길 자리가 비어있을 경우
				else
					waitClone.put(coor, new Node(pairs.getValue().value));
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		activate = new HashMap<Coor, Node>();
		inActivate = new HashMap<Coor, Node>();
		waitActivate = new HashMap<Coor, Node>();
		dead = new HashMap<Coor, Node>();
		waitClone = new HashMap<Coor, Node>();
		for(int i=0; i<t; i++) {
			String[] temp = br.readLine().split(" ");
			int n = Integer.parseInt(temp[0]);
			int m = Integer.parseInt(temp[1]);
			time = Integer.parseInt(temp[2]);
			activate.clear();
			inActivate.clear();
			waitActivate.clear();
			dead.clear();
			waitClone.clear();
			for(int j=0; j<n; j++) {
				temp = br.readLine().split(" ");
				for(int k=0; k<temp.length; k++)
					if(!temp[k].equals("0"))
						inActivate.put(new Coor(j,k), new Node(Integer.parseInt(temp[k])));
			}
			solution(i+1);
		} 
		bw.flush();
	}
}
