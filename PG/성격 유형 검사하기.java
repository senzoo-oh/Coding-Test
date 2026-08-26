import java.util.*;

class Solution {
    public String solution(String[] survey, int[] choices) {
        
        HashMap<Character, Integer> map = new HashMap<>();
        
        map.put('R', 0);
        map.put('T', 0);
        map.put('C', 0);
        map.put('F', 0);
        map.put('J', 0);
        map.put('M', 0);
        map.put('A', 0);
        map.put('N', 0);
        
        for (int i = 0; i < survey.length; i++) {
            
            char selection = 'a';
            int score = choices[i];
            
            int addScore = 0;
            
            if (score <= 3) {
                selection = survey[i].charAt(0);
                
                if (score == 1) {
                    addScore = 3;
                }
                else if (score == 2) {
                    addScore = 2;
                }
                else {
                    addScore = 1;
                }
            }
            else if (5 <= score) {
                selection = survey[i].charAt(1);
                
                if (score == 5) {
                    addScore = 1;
                }
                else if (score == 6) {
                    addScore = 2;
                }
                else {
                    addScore = 3;
                }
            }
            else continue;
            
            // map.computeIfPresent(selection, (key, value) -> value + addScore); 
            map.put(selection, map.get(selection) + addScore);
        }
        
        StringBuilder sb = new StringBuilder();
        
        sb.append(map.get('R') < map.get('T') ? "T" : "R");
        sb.append(map.get('C') < map.get('F') ? "F" : "C");
        sb.append(map.get('J') < map.get('M') ? "M" : "J");
        sb.append(map.get('A') < map.get('N') ? "N" : "A");
        
        return sb.toString();
    }
}





