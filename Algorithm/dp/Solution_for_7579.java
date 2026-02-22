import java.io.*;
import java.util.*;

public class Solution_for_7579 {
    static int N, M;    // N: 앱의 개수, M: 필요한 메모리

    static int[][] app;

    static int[] m;     // 앱이 사용중인 메모리
    static int[] c;     // 앱을 비활성화하는데 필요한 비용

    static int appMemorySum = 0;
    static int maxMemory = 0;   // 배낭의 무게

    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        app = new int[N+1][2];

        st = new StringTokenizer(br.readLine());
        for (int n = 1; n < N+1; n++) {
            app[n][0] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int n = 1; n < N+1; n++) {
            app[n][1] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N+1][10001];

        int answer = Integer.MAX_VALUE;

        for (int c = 0; c < 10001; c++) {
            for (int item = 1; item < N+1; item++) {
                int memory = app[item][0];
                int cost = app[item][1];

                // 현재 앱을 비활성화 할 수 없는 경우
                if (c < cost) {
                    dp[item][c] = dp[item-1][c];
                }
                // 현재 앱을 비활성화 할 수 있는 경우
                else {
                    if (memory + dp[item-1][c-cost] > dp[item-1][c]) {
                        dp[item][c] = memory + dp[item-1][c-cost];
                    }
                    else {
                        dp[item][c] = dp[item-1][c];
                    }
                }
                
                if (M <= dp[item][c]) {
                    System.out.println(c);
                    return;
                    // answer = Math.min(answer, c);
                }
            }
        }

        System.out.println(answer);
    }

    public static void failSolution2 (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 앱이 사용하는 메모리
        m = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for (int n = 1; n < N+1; n++) {
            m[n] = Integer.parseInt(st.nextToken());
            appMemorySum += m[n];
        }

        // 앱을 비활성화 하는데 필요한 비용
        c = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for (int n = 1; n < N+1; n++) {
            c[n] = Integer.parseInt(st.nextToken());
        }
        
        // 배낭의 크기
        maxMemory = appMemorySum - M;


        // dp 채우기
        dp = new int[N+1][maxMemory+1];
        for (int i = 1; i < N+1; i++) {
            for (int w = 0; w < maxMemory+1; w++) {
                // 현재 물건의 무게가 배낭의 크기보다 작은 경우
                if (m[i] <= w) {
                    // 기존의 물건을 빼고 현재 물건을 넣은 경우의 가치가 더 높은 경우
                    if (dp[i-1][w] < c[i] + dp[i][w-m[i]]) {
                        dp[i][w] = c[i] + dp[i][w-m[i]];
                    }
                    else {
                        dp[i][w] = dp[i-1][w];
                    }
                }
                else {
                    dp[i][w] = dp[i-1][w];
                }
            }
        }

        System.out.println(dp[N][maxMemory]);
    }

    public static void failSolution(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        m = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            m[n] = Integer.parseInt(st.nextToken());
        }

        c = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            c[n] = Integer.parseInt(st.nextToken());
        }

        ArrayList<App> list = new ArrayList<>();
        for (int n = 0; n < N; n++) {
            list.add(new App(m[n], c[n]));
        }

        int[] sum = new int[N];
        sum[0] = m[0];
        
        int sumMemory = 0;
        int sumCost = 0;
        for (int n = 0; n < N; n++) {
            sumMemory += list.get(n).m;
            sumCost += list.get(n).c;

            if (M <= sumMemory) {
                System.out.println(sumCost);
                return;
            }
        }
    }

    public static class App implements Comparable<App> {
        int m;
        int c;

        public App(int m, int c) {
            this.m = m;
            this.c = c;
        }

        @Override
        public int compareTo(App a) {
            if (this.c == a.c) {
                return a.m - this.m;
            }
            else {
                return this.c - a.c;
            }
        }
    }
}