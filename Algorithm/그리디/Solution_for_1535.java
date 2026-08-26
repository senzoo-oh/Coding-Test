import java.io.*;
import java.util.*;

public class Solution_for_1535 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[] cost = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            cost[n] = Integer.parseInt(st.nextToken());
        }

        int[] happy = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            happy[n] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[100];

        for (int n = 0; n < N; n++) {

            for (int i = 99; cost[n] <= i; i--) {
                dp[i] = Math.max(dp[i], happy[n] + dp[i - cost[n]]);
            }
        }

        for (int n = 0; n < 100; n++) {
            System.out.print(dp[n] + " ");
        }


        System.out.println(dp[99]);
    }
}

class Fail_Solution_for_1535 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[] cost = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            cost[n] = Integer.parseInt(st.nextToken());
        }

        int[] happy = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            happy[n] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Person> people = new ArrayList<>();
        for (int n = 0; n < N; n++) {
            people.add(new Person(cost[n], happy[n]));
        }

        people.sort((a, b) -> {
            return a.cost - b.cost;
        });

        int[] dp = new int[100];

        for (int n = 0; n < N; n++) {
            Person cur = people.get(n);

            for (int i = 0; i < 100; i++) {
                if (cur.cost < 100) {
                    if (cur.cost < i) {
                        dp[i] = Math.max(dp[i], cur.happy + dp[i - cur.cost]);
                    }
                }
                else break;
            }
        }

        // for (int n = 0; n < N; n++) {
        //     System.out.print(people.get(n).cost + " " + people.get(n).happy);
        //     System.out.println();
        // }

        for (int i = 0; i < 100; i++) {
            System.out.print(dp[i] + " ");
        }

        System.out.println(dp[99]);
    }
}

class Person {
    int cost;
    int happy;

    public Person (int cost, int happy) {
        this.cost = cost;
        this.happy = happy;
    }
}