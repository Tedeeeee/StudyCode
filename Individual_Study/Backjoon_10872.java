package Individual_Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Backjoon_10872 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Recursion(N);
        int m = Recursion(N);
        System.out.println(m);
    }
    public static int Recursion(int x){
        if (x <= 1) {
           return 1;
        }
        return x * Recursion(x -1);
    }
}
