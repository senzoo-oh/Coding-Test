import java.io.*;
import java.util.*;

public class Solution_for_10282 {
    static int T;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());

        for (int t = 0; t < T; t++) {

            st = new StringTokenizer(br.readLine());
            
            int N, D, C;

            N = Integer.parseInt(st.nextToken());   // 컴퓨터 개수
            D = Integer.parseInt(st.nextToken());   // 의존성 개수
            C = Integer.parseInt(st.nextToken());   // 해킹당한 컴퓨터 번호

            // LinkedList<LinkedList<Integer>> map = new LinkedList<>();
            
            List<List<Node>> graph = new ArrayList<>();
            for (int i = 0; i < N+1; i++) {
                graph.add(new ArrayList<>());
            }

            for (int d = 0; d < D; d++) {
                st = new StringTokenizer(br.readLine());

                int a, b, s;
                
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                s = Integer.parseInt(st.nextToken());
                
                graph.get(b).add(new Node(a, s));
            }

            int[] dist = new int[N+1];
            Arrays.fill(dist, INF);

            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.offer(new Node(C, 0));
            dist[C] = 0;

            while (!pq.isEmpty()) {
                Node cur = pq.poll();
                int now = cur.to;
                int time = cur.time;

                if (time > dist[now]) continue;

                for (Node next : graph.get(now)) {
                    int newTime = time + next.time;

                    if (newTime < dist[next.to]) {
                        dist[next.to] = newTime;
                        pq.offer(new Node(next.to, newTime));
                    }
                }
            }

            int count = 0;
            int maxTime = 0;
            for (int i = 0; i < N+1; i++) {
                if (dist[i] != INF) {
                    count++;
                    maxTime = Math.max(maxTime, dist[i]);
                }
            }
            System.out.println(count + " " + maxTime);
        }
    }

    static class Node implements Comparable<Node> {
        int to;
        int time;

        Node(int to, int time) {
            this.to = to;
            this.time = time;
        }

        @Override
        public int compareTo(Node n) {
            return this.time - n.time;
        }
    }
}
