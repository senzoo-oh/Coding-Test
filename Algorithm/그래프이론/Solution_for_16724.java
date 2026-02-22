import java.io.*;
import java.util.*;

public class Solution_for_16724 {
    static int N, M;
    static char[][] maps;
    static int[][] visited;

    // U: 위, D: 아래, L:왼쪽 , R: 오른쪽
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maps = new char[N][M];
        visited = new int[N][M];

        for (int n = 0; n < N; n++) {
            
            String s = br.readLine();

            for (int m = 0; m < M; m++) {
                maps[n][m] = s.charAt(m);
            }
        }

        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                if (visited[n][m] == 0) {
                    dfs(n, m);
                }
            }
        }

        System.out.println(answer);
    }

    public static void dfs(int n, int m) {
        // 방문처리함
        visited[n][m] = 1;

        int nextN = n;
        int nextM = m;
        
        // 방향따라 움직임
        if (maps[n][m] == 'U') {
            nextN += dirs[0][0];
            nextM += dirs[0][1];
        }
        else if (maps[n][m] == 'D') {
            nextN += dirs[1][0];
            nextM += dirs[1][1];
        }
        else if (maps[n][m] == 'L') {
            nextN += dirs[2][0];
            nextM += dirs[2][1];
        }
        else {
            nextN += dirs[3][0];
            nextM += dirs[3][1];
        }

        // 지도 범위 내에 있는지 확인
        if (nextN < 0 || nextM < 0 || N <= nextN || M <= nextM) return;

        // 재방문하지 않은 경우, dfs() 호출함
        if (visited[nextN][nextM] == 0) {
            dfs(nextN, nextM);
        }
        // 현재 탐색중인 dfs경로 상에 있는 경우 -> 사이클 발견
        else if (visited[nextN][nextM] == 1) {
            answer++;
        }

        visited[n][m] = 2;
    }
}
