import java.util.*;
import java.io.*;

public class Solution_for_2632 {
    static int pizzaSize;
    static int M, N;

    static int[] sizeA;
    static int[] sizeB;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        pizzaSize = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        
        M = Integer.parseInt(st.nextToken());
        sizeA = new int[M];
        
        N = Integer.parseInt(st.nextToken());
        sizeB = new int[N];
        
        int totalA = 0;
        for (int m = 0; m < M; m++) {
            int size = Integer.parseInt(br.readLine());
            sizeA[m] = size;
            totalA += size;
        }

        int totalB = 0;
        for (int n = 0; n < N; n++) {
            int size = Integer.parseInt(br.readLine());
            sizeB[n] = size;
            totalB += size;
        }

        // 피자A로만 판매하는 경우의 수
        int[] sumA = new int[1_000_001];
        // 1개부터 M-1개
        for (int i = 0; i < M; i++) {
            int sum = 0;
            for (int j = i; j < i+M-1; j++) {
                sum += sizeA[j%M];
                sumA[sum]++;
            }
        }
        sumA[totalA]++;

        // 피자B로만 판매하는 경우의 수
        int[] sumB = new int[1_000_001];
        for (int i = 0; i < N; i++) {
            int sum = 0;
            for (int j = i; j < i+N-1; j++) {
                sum += sizeB[j%N];
                sumB[sum]++;
            }
        }
        sumB[totalB]++;

        // 피자A와 피자B를 혼합해서 판매하는 경우의 수
        int answer = 0;
        answer = sumA[pizzaSize];
        answer += sumB[pizzaSize];

        for (int i = 1; i < pizzaSize; i++) {
            answer += (sumA[i] * sumB[pizzaSize-i]);
        }

        System.out.println(answer);
    }
}
