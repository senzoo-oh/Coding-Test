import java.io.*;
import java.util.*;

public class Solution_for_1339 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        int N = Integer.parseInt(s);

        int[] weight = new int[26];

        
        for (int n = 0 ; n < N; n++) {
            s = br.readLine();

            int pow = 1;
            for (int i = s.length()-1; -1 < i; i--) {
                weight[s.charAt(i)-'A'] += pow;

                pow *= 10;
            }
        }

        Arrays.sort(weight);
        int num = 9;
        int sum = 0;
        for (int i = 25; -1 < i; i--) {
            if (weight[i] == 0) break;
            
            sum += (num * weight[i]);
            num--;
        }

        System.out.println(sum);
    }


    public static void main1(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        int N = Integer.parseInt(s);

        boolean[] isChecked = new boolean[27];
        int[] alphabetToNum = new int[27];
        int[][] cnt = new int[10][27];

        ArrayList<String> list = new ArrayList<>();
        for (int n = 0 ; n < N; n++) {
            s = br.readLine();

            list.add(s);

            for (int i = 0; i < s.length(); i++) {

                int pos = s.length()-i-1;

                int c = s.charAt(i) - 'A';

                cnt[pos][c]++;
            }
        }

        int num = 9;
        for (int i = 9; -1 < i; i--) {
            for (int j = 0; j < 27; j++) {
                if (cnt[i][j] != 0 && !isChecked[j]) {
                    alphabetToNum[j] = num;
                    isChecked[j] = true;
                    num--;
                }
            }
        }

        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            String ss = list.get(i);

            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < ss.length(); j++) {
                int no = alphabetToNum[ss.charAt(j) - 'A'];
                
                sb.append(no);
            }

            sum += Integer.parseInt(sb.toString());
        }

        System.out.println(sum);
    }
}