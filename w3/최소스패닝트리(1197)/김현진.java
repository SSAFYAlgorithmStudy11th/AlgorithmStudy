import java.io.*;
import java.util.*;
class Edge implements Comparable<Edge> {
    int e,d,v;
    Edge(int a,int b,int c){
     e = a; d = b; v = c;
    }

    @Override
    public int compareTo(Edge e){
        if(this. v > e.v){
            return 1;
        }else if(this.v == e.v){
            return 0;
        }else{
            return -1;
        }
    }
}

public class Main {
    static int[] data;
    static int findSet(int p){
        if(p == data[p]){
            return p;
        }
        data[p] = findSet(data[p]);
        return data[p];
    }

    static void unionSet(int a, int b){
        int ap = findSet(a);
        int bp = findSet(b);
        data[ap] = bp;
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int nodeNum = Integer.parseInt(st.nextToken());
        int lineNum = Integer.parseInt(st.nextToken());

        ArrayList<Edge> line = new ArrayList<>();
        data = new int[nodeNum+1];

        for(int i  = 1 ; i < data.length; i++){
            data[i] = i;
        }

        for(int i = 0 ; i < lineNum ; i++){
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());
            line.add(new Edge(first,second,val));
        }
        Collections.sort(line);

        int sum = 0;
        int t = 0;
        for(int i = 0 ; i < lineNum; i++){
            Edge e = line.get(i);
            if(findSet(e.e) != findSet(e.d)){
                unionSet(e.e, e.d);
                sum += e.v;
            }
        }
        bw.append(sum+"");
        bw.flush();
    }
}
