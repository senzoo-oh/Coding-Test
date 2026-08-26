import java.util.*;

// 재귀, 순열
class Solution {
    
    public static int selectedCnt;      // 골라야 할 user_id 개수
    public static boolean[] selected;
    
    // 각 banned_id의 인덱스별 후보 목록을 저장하는 구조가 적절함.
    // public static HashMap<String, String[]> mappedId;
    public static HashMap<Integer, ArrayList<Integer>> mappedId;
    
    public static Set<String> resultSet;
    
    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        
        selectedCnt = banned_id.length;
        selected = new boolean[user_id.length];
        
        // Set는 인터페이스이므로 구현체인 HashSet을 사용함.
        resultSet = new HashSet<>();
        
        // banned_id 에 맞는 user_id를 매핑시킴
        mappedId = new HashMap<>();
        
        for (int i = 0; i < banned_id.length; i++) {
            String bannedId = banned_id[i];
            
            // 재귀에서 문자열을 꺼내면, 그 문자열이 몇번재 사용자인지 다시 찾아야 함. 그래서 후보 모곩에는 사용자 문자열보다 사용자 인덱스를 저장하는 것이 편함
            // ArrayList<String> list = new ArrayList<>();
            ArrayList<Integer> list = new ArrayList<>();
            
            for (int j = 0; j < user_id.length; j++) {
                // 현재 userId가 banned_id의 규칙과 동일하다면
                if (isMatched(bannedId, user_id[j])) list.add(j);
            }
            
            mappedId.put(i, list);
        }
        
        selectId(0);
        
        return resultSet.size();
    }
    
    public void selectId(int cnt) {
        
        // 모든 user_id를 선택한 경우
        if (cnt == selectedCnt) {
            
            StringBuilder sb = new StringBuilder();
            
            for (int i = 0; i < selected.length; i++) {
                if (selected[i]) sb.append("1");
                else sb.append("0");
            }
            
            resultSet.add(sb.toString());
            
            return;
        }
        
        // cnt 번째 banned_id 후보 목록을 가져온다.
        ArrayList<Integer> mappedIdList = mappedId.get(cnt);
        
        // 후보 목록을 반복한다.
            // 선택되지 않은 사용자라면
                // 선택 처리
                // 재귀 호출
                // 선택 해제
        for (int i = 0; i < mappedIdList.size(); i++) {
            if (!selected[mappedIdList.get(i)]) {
                selected[mappedIdList.get(i)] = true;
                
                selectId(cnt+1);
                
                selected[mappedIdList.get(i)] = false;
            }
        }
        
        
//         else {
//             // user_id를 하나씩 탐색하면서 선택하지 않은 user_id 인 경우, user_id를 선택한다.
            
//             // selected 에 선택한 user_id를 체크한다.
            
//             // selectId(cnt+1) 을 재귀호출 한다.
            
//             // selected 에 선택한 user_id의 체크를 푼다.
//         }
    }
    
    public boolean isMatched(String bannedId, String userId) {
        
        // 길이가 동일한지 확인
        if (bannedId.length() != userId.length()) {
            return false;
        }
        
        // *이 아닌 자리에 같은 문자가 있는지 확인
        for (int i = 0; i < bannedId.length(); i++) {
            
            char cur = bannedId.charAt(i);
            
            if (cur != '*') {
                if (cur != userId.charAt(i)) {
                    return false;
                }
            }
        }
        
        return true;
    }
}