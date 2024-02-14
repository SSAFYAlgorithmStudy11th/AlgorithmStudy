import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
	static class Node implements Comparable<Node>{
		Node(int y, int x, int distance){
			this.y = y;
			this.x = x;
			this.distance = distance;
		}
		int y;
		int x;
		int distance;
		@Override
		public int compareTo(Node o) {
			if(this.distance == o.distance)
				if(this.y == o.y)
					return this.x - o.x;
				else
					return this.y - o.y;
			else
				return this.distance - o.distance;
		}
	}
	static Queue<Node> queue;
	static int n;
	static int m;
	static int f;
	static int[][] startMatrix;
	static int[][] endMatrix;
	static int[][] endPoint;
	static int[] start;
	static boolean[][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static int solution() {
		int cnt = m;
		int fuel = f;
		
		int posCarY = start[0];
		int posCarX = start[1];
		
		while(true) {
			Node person = getMinPersonNode(posCarY, posCarX);
			
			if(person == null)
				if(cnt == 0)
					return fuel;
				else
					return -1;
			
			int personPosY = person.y;
			int personPosX = person.x;
			int personNum = startMatrix[personPosY][personPosX];
			int distance = person.distance;
			
			if(distance > fuel)
				return -1;
			
			posCarY = personPosY;
			posCarX = personPosX;
			startMatrix[posCarY][posCarX] = 0;
			fuel -= distance;
			
			
			endMatrix[endPoint[personNum][0]][endPoint[personNum][1]] = 2;
			
			Node target = getTargetNode(posCarY, posCarX);
			if(target == null)
				return -1;
			
			int targetPosY = target.y;
			int targetPosX = target.x;
			distance = target.distance;
			
			if(distance > fuel)
				return -1;
			
			posCarY = targetPosY;
			posCarX = targetPosX;
			endMatrix[posCarY][posCarX] = 0;
			fuel += distance;
			
			cnt--;
		}
	}
	
	public static void initVisited() {
		for(boolean[] arr : visited)
			Arrays.fill(arr, false);
	}
	
	public static Node getMinPersonNode(int i, int j) {
		Node result = null;
		initVisited();
		queue.clear();
		queue.add(new Node(i, j, 0));
		visited[i][j] = true;
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			if(startMatrix[node.y][node.x] > 1) {
				if (result == null || node.compareTo(result) < 0)
	                result = node;
			}
			for(int k=0; k<4; k++) {
				int y = node.y+dy[k];
				int x = node.x+dx[k];
				if(x<0 || x>=n || y<0 || y>=n)
					continue;
				if(startMatrix[y][x] != 1 && !visited[y][x]) {
					queue.add(new Node(y, x, node.distance+1));
					visited[y][x] = true;
				}
					
			}
		}
		return result;
	}
	
	public static Node getTargetNode(int i, int j) {
		Node result = null;
		initVisited();
		queue.clear();
		queue.add(new Node(i, j, 0));
		visited[i][j] = true;
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			if(endMatrix[node.y][node.x] == 2)
				return node;
			for(int k=0; k<4; k++) {
				int y = node.y+dy[k];
				int x = node.x+dx[k];
				if(x<0 || x>=n || y<0 || y>=n)
					continue;
				if(endMatrix[y][x] != 1 && !visited[y][x]) {
					queue.add(new Node(y, x, node.distance+1));
					visited[y][x] = true;
				}
			}
		}
		return result;
	}
	
	public static int[] convert(String[] temp, int weight) {
		int[] result = new int[temp.length];
		for(int i=0; i<temp.length; i++)
			result[i] = Integer.parseInt(temp[i])+weight;
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		
		n = Integer.parseInt(temp[0]);
		m = Integer.parseInt(temp[1]);
		f = Integer.parseInt(temp[2]);
	
		startMatrix = new int[n][];
		endMatrix = new int[n][];
		endPoint = new int[m+2][2];
		queue = new ArrayDeque<Node>();
		visited = new boolean[n][n];
		
		for(int i=0; i<n; i++) {
			startMatrix[i] = convert(br.readLine().split(" "), 0);
			endMatrix[i] = startMatrix[i].clone();
		}
		
		start = convert(br.readLine().split(" "), -1);
		
		int personNum = 2;
		
		for(int i=0; i<m; i++) {
			int[] position = convert(br.readLine().split(" "), -1);
			startMatrix[position[0]][position[1]] = personNum;
			endPoint[personNum][0] = position[2];
			endPoint[personNum][1] = position[3];
			personNum++;
		}
		System.out.println(solution());
	}
}
