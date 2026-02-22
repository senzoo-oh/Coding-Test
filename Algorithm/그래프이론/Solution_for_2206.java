import java.io.*;
import java.util.*;

public class Solution_for_2206 {
    static int N, M;
    static int[][] map;
    static boolean[][][] visited;

    static int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M][2];

        for (int n = 0; n < N; n++) {
            String s = br.readLine();

            for (int m = 0; m < M; m++) {
                map[n][m] = s.charAt(m) - '0';
            }
        }

        Queue<Loc> queue = new LinkedList<>();
        queue.add(new Loc(0, 0, 1, false));
        
        while(!queue.isEmpty()) {
            Loc cur = queue.poll();

            if ((cur.n == N - 1) && (cur.m == M - 1)) {
                System.out.println(cur.cnt);
                return;
            }
            
            for (int d = 0; d < 4; d++) {
                int nextRow = cur.n + dirs[d][0];
                int nextCol = cur.m + dirs[d][1];

                if (nextRow < 0 || nextCol < 0 || N <= nextRow || M <= nextCol) continue;

                // 벽이 아닌 경우
                if (map[nextRow][nextCol] == 0) {
                    // 한번도 벽을 부수지 않은 경우
                    if (!cur.breakYN && !visited[nextRow][nextCol][0]) {
                        queue.add(new Loc(nextRow, nextCol, cur.cnt + 1, cur.breakYN));
                        visited[nextRow][nextCol][0] = true;
                    }
                    else if (cur.breakYN && !visited[nextRow][nextCol][1]) {
                        queue.add(new Loc(nextRow, nextCol, cur.cnt + 1, cur.breakYN));
                        visited[nextRow][nextCol][1] = true;
                    }
                }

                // 벽인 경우
                else {
                    if (!cur.breakYN && !visited[nextRow][nextCol][1]) {        // visited[nextRow][nextCol][1]: 벽을 부수어서 방문할 곳
                        queue.add(new Loc(nextRow, nextCol, cur.cnt + 1, true));
                        visited[nextRow][nextCol][1] = true;
                    }
                }
            }
        }

        System.out.println(-1);
    }

    public static class Loc {
        int n;
        int m;
        int cnt;
        boolean breakYN;

        public Loc(int n, int m, int cnt, boolean breakYN) {
            this.n = n;
            this.m = m;
            this.cnt = cnt;
            this.breakYN = breakYN;
        }
    }
}
