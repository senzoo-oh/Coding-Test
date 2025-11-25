import java.util.*;
import java.io.*;


public class Solution_for_12904 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int answer = 0;

        String S = br.readLine();
        String T = br.readLine();

        /***** 처음에 생각한 문제풀이 방법 *****
        Queue<Word> queue = new LinkedList<>();
        queue.add(new Word(S));
        
        while (!queue.isEmpty()) {
            Word cur = queue.poll();
            
            // cur가 T와 동일하지 않다면
                // 연산1의 결과 만듦
                // 연산2의 결과 만듦
        
                // 연산1의 결과의 길이가 T보다 작다면 queue에 넣음
                // 연산2의 결과의 길이가 T보다 작다면 queue에 넣음

            // cur가 T와 동일하다면
                // answer = 1로 설정하고 반복문을 종료함
        }
        **********************************/

        /***** 문제 해결 방법 *****/
        int sLen = S.length();
        int tLen = T.length();

        while (sLen <= tLen) {
            
            if (S.equals(T)) {
                answer = 1;
                break;
            }

            if (T.charAt(tLen-1) == 'A') {
                String newT = T.substring(0, tLen-1);
                T = newT;
            }
            else {
                String newT = T.substring(0, tLen-1);
                T = new StringBuilder(newT).reverse().toString();
            }

            tLen--;
        }

        System.out.println(answer);
    }
}