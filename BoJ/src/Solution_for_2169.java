import java.io.*;
import java.util.*;

public class Solution_for_2169 {
    static int N, M;
    static int[][] map;
    static int[][] dp;
    static int[] toLeft;
    static int[] toRight;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];
        dp = new int[N][M];

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());

            for (int m = 0; m < M; m++) {
                map[n][m] = Integer.parseInt(st.nextToken());
            }
        }

        // 첫번째 행은 '왼쪽 -> 오른쪽' 방향으로 밖에 탐사할 수 있음
        dp[0][0] = map[0][0];
        for (int m = 1; m < M; m++) {
            dp[0][m] = dp[0][m-1] + map[0][m];
        }
        
        // 각각의 행에 대해서 오른쪽으로의 결과와 왼쪽으로의 결과를 toLeft, toRight에 담음
        for (int n = 1; n < N; n++) {
            
            // 오른쪽으로의 결과를 구함
            toRight = new int[M];
            toRight[0] = dp[n-1][0] + map[n][0];
            
            for (int m = 1; m < M; m++) {
                toRight[m] = map[n][m] + Math.max(toRight[m-1], dp[n-1][m]);
            }
            
            // 왼쪽으로의 결과를 구함
            toLeft = new int[M];
            toLeft[M-1] = dp[n-1][M-1] + map[n][M-1];

            for (int m = M-2; 0 <= m; m--) {
                toLeft[m] = map[n][m] + Math.max(toLeft[m+1], dp[n-1][m]);
            }

            // dp배열을 toLeft, toRight의 값에서 최댓값으로 담음
            for (int m = 0; m < M; m++) {
                dp[n][m] = Math.max(toRight[m], toLeft[m]);
            }
        }

        System.out.println(dp[N-1][M-1]);
    }
}
