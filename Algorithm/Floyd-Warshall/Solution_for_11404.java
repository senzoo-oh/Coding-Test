import java.io.*;
import java.util.*;


public class Solution_for_11404 {
    static int N, M;
    static int[][] maps;

    static int INF = 9_900_001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        maps = new int[N][N];

        // 인접행렬을 INF로 초기화 함(시작노드와 목적지노드가 동일한 경우 0)
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i != j) {
                    maps[i][j] = INF;
                }
            }
        }
        
        // 간선을 이어줌
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());

            int startNode = Integer.parseInt(st.nextToken()) - 1;
            int endNode = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            // 문제에서 "시작 도시와 도착 도시를 연결하는 노선은 하나가 아닐 수 있다."
            if (maps[startNode][endNode] < cost) continue;

            maps[startNode][endNode] = cost;
        }

        floyd();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (maps[i][j] == INF) {
                    System.out.print(0 + " ");
                }
                else System.out.print(maps[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void floyd() {
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    maps[i][j] = Math.min(maps[i][j], maps[i][k] + maps[k][j]);
                }
            }
        }
    }
}
