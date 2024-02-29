import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Solution
{
	 static int n,m,c;
    static int[][] arr;
    static boolean[][] visit;
    static int[] maxHoney;
    static int answer;
    static ArrayList<int[]> hList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int tc = 1;tc<=T;tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            arr = new int[n][n];
            visit = new boolean[n][n-m+1];
            for(int i = 0; i <n;i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j<n;j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            answer = 0;
            hList = new ArrayList<>();
            selectHoney(0,0);

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        System.out.print(sb);

    }

    public static int[] makeArray(int row, int col) {
        int[] tmp = new int[m];
        for(int i = col ; i<col+m;i++) {
            tmp[i-col] = arr[row][i];
        }

        return tmp;
    }

    public static void permutation(int[] honey, int income, int sum ,int idx, int num) {
        maxHoney[num] = Math.max(income,maxHoney[num]);
        for(int i = idx;i<honey.length;i++) {
            if(sum+honey[i] <= c) {
                permutation(honey,income+(honey[i]*honey[i]),sum+honey[i],i+1,num);
            }

        }
    }
    public static void cal() {
        maxHoney = new int[2];
        int[] arr1 = makeArray(hList.get(0)[0],hList.get(0)[1]);
        int[] arr2 = makeArray(hList.get(1)[0],hList.get(1)[1]);
        permutation(arr1,0,0,0,0);
        permutation(arr2,0,0,0,1);

        answer = Math.max(answer,maxHoney[0]+maxHoney[1]);
    }

    public static boolean chk(int row, int col) {
        for(int i = col;i<col+m;i++) {
            if(i>=n-m+1)
                return true;
            if(visit[row][i])
                return false;
        }
        return true;
    }
    public static void selectHoney(int idx1,int cnt) {
        if(cnt==2) {
            cal();
            return;
        }
        for(int i = idx1; i< n;i++) {
            for(int j = 0; j<n-m+1;j++) {
                if(!visit[i][j]) {
                    if(!chk(i,j))
                        continue;
                    for(int k = j;k<j+m;k++) {
                        if(k>=n-m+1)
                            break;
                        visit[i][k] = true;
                    }
                    hList.add(new int[]{i,j});
                    selectHoney(i,cnt+1);
                    hList.remove(hList.size()-1);
                    for(int k = j;k<j+m;k++) {
                        if(k>=n-m+1)
                            break;
                        visit[i][k] = false;
                    }
                }
            }
        }
    }


}
