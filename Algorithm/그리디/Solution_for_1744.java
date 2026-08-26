import java.io.*;
import java.util.*;

public class Solution_for_1744 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> minusQueue = new PriorityQueue<>();
        ArrayList<Integer> one = new ArrayList<>();
        PriorityQueue<Integer> plusQueue = new PriorityQueue<>((a, b) -> { return b - a; });
        for (int n = 0; n < N; n++) {
            int num = Integer.parseInt(br.readLine());

            if (num <= 0) {
                minusQueue.add(num);
            }
            else if (num == 1) {
                one.add(1);
            }
            else plusQueue.add(num);
        }

        // 양수 묶기
        int answer = 0;

        while (2 <= plusQueue.size() ) {
            int num1 = plusQueue.poll();
            int num2 = plusQueue.poll();

            answer += (num1 * num2);
        }
        if (!plusQueue.isEmpty()) {
            answer += plusQueue.poll();
        }

        // 음수 묶기
        while (2 <= minusQueue.size()) {
            int num1 = minusQueue.poll();
            int num2 = minusQueue.poll();

            answer += (num1 * num2);
        }
        if (!minusQueue.isEmpty()) {
            answer += minusQueue.poll();
        }

        answer += one.size();

        System.out.println(answer);
    }
}
