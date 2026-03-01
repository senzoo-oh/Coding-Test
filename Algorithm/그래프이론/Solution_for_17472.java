import java.io.*;
import java.util.*;

public class Solution_for_17472 {
    static int N, M;
    static int[][] maps;

    static int[][] islandMap;
    static boolean[][] findIslandVisited;
    static int islandNo = 1;

    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static ArrayList<Bridge> bridges = new ArrayList<>();

    static int bridgeCnt = 0;
    static int totalBridgeSum = 0;

    static int[] parent;

    static class Bridge {
        int start;
        int end;
        int length;

        public Bridge(int start, int end, int length) {
            this.start = start;
            this.end = end;
            this.length = length;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maps = new int[N][M];
        
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++) {
                maps[n][m] = Integer.parseInt(st.nextToken());
            }
        }

        // 나누어진 섬의 영역을 구함
        islandMap = new int[N][M];
        findIslandVisited = new boolean[N][M];
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                if (maps[n][m] == 1 && !findIslandVisited[n][m]) {
                    findIsland(n, m);
                    
                    islandNo++;
                }
            }
        }

        // 각 섬의 영역에서 바다와 인접한 칸이 있다면 인접한 방향으로 DFS탐색을 진행해서 다른 섬과 연결 가능한지 확인함
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                if (maps[n][m] == 1) {

                    int start = islandMap[n][m];
                    
                    // 바다와 인접해 있다면
                    for (int d = 0; d < 4; d++) {
                        
                        int nr = n + dirs[d][0];
                        int nc = m + dirs[d][1];

                        if (nr < 0 || nc < 0 || N <= nr || M <= nc) continue;
                        
                        if (maps[nr][nc] == 0) {
                            findBridge(n, m, d, 0, start);
                        }
                    }
                }
            }
        }

        // list를 다리의 길이를 기준으로 오름차순 정렬함
        bridges.sort((b1, b2) -> {
            return b1.length - b2.length;
        });

        // list에서 다리를 하나씩 확인해서 연결되지 않은 섬인 경우, 건설하고 섬을 연결해줌
        parent = new int[islandNo];
        for (int i = 1; i < islandNo; i++) {
            parent[i] = i;
        }

        for (Bridge b : bridges) {

            // 섬의 연결 여부를 확인함
            if (union(b.start, b.end)) {
                totalBridgeSum += b.length;
                bridgeCnt++;
            }
        }

        // 건설한 다리의 개수가 섬의 개수 - 1 보다 작다면 -1을 출력함
        if (bridgeCnt != (islandNo - 2)) {
            System.out.println(-1);
        }
        else {
            System.out.println(totalBridgeSum);
        }
    }

    public static void findIsland(int r, int c) {
        Queue<int[]> queue = new ArrayDeque<>();

        queue.add(new int[] {r, c});
        findIslandVisited[r][c] = true;
        islandMap[r][c] = islandNo;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dirs[d][0];
                int nc = cur[1] + dirs[d][1];

                if (nr < 0 || nc < 0 || N <= nr || M <= nc) continue;
                
                if (maps[nr][nc] == 1 && !findIslandVisited[nr][nc]) {
                    queue.add(new int[] {nr, nc});
                    findIslandVisited[nr][nc] = true;
                    islandMap[nr][nc] = islandNo;
                }
            }
        }
    }

    // dirs 매개변수, 0: 상, 1: 하, 2: 좌, 3: 우
    public static void findBridge(int r, int c, int d, int length, int start) {
        
        int nr = r + dirs[d][0];
        int nc = c + dirs[d][1];

        // 범위 밖을 벗어난 경우, 섬과 섬을 이어주지 못하는 다리이므로 return 함
        if (nr < 0 || nc < 0 || N <= nr || M <= nc) return;

        // 다리의 길이가 2 이상이고 다른 섬을 연결하는 경우
        if (maps[nr][nc] == 1 && islandMap[nr][nc] != start) {
            
            if (2 <= length) {
                bridges.add(new Bridge(start, islandMap[nr][nc], length));
                return;
            }
        }

        // 바다인 경우
        if (maps[nr][nc] == 0) findBridge(nr, nc, d, length + 1, start);
    }

    static int find(int x) {
        if (parent[x] == x) return x;

        return parent[x] = find(parent[x]);
    }

    static boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        
        if (pa == pb) return false;

        parent[pb] = pa;
        return true;
    }
}
