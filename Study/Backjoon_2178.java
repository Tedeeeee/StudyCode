package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Backjoon_2178 {
    public static int n,m;
    public static int[][] Graph;
    public static boolean[][] visited;
    public static int[] zx = {-1, 1, 0, 0};
    public static int[] zy = {0, 0, -1, 1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 전체 크기 초기화
        Graph = new int[n][m];
        for (int i = 0; i < n; i++) {
            String S = br.readLine();
            for (int j = 0; j < m; j++) {
                Graph[i][j] = S.charAt(j) - '0';
            }
        }
        // visited배열 초기화
        visited = new boolean[n][m];
        // 첫번째 방문위치
        visited[0][0] = true;

        BFS(0, 0);
        System.out.println(Graph[n-1][m-1]);
    }

    public static void BFS(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        // 현재 장소를 q에 넣어준다
        q.add(new int[] {x, y});

        while (!q.isEmpty()) {
            // 현재 장소를 큐에서 빼서 위치를 잡아준다
            int[] N = q.poll();
            // x숫자를 nx에 대입
            int nx = N[0];
            // y숫자를 ny에 대입
            int ny = N[1];

            // 총 4방향이다.
            for (int i = 0; i < 4; i++) {
                // (x축) 검색할 방향을 설정
                int ax = zx[i] + nx;
                // (y축) 변하는 값은 방향을 설정하는 zx,zy
                int ay = zy[i] + ny;

                // 만약 전체범위를 벋어나면 계산할 필요가 없음
                if (ax < 0 || ay < 0 || ax >= n || ay >= m) {
                    continue;
                }
                // 만약 해당값을 이미 방문했거나 0이여도 관심X
                if (visited[ax][ay] || Graph[ax][ay] == 0) {
                    continue;
                }

                // 만약 1을 찾았다면
                // 큐에 해당 좌표를 넣어준다
                q.add(new int[]{ax, ay});
                // 해당 좌표에 전의 좌표 값에서 1을 더한 값을 넣어준다
                Graph[ax][ay] = Graph[nx][ny] + 1;
                // 방문처리로 마무리
                visited[ax][ay] = true;
            }

        }
    }
}