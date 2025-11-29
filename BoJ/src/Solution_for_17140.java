import java.io.*;
import java.util.*;

public class Solution_for_17140 {
    static int r, c, k;
    static int time = 0;
    static int numRow = 3, numCol = 3;

    public static int[][] nums = new int[100][100];

    static class Number implements Comparable<Number> {
        int num;
        int cnt;

        Number(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Number n) {
            if (this.cnt != n.cnt) {
                return this.cnt - n.cnt;
            }
            else 
                return this.num - n.num;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken()) - 1;
        c = Integer.parseInt(st.nextToken()) - 1;
        k = Integer.parseInt(st.nextToken());

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 3; j++) {
                nums[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (time <= 100) {

            if (nums[r][c] == k) {
                break;
            }

            // 열의 개수 <= 행의 개수 -> R연산
            if (numCol <= numRow) {
                sortR();
            }

            // 행의 개수 < 열의 개수 -> C연산
            else {
                sortC();
            }

            time++;
        }

        System.out.println(100 < time ? "-1" : time);
    }

    // R 연산
    public static void sortR() {
        int newNumCol = 0;
        for (int i = 0; i < numRow; i++) {
            
            int[] count = new int[101];
            for (int j = 0; j < numCol; j++) {
                int num = nums[i][j];
                if (num != 0) count[num]++;
            }

            List<Number> list = new ArrayList<>();
            for (int v = 1; v < 101; v++) {
                if (0 < count[v]) list.add(new Number(v, count[v]));
            }

            Collections.sort(list);

            int idx = 0;
            for (Number n : list) {
                if (100 <= idx) break;
                nums[i][idx++] = n.num;
                if (100 <= idx) break;
                nums[i][idx++] = n.cnt;
            }
            newNumCol = Math.max(newNumCol, idx);

            for (int j = idx; j < 100; j++) {
                nums[i][j] = 0;
            }
        }
        numCol = newNumCol;
    }

    // C 연산
    public static void sortC() {
        int newNumRow = 0;
        for (int j = 0; j < numCol; j++) {
            int[] count = new int[101];
            for (int i = 0; i < numRow; i++) {
                int num = nums[i][j];
                if (num != 0) count[num]++;
            }

            List<Number> list = new ArrayList<>();
            for (int v = 1; v < 101; v++) {
                if (0 < count[v]) {
                    list.add(new Number(v, count[v]));
                }
            }

            Collections.sort(list);

            int idx = 0;
            for (Number n : list) {
                if (100 <= idx) break;
                nums[idx++][j] = n.num;
                if (100 <= idx) break;
                nums[idx++][j] = n.cnt;
            }
            newNumRow = Math.max(newNumRow, idx);

            for (int i = idx; i < 100; i++) {
                nums[i][j] = 0;
            }
        }
        numRow = newNumRow;
    }

    // R 연산: 행 정렬
    // public static void R() {
    //     for (int row = 0; row < 100; row++) {

    //         // 하나의 행에 들어 있는 수의 종류와 개수를 구함
    //         for (int col = 0; col < 100; col++) {
    //             if (nums[row][col] == 0) break;

    //             // 이미 나온 숫자라면 cnt를 늘림
    //             int key = nums[row][col];
                
    //             if (map.containsKey(key)) {
    //                 map.put(key, map.get(key)+1);
    //             }
    //             else map.put(key, 1);
    //         }

    //         // 정렬함
    //         if (!map.isEmpty()) {
    //             for (Map.Entry<Integer,Integer> entry : map.entrySet()) {
    //                 pq.add(new Number(entry.getKey(), entry.getValue()));
    //             }
    //         }

    //         // 배열을 다시 채움
    //         for (int col = 0; col < 100; col++) {
                
    //         }
    //     }
    // }
}