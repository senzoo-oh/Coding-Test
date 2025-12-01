import java.util.*;
import java.io.*;

public class Solution_for_2668 {
    static int N;
    static int[][] table;

    static List<Integer> nums = new ArrayList<>();

    static List<Integer> in = new ArrayList<>();
    static List<Integer> out = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        table = new int[2][N];

        for (int n = 0; n < N; n++) {
            table[0][n] = n+1;
            
            st = new StringTokenizer(br.readLine());
            table[1][n] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            if (nums.contains(table[0][i])) continue;
            
            in = new ArrayList<>();
            out = new ArrayList<>();
            
            int index = i;
            while (true) {
                
                int outNum = table[1][index];
                in.add(index+1);
                
                // 동일한 숫자가 들어있는 경우 종료
                if (out.contains(outNum)) break;
                out.add(outNum);

                // in과 out에 들어있는 숫자가 동일히다면 조건을 만족함
                if (isSame()) {
                    for (int j = 0; j < in.size(); j++) {
                        nums.add(in.get(j));
                    }
                    break;
                }
                index = table[1][index]-1;
            }
        }
        
        Collections.sort(nums);
        System.out.println(nums.size());
        for (int i = 0; i < nums.size(); i++) {
            System.out.println(nums.get(i));
        }
    }

    public static boolean isSame() {
        boolean result = true;
        
        Collections.sort(in);
        Collections.sort(out);

        for (int i = 0; i < in.size(); i++) {
            if (in.get(i) != out.get(i)) {
                return result = false;
            }
        }        
        return result;
    }
}
