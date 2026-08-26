import java.io.*;
import java.util.*;

public class Solution_for_17615 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        String s = br.readLine();
        
        int redCnt = 0;
        int blueCnt = 0;
        for (int n = 0; n < N; n++) {
            char color = s.charAt(n);

            if (color == 'B') blueCnt++;
            else redCnt++;
        }
        
        int answer = Integer.MAX_VALUE;
        // 파란공을 왼쪽으로 옮기는 경우, 왼쪽에서 빨간공이 나온 이후, 파란공의 개수를 구함
        int cnt1 = 0;
        for (int n = 0; n < N; n++) {
            
        }

        int cnt = 0;
        for (int n = 0; n < N; n++) {
            char color = s.charAt(n);

            if (color == 'B') cnt++;
            else break;
        }

        answer = Math.min(answer, blueCnt - cnt);
        
        // 파란공을 오른쪽으로 옮기는 경우
        cnt = 0;
        for (int n = N-1; -1 < n; n--) {
            char color = s.charAt(n);

            if (color == 'B') cnt++;
            else break;
        }

        answer = Math.min(answer, blueCnt - cnt);

        // 빨간공을 왼쪽으로 옮기는 경우
        cnt = 0;
        for (int n = 0; n < N; n++) {
            char color = s.charAt(n);

            if (color == 'R') cnt++;
            else break;
        }

        answer = Math.min(answer, redCnt - cnt);

        // 빨간공을 오른쪽으로 옮기는 경우
        cnt = 0;
        for (int n = N-1; -1 < n; n--) {
            char color = s.charAt(n);

            if (color == 'R') cnt++;
            else break;
        }

        answer = Math.min(answer, redCnt - cnt);

        System.out.println(answer);
    }
}
