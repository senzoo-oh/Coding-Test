import java.io.*;
import java.util.*;

public class Solution_for_1697 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Queue<Move> queue = new LinkedList<>();
        boolean[] visited = new boolean[100_001];

        visited[N] = true;
        queue.add(new Move(N, 0));

        while (!queue.isEmpty()) {
            Move cur = queue.poll();

            if (cur.loc == K) {
                System.out.println(cur.time);
                return;
            }
            else {
                // X-1로 이동
                if (0 <= (cur.loc - 1) && !visited[cur.loc - 1]) {
                    visited[cur.loc-1] = true;
                    queue.add(new Move(cur.loc-1, cur.time+1));
                }
                
                // X+1로 이동
                if ((cur.loc+1) < 100001 && !visited[cur.loc+1]) {
                    visited[cur.loc+1] = true;
                    queue.add(new Move(cur.loc+1, cur.time+1));
                }

                // 2*X로 이동
                if ((cur.loc*2) < 100001 && !visited[cur.loc*2]) {
                    visited[cur.loc*2] = true;
                    queue.add(new Move(cur.loc*2, cur.time+1));
                } 
            }
        }
    }

    public static class Move {
        int loc;
        int time;

        public Move(int loc, int time) {
            this.loc = loc;
            this.time = time;
        }
    }
}
