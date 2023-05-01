package Individual_Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backjoon_9465 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {

            int m =Integer.parseInt(br.readLine());

            int[][] arr = new int[2][m+1];
            int[][] dp = new int[2][m+1];

            for (int j = 0; j < 2; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 1; k <= m; k++) {
                    arr[j][k] = Integer.parseInt(st.nextToken());
                }
            }

            dp[0][1] = arr[0][1];
            dp[1][1] = arr[1][1];

            // 최종의 수가 놓일 위치를 선택하는 것이 중요하다. 그렇다면 이건 top 인가 bottom 인가
            // 두가지의 경우만 존재하는 이유 = 최댓값 ( 가장 많은 곳을 밟아라 )
            // 바로 한칸만 가면 밟는건 많아져도 두수의 차이가 얼마 나지 않으면 한칸 더 예를 들어도 된다
            for (int j = 2; j <= m; j++) {
                dp[0][j] = Math.max(dp[1][j-1], dp[1][j-2]) + arr[0][j];
                dp[1][j] = Math.max(dp[0][j-1], dp[0][j-2]) + arr[1][j];
            }
            System.out.println(Math.max(dp[0][m], dp[1][m]));
        }
    }
}
