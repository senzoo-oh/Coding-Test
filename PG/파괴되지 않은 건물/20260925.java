import java.util.*;

class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        
        // 누적합 배열
        int[][] dist = new int[board.length+1][board[0].length+1];
        
        for (int s = 0; s < skill.length; s++) {
            int[] cur = skill[s];
            
            int type = cur[0];
            
            int startRow = cur[1];
            int startCol = cur[2];
            
            int endRow = cur[3];
            int endCol = cur[4];
            
            int value = 0;
            // 공격인 경우(4를 -4로)
            if (type == 1) {
                value = 0 - cur[5];
            }
            // 회복인 경우
            else {
                value = cur[5];
            }
            
            // 4군데에 기록한다.(근데, 행의 끝까지 +3을 해준다고 하면, 변화량 배열의 행은 어디에 기록을 해야할까?)
            dist[startRow][startCol] += value;
            dist[startRow][endCol+1] -= value;
            dist[endRow+1][startCol] -= value;
            dist[endRow+1][endCol+1] += value;
        }
        
        // 누적합 배열의 가로 누적합을 계산한다.
        for (int r = 0; r < dist.length; r++) {
            for (int c = 1; c < dist[0].length; c++) {
                dist[r][c] = dist[r][c-1] + dist[r][c];
            }
        }
        // 누적합 배열의 세로 누적합을 계산한다.
        for (int c = 0; c < dist[0].length; c++) {
            for (int r = 1; r < dist.length; r++) {
                dist[r][c] = dist[r-1][c] + dist[r][c];
            }
        }
        
        // 누적합 배열과 board값을 비교하여 건물의 내구도가 1이상인 건물의 수를 구한다.
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (1 <= board[r][c] + dist[r][c]) answer++;
            }
        }
        
        return answer;
    }
}