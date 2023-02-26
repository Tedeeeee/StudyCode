package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Gem {
    int mass;
    int value;

    Gem (int mass, int value) {
        this.mass = mass;
        this.value = value;
    }

}
public class Backjoon_1202 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Gem[] jel = new Gem[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            jel[i] = new Gem(m , v);
        }

        Arrays.sort(jel, new Comparator<Gem>() {
            @Override
            public int compare(Gem o1, Gem o2) {
                if (o1.mass == o2.mass) {
                    return o2.value - o1.value;
                } else {
                    return o1.mass - o2.mass;
                }
            }
        });

        int[] bag = new int[K];
        for (int i = 0; i < K; i++) {
            bag[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(bag);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        long cash = 0;
        for (int i = 0, j = 0; i < K; i++) {
            while (j < N && jel[j].mass <= bag[i]) {
                pq.add(jel[j++].value);
            }

            if (!pq.isEmpty()) {
                cash += pq.poll();
            }
        }
        System.out.println(cash);

    }

}
// 아에 정답을 보고 배낀수준으로 풀었음 이거는 내가 다시 풀어야함
// 이해는 했는데 나중에 다시 풀어서 내걸로 만들거임