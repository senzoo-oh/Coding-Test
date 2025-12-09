import java.io.*;
import java.util.*;

public class Solution_for_17135 {
    static int N, M, D;
    static int[][] map;
    static int answer = 0;
    static int[] archer = new int[3];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());

            for (int m = 0; m < M; m++) {
                map[n][m] = Integer.parseInt(st.nextToken());
            }
        }

        // 3명의 궁수 위치 선택(조합)
        setArcher(0, 0);


        System.out.println(answer);
    }

    public static void setArcher(int idx, int start) {
        if (idx == 3) {
            // 게임 시뮬레이션을 돌리며 제거할 수 있는 적의 수 카운트 하기
            answer = Math.max(answer, startGame());
            return;
        }
        else {
            for (int col = start; col < M; col++) {
                archer[idx] = col;
                setArcher(idx+1, col+1);
            }
        }
    }

    public static int startGame() {
        int[][] copyMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copyMap[i][j] = map[i][j];
            }
        }

        int enemyCnt = 0;

        while (true) {
            Set<Point> targets = new HashSet<>();

            for (int a = 0; a < 3; a++) {
                Point target = findEnemy(copyMap, archer[a]);
                if (target != null) targets.add(target);
            }

            for (Point p : targets) {
                if (copyMap[p.r][p.c] == 1) {
                    enemyCnt++;
                    copyMap[p.r][p.c] = 0;
                }
            }

            moveEnemies(copyMap);

            if (!existsEnemy(copyMap)) break;
        }
        
        return enemyCnt;
    }

    public static Point findEnemy(int[][] map, int col) {
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];

        q.add(new Point(N - 1, col, 1));
        visited[N-1][col] = true;

        while (!q.isEmpty()) {
            Point cur = q.poll();

            if (D < cur.dist) break;

            if (map[cur.r][cur.c] == 1) return cur;

            int[][] dir = {{0, -1}, {-1, 0}, {0, 1}};
            for (int i = 0; i < 3; i++) {
                int nr = cur.r + dir[i][0];
                int nc = cur.c + dir[i][1];

                if (nr < 0 || nc < 0 || N <= nr || M <= nc) continue;
                if (visited[nr][nc]) continue;

                visited[nr][nc] = true;
                q.add(new Point(nr, nc, cur.dist + 1));
            }
        }
        return null;
    }

    public static void moveEnemies(int[][] map) {
        for (int r = N - 1; 0 < r; r--) {
            for (int c = 0; c < M; c++) {
                map[r][c] = map[r-1][c];
            }
        }

        for (int c = 0; c < M; c++) {
            map[0][c] = 0;
        }
    }

    public static boolean existsEnemy(int[][] map) {
        boolean isEnemyExist = false;
        
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (map[r][c] == 1) isEnemyExist = true;
            }
        }

        return isEnemyExist;
    }

    public static class Point {
        int r;
        int c;
        int dist;

        Point(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }
    }
}
