import java.io.*;
import java.util.*;

/*
그리디로 풀 수 없음. 예제 -> (5, 7), (3, 4), 9명을 늘리고 싶음.
(3, 4)가 비용당 늘릴 수 있는 고객 수가 크니깐, 3을 3번써서 12명의 고객을 확보하는데에 비용 9가 듦.
근데, 답은 (3, 4) 1개, (5, 7) 1개 이렇게 선택하는게 비용의 최솟값임
*/
public class Solution_for_1106 {
    static int C, N;
    static int[][] cities;
    
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        cities = new int[2][N+1];

        for (int n = 1; n < N+1; n++) {
            st = new StringTokenizer(br.readLine());

            int cost = Integer.parseInt(st.nextToken());
            int cust = Integer.parseInt(st.nextToken());

            cities[0][n] = cost;
            cities[1][n] = cust;
        }

        dp = new int[N+1][C+1];
        
        // 첫번째 도시에 대해서만 초기화 해줌
        for (int c = 1; c < C+1; c++) {
            if ((c % cities[1][1]) != 0) {
                dp[1][c] = ((c / cities[1][1]) + 1) * cities[0][1];
            }
            else {
                dp[1][c] = (c / cities[1][1]) * cities[0][1];
            }
        }

        for (int n = 2; n < N+1; n++) {
            int cost = cities[0][n];
            int cust = cities[1][n];

            for (int cnt = 1; cnt < C+1; cnt++) {
                // cnt - cust가 0보다 작은 경우, 그냥 현재 비용으로 해줌
                if (cnt - cust < 0) {
                    dp[n][cnt] = Math.min(dp[n-1][cnt], cost);
                }
                else dp[n][cnt] = Math.min(dp[n-1][cnt], cost + dp[n][cnt - cust]);
            }
        }

        // for (int n = 0; n < N+1; n++) {
        //     for (int c = 0; c < C+1; c++) {
        //         System.out.print(dp[n][c] + " ");
        //     }
        //     System.out.println();
        // }
        System.out.println(dp[N][C]);
    }
}
