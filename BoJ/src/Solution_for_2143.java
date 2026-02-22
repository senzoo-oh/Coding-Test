import java.util.*;
import java.io.*;

public class Solution_for_2143 {
    static int T;
    static int N, M;

    static int[] A;
    static int[] B;

    static HashMap<Integer, Long> mapA = new HashMap<>();
    static HashMap<Integer, Long> mapB = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = new int[N];
        
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            A[n] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        B = new int[M];
        
        st = new StringTokenizer(br.readLine());
        for (int m = 0; m < M; m++) {
            B[m] = Integer.parseInt(st.nextToken());
        }

        // 누적합 구하기
        for (int i = 0; i < N; i++) {
            int sum = 0;
            for (int j = i; j < N; j++) {
                sum += A[j];
                
                if (mapA.containsKey(sum)) {
                    mapA.replace(sum, mapA.get(sum)+1);
                }
                else mapA.put(sum, 1L);
            }
        }

        // 누적합 구하기
        for (int i = 0; i < M; i++) {
            int sum = 0;
            for (int j = i; j < M; j++) {
                sum += B[j];
                
                if (mapB.containsKey(sum)) {
                    mapB.replace(sum, mapB.get(sum)+1);
                }
                else mapB.put(sum, 1L);
            }
        }

        long answer = 0;
        for (Map.Entry<Integer, Long> entry : mapA.entrySet()) {
            int sumA = entry.getKey();
            long cntA = entry.getValue();

            if (mapB.containsKey(T - sumA)) {
                answer += (cntA * mapB.get(T - sumA));
            }
        }

        System.out.println(answer);
    }
}



코노(1시간) -> 저녁() -> 영화
