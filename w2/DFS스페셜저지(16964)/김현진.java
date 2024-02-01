import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int SIZE;
    static List<Set<Integer>> tree;
    static int[] answer;
    static int cnt = 1;
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

        System.out.println(dfs(1) ? 1 : 0);
    }

    static boolean dfs(int start) {
        Set<Integer> set = tree.get(start);
        boolean flag = true;
        for(int j = set.size() - 1; j >= 0 ; j--) {
            int val = answer[++cnt];
            if(set.contains(val)){
                set.remove(val);
                tree.get(val).remove(start);
                flag = dfs(val);
                if(!flag) {
                    return false;
                }
            }else {
                return false;
            }
        }
        return flag;
    }
}
