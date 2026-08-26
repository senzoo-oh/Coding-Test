import java.io.*;
import java.util.*;

public class Solution_for_13305 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        // 도로의 길이(위치) 입력받음
        st = new StringTokenizer(br.readLine());
        long[] location = new long[N];
        long dis = 0;
        for (int n = 1; n < N; n++) {
            dis += Long.parseLong(st.nextToken());
            location[n] = dis;
        }

        // 도시 주유소의 리터당 가격 입력받음
        st = new StringTokenizer(br.readLine());
        long[] prices = new long[N];
        for (int n = 0; n < N; n++) {
            prices[n] = Long.parseLong(st.nextToken());
        }
        
        PriorityQueue<City> queue = new PriorityQueue<City>((a, b) -> {
            return Long.compare(a.price, b.price);
        });

        PriorityQueue<City> selectedCities = new PriorityQueue<City>((a, b) -> {
            return Long.compare(a.location, b.location);
        });

        for (int n = 0; n < N; n++) {
            queue.add(new City(location[n], prices[n]));
        }

        long curLocation = Long.MAX_VALUE;
        
        while (!queue.isEmpty()) {
            City city = queue.poll();
            
            // 현재까지 탐색한 도시 중에서 시작점에서 가장 가까운 도시인 경우 들려서 주유함.
            if (city.location < curLocation) {
                selectedCities.add(city);
                curLocation = city.location;
            }
        }

        // 방문한 도시들을 기준으로 주유하는데 필요한 비용을 계산
        long sum = 0;
        long lastLocation = location[N-1];

        while (!selectedCities.isEmpty()) {
            City cur = selectedCities.poll();

            long nextLocation;
            
            if (!selectedCities.isEmpty()) {
                nextLocation = selectedCities.peek().location;
            }
            else {
                nextLocation = lastLocation;
            }

            sum += cur.price * (nextLocation - cur.location);
        }

        System.out.println(sum);
    }

    public static class City {
        long location;
        long price;

        public City(long location, long price) {
            this.location = location;
            this.price = price;
        }
    }
}
