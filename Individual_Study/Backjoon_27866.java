package src.Individual_Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Backjoon_27866 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] S = br.readLine().toCharArray();
        int N = Integer.parseInt(br.readLine());

        System.out.println(S[N-1]);
    }
}
