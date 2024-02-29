import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
 
public class Solution {
    static int n;
    static int k;
    static int[][] matrix;
    static boolean[][] visited;
    static int result;
    static int max;
    static int[] dx = new int[] {-1, 1, 0, 0};
    static int[] dy = new int[] {0, 0, -1, 1};
     
    public static int[] convert(String[] temp) {
        int[] result = new int[temp.length];
        for(int i=0; i<temp.length; i++) {
            result[i] = Integer.parseInt(temp[i]);
            max = Math.max(result[i], max);
        }
        return result;
    }
     
    public static int solution() {
        ArrayList<int[]> list = new ArrayList<int[]>();
        for(int i=0; i<n; i++)
            for(int j=0; j<n; j++)
                if(matrix[i][j] == max)
                    list.add(new int[] {i, j});
        for(int[] target : list) {
            visited[target[0]][target[1]] = true;
            dfs(target[0], target[1], true, 1);
            visited[target[0]][target[1]] = false;
        }
        return result;
    }
     
    public static void dfs(int cy, int cx, boolean chance, int length) {
        int currentValue = matrix[cy][cx];
        for(int i=0; i<4; i++) {
            int y = cy+dy[i];
            int x = cx+dx[i];
            if(x<0 || x>=n || y<0 || y>=n || visited[y][x])
                continue;
            visited[y][x] = true;
            if(matrix[y][x]<currentValue)
                dfs(y, x, chance, length+1);
            else if(chance && matrix[y][x]-k<currentValue) {
                int value = matrix[y][x];
                matrix[y][x] = currentValue-1;
                dfs(y, x, !chance, length+1);
                matrix[y][x] = value;
            }
            visited[y][x] = false;
        }
        result = Math.max(result, length);
    }
     
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());
        for(int i=0; i<t; i++) {
            String[] temp = br.readLine().split(" ");
            n = Integer.parseInt(temp[0]);
            k = Integer.parseInt(temp[1]);
            max = 0;
            result = 0;
            matrix = new int[n][];
            visited = new boolean[n][n];
            for(int j=0; j<n; j++)
                matrix[j] = convert(br.readLine().split(" "));
            bw.append("#").append(String.valueOf(i+1)).append(" ").append(String.valueOf(solution())).append("\n");
        }
        bw.flush();
    }
}
