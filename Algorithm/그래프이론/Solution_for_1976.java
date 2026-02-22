import java.io.*;
import java.util.*;

public class Solution_for_1976 {
    static int N, M;
    static int[][] maps;

    static int[] dest;
    static int[] cities;

    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        cities = new int[N];
        Arrays.fill(cities, -1);

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        maps = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
                maps[j][i] = maps[i][j];
            }
        }

        st = new StringTokenizer(br.readLine());
        dest = new int[M];
        for (int m = 0; m < M; m++) {
            dest[m] = Integer.parseInt(st.nextToken()) - 1;
        }

        for (int i = 0; i < N; i++) {
            // 방문한 적 없는 도시라면
            if (cities[i] == -1) {
                cnt++;
                cities[i] = cnt;
                dfs(i);
            }
        }

        int route = cities[dest[0]];
        
        for (int m = 1; m < M; m++) {
            if (route != cities[dest[m]]) {
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");
    }

    public static void dfs(int start) {
        for (int next = 0; next < N; next++) {
            
            // 연결된 도시이고 방문하지 않은 도시라면
            if (maps[start][next] == 1 && cities[next] == -1) {
                cities[next] = cnt;
                dfs(next);
            }
        }
    }
}
