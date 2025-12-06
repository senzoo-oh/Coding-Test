import java.io.*;
import java.util.*;

public class solution_for_2056 {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        List<List<Integer>> list = new ArrayList<>();
        for (int n = 0; n < N; n++) {
            list.add(new LinkedList<>());
        }

        int[] dp = new int[N+1];
        for (int i = 0; i < N+1; i++) {
            dp[i] = -1;
        }
        
        int[] times = new int[N+1]; // 작업을 수행하는데 걸리는 시간

        for (int n = 1; n < N+1; n++) {
            st = new StringTokenizer(br.readLine());

            int time, jobCnt;

            time = Integer.parseInt(st.nextToken());
            jobCnt = Integer.parseInt(st.nextToken());

            int spentTime = time;
            for (int j = 0; j < jobCnt; j++) {
                int jobNum = Integer.parseInt(st.nextToken());

                spentTime = Math.max(spentTime, dp[jobNum] + time);
            }

            dp[n] = spentTime;
        }

        int answer = 0;
        for (int i = 1; i < N+1; i++) {
            answer = Math.max(answer, dp[i]);
        }
        
        System.out.println(answer);
    }
}
