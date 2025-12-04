import java.io.*;
import java.util.*;

public class Solution_for_16987 {
    static int N;
    static int[][] infos;

    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        infos = new int[N][2];

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());

            infos[n][0] = Integer.parseInt(st.nextToken());
            infos[n][1] = Integer.parseInt(st.nextToken());
        }

        breakEggs(0, 0);

        System.out.println(answer);
    }

    public static void breakEggs(int cur, int cnt) {
        // 최근에 든 계란이 가장 오른쪽에 위치한 계란일 경우 종료
        if (cnt == N-1 || cur == N) {
            answer = Math.max(answer, cnt);
            return;
        }

        if (infos[cur][0] <= 0) {
            breakEggs(cur+1, cnt);
        }
        else {
            for (int next = 0; next < N; next++) {
                if (next == cur) continue;

                // 깨트릴 수 있는 다음 계란인 경우
                if (0 < infos[next][0]) {
                    infos[next][0] -= infos[cur][1];
                    infos[cur][0] -= infos[next][1];

                    int breakEggCnt = 0;
                    
                    if (infos[cur][0] <= 0) breakEggCnt++;
                    if (infos[next][0] <= 0) breakEggCnt++;

                    breakEggs(cur+1, cnt + breakEggCnt);

                    infos[next][0] += infos[cur][1];
                    infos[cur][0] += infos[next][1];
                }
            }
        }
    }
}
