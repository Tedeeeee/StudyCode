package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backjoon_2805 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        long max = 0;
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());

            if (arr[i] > max) {
                max = arr[i];
            }
        }

        long sum = 0;
        long target = max / 2;

        while (sum != M) {

            for (int i = 0; i < arr.length; i++) {
                if (arr[i] - target > 0) {
                    sum += arr[i] - target;
                }
            }

            if (sum > M) {
                target += max / 2;
                sum = 0;
            } else if (sum < M) {
                target -= max / 2;
                sum = 0;
            } else {
                break;
            }
            max = max / 2;
        }
        System.out.println(target);
    }
}
/*
    시간 초과로 인한 새로운 답안
    while( min < max ) {
        자르는 위치 내가 푼곳에선 target
        가장 높은 곳을 반으로 자르기
        int mid = (min + max) / 2;
        long sum = 0;

        for(int high : arr) {
            if( high - mid > 0) {
                sum += ( high - mid );
            }
        }

        이 생각을 못함...
        여기서 upper 와 lower 방식의 차이가 생긴다
        if(sum < M) {
            max = mid;
        } else {
            min = mid + 1;
        }
    }
    upper 방식을 사용하면 원하는 탐색값의 +1이 되기 때문에 실제로 만족하는 값은 upper 방식을 통해 반환 된 값의
    -1을 한 값이다.
    System.out.println(mid - 1);

*/
