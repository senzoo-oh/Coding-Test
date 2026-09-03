import java.util.*;

class Solution {
    
    public int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public char[][] storages;
    
    public int row;
    public int col;
    
    public boolean[][] visited;
    
    public int solution(String[] storage, String[] requests) {
        int answer = 0;
        
        // storages를 인덱스 2씩 늘려서 창고가 배열의 정중앙에 오게 구현
        row = storage.length + 2;
        col = storage[0].length() + 2;
        
        storages = new char[row][col];
        
        for (int r = 1; r < row - 1; r++) {
            for (int c = 1; c < col - 1;  c++) {
                storages[r][c] = storage[r-1].charAt(c-1);
            }
        }
        
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col;  c++) {
                if (r == 0 || r == row - 1 || c == 0 || c == col - 1) storages[r][c] = '.';
            }
        }
        
        // 각 요청에 대해서
            // 상태 배열 생성
            
            // BFS(0, 0) 으로 외부와 연결된 컨테이너를 제거함
        
            // 크레인을 사용하는 경우
                // 모든 칸을 돌면서 요청이 들어온 알파벳이면 제거함
        for (int req = 0; req < requests.length; req++) {
            
            if (requests[req].length() == 2) {
                for (int r = 1; r < row - 1; r++) {
                    for (int c = 1; c < col - 1; c++) {
                        if (storages[r][c] == requests[req].charAt(0)) {
                            storages[r][c] = '.';
                        }
                    }
                }
            }
            else BFS(0, 0, requests[req]);
        }
        
        for (int r = 1; r < row - 1; r++) {
            for (int c = 1; c < col - 1; c++) {
                if (storages[r][c] != '.') answer++;
            }
        }
        
        return answer;
    }
    
    public void BFS(int r, int c, String req) {
        Queue<int[]> queue = new LinkedList<>();
        visited = new boolean[row][col];
        
        queue.add(new int[] {r, c});
        visited[r][c] = true;
        
        List<int[]> removeList = new LinkedList<>();
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            
            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dirs[d][0];
                int nc = cur[1] + dirs[d][1];
                
                if (nr < 0 || nc < 0 || row <= nr || col <= nc) continue;
                if (visited[nr][nc]) continue;
                
                // 현재 외부 주위에 요청이 들어온 컨테이너가 있으면 -> 상태 업데이트
                if (storages[nr][nc] == req.charAt(0)) {
                    removeList.add(new int[] {nr, nc});
                    visited[nr][nc] = true;
                }
                
                // 현재 외부와 연결된 외부가 있다면 큐에 담고 탐색함
                else if (storages[nr][nc] == '.') {
                    queue.add(new int[] {nr, nc});
                    visited[nr][nc] = true;
                }
            }
        }
        
        for (int[] remove : removeList) {
            storages[remove[0]][remove[1]] = '.';
        }
    }
}
