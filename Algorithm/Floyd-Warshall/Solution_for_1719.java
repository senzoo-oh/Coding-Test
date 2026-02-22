import java.io.*;
import java.util.*;

public class Solution_for_1719 {
    static int N, M;

    static int[][] graph;
    static int[][] route;

    static int INF = 200_000;     // 1000 * 199 = 199,000

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                graph[i][j] = INF;
            }
        }

        route = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                route[i][j] = -1;
            }
        }

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            graph[start][end] = cost;
            graph[end][start] = cost;

            route[start][end] = end;
            route[end][start] = start;
        }

        floyd();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) {
                    System.out.print('-' + " ");
                }
                else System.out.print((route[i][j] + 1) + " ");
            }
            System.out.println();
        }
    }

    public static void floyd() {
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    // 중간노드를 거쳐가는 경로가 최단경로인 경우
                    if ((graph[i][k] + graph[k][j]) < graph[i][j]) {

                        graph[i][j] = graph[i][k] + graph[k][j];
                        
                        // route[i][j] = k;
                        route[i][j] = route[i][k];
                    }
                }
            }
        }
    }
}
