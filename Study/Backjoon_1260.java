package Study;

import java.beans.JavaBean;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Backjoon_1260 {
    public static StringTokenizer st;
    public static StringBuilder sb = new StringBuilder();
    public static int n,m,start;
    public static int[][] Graph;
    public static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());

        Graph = new int[n + 1][n + 1];
        visited = new boolean[n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            Graph[a][b] = Graph[b][a] = 1;
        }
        sb.append("\n");
        DFS(start);
        sb.append("\n");
        // 그리고 노드 방문횟수 초기화해
        visited = new boolean[n + 1];
        BFS(start);

        System.out.println(sb);
    }
    public static void DFS(int start) {
        // 방문 처리
        visited[start] = true;
        // 방문한애 출력
        sb.append(start + " ");

        // 노드 갯수 만큼 확인
        for (int i = 1; i <= n; i++) {
            // 출력했던애랑 새로운 노드랑 같은애 아니고 방문한적이 없다면
            if (Graph[start][i] == 1 && !visited[i]) {
                // start에 i넣고 다시 돌려
                DFS(i);
            }
        }
    }
    public static void BFS(int start) {
        // 큐쓸꺼야
        Queue<Integer> q = new LinkedList<>();
        // 큐에 시작하는애 집어넣고 시작
        q.add(start);
        // 시작하는애 방문처리로 시작
        visited[start] = true;

        // 남은 애들 없을때까지
        while (!q.isEmpty()) {
            // 방문 한 애는 빼서 start에 대입
            start = q.poll();
            // 출력할거니까
            sb.append(start + " ");

            // 똑같이 노드 갯수만큼
            for (int i = 1; i <= n; i++) {
                // 조건은 DFS랑 같어
                if (Graph[start][i] == 1 && !visited[i]) {
                    // 방문안했으면 큐로 방문해야지
                    q.add(i);
                    // 방문처리는 여기서 해주고
                    visited[i] = true;
                }
            }
        }
    }
}
