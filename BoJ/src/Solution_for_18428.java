import java.io.*;
import java.util.*;

public class Solution_for_18428 {
    static int N;
    static char[][] map;
    static List<Teacher> teachers = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        map = new char[N][N];

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());

            for (int m = 0; m < N; m++) {
                char c = st.nextToken().charAt(0);

                // 탐색을 시작할 선생님의 위치
                if (c == 'T') {
                    teachers.add(new Teacher(n, m));
                }

                map[n][m] = c;
            }
        }

        comb(0, 0);
        System.out.println("NO");
    }
    
    public static void comb(int idx, int cnt) {
        if (cnt == 3) {
            if (hidePossible()) {
                System.out.println("YES");
                System.exit(0);
            }
            return;
        }

        if (idx == N * N) return;

        int r = idx / N;
        int c = idx % N;

        if (map[r][c] == 'X') {
            map[r][c] = 'O';

            comb(idx + 1, cnt + 1);

            map[r][c] = 'X';
        }

        comb(idx + 1, cnt);
    }

    public static boolean hidePossible() {

        for (Teacher t : teachers) {
            int startRow = t.row;
            int startCol = t.col;

            // O가 나오거나 배열의 끝 부분에 도달하면 종료
            // 상
            for (int i = startRow; 0 <= i; i--) {
                if (map[i][startCol] == 'O') break;
                if (map[i][startCol] == 'S') return false;
            }
            // 하
            for (int i = startRow; i < N; i++) {
                if (map[i][startCol] == 'O') break;
                if (map[i][startCol] == 'S') return false;
            }
            // 좌
            for (int j = startCol; 0 <= j; j--) {
                if (map[startRow][j] == 'O') break;
                if (map[startRow][j] == 'S') return false;
            }
            // 우
            for (int j = startCol; j < N; j++) {
                if (map[startRow][j] == 'O') break;
                if (map[startRow][j] == 'S') return false;
            }
        }

        return true;
    }

    public static class Teacher {
        int row;
        int col;

        Teacher(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
