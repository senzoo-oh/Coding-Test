import java.util.*;

// 다익스트라
class Solution {
    
    public static int N;
    public static int[][] dirs = {
        {0, 1},     // 동
        {0, -1},    // 서
        {1, 0},     // 남
        {-1, 0}     // 북
    };   
    
    public static int[][][] minCost;
    
    public static class Node {
        int r;
        int c;
        int dir;
        int cost;
        
        public Node(int r, int c, int dir, int cost) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.cost = cost;
        }
    }
    
    public int solution(int[][] board) {
        
        N = board.length;
        
        // 우선순위 큐는 비용을 기준으로 오름차순(다음에 방문할 노드들)
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> {
            return a.cost - b.cost;
        });
        
        minCost = new int[N][N][2];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                for (int d = 0; d < 2; d++) {
                    minCost[r][c][d] = Integer.MAX_VALUE;
                }
            }
        }
        
        // 시작점에서 탐색 가능한 노드를 우선순위 큐에 담음
        for (int i = 0; i < 4; i++) {
            int nr = dirs[i][0];
            int nc = dirs[i][1];
            
            if (nr < 0 || nc < 0 || N <= nr || N <= nc) continue;
            if (board[nr][nc] == 1) continue;
            
            int nextDir;
            
            if (i == 0 || i == 1) nextDir = 0;
            else nextDir = 1;
            
            int nextCost = 100;
            
            pq.offer(new Node(nr, nc, nextDir, nextCost));
        }
        
        // 큐에 있는 노드들을 하나씩 꺼내면서 다음 탐색 가능한 노드들을 큐에 계속 담음.
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            
            // 동일한 위치에 같은 방향으로 갈때, 지금까지 구한 최소비용보다 더 많은 비용이 든다면, 탐색할 가치가 없으므로 건너뜀
            if (minCost[cur.r][cur.c][cur.dir] <= cur.cost) continue;
            
            // 최소비용 갱신
            minCost[cur.r][cur.c][cur.dir] = cur.cost;
            
            // 다음 탐색 가능한 노드들을 큐에 담음
            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dirs[i][0];
                int nc = cur.c + dirs[i][1];
                
                if (nr < 0 || nc < 0 || N <= nr || N <= nc) continue;
                if (board[nr][nc] == 1) continue;
                
                // 다음노드로 가는 방향 구하기
                int nextDir;
                
                if (i == 0 || i == 1) nextDir = 0;
                else nextDir = 1;
                
                // 비용 구하기(현재 방향이랑 다음 방향이랑 같은지 다른지에 따라 비용이 다름)
                int nextCost;
                
                if (cur.dir != nextDir) nextCost = 600;
                else nextCost = 100;
                
                // 만약, 지금까지 구한 최소비용보다 더 많은 비용이 든다면 큐에 담지 않는다.
                if (minCost[nr][nc][nextDir] <= cur.cost + nextCost) continue;
                
                pq.offer(new Node(nr, nc, nextDir, cur.cost + nextCost));
            }
            
        }
        return Math.min(minCost[N-1][N-1][0], minCost[N-1][N-1][1]);
    }
}

// DFS + 백트래킹
class Solution1 {
    public static int answer = Integer.MAX_VALUE;
    
    public static int N;
    public static int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};   // 동, 서, 남, 북
    
    public static int[][][] minCost;
    
    public static int dir = 0;  // -1: 시작점, 0: 가로, 1: 세로
    
    public int solution(int[][] board) {
        
        N = board.length;
        
        minCost = new int[N][N][2];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                for (int d = 0; d < 2; d++) {
                    minCost[r][c][d] = Integer.MAX_VALUE;
                }
            }
        }
        
        DFS(board, 0, 0, 0, -1);
        
        return Math.min(minCost[N-1][N-1][0], minCost[N-1][N-1][1]);
    }
    
    public void DFS(int[][] board, int r, int c, int cost, int dir) {
        
        for (int i = 0; i < 4; i++) {
            int nr = r + dirs[i][0];
            int nc = c + dirs[i][1];
            
            // 접근 가능한 인덱스
            if (0 <= nr && 0 <= nc && nr < N && nc < N) {
                
                // 벽이 아닌 경우
                if (board[nr][nc] == 0) {
                    
                    int nextDir;
                    
                    // 다음 방향 선택
                    if (i == 0 || i == 1) {
                        nextDir = 0;
                    }
                    else {
                        nextDir = 1;
                    }
                    
                    int nextCost;
                    
                    if (dir == -1) {
                        nextCost = cost + 100;
                    }
                    // 같은 방향인 경우
                    else if (dir == nextDir) {
                        nextCost = cost + 100;
                    }
                    // 다른 방향인 경우
                    else {
                        nextCost = cost + 600;
                    }
                    
                    if (nextCost < minCost[nr][nc][nextDir]) {
                        minCost[nr][nc][nextDir] = nextCost;
                        DFS(board, nr, nc, nextCost, nextDir);
                    }
                }
            }
        }
    }
}