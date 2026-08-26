import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_for_1789 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Long S = Long.parseLong(st.nextToken());

        long num = 1;
    
        while(true) {
            if (num * (num + 1) > 2 * S) break;

            num++;
        }

        System.out.println(num - 1);
    }
}
