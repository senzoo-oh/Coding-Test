import java.util.*;
import java.io.*;

/*
 * 123 ~ 987 까지의 수를 하나씩 기준잡아서 지금까지 입력받은 N개의 수와 비교하여 스트라이크와 볼의 개수가 일치하지 않으면 정답일 수 없으므로 false 처리 함.
 * 
 * 123 ~ 987 까지의 수와 N개의 수의 비교가 끝이나면 여전히 true인 숫자는 정답일 가능성이 있는 수이므로 true의 개수를 return 함.
 */
public class Solution_for_2503 {
    static int N;
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        
        // 질문한 세자리 수, 스트라이크 개수, 볼의 개수
        int[][] questions = new int[N][3];

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            
            questions[n][0] = Integer.parseInt(st.nextToken());
            questions[n][1] = Integer.parseInt(st.nextToken());
            questions[n][2] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;
        for (int i = 123; i < 988; i++) {
            int num = i;

            // 숫자가 0을 포함하고 있거나 중복된 숫자로 이루어진 경우(301, 333)
            if (isContainZeroOrDuplicatedNums(num)) continue;
            
            boolean isPossible = true;
            for (int j = 0; j < N; j++) {
                int[] question = questions[j];

                // num과 question을 비교하여 스트라이크 수와 볼의 개수 구하기
                if (!isEqualStrikeAndBallCount(num, question[0], question[1], question[2])) {
                    isPossible = false;
                    break;
                }
            }

            // 스트라이크와 볼의 개수가 동일하다면
            if (isPossible) {
                answer++;
            }
        }

        System.out.println(answer);
    }

    public static boolean isContainZeroOrDuplicatedNums(int num) {
        boolean result = false;

        int first = num / 100;
        int second = (num / 10) % 10;
        int third = (num % 10);

        // 0을 포함하는 경우
        if(first == 0 || second == 0 || third == 0) {
            result = true;
            return result;
        }

        // 동일한 숫자가 포함되어 있는 경우
        if(first == second || second == third || first == third) {
            result = true;
            return result;
        }

        return result;
    }

    public static boolean isEqualStrikeAndBallCount(int num, int question, int strike, int ball) {
        boolean result = true;

        int numFirst = num / 100;
        int numSecond = (num / 10) % 10;
        int numThird = (num % 10);

        int numQuestionFirst = question / 100;
        int numQuestionSecond = (question / 10) % 10;
        int numQuestionThrid = (question % 10);

        int strikeCnt = 0;
        int ballCnt = 0;

        // 스트라이크 개수 비교하기
        if (numFirst == numQuestionFirst) strikeCnt++;
        if (numSecond == numQuestionSecond) strikeCnt++;
        if (numThird == numQuestionThrid) strikeCnt++;

        if (strikeCnt != strike) {
            result = false;
            return result;
        }

        // 볼 개수 비교하기
        if (numFirst == numQuestionSecond || numFirst == numQuestionThrid) {
            ballCnt++;
        }
        if (numSecond == numQuestionFirst || numSecond == numQuestionThrid) {
            ballCnt++;
        }
        if (numThird == numQuestionFirst || numThird == numQuestionSecond) {
            ballCnt++;
        }

        if (ballCnt != ball) {
            result = false;
            return result;
        }

        return result;
    }
}
