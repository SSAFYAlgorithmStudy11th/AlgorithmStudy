package boj0222.No17471;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 게리맨더링
public class Main {
	static int N , res;
	static ArrayList<Integer>[] arr;
	static ArrayList<Integer> a;
	static ArrayList<Integer> b;
	static boolean[] ab;
	static int[] p;
	static boolean[] visited;
	
	static void select(int idx) {
		if(idx == N+1) {
			a = new ArrayList<Integer>(); // a 구역
			b = new ArrayList<Integer>(); // b구역
			int acnt = 0; // a구역 사람 수 
			int bcnt = 0; // b구역 사람수 

			for(int i=1; i<N+1 ; i++) {
				if(ab[i]) {
					a.add(i);
				}else {
					b.add(i);
				}
			}
			
			if(a.size() == 0 || b.size()==0) {
				return;
			}
			
			if(link(a.get(0)) + link(b.get(0)) == N ) { // 두개다 연결돼있는 경우
				for(int i=1;i<N+1;i++) {
					if(ab[i]) {
						acnt+= p[i];
					}
					else {
						bcnt+=p[i];
					}
				}
				res = Math.min(res, Math.abs(acnt - bcnt));
			}
			
			return;
		}
		ab[idx] = true; // a
		select(idx+1);
		ab[idx] = false; // b
		select(idx+1);
	}
	
	static int link(int x) {
		visited = new boolean[N+1];
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(x);
		visited[x] = true;
		int c = 1;
		
		while(!q.isEmpty()) {
			int n = q.poll();
			
			for(int i=0;i<arr[n].size();i++) {
				if(!visited[arr[n].get(i)]&& (ab[x]== ab[arr[n].get(i)])) {
					c++;
					q.add(arr[n].get(i));
					visited[arr[n].get(i)] = true;
				}
			}
		}
		
		return c;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		res = Integer.MAX_VALUE; // 결과값
		arr = new ArrayList[N+1]; // 연결정보 
		ab = new boolean[N+1]; // ab 구역 나눌때 
		p = new int[N+1]; // 사람 수
		
		for(int i=0;i<N+1;i++) {
			arr[i] = new ArrayList<>(); 
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=1 ; i<=N ;i++) {
			p[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=1;i<N+1;i++) { // 연결정보 입력받음
			st = new StringTokenizer(br.readLine());
			int s= Integer.parseInt(st.nextToken());
			for(int j=0;j<s;j++) {
				arr[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		select(1); // a구역 b구역 선택

		if(res == Integer.MAX_VALUE) {
			System.out.println(-1);
		}
		else {
			System.out.println(res);
			
		}
	}

}
