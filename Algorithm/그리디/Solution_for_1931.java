import java.io.*;
import java.util.*;

public class Solution_for_1931 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        ArrayList<Meet> meetings = new ArrayList<>();

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());

            int time1 = Integer.parseInt(st.nextToken());
            int time2 = Integer.parseInt(st.nextToken());

            meetings.add(new Meet(time1, time2));
        }

        /*
        조건: 종료시간을 기준으로 오름차순, 종료시간이 동일하다면 시작시간을 기준으로 오름차순 정렬
        Comparator의 반환값 규칙
        */
        meetings.sort((a, b) -> {
            if (a.endTime != b.endTime) {
                return a.endTime - b.endTime;
            }
            else {
                return a.startTime - b.startTime;
            }
        });

        int meetingCnt = 0;
        int currentTime = 0;
    
        for (Meet m : meetings) {
            // 회의를 시작할 수 있을 때 선택
            if (currentTime <= m.startTime) {
                meetingCnt++;
                currentTime = m.endTime;
            }
            else continue;
        }

        System.out.println(meetingCnt);
    }
    
    static class Meet {
        int startTime;
        int endTime;

        public Meet(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }
}