import java.io.*;
import java.util.*;

public class Solution_for_1766 {
    static int N, M;
    static int[] indegrees;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        // 문제의 수만큼 indegree의 수를 저장할 1차원 배열을 만듦
        indegrees = new int[N+1];

        // 먼저 풀어야 하는 문제 -> 나중에 풀어야 하는 문제를 기록하기 위한 ArrayList
        ArrayList<ArrayList<Integer>> a = new ArrayList<>();

        for (int n = 0; n < N+1; n++) {
            a.add(new ArrayList<>());
        }

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            // 문제를 연결해줌
            a.get(start).add(end);

            // 문제의 indegree를 증가시킴
            indegrees[end]++;
        }

        // 푸는 문제들이 담길 우선순위 큐
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 1; i < N+1; i++) {
            if (indegrees[i] == 0) {
                pq.add(i);
            }
        }

        while (!pq.isEmpty()) {
            Integer cur = pq.poll();

            sb.append(cur).append(" ");

            for (int next : a.get(cur)) {
                
                indegrees[next]--;
                if (indegrees[next] == 0) {
                    pq.add(next);
                }
            }
        }

        System.out.println(sb);
    }
}
