import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_for_1049 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] packs = new int[M];
        int[] ones = new int[M];

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());

            int pack = Integer.parseInt(st.nextToken());
            int one = Integer.parseInt(st.nextToken());

            packs[m] = pack;
            ones[m] = one;
        }

        Arrays.sort(packs);
        Arrays.sort(ones);

        int answer = 0;

        /*
        // 패키지 1개의 가격이 낱개 6개의 가격보다 저렴한 경우, 기타줄 N개를 패키지 가격으로 구매한다.
        if (packs[0] < (ones[0] * 6)) {
            answer += ((N / 6) * packs[0]);
            N -= (N/6) * 6;
        }

        // 패키지 묶음으로 구매하지 못한 N개의 기타줄에 대해서 패키지 1개 가격보다 낱개로 구매하는 가격이 저렴하다면, 낱개로 구매한다.
        if (packs[0] > (N * ones[0])) {
            answer += (N * ones[0]);
            N = 0;
        }
        else {
            answer += packs[0];
            N = 0;
        }

        System.out.println(answer);

        */

        // 낱개로만 구매하는 경우
        int price1 = N * ones[0];

        // 패키지로만 구매하는 경우
        int price2 = 0;
        if (N % 6 == 0) {
            price2 = (N / 6) * packs[0];
        }
        else {
            price2 = ((N / 6) + 1) * packs[0];
        }

        // 패키지 + 낱개로 구매하는 경우
        int price3 = 0;
        if (N % 6 == 0) {
            price3 = (N / 6) * packs[0];
        }
        else {
            price3 = (N / 6) * packs[0];
            
            N -= ((N / 6) * 6);
            
            price3 += N * ones[0];
        }

        System.out.println(Math.min(price1, Math.min(price2, price3)));
        
    }
}
