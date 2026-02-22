import java.io.*;
import java.util.*;

public class Solution_for_1012 {
    static int T;
    static int M, N, K;
    static boolean[][] farm;
    static boolean[][] visited;

    static int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(st.nextToken());

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());   // 열
            N = Integer.parseInt(st.nextToken());   // 행
            K = Integer.parseInt(st.nextToken());

            farm = new boolean[N][M];
            visited = new boolean[N][M];

            for (int k = 0; k < K; k++) {
                st = new StringTokenizer(br.readLine());

                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());

                farm[Y][X] = true;
            }
            
            // 배추가 심어져 있는 부분의 개수를 구함
            int ans = 0;
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    if (farm[n][m] && !visited[n][m]) {
                        ans++;
                        bfs(n, m);
                    }
                }
            }
            sb.append(ans + "\n");
        }
        System.out.print(sb.toString());
    }

    public static void bfs(int n, int m) {
        Queue<int[]> queue = new LinkedList<>();
        
        queue.add(new int[] {n, m});
        visited[n][m] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            
            for (int d = 0; d < 4; d++) {
                int nextRow = cur[0] + dirs[d][0];
                int nextCol = cur[1] + dirs[d][1];

                if (nextRow < 0 || nextCol < 0 || N <= nextRow || M <= nextCol) continue;

                if (!farm[nextRow][nextCol] || visited[nextRow][nextCol]) continue;

                queue.add(new int[] {nextRow, nextCol});
                visited[nextRow][nextCol] = true;
            }
        }
    }
}
