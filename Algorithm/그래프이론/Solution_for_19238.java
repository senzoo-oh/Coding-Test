import java.io.*;
import java.util.*;

public class Solution_for_19238 {
    static int N, M;
    static int[][] maps;
    static boolean[] visited; // 태운 승객 여부 확인

    // 택시의 현재 위치
    static int curRow;
    static int curCol;

    // 택시가 태울 승객
    static PASSENGER selPass;

    static int passengerCnt = 0;
    static int oil;

    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static int[][] passLoc;

    static boolean isPossible = true;
    /*
    현재 택시 위치에서 태울 수 있는 승객들 중에서 가장 가까운 거리에 있는 승객과의 거리를 구함
        만약, 태울 수 있는 승객이 없다면 반복문 종료함
    현재 원료에서 가장 가까운 거리에 있는 승객과의 거리를 뺌
        만약, 원료가 부족하다면 -1을 출력하고 종료함
    현재 택시 위치를 승객의 위치로 변경함
    현재 택시 위치에서 승객의 목적지까지의 거리를 구함
    현재 원료에서 승객의 목적지까지의 거리를 뺌
        만약, 원료가 부족하다면 -1을 출력하고 종료함
    현재 원료에 (승객의 목적지까지의 거리 * 2)를 더함
    현재 택시 위치를 승객의 목적지로 변경함
    태운 승객의 수를 1 증가시킴
    
    태운 승객 방문처리 함

    만약, 마지막에 태운 승객의 수가 M보다 작다면, -1을 출력함
    아니라면, 남은 연료를 출력함
    */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        oil = Integer.parseInt(st.nextToken());

        // 지도
        maps = new int[N][N];
        for (int r = 0; r < N; r++) {
            
            st = new StringTokenizer(br.readLine());
            
            for (int c = 0; c < N; c++) {
                maps[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        // 택시의 시작위치
        st = new StringTokenizer(br.readLine());

        curRow = Integer.parseInt(st.nextToken()) - 1;
        curCol = Integer.parseInt(st.nextToken()) - 1;

        // 승객의 위치와 목적지 위치
        passLoc = new int[M][4];
        visited = new boolean[M];
        
        for (int m = 0; m < M; m++) {
            
            st = new StringTokenizer(br.readLine());
            
            for (int i = 0; i < 4; i++) {
                passLoc[m][i] = Integer.parseInt(st.nextToken()) - 1;
            }
        }

        while (true) {
            // 현재 택시 위치에서 태울 수 있는 승객을 구함
            findPassenger(curRow, curCol);
            if (!isPossible) {
                System.out.println(-1);
                return;
            }

            // 현재 원료에서 가장 가까운 거리에 있는 승객과의 거리를 뺌
                // 만약, 원료가 부족하다면 -1을 출력하고 종료함
            if (oil < selPass.sDist) {
                System.out.println(-1);
                return;
            }
            else {
                oil -= selPass.sDist;
            }

            // 현재 택시 위치를 승객의 위치로 변경함
            curRow = selPass.sr;
            curCol = selPass.sc;

            // 현재 택시 위치에서 승객의 목적지까지의 거리를 구함
            findDestination(curRow, curCol);
            if (!isPossible) {
                System.out.println(-1);
                return;
            }

            // 현재 원료에서 승객의 목적지까지의 거리를 뺌
            //     만약, 원료가 부족하다면 -1을 출력하고 종료함
            if (oil < selPass.dDist) {
                System.out.println(-1);
                return;
            }
            else oil -= selPass.dDist;

            // 현재 원료에 (승객의 목적지까지의 거리 * 2)를 더함
            oil += (selPass.dDist * 2);

            // 현재 택시 위치를 승객의 목적지로 변경함
            curRow = selPass.dr;
            curCol = selPass.dc;

            // 태운 승객의 수를 1 증가시킴
            passengerCnt++;
    
            // 태운 승객 방문처리 함
            visited[selPass.passNo] = true;

            if (passengerCnt == M) {
                System.out.println(oil);
                break;
            }
        }
    }

    public static void findPassenger(int r, int c) {
        Queue<LOC> queue = new ArrayDeque<>();
        boolean[][] checked = new boolean[N][N];

        queue.add(new LOC(r, c, 0));
        checked[r][c] = true;

        // 동일한 거리내에 태울 수 있는 승객
        List<PASSENGER> list = new ArrayList<>();

        while (!queue.isEmpty()) {

            int size = queue.size();
            list.clear();

            for (int s = 0; s < size; s++) {
                LOC cur = queue.poll();

                // 승객이 서 있는 위치와 비교함
                for (int i = 0; i < M; i++) {
                    if (!visited[i] && cur.r == passLoc[i][0] && cur.c == passLoc[i][1]) {
                        list.add(new PASSENGER(i, passLoc[i][0], passLoc[i][1], passLoc[i][2], passLoc[i][3], cur.d, 0));
                    }
                }

                // 택시가 이동할 다음 위치를 큐에 담음
                for (int d = 0; d < 4; d++) {
                    int nextRow = cur.r + dirs[d][0];
                    int nextCol = cur.c + dirs[d][1];

                    if (nextRow < 0 || nextCol < 0 || N <= nextRow || N <= nextCol || checked[nextRow][nextCol]) continue;
                    if (maps[nextRow][nextCol] == 1) continue;

                    checked[nextRow][nextCol] = true;
                    queue.add(new LOC(nextRow, nextCol, cur.d + 1));
                }
            }

            if (!list.isEmpty()) {
                
                list.sort((a, b) -> {
                    if (a.sr != b.sr) return a.sr - b.sr;
                    return a.sc - b.sc;
                });

                selPass = list.get(0);
                return;
            }
        }

        isPossible = false;
    }

    public static void findPassenger1(int r, int c) {
        Queue<LOC> queue = new ArrayDeque<>();
        boolean[][] checked = new boolean[N][N];

        queue.add(new LOC(r, c, 0));
        checked[r][c] = true;

        // 동일한 거리내에 태울 수 있는 승객
        List<PASSENGER> list = new ArrayList<>();

        while (!queue.isEmpty()) {
            LOC cur = queue.poll();

            // 현재 택시위치에 서있는 승객이 있다면, 동일한 거리내에서 태울 수 있는 승객을 모두 구함
            int dist;

            for (int m = 0; m < M; m++) {
                if (cur.r == passLoc[m][0] && cur.c == passLoc[m][1] && !visited[m]) {

                    dist = cur.d;

                    while (!queue.isEmpty() && dist == queue.peek().d) {
                    LOC next = queue.poll();

                    for (int i = 0; i < M; i++) {
                        if (next.r == passLoc[i][0] && next.c == passLoc[i][1] && !visited[i]) {
                            list.add(new PASSENGER(i, passLoc[i][0], passLoc[i][1], passLoc[i][2], passLoc[i][3], dist, 0));
                        }
                    }
                }

                list.sort((a, b) -> {
                    if (a.sr != b.sr) return a.sr - b.sr;
                    return a.sc - b.sc;
                });

                selPass = list.get(0);
                return;
                }
            }
            // 택시가 이동할 수 있는 상하좌우를 탐색하여 큐에 넣는다.
            for (int d = 0; d < 4; d++) {
                int nextRow = cur.r + dirs[d][0];
                int nextCol = cur.c + dirs[d][1];

                if (nextRow < 0 || nextCol < 0 || N <= nextRow || N <= nextCol || checked[nextRow][nextCol]) continue;
                if (maps[nextRow][nextCol] == 1) continue;

                queue.add(new LOC(nextRow, nextCol, cur.d + 1));
                checked[nextRow][nextCol] = true;
            }

            // 더이상 택시가 움직일 수 없고, 승객을 태울 수 없고, 승객을 모두 태우지 못한 경우
            if (queue.isEmpty() && passengerCnt < M) {
                System.out.println(-1);
                isPossible = false;
            }
        }
    }

    public static void findDestination(int r, int c) {
        Queue<LOC> queue = new ArrayDeque<>();
        boolean[][] checked = new boolean[N][N];

        queue.add(new LOC(r, c, 0));
        checked[r][c] = true;

        while (!queue.isEmpty()) {
            LOC cur = queue.poll();
            
            // 승객의 목적지에 도달한 경우
            if (cur.r == selPass.dr && cur.c == selPass.dc) {
                selPass.dDist = cur.d;
                return;
            }

            // 목적지가 아닌 경우, 상하좌우 이동
            for (int d = 0; d < 4; d++) {
                int nextRow = cur.r + dirs[d][0];
                int nextCol = cur.c + dirs[d][1];

                if (nextRow < 0 || nextCol < 0 || N <= nextRow || N <= nextCol || checked[nextRow][nextCol]) continue;
                if (maps[nextRow][nextCol] == 1) continue;

                queue.add(new LOC(nextRow, nextCol, cur.d + 1));
                checked[nextRow][nextCol] = true;
            }
        }

        isPossible = false;
    }

    public static class LOC {
        int r;
        int c;
        int d;

        public LOC (int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }

    public static class PASSENGER {

        int passNo;
        int sr;
        int sc;
        int dr;
        int dc;
        int sDist;
        int dDist;

        public PASSENGER (int passNo, int sr, int sc, int dr, int dc, int sDist, int dDist) {
            this.passNo = passNo;
            this.sr = sr;
            this.sc = sc;
            this.dr = dr;
            this.dc = dc;
            this.sDist = sDist;
            this.dDist = dDist;
        }
    }
}
