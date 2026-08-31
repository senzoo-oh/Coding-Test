import java.util.*;

class Solution {
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        
        ArrayList<int[]> list = new ArrayList<>();
        
        // 광물을 5개의 묶음으로 만듦
        int diamond = 0;
        int iron = 0;
        int stone = 0;
        
        int bundle = 0;
        
        // 곡괭이의 개수로 캘 수 있는 광물들로만 묶음을 만들어 줌
        int picksCnt = 0;
        for (int c : picks) {
            picksCnt += c;
        }
        
        int mineralCnt = Math.min(minerals.length, picksCnt * 5);
        
        for (int i = 0; i < mineralCnt; i++) {
            // 묶음이 바뀌었다면 지금까지의 묶음을 list에 담고 '각 광물의 개수'를 의미하는 변수들을 초기화 함
            if (bundle != (i / 5)) {
                
                list.add(new int[] {diamond, iron, stone});
                
                diamond = 0;
                iron = 0;
                stone = 0;
                
                bundle = (i / 5);
            }
            
            // 광물의 개수를 셈
            if (minerals[i].equals("diamond")) diamond++;
            else if (minerals[i].equals("iron")) iron++;
            else stone++;
            
            // 마지막 묶음인 경우
            if (i == (mineralCnt - 1)) {
                list.add(new int[] {diamond, iron, stone});
            }
        }
        
        list.sort((a, b) -> {
            if (a[0] != b[0]) {
                return b[0] - a[0];
            }
            else if (a[1] != b[1]) {
                return b[1] - a[1];
            }
            else {
                return b[2] - a[2];
            }
        });
        
        // for (int i = 0; i < list.size(); i++) {
        //     System.out.print(list.get(i)[0] + ", " + list.get(i)[1] + ", " + list.get(i)[2]);
        //     System.out.println();
        // }
        
        // 각 광물의 묶음에 곡괭이를 사용함
        for (int i = 0; i < list.size(); i++) {
            
            int[] cur = list.get(i);
            
            // System.out.println(cur[0] + ", " + cur[1] + ", " + cur[2]);
            
            // 다이아몬드 곡괭이 사용 -> 피로도 1
            if (picks[0] != 0) {
                for (int j = 0; j < 3; j++) {
                    answer += cur[j];
                }
                picks[0]--;
            }
            // 철 곡괭이 사용
            else if (picks[1] != 0) {
                for (int j = 0; j < 3; j++) {
                    if (j == 0 && cur[j] != 0) {
                        answer += (5 * cur[j]);
                    }
                    else answer += cur[j];
                }
                picks[1]--;
            }
            // 돌 곡괭이 사용
            else if (picks[2] != 0) {
                for (int j = 0; j < 3; j++) {
                    if (j == 0 && cur[j] != 0) {
                        answer += (25 * cur[j]);
                    }
                    else if (j == 1 && cur[j] != 0) {
                        answer += (5 * cur[j]);
                    }
                    else answer += cur[j];
                }
                picks[2]--;
            }
            // 사용할 곡괭이가 더 이상 없는 경우
            else {
                break;
            }
            
            // System.out.println("answer: " + answer);
        }
        
        return answer;
    }
}