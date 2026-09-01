import java.util.*;

class Solution {
    
    public int[] selection = new int[5];
    public int[][] qq;
    
    public int answer = 0;
    
    public int solution(int n, int[][] q, int[] ans) {
        
        qq = q;
        
        comb(n, 0, 1, ans);
        
        return answer;
    }
    
    public void comb(int n, int cnt, int index, int[] ans) {
        
        // 5개의 수를 모두 선택했다면
        if (cnt == 5) {
            if (compare(ans)) answer++;
            return;
        }
        
        for (int i = index; i < n + 1; i++) {
            selection[cnt] = i;
            comb(n, cnt + 1, i + 1, ans);
        }
    }
    
    public boolean compare(int[] ans) {
        
        HashSet set = new HashSet<>();
        for (int num : selection) {
            set.add(num);
        }
        
        // 각각의 입력한 값에 대해서
        for (int i = 0; i < qq.length; i++) {
            int[] input = qq[i];    // [1, 2, 3, 4, 5]
            
            int cnt = 0;
            for (int j = 0; j < 5; j++) {
                if (set.contains(input[j])) cnt++;
            }
            
            if (ans[i] != cnt) {
                return false;
            }
        }
        
        return true;
    }
}