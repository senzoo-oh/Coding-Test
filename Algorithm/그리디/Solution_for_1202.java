import java.io.*;
import java.util.*;

public class Solution_for_1202 {
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   // N: 보석의 개수
        K = Integer.parseInt(st.nextToken());   // K: 가방의 개수

        // 보석을 무게 순으로 정렬함
        ArrayList<Jewel> jewels = new ArrayList<>();
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());

            int M = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            jewels.add(new Jewel(M, V));
        }
        jewels.sort((a, b) -> {
            return a.weight - b.weight;
        });

        // 가방을 용량이 작은 순으로 정렬함
        int[] bags = new int[K];
        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());

            int C = Integer.parseInt(st.nextToken());

            bags[k] = C;
        }

        Arrays.sort(bags);
        
        
        // 보석을 담을 가방을 하나씩 선택하면서 현재 가방에 담을 수 있는 보석들을 구해서 우선순위큐(가치를 기준으로 내림차순 정렬)에 담음
        long valueSum = 0;
        int index = 0;

        PriorityQueue<Jewel> queue = new PriorityQueue<>((a, b) -> {
            return b.value - a.value;
        });

        for (int k = 0; k < K; k++) {
            int curBagSize = bags[k];

            for (int n = index; n < N; n++) {
                Jewel curJewel = jewels.get(n);

                if (curJewel.weight <= curBagSize) {
                    queue.add(curJewel);
                    index++;
                }
                else break;
            }

            // 현재 가방에 담을 수 있는 보석들 중에서 가치가 가장 높은 보석을 선택함
            if (!queue.isEmpty()) {
                valueSum += queue.poll().value;
            }
        }

        System.out.println(valueSum);
    }

    public static class Jewel {
        int weight;
        int value;

        public Jewel (int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }
}
