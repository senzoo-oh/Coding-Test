import java.io.*;
import java.util.*;

public class Solution_for_11501 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        // for (int t = 0; t < T; t++) {
        //     st = new StringTokenizer(br.readLine());

        //     int N = Integer.parseInt(st.nextToken());

        //     // 날 별 주가
        //     ArrayList<Integer> array = new ArrayList<>();

        //     st = new StringTokenizer(br.readLine());
        //     for (int n = 0; n < N; n++) {
        //         Integer price = Integer.parseInt(st.nextToken());
                
        //         array.add(price);
        //     }

        //     Long answer = 0L;
        //     int max = array.get(N - 1);
        //     for (int n = N - 2; -1 < n; n--) {

        //         int curPrice = array.get(n);
        //         if (max > curPrice) {
        //             answer += (max - curPrice);
        //         }
        //         else max = curPrice;
        //     }
            
        //     System.out.println(answer);
        // }

        // 아래 로직은 왜 안될까..????
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());

            // 우선순위 큐(내림차순 정렬)
            ArrayList<Integer> arr1 = new ArrayList<>();

            // 날 별 주가
            Integer[] arr2 = new Integer[N];

            // 구매한 주식
            LinkedList<Integer> stocks = new LinkedList<>();

            st = new StringTokenizer(br.readLine());
            for (int n = 0; n < N; n++) {
                Integer price = Integer.parseInt(st.nextToken());

                arr1.add(price);
                arr2[n] = price;
            }

            Collections.sort(arr1, Collections.reverseOrder());

            Long answer = 0L;
            int index = 0;
            for (int n = 0; n < N; n++) {

                int maxPrice = arr1.get(index);

                // 현재 주가가 우선순위 큐의 맨 앞에 있는 주가보다 작다면 주식을 산다.
                if (arr2[n] < maxPrice) {
                    stocks.add(arr2[n]);
                }
                // 현재 주가가 우선순위 큐의 맨 앞에 있는 주가와 같다면 주식을 판다.
                else if (arr2[n] == maxPrice) {
                    while (!stocks.isEmpty()) {
                        answer += (maxPrice - stocks.poll());
                    }

                    index++;
                }
            }
            // 구매한 주식을 다 못 팔았을 경우, 구매한 주식의 가장 높은 값으로 주식을 팜.
            if (!stocks.isEmpty()) {
                int max = stocks.get(0);

                // 구매한 주식중에서 가장 높은 가격을 구함
                for (int i = 1; i < stocks.size(); i++) {
                    max = Math.max(max, stocks.get(i));
                }

                // 높은 가격으로 구매한 주식을 팜
                for (int i = 0; i < stocks.size(); i++) {
                    answer += (max - stocks.get(i));
                }
            }

            System.out.println(answer);
        }
    }
}
