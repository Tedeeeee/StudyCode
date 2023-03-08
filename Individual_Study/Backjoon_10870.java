package Individual_Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Backjoon_10870 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int sum = Recursion(N);
        System.out.println(sum);
    }
    public static int Recursion(int x) {
        if(x == 0) return 0;
        if(x == 1) return 1;

        return Recursion(x-1)+Recursion(x-2);
    }
}
