import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution_for_1092 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        Integer[] cranes = new Integer[N];

        st = new StringTokenizer(br.readLine());
        int maxCrane = Integer.MIN_VALUE;

        for (int n = 0; n < N; n++) {
            cranes[n] = Integer.parseInt(st.nextToken());
            maxCrane = Math.max(maxCrane, cranes[n]);
        }
        Arrays.sort(cranes, Collections.reverseOrder());     // 객체형만 가능.(Integer[] 가능하지만 int[]는 불가능 함)

        st = new StringTokenizer(br.readLine());
        
        int M = Integer.parseInt(st.nextToken());
        Integer[] boxes = new Integer[M];
        
        st = new StringTokenizer(br.readLine());
        for (int m = 0; m < M; m++) {
            boxes[m] = Integer.parseInt(st.nextToken());

            // 크레인에 실을 수 없는 박스가 있는 경우, -1을 출력하고 종료함
            if (maxCrane < boxes[m]) {
                System.out.println(-1);
                return;
            }
        }
        Arrays.sort(boxes, Collections.reverseOrder());
        

        boolean[] isChecked = new boolean[M];   // 박스를 옮겼는지 여부를 확인함
        
        int count = 0;      // 현재까지 옮긴 박스의 수
        int answer = 0;     // 크레인을 다같이 움직인 횟수
        while (count < M) {
            int idx = 0;

            for (int n = 0; n < N; n++) {
                int curCrane = cranes[n];   // 현재 크레인의 무게제한

                // 현재 크레인에 실을 수 있는 박스 무게의 최대를 구함
                while (idx < M) {
                    
                    // 현재 박스가 실어져 있지 않고, 현재 크레인의 무게제한보다 작거나 같다면, 실을 수 있음
                    if (!isChecked[idx] && curCrane >= boxes[idx]) {
                        isChecked[idx] = true;
                        count++;    // 박스를 하나 옮김
                        break;
                    }

                    idx++;
                }
            }
            answer++;
        }

        System.out.println(answer);
    }
}
