import java.io.*;
import java.util.*;

public class Solution_for_1700 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());

        // 전기용품의 사용순서를 저장함
        int[] seq = new int[K];
        for (int k = 0; k < K; k++) {
            seq[k] = Integer.parseInt(st.nextToken());
        }

        // 각 전기용품이 언제 사용되는지 저장함(전기용품의 이름이 K이하의 자연수로 주어짐)
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for (int k = 0; k < K+1; k++) {
            list.add(new ArrayList<>());
        }

        for (int k = 0; k < K; k++) {
            int num = seq[k];
            
            list.get(num).add(k+1);
        }

        int answer = 0;
        Set<Integer> concent = new HashSet<>();
        for (int k = 0; k < K; k++) {
            int num = seq[k];
            System.out.println("현재 사용할 전기용품: " + num);

            // 현재 콘센트에 꽂혀있는 플러그인 경우
            if (concent.contains(num)) {
                list.get(num).remove(0);
            }
            // 플러그를 꽂지않은 콘센트가 있는 경우
            else if (concent.size() < N) {
                concent.add(num);
                list.get(num).remove(0);
            }
            // 콘센트에 플러그가 모두 꽂혀있는 경우, 꽂혀있는 플러그 중에서 나중에 사용되지 않는 플러그가 있으면 사용되지 않는 플러그를 뽑고, 모두 사용되는 플러그라면 가장 나중에 사용되는 플러그를 뽑는다.
            else {
                int maxSeq = 0;
                int plug = 0;
                for (Integer n : concent) {
                    // 사용되지 않은 플러그인 경우
                    if (list.get(n).isEmpty()) {
                        plug = n;
                        break;
                    }
                    else {
                        if (maxSeq < list.get(n).get(0)) {
                            maxSeq = list.get(n).get(0);
                            plug = n;
                        }
                    }
                }
                concent.remove(plug);
                answer++;

                concent.add(num);
                list.get(num).remove(0);
            }
            System.out.println(concent.toString());
        }
        System.out.println(answer);
    }
}
