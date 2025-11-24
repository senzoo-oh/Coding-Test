import java.util.*;
import java.io.*;

public class Solution_for_14719 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int H = Integer.parseInt(st.nextToken());   // 세로 길이
        int W = Integer.parseInt(st.nextToken());   // 가로 길이

        int[] heights = new int[W];
        
        st = new StringTokenizer(br.readLine());
        for (int w = 0; w < W; w++) {
            heights[w] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        // 양 옆에는 빗물이 고일 수 없음.(0, W-1)
        for (int cur = 1; cur < W-1; cur++) {
            // 좌측의 가장 높은 높이를 구함
            int left = 0;
            for (int l = 0; l < cur; l++) {
                left = Math.max(left, heights[l]);
            }

            // 우측의 가장 높은 높이를 구함
            int right = 0;
            for (int r = cur+1; r < W; r++) {
                right = Math.max(right, heights[r]);
            }

            int lowHeight = (left < right) ? left : right;
            
            // 현재 높이가 좌측과 우측의 높이보다 같거나 높은경우
            if (lowHeight <= heights[cur]) continue;

            // 현재 열에서의 고일 수 있는 빗물의 양을 구하여 총량에 더함
            int curAmount = lowHeight - heights[cur];
            sum += curAmount;
        }

        System.out.println(sum);
    }
}
