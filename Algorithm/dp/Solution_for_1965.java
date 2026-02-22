import java.io.*;
import java.util.*;

public class Solution_for_1965 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[] num = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            num[n] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N];
        
        Arrays.fill(dp, 1);

        int answer = 1;
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                // 담을 수 없는 경우
                if (num[i] <= num[j]) continue;

                // 담을 수 있는 경우
                else {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }

                answer = Math.max(answer, dp[i]);
            }
        }

        System.out.println(answer);
    }
}
