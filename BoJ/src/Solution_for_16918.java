import java.io.*;
import java.util.*;

public class Solution_for_16918 {
    static int R, C, N;
    static char[][] maps;
    static int[][] timeRecord;

    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상, 하, 좌, 우

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        maps = new char[R][C];
        timeRecord = new int[R][C];

        for (int r = 0; r < R; r++) {
            
            String status = br.readLine();
            for (int c = 0; c < C; c++) {

                char s = status.charAt(c);

                maps[r][c] = s;

                // 초기상태에 설치된 폭탄은 3초에 터짐
                if (s == 'O') {
                    timeRecord[r][c] = 3;
                }
            }
        }

        int time = 2;  // 1초까지 현재 상태를 유지함
        while (time <= N) {
            // 짝수일 경우, 폭탄이 설치되어 있지 않은 곳에 폭탄을 설치함
            if (time % 2 == 0) {
                planting(time);
            }

            // 홀수일 경우, 현재 시간과 동일한 곳에 설치된 폭탄을 터뜨림
            else {
                explode(time);
            }

            time++;
        }

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                System.out.print(maps[r][c]);
            }
            System.out.println();
        }
    }

    public static void planting(int time) {
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (maps[r][c] == '.') {
                    maps[r][c] = 'O';
                    timeRecord[r][c] = time + 3;
                }
            }
        }
    }

    public static void explode(int time) {
        boolean[][] isExplode = new boolean[R][C];

        // 터질 폭탄위치 기록
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (maps[r][c] == 'O' && timeRecord[r][c] == time) {
                    isExplode[r][c] = true;
                }
            }
        }

        // 폭탄 터뜨리기(maps 갱신)
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (isExplode[r][c]) {

                    // 폭탄이 있던 칸 파괴
                    maps[r][c] = '.';
                    
                    // 인접한 네 칸도 함께 파괴
                    for (int d = 0; d < 4; d++) {
                        int nextRow = r + dirs[d][0];
                        int nextCol = c + dirs[d][1];

                        if (nextRow < 0 || nextCol < 0 || R <= nextRow || C <= nextCol) continue;

                        maps[nextRow][nextCol] = '.';
                    }
                }
            }
        }
    }
}
