import java.io.*;
import java.util.*;

public class Solution_for_11047 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 동전 입력받기
        int[] coins = new int[N];
        for (int n = 0; n < N; n++) {
            coins[n] = Integer.parseInt(br.readLine());
        }

        int cnt = 0;
        for (int n = N-1; 0 <= n; n--) {
            int value = coins[n];

            if (value <= K) {
                cnt += (K / value);
                K -= ((K / value) * value);
            }
        }

        System.out.println(cnt);
    }
}

// 1000 4개, 790원
// 500원 1개, 290원
// 100원 2개, 90원
// 50원 1개, 40원
// 10원 4개, 0원