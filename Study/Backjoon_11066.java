package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backjoon_11066 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        // 2권을 만들것이다.
        for (int i = 0; i < T; i++) {
            int k = Integer.parseInt(br.readLine());
            int[] novel;
            int[] sum;
            int[][] dp;

            // 파일 갯수
            novel = new int[k + 1];

            sum = new int[k + 1];
            // 범위를 지정해줄 2차원 배열
            dp = new int[k + 1][k + 1];

            // 각 페이지들을 novel에 넣어준다
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= k; j++) {
                novel[j] = Integer.parseInt(st.nextToken());
                sum[j] = sum[j - 1] + novel[j];
            }
            // 몇장을 묶을것인가
            for (int n = 1; n <= k; n++) {
                // 어디부터 묶기 시작할것인가
                for (int from = 1; from + n <= k; from++) {
                    int to = from + n;
                    dp[from][to] = Integer.MAX_VALUE;
                    // 나누어질 특정 지점
                    for (int divide = from; divide < to; divide++) {
                        dp[from][to] = Math.min(dp[from][to], dp[from][divide] + dp[divide + 1][to] + sum[to] - sum[from - 1]);
                    }
                }
            }
            System.out.println(dp[1][k]);
        }
    }
}
