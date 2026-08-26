import java.io.*;
import java.util.*;

public class Solution_for_11000 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        PriorityQueue<Course> allCourses = new PriorityQueue<>((a, b) -> {
            return a.start - b.start;
        });
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());

            int S = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());

            allCourses.add(new Course(S, T));
        }

        PriorityQueue<Course> rooms = new PriorityQueue<>((a, b) -> {
            return a.end - b.end;
        });

        // 시작시간이 빠른 강의를 하나씩 꺼내서 배정할 수 있는지 확인함
        while(!allCourses.isEmpty()) {

            Course cur = allCourses.poll();

            if (rooms.isEmpty()) {
                rooms.add(cur);
            }
            else {
                if (rooms.peek().end <= cur.start) {
                    rooms.poll();
                }
                
                rooms.add(cur);
            }
        }

        System.out.println(rooms.size());
    }

    public static class Course {
        int start;
        int end;

        public Course(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
