package src.DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Backjoon_2667 {
    public static StringBuilder sb = new StringBuilder();
    public static int N;
    public static int count=0;
    public static int[][] Graph;
    public static boolean[][] visited;
    public static int[] dx = {1, -1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    public static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        Graph = new int[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            String S = br.readLine();
            for (int j = 0; j < N; j++) {
                Graph[i][j] = S.charAt(j) - '0';
            }
        }
        arr = new int[N*N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (Graph[i][j] == 1 && !visited[i][j]) {
                    count++;
                    DFS(i,j);
                }
            }
        }
        System.out.println(count);
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {

            } else {
                System.out.println(arr[i]);
            }
        }
    }
    public static void DFS(int x, int y) {
        visited[x][y] = true;
        //카운트 정렬 쓰는건 진짜 미쳤다...
        arr[count]++;
        for (int i = 0; i < 4; i++) {
            int nextX = dx[i] + x;
            int nextY = dy[i] + y;

            if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
                continue;
            }
            if (visited[nextX][nextY] || Graph[nextX][nextY] == 0) {
                continue;
            }
            DFS(nextX,nextY);
        }
    }
}
