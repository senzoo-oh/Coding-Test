import java.io.*;
import java.util.*;

public class Solution_for_2629 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int weightCnt = Integer.parseInt(st.nextToken());
        int[] weights = new int[weightCnt+1];

        st = new StringTokenizer(br.readLine());
        for (int w = 1; w < weightCnt+1; w++) {
            weights[w] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        
        int beadCnt = Integer.parseInt(st.nextToken());
        int[] beads = new int[beadCnt+1];

        st = new StringTokenizer(br.readLine());
        for (int b = 1; b < beadCnt+1; b++) {
            beads[b] = Integer.parseInt(st.nextToken());
        }

        boolean[][] dp = new boolean[weightCnt + 1][15001];
        dp[0][0] = true;

        for (int i = 1; i < weightCnt + 1; i++) {
            // 현재 추의 무게
            int curWeight = weights[i];
            
            // 현재 추 1개만 사용하는 경우
            dp[i][curWeight] = true;

            for (int j = 0; j < 15001; j++) {
                if (!dp[i-1][j]) continue;

                // 현재 추를 사용하지 않는 경우
                dp[i][j] = true;

                // 현재 추를 무거운 쪽에 올린 경우
                if (j + curWeight < 15001)
                    dp[i][j + curWeight] = true;

                // 현재 추를 가벼운 쪽에 올린 경우
                int diff = Math.abs(j - curWeight);
                dp[i][diff] = true;
            }
        }

        for (int b = 1; b < beadCnt+1; b++)  {
            if (15000 < beads[b]) System.out.print("N" + " ");
            else System.out.print((dp[weightCnt][beads[b]] ? "Y" : "N") + " ");
        }
    }
}
