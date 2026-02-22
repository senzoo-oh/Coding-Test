import java.io.*;
import java.util.*;

public class Solution_for_14728 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        // 단원의 예상 공부 시간과 문제의 배점을 저장함
        int[] times = new int[N+1];
        int[] scores = new int[N+1];

        for (int n = 1; n < N+1; n++) {
            st = new StringTokenizer(br.readLine());

            int time = Integer.parseInt(st.nextToken());
            int score = Integer.parseInt(st.nextToken());

            times[n] = time;
            scores[n] = score;
        }

        int[][] dp = new int[N+1][T+1];
        
        for (int t = 0; t < T+1; t++) {
            
            int curTime = t;
            for (int n = 1; n < N+1; n++) {
                
                // 만약, 현재 문제를 푸는 시간보다 단원을 공부하는데 필요한 시간이 크다면 문제 풀지 못함
                if (curTime < times[n]) {
                    dp[n][t] = dp[n-1][t];
                }
                // 만약, 현재 총시간보다 현재 단원을 공부하는 시간이 작아서 문제를 풀 수 있다면 이때는 문제를 푸는게 더 큰 배점인지 풀지 않는 것이 더 큰 배점인지 확인해야 함
                else {
                    dp[n][t] = Math.max(dp[n-1][t], scores[n] + dp[n-1][t - times[n]]);
                }
            }
        }

        // for (int n = 0; n < N+1; n++) {
        //     for (int t = 0; t < T+1; t++) {
        //         System.out.print(dp[n][t] + " ");
        //     }
        //     System.out.println();
        // }

        System.out.println(dp[N][T]);
    }
}
