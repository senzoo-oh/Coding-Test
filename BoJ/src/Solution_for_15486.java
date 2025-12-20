import java.io.*;
import java.util.*;

public class Solution_for_15486 {
    static int[][] schedule;
    static int[] dp;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        
        schedule = new int[2][N+2];
        dp = new int[N+2];

        for (int n = 1; n < N+1; n++) {
            st = new StringTokenizer(br.readLine());

            int time = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            schedule[0][n] = time;
            schedule[1][n] = cost;
        }

        for (int i = 1; i < N+2; i++) {
            dp[i] = Math.max(dp[i], dp[i-1]);

            if (i + schedule[0][i] <= N + 1) {
                dp[i+schedule[0][i]] = Math.max(dp[i+schedule[0][i]], dp[i] + schedule[1][i]);
            }
        }

        System.out.println(dp[N+1]);
    }
}
