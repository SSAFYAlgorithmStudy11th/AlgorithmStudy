package D0129;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class binary_div_graph {

    static int V,E;
    static int[] colors;
    static boolean isno;
    static ArrayList<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(st.nextToken());

        // 테스트 케이스
        for(int i=0 ; i<T;i++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            isno = false;
            list = new ArrayList[V+1];
            colors = new int[V+1];

        //리스트 초기화
        for(int j = 1; j<V+1;j++) {
            list[j] = new ArrayList<Integer>();
        }

        //양방향 연결
        for(int j = 0; j<E; j++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
            list[b].add(a);

        }
        
        
        // 방문하지 않았다면 탐색
        for(int k = 1; k<V+1; k++) {
            if(colors[k] == 0 && !isno)
                dfs(k,1);
        }
        
        
        // 이분 그래프면
        if(!isno) {
            bw.write("YES");
            bw.newLine();
            bw.flush();
        }
        else {
            bw.write("NO");
            bw.newLine();
            bw.flush();
        }
            
    }
    bw.close();
}

static void dfs(int node,int color) {
    colors[node] = color;
    
    
    // 현재 노드와 연결된 노드 탐색
    for(int a : list[node]) {
        
    	//만약 방문하지 않았다면
        if (colors[a] == 0) {
        	//다른 색깔로 다시 탐색
            dfs(a, color * -1);
        }
        
        //만약 방문했는데 현재 노드와 같은 컬러라면
        if (colors[a] == colors[node]) {
            isno = true;
            return;
        }
    }
}
}