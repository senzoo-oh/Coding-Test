import java.io.*;
import java.util.*;

/*
    처음에는 앞의 두 물통은 비어 있고, 세 번째 물통은 가득 차 있다.
    어떤 물통에 들어있는 물을 다른 물통으로 쏟아 부을 수 있는데, 이때에는 한 물통이 비거나, 다른 한 물통이 가득 찰 때까지 물을 부을 수 있다.

    첫 번째 물통이 비어 있을 때, 세 번째 물통에 담겨있을 수 있는 물의 양을 모두 구하기
*/
public class Solution_for_2251 {
    static boolean[][][] checked = new boolean[201][201][201];
    static Queue<Status> queue = new LinkedList<>();
    static LinkedList<Integer> answer = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int maxA = Integer.parseInt(st.nextToken());
        int maxB = Integer.parseInt(st.nextToken());
        int maxC = Integer.parseInt(st.nextToken());

        queue.add(new Status(0, 0, maxC));

        while (!queue.isEmpty()) {
            Status cur = queue.poll();
            
            if (checked[cur.a][cur.b][cur.c]) continue;

            checked[cur.a][cur.b][cur.c] = true;
            
            if (cur.a == 0) {
                answer.add(cur.c);
            }

            // A -> B
            if (maxB < cur.a + cur.b) {
                queue.add(new Status(cur.a + cur.b - maxB, maxB, cur.c));
            }
            else {
                queue.add(new Status(0, cur.a + cur.b, cur.c));
            }

            // A -> C
            if (maxC < cur.a + cur.c) {
                queue.add(new Status(cur.a + cur.c - maxC, cur.b, maxC));
            }
            else {
                queue.add(new Status(0, cur.b, cur.a + cur.c));
            }

            // B -> A
            if (maxA < cur.b + cur.a) {
                queue.add(new Status(maxA, cur.b + cur.a - maxA, cur.c));
            }
            else {
                queue.add(new Status(cur.b + cur.a, 0, cur.c));
            }
            
            // B -> C
            if (maxC < cur.b + cur.c) {
                queue.add(new Status(cur.a, cur.b + cur.c - maxC, maxC));
            }
            else {
                queue.add(new Status(cur.a, 0, cur.b + cur.c));
            }

            // C -> A
            if (maxA < cur.c + cur.a) {
                queue.add(new Status(maxA, cur.b, cur.c + cur.a - maxA));
            }
            else {
                queue.add(new Status(cur.c + cur.a , cur.b, 0));
            }
            
            // C -> B
            if (maxB < cur.c + cur.b) {
                queue.add(new Status(cur.a, maxB, cur.c + cur.b - maxB));
            }
            else {
                queue.add(new Status(cur.a, cur.c + cur.b, 0));
            }
        }

        Collections.sort(answer);
        
        for (int i = 0; i < answer.size(); i++) {
            System.out.print(answer.get(i) + " ");
        }
    }

    static class Status {
        int a;
        int b;
        int c;

        public Status(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
}
