import java.util.*;

class Solution {
    public int[] solution(int[] sequence, int k) {
        
        
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> {
            // 배열의 길이가 짧은게 먼저
            if (a[0] != b[0]) return a[0] - b[0];
            // 시작 인덱스가 작은 것이 먼저
            else {
                return a[1] - b[1];
            }
        });
        
        long sum = sequence[0];
        
        int left = 0;
        int right = 0;
        
        // 슬라이딩 윈도우를 통해서 합을 구한다.
        
        while (left < sequence.length) {
            // 현재의 합이 k와 동일하다면, Priority Queue에 1차원 배열을 담는다.
            if (sum == k) {
                queue.add(new int[]{right - left + 1, left, right});
            }
        
            // 현재 인덱스의 배열의 합이 k보다 작거나 같다면 인덱스를 1 늘리고, 늘어난 인덱스의 값을 합에 더해준다.
            if (sum <= k) {
                
                // 만약, right이 sequence 배열의 끝에 도달한 경우,
                if (right == sequence.length - 1) break;
                
                right++;
                
                sum += sequence[right];
            }
            // 현재 인덱스의 배열의 합이 k보다 크다면 인덱스를 1 줄이고, 줄어든 인덱스의 값을 합에서 제거한다.
            else {
                sum -= sequence[left];
                
                left++;
            }
        }
        
        int[] a = queue.poll();
        
        int[] answer = {a[1], a[2]};
        return answer;
    }
}



