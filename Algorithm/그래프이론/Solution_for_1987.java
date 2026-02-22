import java.io.*;
import java.util.*;

public class Solution_for_1987 {
    static int R, C;
    static char[][] board;

    static int answer = 0;
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static HashSet<Character> set = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new char[R][C];
        
        for (int r = 0; r < R; r++) {
            String s = br.readLine();

            for (int c = 0; c < C; c++) {
                board[r][c] = s.charAt(c);
            }
        }
        
        set.add(board[0][0]);

        DFS(0, 0);

        System.out.println(answer);
    }

    public static void DFS(int r, int c) {

        answer = Math.max(answer, set.size());

        for (int d = 0; d < 4; d++) {
            int nextRow = r + dirs[d][0];
            int nextCol = c + dirs[d][1];

            if (nextRow < 0 || nextCol < 0 || R <= nextRow || C <= nextCol) continue;
            
            char next = board[nextRow][nextCol];
            
            // 이미 지나온 알파벳인 경우
            if (set.contains(next)) continue;
            
            set.add(next);
            DFS(nextRow, nextCol);
            set.remove(next);
        }
    }
}
