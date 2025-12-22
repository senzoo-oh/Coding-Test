import java.io.*;
import java.util.*;

public class Solution_for_1446 {
    static int N, D;
    static int[] dp;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        List<Road>[] shortcuts = new ArrayList[D+1];
        for (int i = 0; i <= D; i++) {
            shortcuts[i] = new ArrayList<>();
        }

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());

            int start, end, dist;
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            dist = Integer.parseInt(st.nextToken());

            if (D < end) continue;
            if (end-start <= dist) continue;
            
            shortcuts[start].add(new Road(end, dist));
        }

        int[] dp = new int[D+1];
        for (int i = 0; i <= D; i++) {
            dp[i] = i;
        }

        for (int i = 0; i < D; i++) {
            dp [i+1] = Math.min(dp[i+1], dp[i]+1);

            for (Road r : shortcuts[i]) {
                dp[r.end] = Math.min(dp[r.end], dp[i] + r.dist);
            }
        }

        System.out.println(dp[D]);
    }
}

class Road {
    int end;
    int dist;

    Road(int end, int dist) {
        this.end = end;
        this.dist = dist;
    }
}