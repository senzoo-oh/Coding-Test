import java.io.*;
import java.util.*;

public class Solution_for_2096 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][3];

        int[][] minDp = new int[N][3];
        int[][] maxDp = new int[N][3];

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());

            for (int nn = 0; nn < 3; nn++) {
                map[n][nn] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < 3; i++) {
            minDp[0][i] = map[0][i];
            maxDp[0][i] = map[0][i];
        }

        // 최대 점수 구하기
        for (int n = 1; n < N; n++) {
            maxDp[n][0] = Math.max(maxDp[n-1][0] + map[n][0], maxDp[n-1][1] + map[n][0]);
            maxDp[n][1] = Math.max(maxDp[n-1][0] + map[n][1], Math.max(maxDp[n-1][1] + map[n][1], maxDp[n-1][2] + map[n][1]));
            maxDp[n][2] = Math.max(maxDp[n-1][1] + map[n][2], maxDp[n-1][2] + map[n][2]);
        }

        // 최소 점수 구하기
        for (int n = 1; n < N; n++) {
            minDp[n][0] = Math.min(minDp[n-1][0] + map[n][0], minDp[n-1][1] + map[n][0]);
            minDp[n][1] = Math.min(minDp[n-1][0] + map[n][1], Math.min(minDp[n-1][1] + map[n][1], minDp[n-1][2] + map[n][1]));
            minDp[n][2] = Math.min(minDp[n-1][1] + map[n][2], minDp[n-1][2] + map[n][2]);
        }

        int maxSum = Math.max(maxDp[N-1][0], Math.max(maxDp[N-1][1], maxDp[N-1][2]));
        int minSum = Math.min(minDp[N-1][0], Math.min(minDp[N-1][1], minDp[N-1][2]));

        System.out.println(maxSum + " " + minSum);
    }
}
