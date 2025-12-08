import java.io.*;
import java.util.*;

public class Solution_for_14442 {
    static int N, M, K;
    static int[][] map;
    static boolean[][][] visited;

    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M][K+1];
        
        for (int n = 0; n < N; n++) {
            // st = new StringTokenizer(br.readLine());
            String line = br.readLine();

            for (int m = 0; m < M; m++) {
                map[n][m] = line.charAt(m) - '0';
            }
        }

        System.out.println(bfs());

    }

    public static int bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {0, 0, 1, K});    // 행, 열, 이동거리, k
        visited[0][0][K] = true;
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (cur[0] == N-1 && cur[1] == M-1) {
                return cur[2];
            }
            
            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dirs[i][0];
                int nc = cur[1] + dirs[i][1];

                if (nr < 0 || nc < 0 || N <= nr || M <= nc) continue;
                if (visited[nr][nc][cur[3]]) continue;

                if (map[nr][nc] == 0) {
                    queue.add(new int[] {nr, nc, cur[2] + 1, cur[3]});
                    visited[nr][nc][cur[3]] = true;
                }
                else {
                    if (0 < cur[3] && !visited[nr][nc][cur[3]-1]) {
                        queue.add(new int[] {nr, nc, cur[2] + 1, cur[3] - 1});
                        visited[nr][nc][cur[3]-1] = true;
                    }
                    else {

                    }
                }
            }
        }

        return -1;
    }
}
