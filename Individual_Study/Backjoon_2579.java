package Individual_Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Backjoon_2579 {

    static int[] arr;
    static Integer[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        arr = new int[n + 1];
        dp = new Integer[n + 1];

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        dp[0] = arr[0];
        dp[1] = arr[1];
        // 이대로 하면 런타임 에러가 발생
        // dp[2] = arr[1] + arr[2];

        if (n >= 2) {
            dp[2] = arr[1] + arr[2];
        }

        System.out.println(fc(n));
    }

    public static int fc(int x) {
        if (dp[x] == null) {
            dp[x] = Math.max(fc(x - 2), fc(x - 3) + arr[x - 1]) + arr[x];
        }
        return dp[x];
    }

}
