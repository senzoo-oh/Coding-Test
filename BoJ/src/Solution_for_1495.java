import java.io.*;
import java.util.*;

public class Solution_for_1495 {
    static int N, S, M;
    static int[] volumes;
    static int[] check;

    static int answer = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        volumes = new int[N+1];
        check = new int[M+1];
        Arrays.fill(check, -1);

        st = new StringTokenizer(br.readLine());
        for (int n = 1; n < N+1; n++) {
            volumes[n] = Integer.parseInt(st.nextToken());
        }

        check[S] = 0;
        
        for (int n = 1; n < N+1; n++) {
            List<Integer> change = new ArrayList<>();

            // 가능한 볼륨의 크기가 0~M이기 때문
            for (int m = 0; m < M+1; m++) {
                if (check[m] == n-1) {
                    int plus = m + volumes[n];
                    int minus = m - volumes[n];

                    if (plus <= M) change.add(plus);
                    if (0 <= minus) change.add(minus);
                }
            }

            for (int i = 0; i < change.size(); i++) {
                check[change.get(i)] = n;

                if (n == N) {
                    answer = Math.max(answer, change.get(i));
                }
            }
        }

        System.out.println(answer);
    }
}
