import java.io.*;
import java.util.*;

public class Solution_for_1260{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int numOf5kg = N / 5;

        for (int i = numOf5kg; 0 <= i; i--) {
            // 남은 설탕의 무게를 구함
            int weight = (N - 5 * i);

            // 남은 설탕의 무게가 3으로 나누어 떨어진다면 i와 남은 설탕의 무게를 3으로 나눈 몫을 더한 결과를 출력하고 종료
            if (weight % 3 == 0) {
                int numOf3kg = weight / 3;
                System.out.println(i + numOf3kg);
                return;
            }
        }

        System.out.println(-1);
    }
}