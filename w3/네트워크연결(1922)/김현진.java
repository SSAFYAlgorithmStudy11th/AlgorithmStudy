import java.io.*;
import java.util.*;

public class Main {
    static class Edge implements Comparable<Edge> {
        int e,d,v;
        Edge(int a, int b, int c) {
            e = a; d = b; v = c;
        }


        @Override
        public int compareTo(Edge edge) {
            return v - edge.v;
        }
    }

    static int[] data;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int nodeNum = Integer.parseInt(br.readLine());
        int lineNum = Integer.parseInt(br.readLine());

        List<Edge> lines = new ArrayList<>();
        data = new int[nodeNum + 1];

        for(int i = 1;  i <= nodeNum; i++) {
            data[i] = i; // 서로 다른 그룹임을 명시
        }

        StringTokenizer st;
        for(int i = 0 ; i < lineNum; i++) {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());
            lines.add(new Edge(first,second, val));
        }

        Collections.sort(lines); // 최소 값을 추가함.

        int sum = 0;
        int t = 0;
        for(int i = 0; i < lineNum; i++) {
            Edge e = lines.get(i);
            if(findSet(e.e) != findSet(e.d)) { //만약 둘이 서로 다른 셋이라면
                unionSet(e.e, e.d);
                sum += e.v;
            }
        }

        bw.append(sum +"");
        bw.flush();
    }

    static int findSet(int p) {
        if(p == data[p]){
            return p; //만약 현재 위치와 셋이 같다면, p를 반환 (종료조건)
        }
        data[p] = findSet(data[p]);
        return data[p];
    }
    static void unionSet(int a, int b) {
        int ap = findSet(a);
        int bp = findSet(b);
        data[ap] = bp;
    }
}
