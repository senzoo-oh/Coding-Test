import java.util.*;
import java.io.*;


public class Solution_for_11066 {
    static int T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());

            int[] num = new int[K];
            st = new StringTokenizer(br.readLine());
            for (int k = 0; k < K; k++) {
                num[k] = Integer.parseInt(st.nextToken());
            }

            // 누적합 구하기
            int[] sum = new int[K];
            sum[0] = num[0];

            for (int i = 1; i < K; i++) {
                sum[i] = sum[i-1] + num[i];
            }

            // dp계산
            int[][] dp = new int[K][K];
            for (int i = 0; i < K; i++) {
                Arrays.fill(dp[i], Integer.MAX_VALUE);
            }

            for (int i = 0; i < K; i++) {
                for (int j = 0; j < K; j++) {
                    if (i == j) dp[i][j] = 0;
                    
                    if ((i + 1) == j) {
                        dp[i][j] = num[i] + num[j];
                    }
                }
            }

            for (int j = 2; j < K; j++) {
                for (int i = 0; i < K - j; i++) {
                    for (int k = i; k < i + j; k++) {
                        if (i == 0) {
                            dp[i][i+j] = Math.min(dp[i][i+j], dp[i][k] + dp[k+1][i+j] + sum[i+j]);
                        }
                        else dp[i][i+j] = Math.min(dp[i][i+j], dp[i][k] + dp[k+1][i+j] + sum[i+j] - sum[i-1]);
                    }
                }
            }
            
            System.out.println(dp[0][K-1]);
        }
    }
}
