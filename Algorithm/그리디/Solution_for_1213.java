import java.io.*;
import java.util.*;

public class Solution_for_1213 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        // 총 알파벳의 개수를 셈
        int totalCnt = s.length();

        // 각 알파벳의 개수를 셈
        int[] cnts = new int[26];
        for (int n = 0; n < s.length(); n++) {
            char c = s.charAt(n);
            
            cnts[c - 'A']++;
        }

        // 홀수, 짝수 공통: 개수가 홀수개인 알파벳이 2개 이상이면 펠린드롬 불가능
        // 홀수, 짝수 공통: 하나의 알파벳으로 구성되어 있다면 펠린드롬 가능
        // 짝수: 총 알파벳의 개수가 짝수개인데, 개수가 1개인 알파벳이 존재한다면 펠린드롬 불가능
        // 홀수: 총 알파벳의 개수가 홀수개인경우, 개수가 1개인 알파벳을 제외하고는 나머지 알파벳들은 짝수개이어야 펠린드롬 가능
        
        // 개수가 홀수개인 알파벳의 수를 셈
        int oddCnt = 0;
        char mid = 0;
        for (int n = 0; n < cnts.length; n++) {
            if (cnts[n] % 2 != 0) {
                oddCnt++;
                mid = (char)(n + 'A');
            }
        }

        if (oddCnt > 1) {
            System.out.println("I'm Sorry Hansoo");
            return;
        }

        StringBuilder left = new StringBuilder();

        // 펠린드롬의 왼쪽
        for (int n = 0; n < cnts.length; n++) {
            if (cnts[n] != 0) {
                for (int m = 0; m < cnts[n] / 2; m++) {
                    left.append((char)(n + 'A'));
                }
            }
        }

        // 펠린드롬의 오른쪽
        StringBuilder right = new StringBuilder(left).reverse();

        if (oddCnt == 1) {
            left.append(mid);
        }

        // 펠린드롬의 가운데
        left.append(right);

        System.out.println(left.toString());
    }
}
