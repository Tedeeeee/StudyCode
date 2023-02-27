package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Backjoon_1049 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] big = new int[M];
        int[] small = new int[M];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            big[i] = Integer.parseInt(st.nextToken());
            small[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(big);
        Arrays.sort(small);


        int k = Math.min(((N/6)+1)*big[0], N * small[0]);
        int j = ((N/6)*big[0]) + ((N%6)*small[0]);

        int result = (int)(Math.min(k,j));

        System.out.println(result);
    }
}
// 허허...이건 뭐 그냥 식 복사 붙여넣기 구만...
