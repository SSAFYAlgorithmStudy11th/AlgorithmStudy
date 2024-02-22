import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[] city;
    static ArrayList<Integer>[] graph;
    static boolean[] visit;
    static int answer;
    static int len;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        if(n % 2 == 1) {
            len = n/2+1;
        }
        else len = n/2;

        city = new int[n+1];
        visit = new boolean[n+1];

        graph = new ArrayList[n+1];
        graph[0] = new ArrayList<>();

        st= new StringTokenizer(br.readLine());
        for(int i = 1 ; i<n+1;i++) {
            city[i] = Integer.parseInt(st.nextToken());
            graph[i] = new ArrayList<>();
        }

        // 맵 생성
        for(int i = 1;i<n+1;i++) {
            st = new StringTokenizer(br.readLine());
            int len = Integer.parseInt(st.nextToken());
            for(int j = 0;j<len;j++) {
                int num = Integer.parseInt(st.nextToken());
                graph[i].add(num);
            }
        }

        answer = Integer.MAX_VALUE;
        makeSector(1,0);

        if(answer == Integer.MAX_VALUE) {
            System.out.println(-1);
        }
        else
            System.out.println(answer);


    }

    public static int isLinked(boolean type) {
        Queue<Integer> q = new ArrayDeque<>();
        ArrayList<Integer> list = new ArrayList<>();
        boolean[] chk = new boolean[n+1];
        for(int i = 1;i<n+1;i++) {
            if(visit[i] == type) {
                list.add(i);
                chk[i] = true;
            }
        }

        q.offer(list.get(0));
        chk[list.get(0)] = false;
        int cnt = 1;
        int sum = city[list.get(0)];
        while(!q.isEmpty()) {

            if(cnt == list.size())
                return sum;

            int out = q.poll();

            for(int a : graph[out]) {
                if(chk[a]) {
                    chk[a] = false;
                    q.offer(a);
                    sum += city[a];
                    cnt++;
                }
            }
        }
        return -1;

    }


    public static void makeSector(int idx, int cnt) {
        if(cnt > 0) {
            int tc = isLinked(true);
            int fc = isLinked(false);

            if(tc>0 && fc>0)
                answer = Math.min(answer, Math.abs(tc-fc));
        }
        if(cnt == len)
            return;
        for(int i = idx;i<n+1;i++) {
            if(!visit[i]) {
                visit[i] = true;
                makeSector(i+1, cnt+1);
                visit[i] = false;
            }
        }
    }


}
