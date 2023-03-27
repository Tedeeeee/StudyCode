package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backjoon_1654 {

    public static void main(String[]args)throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine()," ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];

        long max = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());

            if (arr[i] > max) {
                max = arr[i];
            }
        }
        // 기존 K개의 랜선으로 N개의 랜선을 만들수없는 경우는 없다.
        // 즉, 무조건 N개의 랜선을 만들수 있다
        max++;

        // 같은 갯수일때 가장 긴 길이
        // Upper Bound
        long start = 0;
        long mid = 0;
        long sum = 0;
        while (start < max) {
            sum = 0;
            mid = (start + max) / 2;

            for (int i = 0; i < N; i++) {
                sum += arr[i] / mid;
            }

            // 만약 sum의 값이 M보다 크거나 같다면?
            // 분모를 높일수 없으니 분자를 낮춘다
            // 즉, max값을 낮춘다
            if (sum < M) {
                max = mid;
            }
            // 만약 sum 의 값이 M보다 작다면?
            // 분자를 높인다.
            // 중간 값에서 초과 값인 + 1
            else {
                start = mid + 1;
            }
        }
        // upper bound 의 이유때문에 한개 빼야한다.
        System.out.println(start - 1);
    }
}
