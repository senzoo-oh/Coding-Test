import java.util.*;

class Solution {
    public int solution(int[] a) {
        int answer = 2;
        
        int[] leftMin = new int[a.length];
        int[] rightMin = new int[a.length];
        
        // leftMin 채움
        leftMin[0] = a[0];
        
        for (int i = 1; i < a.length; i++) {
            leftMin[i] = Math.min(leftMin[i - 1], a[i]);
        }
        
        // rightMin 채움
        rightMin[a.length-1] = a[a.length-1];
        for (int i = a.length - 2; -1 < i; i--) {
            rightMin[i] = Math.min(rightMin[i + 1], a[i]);
        }
        
        for (int i = 1; i < a.length - 1; i++) {
            int left = leftMin[i-1];
            int right = rightMin[i+1];
            
            if (left < a[i] && right < a[i]) continue;
            else answer++;
        }
        
        return answer;
    }
}