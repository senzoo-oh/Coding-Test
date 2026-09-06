import java.util.*;

class Solution {
    
    public class Staff {
        int staffNo;
        int attitudeScore;
        int peerScore;
        int scoreSum;
        
        Staff (int staffNo, int attitudeScore, int peerScore) {
            this.staffNo = staffNo;
            this.attitudeScore = attitudeScore;
            this.peerScore = peerScore;
            this.scoreSum = attitudeScore + peerScore;
        }
    }
    
    ArrayList<Staff> list = new ArrayList<>();
    ArrayList<Staff> bonus = new ArrayList<>();
    
    public int solution(int[][] scores) {
        
        for (int i = 0; i < scores.length; i++) {
            int[] score = scores[i];
            
            list.add(new Staff(i + 1, score[0], score[1]));
        }
        
        list.sort((a, b) -> {
            // 태도점수 기준으로 내림차순
            if (a.attitudeScore != b.attitudeScore) {
                return b.attitudeScore - a.attitudeScore;
            }
            // 태도점수가 같다면, 동료점수를 기준으로 오름차순
            else {
                return a.peerScore - b.peerScore;
            }
        });
        
        // 각 사원에 대해서 지금까지 찾은 peerScore 최솟값보다 작다면 성과급을 받을 수 없는 사원임
        int prevAttitudeScore = list.get(0).attitudeScore;
        int maxPeerScore = list.get(0).peerScore;
        
        bonus.add(list.get(0));
        
        for (int i = 1; i < list.size(); i++) {
            // 지금까지 찾은 동료평가 점수의 최댓값 <= 현재 사원의 동료평가 점수
                // 성과급 대상자
            if (maxPeerScore <= list.get(i).peerScore) {
                bonus.add(list.get(i));
                maxPeerScore = Math.max(maxPeerScore, list.get(i).peerScore);
            }
        }
        
        bonus.sort((a, b) -> {
            return b.scoreSum - a.scoreSum;
        });
        
        // System.out.println("성과급 받는 사원");
        // for (int i = 0; i < bonus.size(); i++) {
        //     System.out.println("사원번호: " + bonus.get(i).staffNo + ", "
        //                        + "태도점수: " + bonus.get(i).attitudeScore + ", "
        //                        + "동료점수: " + bonus.get(i).peerScore);
        // }
        
        int prevScoreSum = 0;
        int rank = 1;
        
        Staff first = bonus.get(0);
        
        prevScoreSum = first.scoreSum;
        
        // 1등이 완호라면 rank를 반환함
        if (first.staffNo == 1) {
            return rank;
        }
        
        for (int i = 1; i < bonus.size(); i++) {
            
            // 총합이 앞의 사원과 다른 경우, 석차를 갱신해 줌
            if (prevScoreSum != bonus.get(i).scoreSum) {
                rank = i + 1;
                prevScoreSum = bonus.get(i).scoreSum;
            }
            
            if (bonus.get(i).staffNo == 1) {
                return rank;
            }
        }
        
        return -1;
    }
}