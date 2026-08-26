import java.io.*;
import java.util.*;

public class Solution_for_11399 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[] people = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            people[n] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(people);

        int cnt = N;
        int sum = 0;
        for (int n = 0; n < N; n++) {
            int time = people[n];
            
            sum += (time * cnt--);
        }

        System.out.println(sum);
    }
}
