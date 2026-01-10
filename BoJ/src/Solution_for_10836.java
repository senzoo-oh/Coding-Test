import java.io.*;
import java.util.*;

public class Solution_for_10836 {
    static int M, N;
    static int[][] maps;

    static int growCnt;
    static int[] growSize;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        maps = new int[M][M];
        for (int m = 0; m < M; m++) {
            Arrays.fill(maps[m], 1);
        }

        growCnt = (2 * M) - 1;
        growSize = new int[growCnt];

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());

            int zero = Integer.parseInt(st.nextToken());
            int one = Integer.parseInt(st.nextToken());
            int two = Integer.parseInt(st.nextToken());
            
            int index = 0;
            for (int i = 0; i < zero; i++) {
                growSize[index] = 0;
                index++;
            }

            for (int i = 0; i < one; i++) {
                growSize[index] = 1;
                index++;
            }

            for (int i = 0; i < two; i++) {
                growSize[index] = 2;
                index++;
            }

            // 왼쪽 열 애벌레들이 커짐
            int s = 0;
            for (int i = M-1; 0 <= i; i--) {
                maps[i][0] += growSize[s++];
            }

            // 첫번째 행 애벌레들이 커짐
            for (int j = 1; j < M; j++) {
                maps[0][j] += growSize[s++];
            }
        }

        // (1,1) ~ (M, M) 애벌레들이 커짐
        for (int i = 1; i < M; i++) {
            for (int j = 1; j < M; j++) {

                // int L = maps[i][j-1];
                // int D = maps[i-1][j-1];
                // int U = maps[i-1][j];

                // int maxSize = Math.max(L, Math.max(D, U));
                
                maps[i][j] = maps[i-1][j];
            }
        }

        for (int j = 1; j < M; j++) {
            for (int i = 1; i < M; i++) {

                int L = maps[i][j-1];
                int D = maps[i-1][j-1];
                int U = maps[i-1][j];

                int maxSize = Math.max(L, Math.max(D, U));

                maps[i][j] = maxSize;
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(maps[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main2(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        maps = new int[M][M];
        for (int m = 0; m < M; m++) {
            Arrays.fill(maps[m], 1);
        }

        for (int i = 0; i < M; i++) {
            System.out.println();
            for (int j = 0; j < M; j++) {
                System.out.print(maps[i][j] + " ");
            }
        }

        growCnt = (2 * M) - 1;
        growSize = new int[growCnt];

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());

            for (int c = 0; c < growCnt; c++) {
                growSize[c] = Integer.parseInt(st.nextToken());
            }

            // 현재 애벌레들의 크기를 복사함
            int[][] tomorrow = new int[M][M];
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < M; j++) {
                    tomorrow[i][j] = maps[i][j];
                }
            }

            // 왼쪽 열 애벌레들이 커짐
            int s = 0;
            for (int i = M-1; 0 <= i; i--) {
                tomorrow[i][0] += growSize[s++];
            }

            // 첫번째 행 애벌레들이 커짐
            for (int j = 1; j < M; j++) {
                tomorrow[0][j] += growSize[s++];
            }
 
            // (1,1) ~ (M, M) 애벌레들이 커짐
            for (int i = 1; i < M; i++) {
                for (int j = 1; j < M; j++) {

                    int L = maps[i][j-1];
                    int D = maps[i-1][j-1];
                    int U = maps[i-1][j];

                    int maxSize = Math.max(L, Math.max(D, U));
                    
                    tomorrow[i][j] += maxSize;
                }
            }

            // 현재 애벌레들의 크기를 복사함
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < M; j++) {
                    maps[i][j] = tomorrow[i][j];
                }
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(maps[i][j] + " ");
            }
            System.out.println();
        }
    }
}
