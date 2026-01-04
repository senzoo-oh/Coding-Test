import java.io.*;
import java.util.*;

public class Solution_for_3109 {
    static int R, C;
    static char[][] map;
    static boolean[][] visited;

    // 오른쪽 위, 오른쪽, 오른쪽 아래
    static int[][] dirs = {{-1, 1}, {0, 1}, {1, 1}}; 

    static int pipeCnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        map = new char[R][C];
        visited = new boolean[R][C];

        for (int r = 0; r < R; r++) {
            String s = br.readLine();

            for (int c = 0; c < C; c++) {
                map[r][c] = s.charAt(c);
            }
        }

        // 파이프를 하나만 설치하므로 DFS(재귀). 즉, 모든 방향으로의 탐색이 아님.

        /*
        DFS 탐색을 진행하며 되돌아 올때 설치한 파이프라인을 해제할 필요 없음.
        방문여부를 체크해야 하는데 앞에서 탐색을 진행할 때 파이프라인을 설치하지 못하는 경로라면 나중에 방문하지 못하도록 방문여부를 체크해야 효율적인 탐색이 가능함
        */
        
        // 첫번째 열에서 DFS를 시작함
        for (int r = 0; r < R; r++) {
            installPipe2(r, 0);
        }

        System.out.println(pipeCnt);
    }

    // 파라미터(r: 행, c: 열)
    public static void installPipe(int r, int c) {
        // 원웅이의 빵집(마지막 열)에 도착한 경우
        if (c == (C-1)) {
            // 파이프라인 개수 1증가 하고 return 함
            pipeCnt++;
            return;
        }
        
        // 설치할 수 있는 파이프라인을 탐색함
        for (int d = 0; d < 3; d++) {
            int nextRow = r + dirs[d][0];
            int nextCol = c + dirs[d][1];

            if (nextRow < 0 || nextCol < 0 || R <= nextRow || C <= nextCol) continue;

            // 건물인 경우 또는 파이프가 설치되어 있는 곳인 경우 설치 못함
            if (map[nextRow][nextCol] == 'x' || map[nextRow][nextCol] == 'p') continue;

            // 방문은 했지만 비어있는 공간이면 앞으로 탐색할 필요가 없는 경로
            if (visited[nextRow][nextCol] && map[nextRow][nextCol] == '.') continue;

            visited[nextRow][nextCol] = true;
            map[nextRow][nextCol] = 'p';

            installPipe(nextRow, nextCol);
        }
    }

    public static boolean installPipe2(int r, int c) {
        // 원웅이의 빵집(마지막 열)에 도착한 경우
        if (c == (C-1)) {
            // 파이프라인 개수 1증가 하고 return 함
            pipeCnt++;
            return true;
        }
        
        // 설치할 수 있는 파이프라인을 탐색함
        for (int d = 0; d < 3; d++) {
            int nextRow = r + dirs[d][0];
            int nextCol = c + dirs[d][1];

            if (nextRow < 0 || nextCol < 0 || R <= nextRow || C <= nextCol) continue;

            // 건물인 경우 또는 파이프가 설치되어 있는 곳인 경우 설치 못함
            if (map[nextRow][nextCol] == 'x' || visited[nextRow][nextCol]) continue;

            visited[nextRow][nextCol] = true;
            
            if(installPipe2(nextRow, nextCol)) return true;
        }

        return false;
    }
}
