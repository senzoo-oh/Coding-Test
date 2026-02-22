import java.io.*;
import java.util.*;

public class Solution_for_9084 {
    static int T, N, M;

    static int[] coins;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(st.nextToken());

        for (int t = 0; t < T; t++) {
            // 동전의 가지 수 입력받기
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            
            // 동전의 금액 입력받기
            coins = new int[N+1];
            st = new StringTokenizer(br.readLine());
            for (int n = 1; n < N+1; n++) {
                coins[n] = Integer.parseInt(st.nextToken());
            }

            // 만들어야 할 금액 입력받기
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());

            dp = new int[N+1][M+1];
            dp[0][0] = 1;

            for (int n = 1; n < N+1; n++) {
                
                int amount = coins[n];
                
                for (int m = 0; m < M+1; m++) {
                    // 현재 동전을 사용하지 않는 경우
                    dp[n][m] = dp[n-1][m];

                    // 현재 동전을 사용하는 경우
                    if ((m - amount) < 0) continue;

                    dp[n][m] += dp[n][m - amount];
                }
            }

            sb.append(dp[N][M] + "\n");
        }

        System.out.println(sb);
    }

    public static void main2(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(st.nextToken());

        for (int t = 0; t < T; t++) {
            // 동전의 가지 수 입력받기
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            
            // 동전의 금액 입력받기
            coins = new int[N+1];
            st = new StringTokenizer(br.readLine());
            for (int n = 1; n < N+1; n++) {
                coins[n] = Integer.parseInt(st.nextToken());
            }

            // 만들어야 할 금액 입력받기
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());

            dp = new int[N+1][M+1];

            for (int n = 1; n < N; n++) {
                int amount = coins[n];  // 현재 동전의 금액

                // 현재 동전으로만 만들 수 있는 금액 구하기
                if (amount <= M) {
                    for (int i = 1; i < M/amount; i++) {
                        dp[n][amount * i]++;
                    }
                }
                
                // 이전 동전으로 만들 수 있는 금액 + 현재 동전(1~(M/amount))개로 만들 수 있는 금액
                if (n == 1) continue;

                for (int c = 1; c < n; c++) {

                    for (int m = 1; m < M; m++) {
                        if (dp[c][m] == 0) continue;
                        
                        // 현재 코인 x개로 만들 수 있는 금액
                        if (amount <= M) {
                            for (int x = 1; x < M/amount; x++) {
                                if (M < (m + (amount * x))) break;
                                dp[n][m + (amount * x)] = dp[c][m] + 1;
                            }
                        }
                    }
                }
            }

            sb.append(dp[N][M] + "\n");
        }

        System.out.println(sb);
    }
}
