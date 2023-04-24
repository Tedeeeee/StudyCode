package Individual_Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Backjoon_2748 {

    static long[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        arr = new long[n + 1];

        for (int i = 0; i <= n; i++) {
            arr[i] = -1;
        }

        arr[0] = 0;
        arr[1] = 1;
        System.out.println(dp(n));
    }

    public static long dp(int x) {
        if (arr[x] == -1) {
            arr[x] = dp(x - 1) + dp(x - 2);
        }
        return arr[x];
    }
}
