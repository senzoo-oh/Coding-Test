import java.io.*;
import java.util.*;

public class Solution_for_1106 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[] costs = new int[N];
        int[] people = new int[N];

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());

            costs[n] = Integer.parseInt(st.nextToken());
            people[n] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[100001];
        
        for (int n = 0; n < N; n++) {
            int cost = costs[n];
            int person = people[n];

            for (int i = 0; i < 100001; i++) {
                if (cost <= i) {
                    dp[i] = Math.max(dp[i], dp[i - cost] + person);
                }
            }
        }

        for (int i = 1; i < 100001; i++) {
            if (C <= dp[i]) {
                System.out.println(i);
                return;
            }
        }
    }
}
