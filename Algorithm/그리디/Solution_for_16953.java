import java.io.*;
import java.util.*;

public class Solution_for_16953 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        boolean[] visited = new boolean[B+1];

        Queue<Num> queue = new LinkedList<>();

        queue.add(new Num(A, 0));
        visited[A] = true;

        while (!queue.isEmpty()) {
            Num cur = queue.poll();
            
            if (cur.num == B) {
                System.out.println(cur.cnt + 1);
                return;
            }

            long num1 = cur.num * 2;
            long num2 = cur.num * 10 + 1;
            
            if (num1 <= B && !visited[(int)num1]) {
                queue.add(new Num(num1, cur.cnt+1));
                visited[(int)num1] = true;
            }
            if (num2 <= B &&!visited[(int)num2]) {
                queue.add(new Num(num2, cur.cnt+1));
                visited[(int)num2] = true;
            }
        }

        System.out.println(-1);
    }

    public static class Num {
        long num;
        int cnt;

        public Num (long num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
}
