import java.util.*;

// 누적합
class Solution
{
    
    public static int R;
    public static int C;

    public static int[][] sum;
    
    public int solution(int[][] board)
    {
        R = board.length;
        C = board[0].length;
        
        // 누적합 구하기
        sum = new int[R+1][C+1];
        for (int r = 1; r < R+1; r++) {
            for (int c = 1; c < C+1; c++) {
                sum[r][c] = board[r-1][c-1] + sum[r-1][c] + sum[r][c-1] - sum[r-1][c-1];
            }
        }
        
        for (int r = mid; r < R + 1; r++) {
            for (int c = mid; c < C + 1; c++) {
                if (sum[r][c] - sum[r-mid][c] - sum[r][c-mid] + sum[r-mid][c-mid] == result) {
                    return true;
                }
            }
        }
        
        

        return (right - 1) * (right - 1);
    }
    
    public boolean check_v1(int mid) {
        
        for (int r = 0; r <= R - mid; r++) {
            for (int c = 0; c <= C - mid; c++) {
                
                if (boards[r][c] == 1) {
                    // 한변의 길이가 mid인 정사각형을 만들 수 있는지 확인함
                    Loop1:
                    for (int l = 0; l < mid; l++) {
                        for (int h = 0; h < mid; h++) {
                            // 중간에 0이 있다면 반복문 탈출
                            if (boards[r+l][c+h] == 0) {
                                break Loop1;
                            }
                            
                            // 반복문을 중간에 탈출하지 않고 끝까지 도달하고 끝까지 모두 1인 경우 true를 반환함.
                            if ((l == (mid - 1)) && (h == (mid - 1)) && (boards[r + l][c + h] == 1)) {
                                return true;
                            } 
                        }
                    }
                }
            }
        }
        
        return false;
    }
    
    public boolean check_v2(int mid) {
        
        int result = mid * mid;
        
        for (int r = mid; r < R + 1; r++) {
            for (int c = mid; c < C + 1; c++) {
                // System.out.println("r, c: " + r + ", " + c);
                // System.out.println("sum[r][c]: " + sum[r][c]);
                // System.out.println("sum[r-mid][c]: " + sum[r-mid][c]);
                // System.out.println("sum[r][c-mid]: " + sum[r][c-mid]);
                // System.out.println("sum[r-mid][c-mid]: " + sum[r][c]);
                // System.out.println(sum[r][c] - sum[r-mid][c] - sum[r][c-mid] + sum[r-mid][c-mid]);
                if (sum[r][c] - sum[r-mid][c] - sum[r][c-mid] + sum[r-mid][c-mid] == result) {
                    return true;
                }
            }
        }
        
        return false;
    }
}

// 이분탐색 + 누적합
/*
class Solution
{

    public static int[][] boards;
    public static int R;
    public static int C;

    public static int[][] sum;
    
    public int solution(int[][] board)
    {
        R = board.length;
        C = board[0].length;
        
        boards = new int[R][C];
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                boards[r][c] = board[r][c];
            }
        }
        
        // 누적합 구하기
        sum = new int[R+1][C+1];
        for (int r = 1; r < R+1; r++) {
            for (int c = 1; c < C+1; c++) {
                sum[r][c] = board[r-1][c-1] + sum[r-1][c] + sum[r][c-1] - sum[r-1][c-1];
            }
        }
        
        // for (int r = 0; r < R+1; r++) {
        //     for (int c = 0; c < C+1; c++) {
        //         System.out.print(sum[r][c]);
        //     }
        //     System.out.println();
        // }
        
        int left = 1;
        int right = Math.min(R, C) + 1;
        
        // 이분탐색 (UPPER BOUND)
        while (left < right) {
            int mid = (left + right) / 2;
            
            if (check_v2(mid)) {
                left = mid + 1;
            }
            else {
                right = mid;
            }
        }

        return (right - 1) * (right - 1);
    }
    
    public boolean check_v1(int mid) {
        
        for (int r = 0; r <= R - mid; r++) {
            for (int c = 0; c <= C - mid; c++) {
                
                if (boards[r][c] == 1) {
                    // 한변의 길이가 mid인 정사각형을 만들 수 있는지 확인함
                    Loop1:
                    for (int l = 0; l < mid; l++) {
                        for (int h = 0; h < mid; h++) {
                            // 중간에 0이 있다면 반복문 탈출
                            if (boards[r+l][c+h] == 0) {
                                break Loop1;
                            }
                            
                            // 반복문을 중간에 탈출하지 않고 끝까지 도달하고 끝까지 모두 1인 경우 true를 반환함.
                            if ((l == (mid - 1)) && (h == (mid - 1)) && (boards[r + l][c + h] == 1)) {
                                return true;
                            } 
                        }
                    }
                }
            }
        }
        
        return false;
    }
    
    public boolean check_v2(int mid) {
        
        int result = mid * mid;
        
        for (int r = mid; r < R + 1; r++) {
            for (int c = mid; c < C + 1; c++) {
                // System.out.println("r, c: " + r + ", " + c);
                // System.out.println("sum[r][c]: " + sum[r][c]);
                // System.out.println("sum[r-mid][c]: " + sum[r-mid][c]);
                // System.out.println("sum[r][c-mid]: " + sum[r][c-mid]);
                // System.out.println("sum[r-mid][c-mid]: " + sum[r][c]);
                // System.out.println(sum[r][c] - sum[r-mid][c] - sum[r][c-mid] + sum[r-mid][c-mid]);
                if (sum[r][c] - sum[r-mid][c] - sum[r][c-mid] + sum[r-mid][c-mid] == result) {
                    return true;
                }
            }
        }
        
        return false;
    }
}
*/