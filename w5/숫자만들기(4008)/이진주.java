import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
 
public class Solution {
    static int[] operand;// 피연산자
    static int[] cnt;// 연산자 개수
    static int[]operator;//연산자 순서 저장하는거
    static int N,max,min;
 
    public static void main(String[] args) throws NumberFormatException, IOException {
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb=new StringBuilder();
         
        int Tc = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= Tc; tc++) {
 
            sb.append("#"+tc+" ");
            N = Integer.parseInt(br.readLine());
            operand=new int[N];
            cnt=new int[4];
            operator=new int[N-1];
             
            max=Integer.MIN_VALUE;
            min=Integer.MAX_VALUE;
             
            //연산자
            st=new StringTokenizer(br.readLine());
            for(int i=0;i<4;i++)
                cnt[i]=Integer.parseInt(st.nextToken());
             
            //수
            st=new StringTokenizer(br.readLine());          
            for(int i=0;i<N;i++)
                operand[i]=Integer.parseInt(st.nextToken());
             
            dfs(0);
            sb.append(max-min).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
 
    public static void dfs(int idx) {
         
        if(idx==N-1) {
            int res=cal();
            min=Math.min(min, res);
            max=Math.max(max, res);
             
            return;
        }
         
        for(int i=0;i<4;i++) {
            if(cnt[i]>0) {
                cnt[i]--;
                operator[idx]=i;
                dfs(idx+1);
                cnt[i]++;
            }
        }
    }
 
    public static int cal() {
 
        int sum=operand[0];
        int oi=1;
        for(int i=0;i<N-1;i++) {
             
            switch(operator[i]) {
            case 0:
                sum+=operand[oi++];
                break;
            case 1:
                sum-=operand[oi++];
                break;
            case 2:
                sum*=operand[oi++];
                break;
            case 3:
                sum/=operand[oi++];
                break;
            }
        }
        return sum;
    }
 
}
