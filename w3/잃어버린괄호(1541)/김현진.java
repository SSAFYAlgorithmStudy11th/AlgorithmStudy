import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<String> expression;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        expression = new ArrayList<>();

        int plusCount = 0;

        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < input.length(); i++) {
            if(Character.isDigit(input.charAt(i))){
                for(int j = i ; j < input.length(); j++, i++) {
                    if(!Character.isDigit(input.charAt(j))){
                        expression.add(sb.toString());
                        sb.setLength(0);
                        expression.add(Character.toString(input.charAt(j)));
                        if(input.charAt(j) == '+') {
                            plusCount++;
                        }
                        break;
                    }
                    sb.append(input.charAt(j));
                }
            }
        }
        expression.add(sb.toString());
        int minusCount = expression.size() / 2 - plusCount;

        int idx = 0;

        //플러스끼리 그룹화
        while(expression.size() != (minusCount * 2 + 1)) {
            if(isOper(expression.get(idx))) {
                if(expression.get(idx).charAt(0) == '+') {
                    int val = op("+", Integer.parseInt(expression.get(idx - 1)), Integer.parseInt(expression.get(idx + 1)));
                    expression.set(idx - 1, String.valueOf(val));
                    expression.remove(idx); // 연산 제거
                    expression.remove(idx); // 연산 제거
                    idx = 0; //처음부터 다시
                }
            }
            idx++;
        }

        while(expression.size() != 1) {
            if(isOper(expression.get(idx))) {
                int val = op("-", Integer.parseInt(expression.get(idx - 1)), Integer.parseInt(expression.get(idx + 1)));
                expression.set(idx - 1, String.valueOf(val));
                expression.remove(idx); // 연산 제거
                expression.remove(idx); // 연산 제거
                idx = 0; //처음부터 다시
            }
            idx++;
        }
        System.out.println(expression.get(0));
    }

    static boolean isOper(String oper) {
        return oper.charAt(0) == '+' || oper.charAt(0) == '-';
    }

    static int op(String op, int l, int r) {
        return switch (op) {
            case "+" -> l + r;
            default -> l - r;
        };
    }
}
