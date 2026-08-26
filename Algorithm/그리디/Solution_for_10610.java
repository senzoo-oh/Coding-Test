import java.io.*;
import java.util.*;

public class Solution_for_10610 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String N = br.readLine();

        // 0포함 여부
        boolean containZero = false;

        // 모든 자리의 합이 3의 배수인지 여부
        boolean isMultipleThree = false;

        int sum = 0;

        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> {
            return b - a;
        });

        for (int i = 0; i < N.length(); i++) {
            int num = N.charAt(i) - '0';
            queue.add(num);
            if (num == 0) containZero = true;
            
            sum += num;
        }

        if (sum % 3 == 0) isMultipleThree = true;

        StringBuilder sb = new StringBuilder();

        if (containZero && isMultipleThree) {
            while (!queue.isEmpty()) {
                sb.append(queue.poll());
            }
            System.out.println(sb.toString());
        }
        else System.out.println(-1);
    }
}
