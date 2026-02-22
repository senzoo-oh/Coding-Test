import java.io.*;
import java.util.*;

public class Solution_for_1535 {
    static int N;
    static int[] L; // 체력
    static int[] J; // 기쁨

    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        L = new int[N+1];
        J = new int[N+1];

        dp = new int[N+1][101];

        st = new StringTokenizer(br.readLine());
        for (int n = 1; n < N+1; n++) {
            L[n] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int n = 1; n < N+1; n++) {
            J[n] = Integer.parseInt(st.nextToken());
        }

        // dp[i][j] = i번째 사람까지 고려했을 때, 체력이 j라면 얻을 수 있는 최대 기쁨
        for (int j = 1; j < 101; j++) {
            for (int n = 1; n < N+1; n++) {
                int curL = L[n];    // 체력
                int curJ = J[n];    // 기쁨

                // 안 만나는 경우
                if (j - curL < 1) {
                    dp[n][j] = dp[n-1][j];
                }
                // 만날 수 있는 경우
                else {
                    dp[n][j] = Math.max(dp[n-1][j], curJ + dp[n-1][j-curL]);
                }
            }
        }

        System.out.println(dp[N][100]);
    }
}
