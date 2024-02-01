import java.io.*;
import java.util.*;

public class Main {

	static int t, n, cnt;
	static ArrayList<Integer> arr;
	static boolean[] visit;
	static boolean[] finished;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		t = Integer.parseInt(br.readLine());

		for (int i = 0; i < t; i++) {
			n = Integer.parseInt(br.readLine());
			arr = new ArrayList<>();
			visit = new boolean[n + 1];
			finished = new boolean[n + 1];
			cnt = 0;
			st = new StringTokenizer(br.readLine());

			arr.add(0);
			for (int j = 1; j < n + 1; j++) {
				arr.add(Integer.parseInt(st.nextToken()));
			}

			for (int j = 1; j < n + 1; j++) {
				dfs(j);

			}

			System.out.println(n - cnt);
		}

	}

	public static void dfs(int idx) {
		if (visit[idx])
			return;

		visit[idx] = true;
		int next = arr.get(idx);

		if (!visit[next])
			dfs(next);
		else {
			if (!finished[next]) {

				cnt++;
				while (next != idx) {
					cnt++;
					next = arr.get(next);
				}
			}
		}

		finished[idx] = true;
	}

}
