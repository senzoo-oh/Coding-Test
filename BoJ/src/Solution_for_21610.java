import java.io.*;
import java.util.*;

public class Solution_for_21610 {
    static int N, M;
    static int[][] map;
    static int[][] move;

    static boolean[][] cloudMap;

    // 명령중 1번을 위한 방향
    static int[][] dirs = {
        {0, 0}, // 인덱스를 맞추기 위해
        {0, -1}, {-1, -1}, {-1, 0}, {-1, 1},    // ←, ↖, ↑, ↗
        {0, 1}, {1, 1}, {1, 0}, {1, -1}         // →, ↘, ↓, ↙
    };

    // 명령중 4번을 위한 방향
    static int[][] checkDirs = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};    

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        move = new int[M][2];

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int nn = 0; nn < N; nn++) {
                map[n][nn] = Integer.parseInt(st.nextToken());
            }
        }

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());

            move[m][0] = Integer.parseInt(st.nextToken());
            move[m][1] = Integer.parseInt(st.nextToken());
        }

        cloudMap = new boolean[N][N];

        cloudMap[N-1][0] = true;
        cloudMap[N-1][1] = true;
        cloudMap[N-2][0] = true;
        cloudMap[N-2][1] = true;

        for (int m = 0; m < M; m++) {
            int d = move[m][0];     // 방향
            int s = move[m][1];     // 거리

            // 구름 이동시킴
            int rowMove = dirs[d][0] * s;
            int colMove = dirs[d][1] * s;

            boolean[][] nextCloudMap = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (cloudMap[i][j]) {
                        int nextRow = ((i + (rowMove)) % N + N) % N;
                        int nextCol = ((j + (colMove)) % N + N) % N;

                        nextCloudMap[nextRow][nextCol] = true;
                    }
                }
            }
            cloudMap = nextCloudMap;

            // 구름이 있는 칸의 물의 양을 1 증가시킴
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (cloudMap[i][j]) {
                        map[i][j]++;
                    }
                }
            }

            // 구름 사라짐(구름이 있던 위치는 기억해야함)

            // 구름이 있던 칸의 대각선 방향에 물이 존재하는 칸의 개수만큼 물의 양이 증가함
            int[][] waterSums = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (cloudMap[i][j]) {

                        for (int k = 0; k < 4; k++) {
                            int checkRow = i + checkDirs[k][0];
                            int checkCol = j + checkDirs[k][1];

                            if (checkRow < 0 || checkCol < 0 || N <= checkRow || N <= checkCol) continue;
                            if (map[checkRow][checkCol] != 0) {
                                waterSums[i][j]++;
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (cloudMap[i][j]) {
                        map[i][j] += waterSums[i][j];
                    }
                }
            }

            // 물의 양이 2이상인 곳이면서 구름이 없는 곳에 구름을 생성함
            nextCloudMap = new boolean[N][N];
            int[][] afterCloudWaterSums = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (2 <= map[i][j] && !cloudMap[i][j]) {
                        nextCloudMap[i][j] = true;
                        afterCloudWaterSums[i][j] = -2;
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (nextCloudMap[i][j]) {
                        map[i][j] += afterCloudWaterSums[i][j];
                    }
                }
            }
            cloudMap = nextCloudMap;
        }

        // 모든칸에 있는 물의 양의 합을 구함
        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                answer += map[i][j];
            }
        }

        System.out.println(answer);
    }
}
