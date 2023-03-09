package src.Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Backjoon_7576 {
    public static int n,m,count;
    public static int[][] Graph;
    public static boolean[][] visited;
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    public static Queue<int[]> q = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 토마토 넝장 너비 초기화
        Graph = new int[m][n];
        visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                Graph[i][j] = Integer.parseInt(st.nextToken());

                // 익은 토마토가 있다면
                if (Graph[i][j] == 1) {
                    // 위치를 넣어주기
                    q.add(new int[]{i, j});
                    // 방문처리
                    visited[i][j] = true;
                }
            }
        }
        System.out.println(BFS());
    }
    public static int BFS () {
        while (!q.isEmpty()) {
            int[] now = q.poll();
            int nowX = now[0];
            int nowY = now[1];
            for (int i = 0; i < 4; i++) {
                int nextX = dx[i] + nowX;
                int nextY = dy[i] + nowY;

                if (nextX < 0 || nextY < 0 || nextX >= m || nextY >= n) {
                    continue;
                }
                if (visited[nextX][nextY] || Graph[nextX][nextY] == -1) {
                    continue;
                }

                q.add(new int[]{nextX, nextY});
                Graph[nextX][nextY] = Graph[nowX][nowY] + 1;
                visited[nextX][nextY] = true;
            }
        }
        int num = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 만약 0인 자리가 존재하면 -1 출력
                if (Graph[i][j] == 0) {
                    return -1;
                }
                // 만들어낸 수중에 가장 큰거
                num = Math.max(num, Graph[i][j]);
            }
        }
        // 시작지점부터 센거라서 -1 해줘야함...
        return num-1;
    }
}





