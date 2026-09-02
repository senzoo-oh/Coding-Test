import java.util.*;

class Solution {
    
    public char[][] storages;
    public char[][] status;
    
    public int row;
    public int col;
    
    public int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public int solution(String[] storage, String[] requests) {
        int answer = 0;
        
        row = storage.length;
        col = storage[0].length();
        
        storages = new char[row][col];
        
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                storages[r][c] = storage[r].charAt(c);
            }
        }
        
        // 각 요청에 대해서
        for (int req = 0; req < requests.length; req++) {
            
            // System.out.println("req: " + requests[req]);
            status = new char[row][col];
            
            for (int r = 0; r < row; r++) {
                for (int c = 0; c < col; c++) {
                    if (storages[r][c] == requests[req].charAt(0)) {
                        
                        // 외부와 맞닿아 있는 칸인 경우
                        // System.out.println("동일한 r, c: " + r+", "+c);
                        if (isOutside(r, c)) {
                            status[r][c] = '.';
                        }
                        // 크레인 요청인 경우
                        else if (requests[req].length() == 2) {
                            status[r][c] = '-';
                        }
                    }
                }
            }
            
            
            
            // System.out.println("[status]");
            // for (int r = 0; r < row; r++) {
            //     for (int c = 0; c < col; c++) {
            //         if (status[r][c] == '.') {
            //             System.out.print('x');
            //         }
            //         else if (status[r][c] == '-') {
            //             System.out.print('c');
            //         }
            //         else System.out.print('o');
            //     }
            //     System.out.println();
            // }
            // System.out.println();
            
            // 창고 배열과 상태 배열을 비교하여 창고 배열을 변경함
            for (int r = 0; r < row; r++) {
                for (int c = 0; c < col; c++) {
                    if (status[r][c] == '.') storages[r][c] = '.';
                    else if (status[r][c] == '-') storages[r][c] = '-';
                }
            }
            
            // 크레인으로 제거한 컨테이너가 외부와 맞닿아 있는 경우 상태를 '내부' 에서 '외부'로 변경한다.
            for (int r = 0; r < row; r++) {
                for (int c = 0; c < col; c++) {
                    if (status[r][c] == '.') {
                        BFS(r, c);
                    }
                }
            }
            
            // System.out.println("[storages] BFS 탐색 후");
            // for (int r = 0; r < row; r++) {
            //     for (int c = 0; c < col; c++) {
            //         System.out.print(storages[r][c]);
            //     }
            //     System.out.println();
            // }
            // System.out.println();
        }
        
        // storages에 남아있는 컨테이너 개수를 셈.
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (storages[r][c] != '.' && storages[r][c] != '-') answer++;
            }
        }
        
        return answer;
    }
    
    public boolean isOutside(int r, int c) {
        
        // System.out.println("알파벳을 찾은 후, 외부에 인접한 갑인지 확인하기 전 [storages]");
        // for (int rr = 0; rr < row; rr++) {
        //     for (int cc = 0; cc < col; cc++) {
        //         System.out.print(storages[rr][cc]);
        //     }
        //     System.out.println();
        // }
        // System.out.println();
        
        if (r == 0 || c == 0 || r == (row - 1) || c == (col - 1)) return true;
        
        for (int d = 0; d < 4; d++) {
            int nr = r + dirs[d][0];
            int nc = c + dirs[d][1];
            
            if (nr < 0 || nc < 0 || row <= nr || col <= nc) continue;
            if (storages[nr][nc] == '.') return true;
        }
        
        return false;
    }
    
    public void BFS(int r, int c) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[row][col];
        
        queue.add(new int[] {r, c});
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            storages[cur[0]][cur[1]] = '.';
            
            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dirs[d][0];
                int nc = cur[1] + dirs[d][1];

                if (nr < 0 || nc < 0 || row <= nr || col <= nc) continue;

                if (storages[nr][nc] == '-' && !visited[nr][nc]) {
                    //System.out.println("크레인으로 제거한 컨테이너: " + nr + ", " + nc);
                    queue.add(new int[] {nr, nc});
                    visited[nr][nc] = true;
                }
            }
        }
    }
}