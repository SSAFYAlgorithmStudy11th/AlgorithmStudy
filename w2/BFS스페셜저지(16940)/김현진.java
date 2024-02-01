import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int SIZE;
    static List<Set<Integer>> tree;
    static int[] answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        SIZE = Integer.parseInt(br.readLine());
        answer = new int[SIZE + 1];
        tree = new ArrayList<>();
        for(int i = 0; i <= SIZE; i++){
            tree.add(new HashSet<>());
        }

        for(int i = 0; i < SIZE - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree.get(u).add(v);
            tree.get(v).add(u);
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= SIZE; i++) {
            answer[i] = Integer.parseInt(st.nextToken());
        }

        if(answer[1] != 1) {
            System.out.println(0);
            return;
        }

        System.out.println(bfs() ? 1 : 0);
    }

    static boolean bfs() {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(1);
        int i = 2;
        while(!q.isEmpty()) {
            int cur = q.poll();
            Set<Integer> curSet = tree.get(cur);
            for(int j = curSet.size() - 1; j >= 0; j--) {
                if(curSet.contains(answer[i])) {
                    //마지막까지 탐색 완료
                    if(i >= SIZE) {
                        return true;
                    }
                    q.add(answer[i]);
                    tree.get(cur).remove(answer[i]);
                    tree.get(answer[i]).remove(cur);
                    i++;
                }else {
                    return false;
                }
            }
        }

        return true;
    }
}
