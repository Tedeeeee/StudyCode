package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backjoon_4796 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int i = 1;

        // for문을 쓰기엔 돌리는 횟수 조건이 마땅치 않다
        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            // break를 통해 무한으로 돌수있는 코드를 멈춘다.
            if(L == 0 && P == 0 && V == 0) break;

            // 음...조금 생각해보면 나온다.
            int sum = L * (V/P) + Math.min(L,V%P);
            System.out.println("Case "+ i++ + ": " + sum);
        }
    }
}
// 이 문제는 식 자체는 간단한데 0,0,0을 어떻게 읽게 할 것 인지가 관건이다.

