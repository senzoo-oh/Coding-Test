import java.io.*;
import java.util.*;
/*
    비트연산의 결과는 0 또는 bit값 자체이므로 != 0 이라고 해야한다
    # (w & 4) -> 0 또는 4
    # (w & 8) -> 0 또는 8

    (결과의 의미, 0이면 벽이 없음, 0이 아니면 벽이 있음)

    나는 true/false 두가지의 결과로 나오는 줄 알았다.
    그래서 결과가 1이라면 그 방향에 벽이 있는 것으로 생각하여
    "if(비트 연산의 결과 == 1) continue;" 로 했더니 오류가 났다.
*/
public class Solution_for_2234 {
    static int N, M;
    static int[][] walls;   // 벽에 대한 정보를 담은 2차원 배열
    static int[][] visited;     // 몇번째 방인지에 대한 정보를 담은 2차원 배열
    
    static int[][] dirs = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};   // 서,북,동,남
    static int[] bit = {1, 2, 4, 8};    // 1:서, 2:북, 4:동, 8:남

    static int roomNum = 0;     // 이 성에 있는 방의 개수
    static int maxRoomCnt = -1;
    static int roomCnt = 0;

    static int maxRoomSize = -1;

    static ArrayList<Integer> info = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        walls = new int[M][N];
        visited = new int[M][N];
        info.add(0);    // 인덱스를 맞추기 위해 0으로 패딩함.

        for (int m = 0; m < M; m++) {
            Arrays.fill(visited[m], -1);
        }

        // 벽에대한 정보 입력 받기
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            for (int n = 0; n < N; n++) {
                walls[m][n] = Integer.parseInt(st.nextToken());
            }
        }
        
        // BFS
        for (int m = 0; m < M; m++) {
            for (int n = 0; n < N; n++) {
                // 이미 방문한 방인 경우
                if (visited[m][n] != -1) continue;
                
                Queue<int[]> queue = new ArrayDeque<>();
                roomNum++;
                
                queue.add(new int[]{m, n});
                visited[m][n] = roomNum;
                roomCnt = 0;

                while (!queue.isEmpty()) {
                    int[] cur = queue.poll();
                    roomCnt++;

                    for (int d = 0; d < 4; d++) {
                        // 이동하고자 하는 방향으로 벽이 있는 경우
                        if ((walls[cur[0]][cur[1]] & bit[d]) != 0) continue;
                        
                        int nextRow = cur[0] + dirs[d][0];
                        int nextCol = cur[1] + dirs[d][1];

                        // 성의 범위를 벗어나는 경우
                        if (nextRow < 0 || nextCol < 0 || M <= nextRow || N <= nextCol) continue;

                        // 이미 방문한 방인 경우
                        if (visited[nextRow][nextCol] != -1) continue;

                        visited[nextRow][nextCol] = roomNum;
                        queue.add(new int[]{nextRow, nextCol});
                    }
                }
                // 현재 방의 넓이 저장
                info.add(roomCnt);

                maxRoomCnt = Math.max(maxRoomCnt, roomCnt);
            }
        }

        // 하나의 벽을 제거하여 얻을 수 있는 가장 넓은 방의 크기 구하기
        for (int m = 0; m < M; m++) {
            for (int n = 0; n < N; n++) {
                
                // 현재 방의 넓이
                int curSize = info.get(visited[m][n]);

                for (int d = 0; d < 4; d++) {
                    int nextRow = m + dirs[d][0];
                    int nextCol = n + dirs[d][1];

                    // 성의 범위를 벗어나는 경우
                        if (nextRow < 0 || nextCol < 0 || M <= nextRow || N <= nextCol) continue;

                    // 다른 방인경우, 두 방의 합을 구함
                    if ((walls[m][n] & bit[d]) != 0) {
                        if (visited[nextRow][nextCol] != visited[m][n]) {
                        maxRoomSize = Math.max(maxRoomSize, curSize + info.get(visited[nextRow][nextCol]));
                        }
                    }
                }
            }
        }

        System.out.println(roomNum);
        System.out.println(maxRoomCnt);
        System.out.println(maxRoomSize);
    }
}
