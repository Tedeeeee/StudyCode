package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class Backjoon_1339 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // 받는 문자를 배열 한 index에 들어가게 한다
        String[] S = new String[N];

        //알파벳은 총 26자리
        int[] Aph = new int[26];

        for (int i = 0; i < N; i++) {
            S[i] = br.readLine();
        }

        // 2중 표문으로 각각 index의 문자열을 나눠서 자릿수를 확인한다.
        for (int i = 0; i < N; i++) {

            // 가장 큰 수의 자릿수 부터 정한다
            // 한 인덱스가 끝날때 마다 리셋
            int temp = (int)(Math.pow(10, S[i].length()-1));

            // 0번째 index의 첫번째 단어부터 1번째 index까지의
            // 자릿수를 모두 정해준다
            for (int j = 0; j < S[i].length(); j++) {

                // 65는 대문자 A의 아스키 코드이다 자릿수에 더해준다
                Aph[(int)(S[i].charAt(j)-65)] += temp;

                // 다음 자릿수
                temp /= 10;
            }
        }

        // 겹치는 수가 있다.
        // 수를 더하는 과정에서 섞이기 때문에 정렬을 해줘야한다
        Arrays.sort(Aph);

        // 큰 수 부터 정한다
        // 범위는  0 ~ 9 이다
        int index = 9;
        int sum = 0;

        // 인덱스는 0부터 시작 length는 26개라 오류남
        for (int i = Aph.length - 1; i >= 0; i--) {
            if (Aph[i] == 0) {
                break;
            }

            // 자릿수랑 곱해서 합하면 정답
            sum += Aph[i] * index;

            // 9부터 내려간다
            index--;
        }
        System.out.println(sum);
    }
}
