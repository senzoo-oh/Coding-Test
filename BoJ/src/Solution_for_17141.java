import java.io.*;
import java.util.*;

public class Solution_for_17141 {
    static int N, M;
    static int[][] map;
    static boolean[] isSelected;

    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        isSelected = new boolean[N*N];

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());

            for (int nn = 0; nn < N; nn++) {
                map[n][nn] = Integer.parseInt(st.nextToken());
            }
        }

        selectPlace(0, 0);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    public static void selectPlace(int cnt, int pos) {
        // M개의 바이러스를 다 놓았을 경우
        if (cnt == M) {
            // 바이러스를 퍼뜨림
            spread();
            return;

            // 모든 빈 칸에 바이러스가 있게 되는 시간을 출력

        }
        
        else {
            for (int i = pos; i < N*N; i++) {
                // 해당 칸이 바이러스를 놓을 수 있는 공간이고, 아직 바이러스를 놓지 않았을 경우
                int row = i / N;
                int col = i % N;

                if ((map[row][col] == 2) && !isSelected[i]) {
                    map[row][col] = -1;
                    isSelected[i] = true;

                    selectPlace(cnt+1, i+1);

                    map[row][col] = 2;
                    isSelected[i] = false;
                }
            }
        }
    }

    public static void spread() {
        int[][] status = new int[N][N];
        Queue<Virus> queue = new LinkedList<>();
        
        // 벽은 -2 로, 바이러스는 -1로 표시함
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1) status[i][j] = -2;
                
                else if (map[i][j] == 2) status[i][j] = -3;

                else if (map[i][j] == -1) {
                    status[i][j] = 0;
                    queue.add(new Virus(i, j, 0));
                }
            }
        }

        // 바이러스 퍼짐
        while (!queue.isEmpty()) {
            Virus cur = queue.poll();
            
            for (int d = 0; d < 4; d++) {
                int nextRow = cur.row + dirs[d][0];
                int nextCol = cur.col + dirs[d][1];

                if (nextRow < 0 || nextCol < 0 || N <= nextRow || N <= nextCol) continue;

                // 바이러스가 퍼질 수 없는 공간(벽, 바이러스가 놓여있는 곳)이라면
                if (status[nextRow][nextCol] == -2) continue; // 벽만 차단
                if (status[nextRow][nextCol] >= 0) continue; // 이미 방문

                status[nextRow][nextCol] = cur.time+1;
                
                queue.add(new Virus(nextRow, nextCol, cur.time+1));
            }
        }

        // 모든 공간에 바이러스가 퍼져있는지 확인
        boolean isAllSpread = true;
        int maxTime = 0;

        Loop1:
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 바이러스가 모두 퍼지지 않은 경우
                if (map[i][j] == 0 && status[i][j] < 0) {
                    isAllSpread = false;
                    break Loop1;
                }

                if (map[i][j] == 0) {
                    maxTime = Math.max(maxTime, status[i][j]);
                }
            }
        }

        if (isAllSpread) {
            answer = Math.min(answer, maxTime);
        }
    }
}

class Virus {
    int row;
    int col;
    int time;

    public Virus(int row, int col, int time) {
        this.row = row;
        this.col = col;
        this.time = time;
    }
}
