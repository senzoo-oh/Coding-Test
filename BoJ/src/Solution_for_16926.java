import java.io.*;
import java.util.*;

public class Solution_for_16926 {
    static int N, M;
    static int R;
    static int[][] map;

    static int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    static int layer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int n = 0; n < N; n++) {

            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++) {
                map[n][m] = Integer.parseInt(st.nextToken());
            }
        }

        layer = Math.min(N, M);
        
        for (int r = 0; r < R; r++) {
            rotate();
        }

        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                System.out.print(map[n][m] + " ");
            }
            System.out.println();
        }
    }

    public static void rotate() {
        
        for (int t = 0; t < layer / 2; t++) {
            int r = t;
            int c = t;

            int temp = map[r][c];

            int idx = 0;

            while (idx < 4) {
                int nr = r + dirs[idx][0];
                int nc = c + dirs[idx][1];

                if (nr < N-t && t <= nr && nc < M-t && t <= nc) {
                    map[r][c] = map[nr][nc];

                    r = nr;
                    c = nc;
                }
                else idx++;
            }

            map[t+1][t] = temp;

        }
    }
}
