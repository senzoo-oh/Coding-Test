import java.io.*;
import java.util.*;
/*
    1. 언제 방문처리를 해야 하는가?
    dist를 갱신할 때 방문처리를 하는 것이 아니라, 큐에서 꺼낼 때 방문처리를 해주어야 한다. dist가 갱신될 가능성은 모든 탐색이 끝날때까지 있기 때문에 방문했다고 해서 dist를 갱신할 수는 없다. 즉, 방문할 기준인 노드를 잡았을 때 방문처리를 해주어야 한다.

    2. 노드에 비해서 간선의 수가 적을 때 어떤 자료구조를 써야하나? 또 어떻게 선언해야 하나?
    static ArrayList<Node>[] list;

    for (int n = 1; n < N+1; n++) {
        list[n] = new ArrayList<>();
    }


*/
public class Solution_for_5972 {
    static int N, M;
    static boolean[] visited;
    static int[] dist;

    static ArrayList<Node>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N+1];
        dist = new int[N+1];
        list = new ArrayList[N+1];

        for (int n = 1; n < N+1; n++) {
            list[n] = new ArrayList<>();
        }

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            list[A].add(new Node(B, C));
            list[B].add(new Node(A, C));
        }

        Arrays.fill(dist, Integer.MAX_VALUE);
        dijkstra();
        System.out.println(dist[N]);
    }

    public static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        dist[1] = 0;
        pq.add(new Node(1, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            
            if (!visited[cur.dest]) {
                visited[cur.dest] = true;

                // 이때 방문처리를 해주어야 함
                for (Node next : list[cur.dest]) {
                    if (next.cost + dist[cur.dest] < dist[next.dest]) {
                        dist[next.dest] = next.cost + dist[cur.dest];
                        pq.add(new Node(next.dest, dist[next.dest]));
                    }
                }
            }
        }
    }

    public static class Node implements Comparable<Node> {
        int dest;
        int cost;

        public Node(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node n) {
            return this.cost - n.cost;
        }
    }
}


