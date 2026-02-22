import java.io.*;
import java.util.*;

public class Solution_for_6087 {
    static int W, H;
    static char[][] maps;
    static PriorityQueue<Node> queue = new PriorityQueue<>();

    static ArrayList<int[]> locationOfC = new ArrayList<>();
    static int[] first;
    static int[] second;

    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};   // 상, 하, 좌, 우
    
    static int[][][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());   // 열
        H = Integer.parseInt(st.nextToken());   // 행

        maps = new char[H][W];

        for (int h = 0; h < H; h++) {
            String s = br.readLine();
            for (int w = 0; w < W; w++) {
                maps[h][w] = s.charAt(w);

                if (maps[h][w] == 'C') {
                    locationOfC.add(new int[] {h, w});
                }
            }
        }

        dist = new int[H][W][4];

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                Arrays.fill(dist[i][j], Integer.MAX_VALUE);
            }
        }

        first = locationOfC.get(0);
        second = locationOfC.get(1);

        queue.add(new Node(first[0], first[1], 0, -1));
        BFS(first[0], first[1]);
    }

    public static void BFS(int r, int c) {
        
        for (int d = 0; d < 4; d++) {
            dist[r][c][d] = 0;
        }

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (cur.r == second[0] && cur.c == second[1]) {
                System.out.println(cur.rotateCnt);
                return;
            }

            for (int d = 0; d < 4; d++) {
                int nextRow = cur.r + dirs[d][0];
                int nextCol = cur.c + dirs[d][1];

                if (nextRow < 0 || nextCol < 0 ||  H <= nextRow || W <= nextCol) continue;
                if (maps[nextRow][nextCol] == '*') continue;

                int newCost;

                if (cur.dirs == -1) {
                    newCost = cur.rotateCnt;
                }
                else {
                    newCost = cur.rotateCnt + (cur.dirs == d ? 0 : 1);
                }

                if (dist[nextRow][nextCol][d] > newCost) {
                    dist[nextRow][nextCol][d] = newCost;
                    queue.add(new Node(nextRow, nextCol, newCost, d));
                }
            }
        }
    }

    public static void BFS1(int r, int c) {

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (cur.r == second[0] && cur.c == second[1]) {
                System.out.println(cur.rotateCnt);
                return;
            }

            for (int d = 0; d < 4; d++) {

                int nextRow = cur.r + dirs[d][0];
                int nextCol = cur.c + dirs[d][1];

                if (nextRow < 0 || nextCol < 0 || H <= nextRow || W <= nextCol) continue;   // 지도 범위 밖을 벗어난 경우
                if (maps[nextRow][nextCol] == '*') continue;    // 벽인 경우
                
                // 시작 C 지점인 경우, 이전의 방향과 비교하지 않음
                if (cur.dirs == -1) {
                    queue.add(new Node(nextRow, nextCol, cur.rotateCnt, d));
                }
                // 이전의 방향과 비교해서 회전여부를 확인함
                else {
                    // 위 또는 아래 방향에서 탐색한 경우
                    if (cur.dirs == 0 || cur.dirs == 1) {
                        // 왼쪽 또는 오른쪽 방향으로 탐색을 한다면 회전하는 경우임
                        if (d == 2 || d == 3) {
                            queue.add(new Node(nextRow, nextCol, cur.rotateCnt + 1, d));
                        }
                        // 위 또는 아래 방향으로 탐색을 한다면 회전하지 않는 경우임
                        else {
                            queue.add(new Node(nextRow, nextCol, cur.rotateCnt, d));
                        }
                    }
                    // 왼쪽 또는 오른쪽 방향에서 탐색한 경우
                    else {
                        // 왼쪽 또는 오른쪽 방향으로 탐색을 한다면 회전하지 않는 경우임
                        if (d == 2 || d == 3) {
                            queue.add(new Node(nextRow, nextCol, cur.rotateCnt, d));
                        }
                        // 위 또는 아래 방향으로 탐색을 한다면 회전하는 경우임
                        else {
                            queue.add(new Node(nextRow, nextCol, cur.rotateCnt + 1, d));
                        }
                    }
                }
            }
        }
    }

    public static class Node implements Comparable<Node> {
        int r;
        int c;
        int rotateCnt;
        int dirs;

        public Node(int r, int c, int rotateCnt, int dirs) {
            this.r = r;
            this.c = c;
            this.rotateCnt = rotateCnt;
            this.dirs = dirs;
        }

        @Override
        public int compareTo(Node o) {
            return this.rotateCnt - o.rotateCnt;    // rotateCnt 기준 오름차순 정렬
        }
    }
}
