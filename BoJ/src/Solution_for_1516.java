import java.io.*;
import java.util.*;

public class Solution_for_1516 {
    static int N;
    static ArrayList<ArrayList<Integer>> buildings = new ArrayList<>();

    static int[] indegree;
    static int[] times;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        for (int n = 0; n < N+1; n++) {
            buildings.add(new ArrayList<Integer>());
        }

        indegree = new int[N+1];
        times = new int[N+1];

        for (int n = 1; n < N+1; n++) {
            st = new StringTokenizer(br.readLine());
            
            times[n] = Integer.parseInt(st.nextToken());

            while (st.hasMoreTokens()) {
                int pre = Integer.parseInt(st.nextToken());
                
                if (pre == -1) break;

                indegree[n]++;
                buildings.get(pre).add(n);
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        int[] result = new int[N+1];

        // queue에는 지을 수 있는 건물만 들어감
        for (int n = 1; n < N+1; n++) {
            if (indegree[n] == 0) {
                queue.add(n);
                result[n] = times[n];
            }
        }

        while (!queue.isEmpty()) {
            int idx = queue.poll();

            for (Integer i : buildings.get(idx)) {
                result[i] = Math.max(result[i], result[idx] + times[i]);
                
                // 먼저 지어야 할 건물을 다 지은 경우
                if (--indegree[i] == 0) queue.offer(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int n = 1; n < N+1; n++) {
            sb.append(result[n]).append('\n');
        }

        System.out.println(sb);
    }
}
