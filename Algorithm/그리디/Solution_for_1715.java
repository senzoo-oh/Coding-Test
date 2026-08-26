import java.io.*;
import java.util.*;

public class Solution_for_1715 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        PriorityQueue<Long> queue = new PriorityQueue<>();

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());

            queue.add(Long.parseLong(st.nextToken()));
        }

        long sum = 0;

        while (queue.size() >= 2) {
            long num1 = queue.poll();
            long num2 = queue.poll();

            long result = num1 + num2;

            sum += result;

            queue.add(result);
        }

        System.out.println(sum);
    }
}
