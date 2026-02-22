import java.io.*;
import java.util.*;

public class Solution_for_7576 {
    static int M, N;
    static int[][] box;
    static int[][] status;

    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());   // 열
        N = Integer.parseInt(st.nextToken());   // 행

        box = new int[N][M];

        Queue<int[]> queue = new LinkedList<>();

        // 초기 토마토의 상태를 체크
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++) {
                box[n][m] = Integer.parseInt(st.nextToken());
                if (box[n][m] == 1) {
                    queue.add(new int[] {n, m});
                }
            }
        }
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            
            for (int d = 0; d < 4; d++) {
                int nextRow = cur[0] + dirs[d][0];
                int nextCol = cur[1] + dirs[d][1];

                if (nextRow < 0 || nextCol < 0 || N <= nextRow || M <= nextCol) continue;

                // 안익은 토마토가 아니라면 탐색할 필요 없음
                if (box[nextRow][nextCol] != 0) continue;

                box[nextRow][nextCol] = box[cur[0]][cur[1]] + 1;
                queue.add(new int[] {nextRow, nextCol});
            }
        }

        int ans = 0;
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                if (box[n][m] == 0) {
                    System.out.println(-1);
                    return;
                }
                
                ans = Math.max(ans, box[n][m]);
            }
        }
        System.out.println(ans - 1);
    }
}
