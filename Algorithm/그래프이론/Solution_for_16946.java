import java.io.*;
import java.util.*;


public class Solution_for_16946 {
    static int N, M;
    static int[][] maps;

    static int[][] area;
    
    static int[][] answer;
    static boolean[][] visited;

    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    static ArrayList<int[]> areaCnt = new ArrayList<>();

    static int areaNo = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maps = new int[N][M];
        answer = new int[N][M];
        area = new int[N][M];

        for (int n = 0; n < N; n++) {
            String s = br.readLine();

            for (int m = 0; m < M; m++) {
                maps[n][m] = s.charAt(m) - '0';
            }
        }

        // 각 영역의 크기를 구함
        visited = new boolean[N][M];

        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                int cnt = 0;

                if (maps[n][m] == 0 && !visited[n][m]) {
                    cnt = bfs(n, m);

                    areaCnt.add(new int[] {cnt});
                    areaNo++;
                }
            }
        }

        // 각각의 벽을 허물 경우, 인접한 구역의 합을 구함
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                if (maps[n][m] == 1) {
                    
                    int totalArea = 0;

                    Set<Integer> set = new HashSet<>();

                    for (int d = 0; d < 4; d++) {
                        int nr = n + dirs[d][0];
                        int nc = m + dirs[d][1];

                        if (nr < 0 || nc < 0 || N <= nr || M <= nc) continue;
                        
                        if (maps[nr][nc] == 0) {
                            set.add(area[nr][nc]);
                        }
                    }

                    for (int s : set) {
                        totalArea += areaCnt.get(s - 1)[0];
                    }

                    answer[n][m] = (totalArea + 1) % 10;
                }
            }
        }

        for (int n = 0; n < N; n++) {
            for (int m = 0 ; m < M; m++) {
                System.out.print(answer[n][m]);
            }
            System.out.println();
        }
    }

    public static int bfs(int r, int c) {
        int cnt = 0;

        Queue<int[]> queue = new ArrayDeque<>();
        
        queue.add(new int[] {r, c});
        visited[r][c] = true;
        area[r][c] = areaNo;
        cnt++;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            
            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dirs[d][0];
                int nc = cur[1] + dirs[d][1];

                if (nr < 0 || nc < 0 || N <= nr || M <= nc) continue;
                if (maps[nr][nc] == 1) continue;
                if (visited[nr][nc]) continue;
                
                queue.add(new int[] {nr, nc});
                visited[nr][nc] = true;
                area[nr][nc] = areaNo;
                cnt++;
            }
        }

        return cnt;
    }
}




class Solution_for_16946_fail {
    static int N, M;
    static int[][] maps;
    static int[][] answer;
    static boolean[][] visited;

    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maps = new int[N][M];
        answer = new int[N][M];

        for (int n = 0; n < N; n++) {
            String s = br.readLine();

            for (int m = 0; m < M; m++) {
                maps[n][m] = s.charAt(m) - '0';
            }
        }

        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                if (maps[n][m] == 1) {
                    answer[n][m] = (bfs(n, m) % 10);
                }
            }
        }

        for (int n = 0; n < N; n++) {
            for (int m = 0 ; m < M; m++) {
                System.out.print(answer[n][m]);
            }
            System.out.println();
        }
    }

    public static int bfs(int r, int c) {
        int cnt = 0;

        maps[r][c] = 0;
        visited = new boolean[N][M];

        Queue<int[]> queue = new ArrayDeque<>();
        
        queue.add(new int[] {r, c});
        visited[r][c] = true;
        cnt++;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            
            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dirs[d][0];
                int nc = cur[1] + dirs[d][1];

                if (nr < 0 || nc < 0 || N <= nr || M <= nc) continue;
                if (maps[nr][nc] == 1) continue;
                if (visited[nr][nc]) continue;
                
                queue.add(new int[] {nr, nc});
                visited[nr][nc] = true;
                cnt++;
            }
        }

        maps[r][c] = 1;

        return cnt;
    }
}
