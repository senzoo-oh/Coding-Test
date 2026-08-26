import java.io.*;
import java.util.*;

public class Solution_for_2217 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
    
        int[] weights = new int[N];
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());

            weights[n] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(weights);
        
        int answer = Integer.MIN_VALUE;
        for (int n = 0; n < N; n++) {
            int weight = weights[n];
            int cnt = N - n;

            answer = Math.max(answer, weight * cnt);
        }

        System.out.println(answer);
    }
}
