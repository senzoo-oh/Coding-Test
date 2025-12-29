import java.io.*;
import java.util.*;

public class Solution_for_10159 {
    static int N, M;
    static int[][] compare;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        compare = new int[N+1][N+1];    // 편의를 위해 인덱스 0을 제외하고 +1을 해줌
        
        for (int m = 0; m < M; m++) {

            st = new StringTokenizer(br.readLine());

            int obj1 = Integer.parseInt(st.nextToken());
            int obj2 = Integer.parseInt(st.nextToken());
            
            // 무게 비교이기 때문에 obj1 > obj2 인 경우, 1로 표현함
            //                 obj2 > obj1 인 경우, -1로 표현함
            compare[obj1][obj2] = 1;
            compare[obj2][obj1] = -1;
        }

        for (int k = 1; k < N+1; k++) {
            for (int i = 1; i < N+1; i++) {
                for (int j = 1; j < N+1; j++) {
                    if (compare[i][k] != 0 && compare[i][k] == compare[k][j]) {
                        compare[i][j] = compare[i][k];
                    }
                }
            }
        }

        for (int i = 1; i < N+1; i++) {
            int answer = 0;
            for (int j = 1; j < N+1; j++) {
                if (i == j) continue;   // 자기자신인 경우, 제외
                
                if (compare[i][j] == 0) answer++;
            }

            System.out.println(answer);
        }
    }
}
