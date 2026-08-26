import java.io.*;
import java.util.*;

public class Solution_for_2812 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        String num = br.readLine();
        
        Stack<Integer> stack = new Stack<>();

        stack.push(num.charAt(0) - '0');

        int index = 1;
        while (!stack.isEmpty() && index < num.length()) {
            
            int cur = num.charAt(index) - '0';
            
            // 뺄 수 있는 기회가 남아있고 앞에 숫자가 다음 숫자보다 작은 경우, 뺌
            while (!stack.isEmpty() && 0 < K && stack.peek() < cur) {
                stack.pop();
                K--;
            }
            
            stack.push(cur);
            index++;
        }

        while (0 < K) {
            stack.pop();
            K--;
        }

        StringBuilder sb = new StringBuilder();
        for (int n : stack) sb.append(n);

        System.out.println(sb.toString());
    }
}
