package src.DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backjoon_2606 {
    public static int node, edge, count;
    public static int[][] Graph;
    public static boolean[] visited;
    public static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        node = Integer.parseInt(br.readLine());
        edge = Integer.parseInt(br.readLine());

        Graph = new int[node+1][node+1];
        visited = new boolean[node + 1];
        for (int i = 0; i < edge; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            int a= Integer.parseInt(st.nextToken());
            int b= Integer.parseInt(st.nextToken());

            Graph[a][b] = Graph[b][a] = 1;
        }
        DFS(1);
        System.out.println(count);
    }

    public static void DFS(int x) {
        visited[x] = true;

        for (int i = 1; i <= node; i++) {
            if (!visited[i] && Graph[x][i] == 1) {
                count++;
                DFS(i);
            }
        }
    }
}