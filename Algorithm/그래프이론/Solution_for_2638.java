import java.io.*;
import java.util.*;

/*
public class Solution_for_2638 {
    static int N, M;
    static int[][] maps;
    static int[][] times;

    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};   // 상, 하, 좌, 우

    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maps = new int[N][M];
        times = new int[N][M];
        
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++) {
                maps[n][m] = Integer.parseInt(st.nextToken());
            }
        }
        
        for (int n = 0; n < N; n++) {
            Arrays.fill(times[n], -1);
        }

        for (int n = 0 ; n < N; n++) {
            for (int m = 0; m < M; m++) {
                if (maps[n][m] == 1) {

                    for (int d = 0; d < 4; d++) {

                        int nextRow = n + dirs[d][0];
                        int nextCol = m + dirs[d][1];

                        if (maps[nextRow][nextCol] != 1) times[nextRow][nextCol] = 0; 
                    }
                }
            }
        }
        
        int time = 0;

        boolean isMelted = true;
        while (isMelted) {
            
            time++;

            isMelted = false;
            // 반복하면서 치즈가 녹는 시간을 times 배열에 저장함
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    // 치즈라면
                        // 주변에 공기와 맞닿는 곳인지 여부를 확인
                        // 치즈를 녹임
                        // 치즈가 녹인 시간을 times에 저장함
                    if (maps[n][m] == 1) {
                        
                        int airCnt = 0;
                        for (int d = 0; d < 4; d++) {

                            int nextRow = n + dirs[d][0];
                            int nextCol = m + dirs[d][1];

                            if ((times[nextRow][nextCol] != -1 && times[nextRow][nextCol] < time)) {
                                airCnt++;
                            }
                        }

                        if (2 <= airCnt) {
                            times[n][m] = time;

                            isMelted = true;
                        }
                    }
                }
            }
        }

        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                answer = Math.max(answer, times[n][m]);
            }
        }

        System.out.println(answer);
    }
}

*/

public class Solution_for_2638 {
    static int N, M;
    static int[][] maps;
    static boolean[][] air;

    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};   // 상, 하, 좌, 우

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maps = new int[N][M];
        air = new boolean[N][M];
        
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++) {
                maps[n][m] = Integer.parseInt(st.nextToken());
            }
        }
        
        int time = 0;
        while (true) {

            // 바깥공기가 있는 부분을 구함
            air = new boolean[N][M];

            Queue<int[]> queue = new ArrayDeque<>();
            queue.add(new int[] {0, 0});
            air[0][0] = true;

            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                
                for (int d = 0; d < 4; d++) {
                    int nextRow = cur[0] + dirs[d][0];
                    int nextCol = cur[1] + dirs[d][1];

                    if (nextRow < 0 || nextCol < 0 || N <= nextRow || M <= nextCol) continue;
                    if (air[nextRow][nextCol]) continue;
                    if (maps[nextRow][nextCol] == 1) continue;

                    air[nextRow][nextCol] = true;
                    queue.add(new int[] {nextRow, nextCol});
                }
            }

            List<int[]> meltedList = new ArrayList<>();

            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    
                    if (maps[n][m] == 0) continue;
                    
                    int airCnt = 0;
                    for (int d = 0; d < 4; d++) {
                        int nextRow = n + dirs[d][0];
                        int nextCol = m + dirs[d][1];

                        if (nextRow < 0 || nextCol < 0 || N <= nextRow || M <= nextCol) continue;
                        
                        if (air[nextRow][nextCol]) airCnt++;
                    }

                    if (airCnt >= 2) {
                        meltedList.add(new int[] {n, m});
                    }
                }
            }

            if (meltedList.size() == 0) break;

            for (int[] l : meltedList) {
                maps[l[0]][l[1]] = 0;
            }

            time++;
        }

        System.out.println(time);
    }
}

