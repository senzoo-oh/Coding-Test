import java.io.*;
import java.util.*;


public class Solution_for_1520 {
    static int M, N;
    static int[][] maps;

    static int[][] dp;
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        maps = new int[M][N];
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());

            for (int n = 0; n < N; n++) {
                maps[m][n] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[M][N];
        for (int m = 0; m < M; m++) {
            Arrays.fill(dp[m], -1);
        }

        dfs(0, 0);
        
        System.out.println(dp[0][0]);

        for (int m = 0; m < M; m++) {
            for (int n = 0; n < N; n++) {
                System.out.print(dp[m][n] + " ");
            }
            System.out.println();
        }
    }

    public static int dfs(int m, int n) {
        // 범위 밖을 벗어난 경우
        // if (m < 0 || n < 0 || M <= m || N <= n) return 0;

        // 이미 방문한 지점인 경우
        if (dp[m][n] != -1) return dp[m][n];

        // 최종 목적지인 경우
        if ((m == M-1) && (n == N-1)) return 1;

        dp[m][n] = 0;

        // 방문하지 않은 경우, 4방향으로 탐색
        for (int d = 0; d < 4; d++) {
            int nextM = m + dirs[d][0];
            int nextN = n + dirs[d][1];

            if (nextM < 0 || nextN < 0 || M <= nextM || N <= nextN) continue;
            
            if (maps[nextM][nextN] < maps[m][n]) {
                dp[m][n] += dfs(nextM, nextN);
            }
        }

        return dp[m][n];
    }
}
