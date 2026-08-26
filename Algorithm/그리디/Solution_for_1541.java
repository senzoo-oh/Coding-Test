import java.io.*;
import java.util.*;

/*
스택을 생성함
숫자인 경우에는 스택에 담음.
연산자인 경우에는 prev 연산자에 저장을 하고 스택에 담음
만약, prev연산자가 '-'인데 현재 탐색한 연산자가 '+'라면, 스택에서 숫자를 꺼내서 다음 숫자와 더한 값을 스택에 담음. 그리고 다음에 탐색한 연산자가 '+'라면, 또 다시 스택에서 숫자를 꺼내서 다음 숫자와 더한 값을 스택에 담고 이를 반복함. 만약, prev연산자가 '-'인데, 현재 탐색한 연산자가 '-'라면, 그냥 스택에 숫자를 담음.

만약, prev연산자가 '+'인데, 현재 탐색한 연산자가 '-'라면, 그냥 스택에 담음.
*/
public class Solution_for_1541 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();

        // 문자열을 '-'를 구분자로 분리함
        String[] s1 = s.split("-");

        // for (int i = 0; i < s1.length; i++) {
        //     System.out.print(s1[i] + " ");
        // }

        // '+'연산을 수행한 결과를 저장함
        for (int i = 0; i < s1.length; i++) {
            if (s1[i].contains("+")) {
                String[] s2 = s1[i].split("\\+");
                
                // for (int j = 0; j < s2.length; j++) {
                //     System.out.print(s2[j] + " ");
                // }

                int sum = 0;
                for (int j = 0; j < s2.length; j++) {
                    int num = Integer.parseInt(s2[j]);

                    sum += num;
                }

                s1[i] = String.valueOf(sum);
            }
        }

        int result = Integer.parseInt(s1[0]);
        for (int i = 1; i < s1.length; i++) {
            int num = Integer.parseInt(s1[i]);

            result -= num;
        }

        System.out.println(result);
    }
}
