import java.io.*;
import java.util.*;

public class Solution_for_7682 {
    static char[][] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        while (true) {
            String st = br.readLine();
            if (st.equals("end")) break;

            board = new char[3][3];

            int xCnt = 0;
            int oCnt = 0;
            for (int n = 0; n < 9; n++) {
                int row = n / 3;
                int col = n % 3;

                char c = st.charAt(n);
                board[row][col] = c;

                if (c == 'X') xCnt++;
                else if (c == 'O') oCnt++;
            }
                
            // X가 이길 경우
            if (xCnt == (oCnt+1)) {
                if ((xCnt+oCnt == 9) && !bingo('O')) {
                    sb.append("valid").append('\n');
                }
                else if (!bingo('O') && bingo('X')) {
                    sb.append("valid").append('\n');
                }
                else sb.append("invalid").append('\n');
            }

            // O가 이길 경우
            else if (xCnt == oCnt) {
                if (!bingo('X') && bingo('O')) {
                    sb.append("valid").append('\n');
                }
                else sb.append("invalid").append('\n');
            }
            else sb.append("invalid").append('\n');
            
        }
        System.out.println(sb);
    }

    public static boolean bingo(char c) {
        boolean result = false;

        // 가로
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == c && board[i][1] == c && board[i][2] == c) return true;
        }

        // 세로
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == c && board[1][i] == c && board[2][i] == c) return true;
        }

        // 대각선(왼쪽상단 -> 오른쪽하단)
        if (board[0][0] == c && board[1][1] == c && board[2][2] == c) return true;

        // 대각선(오른쪽상단 -> 왼쪽하단)
        if (board[0][2] == c && board[1][1] == c && board[2][0] == c) return true;

        return result;
    }
}
