import java.io.*;
import java.util.*;

public class Solution_for_1600 {
    static int K, W, H; // K: 이동 가능 횟수, W: 가로, H: 세로
    static int[][] maps;

    static int[][][] visited;
    static Queue<LOC> queue = new ArrayDeque<>();

    static int[][] horseMove = {{-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}, {1, -2}, {2, -1}, {2, 1}, {1, 2}};
    static int[][] monkeyMove = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static int answer = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        maps = new int[H][W];

        for (int h = 0; h < H; h++) {
            st = new StringTokenizer(br.readLine());
            for (int w = 0; w < W; w++) {
                maps[h][w] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new int[H][W][K+1];

        for (int h = 0; h < H; h++) {
            for (int w = 0; w < W; w++) {
                Arrays.fill(visited[h][w], Integer.MAX_VALUE);
            }
        }

        queue.add(new LOC(0, 0, 0, 0));
        visited[0][0][0] = 0;
        BFS(0, 0);

        System.out.println(answer == -1 ? -1 : answer);
    }

    public static void BFS(int r, int c) {

        while (!queue.isEmpty()) {
            LOC cur = queue.poll();

            // 목적지에 도달한 경우
            if ((cur.row == (H - 1)) && (cur.col == (W - 1))) {
                answer = cur.moveCnt;
                return;
            }

            // 말로 이동하는 경우
            if (cur.kCnt < K) {
                for (int h = 0; h < 8; h++) {
                    int nextRow = cur.row + horseMove[h][0];
                    int nextCol = cur.col + horseMove[h][1];

                    // 탐색 가능범위 여부 확인
                    if (nextRow < 0 || nextCol < 0 || H <= nextRow || W <= nextCol) continue;
                    if (maps[nextRow][nextCol] == 1) continue;

                    int nextK = cur.kCnt + 1;
                    int nextMoveCnt = cur.moveCnt + 1;
                    
                    // 최소한의 이동횟수인지 확인
                    if (visited[nextRow][nextCol][nextK] > nextMoveCnt) {
                        visited[nextRow][nextCol][nextK] = nextMoveCnt;
                        queue.add(new LOC(nextRow, nextCol, nextK, nextMoveCnt));
                    }
                }
            }

            // 원숭이로 이동하는 경우
            for (int m = 0; m < 4; m++) {
                int nextRow = cur.row + monkeyMove[m][0];
                int nextCol = cur.col + monkeyMove[m][1];

                // 탐색 가능범위 여부 확인
                if (nextRow < 0 || nextCol < 0 || H <= nextRow || W <= nextCol) continue;
                if (maps[nextRow][nextCol] == 1) continue;

                int nextK = cur.kCnt;
                int nextMoveCnt = cur.moveCnt + 1;
                
                // 최소한의 이동횟수인지 확인
                if (visited[nextRow][nextCol][nextK] > nextMoveCnt) {
                    visited[nextRow][nextCol][nextK] = nextMoveCnt;
                    queue.add(new LOC(nextRow, nextCol, nextK, nextMoveCnt));
                }
            }
        }
    }

    public static class LOC {
        int row;
        int col;
        int kCnt;
        int moveCnt;
        
        public LOC(int row, int col, int kCnt, int moveCnt) {
            this.row = row;
            this.col = col;
            this.kCnt = kCnt;
            this.moveCnt = moveCnt;
        }
    }
}
