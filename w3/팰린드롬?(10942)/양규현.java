package D0202;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int S;
    static int E;
    static int N;
    static int M;
    static int[] nums;
    static Boolean[][] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());

        nums = new int[N + 1]; // 숫자의 배열
        st = new StringTokenizer(br.readLine());
        check = new Boolean[N + 1][N + 1]; //S, E 사이의 펠린드롬 관계를 저장하는 배열

        for (int i = 1; i < N + 1; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            
            //숫자가 하나일경우 펠린드롬
            if (S == E) {
                sb.append("1\n");
                continue;
            }
            
            //숫자가 2개일경우, 숫자가 같으면 펠린드롬
            if ((E - S) == 1) {
                if (nums[S] == nums[E]) {
                    sb.append("1\n");
                } else {
                    sb.append("0\n");
                }
                continue;
            }
            
            //그외의 경우 
            if (checkPalindrome(S, E)) {
                sb.append("1\n");
            } else {
                sb.append("0\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static boolean checkPalindrome(int start, int end) {
        // 이미 확인한 범위는 바로 반환
        if (check[start][end] != null) {
            return check[start][end];
        }

        // 홀수 길이의 팰린드롬인 경우 중간 원소는 한 번만 비교
        int limit = (end - start + 1) / 2;
        for (int j = 0; j < limit; j++) {
            if (nums[start + j] != nums[end - j]) {
                check[start][end] = false;
                return false;
            }
        }

        // 팰린드롬인 경우 작은 범위도 펠린드롬이므로, 해당 범위를 true로 표시
        for (int k = 0; k < limit; k++) {
            check[start + k][end - k] = true;
        }

        return true;
    }
}
