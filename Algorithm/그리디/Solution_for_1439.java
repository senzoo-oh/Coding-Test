import java.io.*;
import java.util.*;

public class Solution_for_1439 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        int[] cnts = new int[2];
        int curNum = s.charAt(0) - '0';

        for (int i = 1; i < s.length(); i++) {
            
            int nextNum = s.charAt(i) - '0';
            
            if (curNum != nextNum) {
                cnts[curNum]++;
                curNum = nextNum;
            }
            else continue;
        }
        
        cnts[curNum]++;

        System.out.println(Math.min(cnts[0], cnts[1]));
    }
}
