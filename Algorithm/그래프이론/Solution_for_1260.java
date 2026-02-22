import java.io.*;
import java.util.*;

public class Solution_for_1260 {
    static int N, M, V;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean[] visited;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        for (int n = 0; n < N+1; n++) {
            graph.add(new ArrayList<>());
        }

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            graph.get(start).add(end);
            graph.get(end).add(start);
        }

        // 노드를 오름차순으로 정렬함
        for (int n = 0; n < N+1; n++) {
            Collections.sort(graph.get(n));
        }

        sb = new StringBuilder();
        visited = new boolean[N+1];
        dfs(V);
        System.out.println(sb.toString());

        sb = new StringBuilder();
        visited = new boolean[N+1];
        bfs(V);
        System.out.println(sb.toString());
    }

    public static void dfs(int v) {
        if (!visited[v]) {
            sb.append(v).append(" ");
            visited[v] = true;
        }

        for (Integer next : graph.get(v)) {
            if (visited[next]) continue;
            dfs(next);
        }
    }

    public static void bfs(int v) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(v);
        visited[v] = true;

        while (!queue.isEmpty()) {
            Integer curNode = queue.poll();
            sb.append(curNode).append(" ");
            
            for (Integer next : graph.get(curNode)) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }
    }
}
