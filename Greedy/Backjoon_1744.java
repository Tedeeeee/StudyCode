package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Backjoon_1744 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 양수 내림차순
        PriorityQueue<Integer> prr = new PriorityQueue<>(Comparator.reverseOrder());

        // 음수 오름차순
        PriorityQueue<Integer> nrr = new PriorityQueue<>();
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            int k = Integer.parseInt(br.readLine());

            // 양수 넣기
            if (k > 0) {
                prr.add(k);

            // 음수 넣기
            } else {
                nrr.add(k);
            }
        }

        int answer = 0;
        int temp = 0;

        //양수
        while (!prr.isEmpty()) {
            temp = prr.poll();
            if (!prr.isEmpty()) {

                // 다음 수와 곱하는데 빼진 않는다
                int next = prr.peek();

                // 혹시나 1과 양수를 곱하는것을 방지
                if (temp * next > temp) {
                    temp *= prr.poll();
                }
            }
            answer += temp;
        }

        // 음수
        while (!nrr.isEmpty()) {
            temp = nrr.poll();
            if (!nrr.isEmpty()) {
                int next = nrr.peek();

                // 음수와 음수를 곱해서 양수로 만들어라
                if (temp * next >= 0) {
                    temp *= nrr.poll();
                }
            }
            answer += temp;
        }
        System.out.println(answer);
    }
}
// 양수와 음수로 나누지 않고 계산하고 싶었는데 잘 안됐다...
// 우선순위 큐로 계산하고 싶었고 각자의 조건을 잘 조합하여 넣었다