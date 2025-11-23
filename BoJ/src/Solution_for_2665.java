import java.io.*;
import java.util.*;

public class Solution_for_2665 {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];
        
        for (int n = 0; n < N; n++) {
            String[] col = br.readLine().split("");

            for (int m = 0; m < N; m++) {
                map[n][m] = Integer.parseInt(col[m]);
            }
        }

        int answer = findMinBreakCount();

        System.out.println(answer);
    }

    public static int findMinBreakCount() {
        PriorityQueue<Room> pq = new PriorityQueue<>();

        pq.offer(new Room(0, 0, 0));
        visited[0][0] = true;

        while (!pq.isEmpty()) {
            Room cur = pq.poll();

            // 끝방에 도착했는지 여부 확인
            if (cur.row == (N-1) && cur.col == (N-1)) {
                return cur.breakCount;
            }

            // 상하좌우 탐색
            for (int d = 0; d < 4; d++) {
                int nextRow = cur.row + dirs[d][0];
                int nextCol = cur.col + dirs[d][1];

                // 범위 검증
                if (nextRow < 0 || nextCol < 0 || N <= nextRow || N <= nextCol) continue;

                // 방문 여부 검증
                if (visited[nextRow][nextCol]) continue;
                
                // 흰방인 경우와 검은방인 경우를 분리하여 큐에 넣어줌
                if (map[nextRow][nextCol] == 0) {
                    pq.offer(new Room(nextRow, nextCol, cur.breakCount+1));
                }
                else {
                    pq.offer(new Room(nextRow, nextCol, cur.breakCount));
                }

                // 방문처리함
                visited[nextRow][nextCol] = true;
            }
        }
        return 0;
    }
}

class Room implements Comparable<Room> {
    int row;
    int col;
    int breakCount;

    public Room(int row, int col, int breakCount) {
        this.row = row;
        this.col = col;
        this.breakCount = breakCount;
    }

    @Override
    public int compareTo(Room r) {
        return this.breakCount - r.breakCount;
    }
}

/Users/senzoo/Desktop/study/Coding_Test/BoJ/src/Solution_for_2665.java