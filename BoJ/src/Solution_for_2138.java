import java.io.*;
import java.util.*;

/*
    그리디 조건: 현재의 스위치를 누를지 말지 결정할 수 있는 근거는 이전 스위치의 상태
*/
public class Solution_for_2138 {
    static int N;
    static int[] cur;
    static int[] answer;

    static int[] firstSwitchOn;
    static int[] firstSwitchOff;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String st = br.readLine();
        String now = br.readLine();
        String target = br.readLine();

        N = Integer.parseInt(st);

        if (N == 1) {
            System.out.println(now.charAt(0) == target.charAt(0) ? 0 : 1);
            return;
        }
        
        cur = new int[N];
        answer = new int[N];
        firstSwitchOn = new int[N];
        firstSwitchOff = new int[N];

        // 전구들의 현재 상태
        for (int n = 0; n < N; n++) {
            cur[n] = now.charAt(n) - '0';
            firstSwitchOn[n] = now.charAt(n) - '0';
            firstSwitchOff[n] = now.charAt(n) - '0';
        }
        
        // 만들고자 하는 전구들의 상태
        for (int n = 0; n < N; n++) {
            answer[n] = target.charAt(n) - '0';
        }
        
        // 첫번째 전구를 눌렀을 경우 전구상태 세팅
        firstSwitchOn[0] = firstSwitchOn[0] == 1 ? 0 : 1;
        firstSwitchOn[1] = firstSwitchOn[1] == 1 ? 0 : 1;

        // 첫번째 전구를 누르지 않았을 경우 전구상태 세팅(변화없음)

        
        // 두번째 전구부터 앞에 있는 전구의 상태에 따라 누를지 안 누를지 결정
        int firstSwitchOnCnt = 1;
        int firstSwitchOffCnt = 0;
        
        for (int i = 1; i < N; i++) {
            // 첫번째 전구를 누른 경우
            if (firstSwitchOn[i-1] != answer[i-1]) {
                firstSwitchOn[i-1] = firstSwitchOn[i-1] == 1 ? 0 : 1;
                firstSwitchOn[i] = firstSwitchOn[i] == 1 ? 0 : 1;

                // 맨 마지막 스위치가 아니라면 다음 스위치도 상태를 바꿈
                if (i+1 < N) {
                    firstSwitchOn[i+1] = firstSwitchOn[i+1] == 1 ? 0 : 1;
                }
                firstSwitchOnCnt++;
            }

            // 첫번째 전구를 누르지 않은 경우
            if (firstSwitchOff[i-1] != answer[i-1]) {
                firstSwitchOff[i-1] = firstSwitchOff[i-1] == 1 ? 0 : 1;
                firstSwitchOff[i] = firstSwitchOff[i] == 1 ? 0 : 1;

                // 맨 마지막 스위치가 아니라면 다음 스위치도 상태를 바꿈
                if (i+1 < N) {
                    firstSwitchOff[i+1] = firstSwitchOff[i+1] == 1 ? 0 : 1;
                }
                firstSwitchOffCnt++;
            }
        }

        // 만들 수 없는 경우 스위치를 누른 횟수를 최댓값으로 설정함
        if (firstSwitchOn[N-1] != answer[N-1]) {
            firstSwitchOnCnt = Integer.MAX_VALUE;
        }
        if (firstSwitchOff[N-1] != answer[N-1]) {
            firstSwitchOffCnt = Integer.MAX_VALUE;
        }

        // 둘중에 작은 수를 구함.
        // 작은 수가 Integer.MAX_VALUE와 동일하다면 "불가능한 경우" 이므로 -1을 출력함
        int res = Math.min(firstSwitchOnCnt, firstSwitchOffCnt);
        System.out.println(res == Integer.MAX_VALUE ? -1 : res);
    }
}
