import java.io.*;
import java.util.*;

public class Solution_for_17182 {
    static int N;   // 행성의 개수
    static int K;   // ana호가 발사되는 행성의 위치

    static int[][] map;
    static boolean[] visited;

    static int minCost = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        floyd();

        visited = new boolean[N];
        visited[K] = true;
        dfs(K, 0, 1);
        
        // dfsFail(K, 0);

        System.out.println(minCost);
    }

    public static void floyd() {
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }
    }

    public static void dfs(int start, int cost, int cnt) {

        if (cnt == N) {
            minCost = Math.min(minCost, cost);
            return;
        }
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(i, cost + map[start][i], cnt+1);
                visited[i] = false;
            }
        }
    }

    /* 
    이렇게 dfs로직을 짜면 모든 노드를 탐색하지 않았을 경우의 cost를 고려하여 갱신이 되기 때문에 cnt 개수를 체크해주어야 한다.
    visited = {T, T, F}, start = 2, cost = 2 인 경우 minCost를 갱신하게 되어 잘못된 결과를 얻음;
    */
    public static void dfsFail(int start, int cost) {
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfsFail(i, cost + map[start][i]);
                visited[i] = false;
            }
        }

        minCost = Math.min(minCost, cost);
    }
}
