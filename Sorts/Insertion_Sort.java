package Sorts;

public class Insertion_Sort {
    public static void insertion_sort(int[] a) {
        insertion_sort(a, a.length);
        // 늘 그렇듯 배열 크기를 정해준다
    }

    private static void insertion_sort(int[] a, int size) {

        // i를 1부터 시작하는건 0은 비교할수가 없다.
        for (int i = 1; i < size; i++) {

            // 가장 먼저 찾을 수를 지정해주고
            int target = a[i];

            // 그 전의 수를 j로 잡는다.
            int j = i - 1;

            // j가 index0까지 가거나
            // 타겟이 이전 원소보다 크기 전 까지 반복
            while (j >= 0 && target < a[j]) {
                // 현재의 j값은 한칸 뒤로 미룬다.
                a[j + 1] = a[j];

                // j는 그 이후에도 앞의 수랑 비교해야한다
                j--;
            }
            // 만약 반복문에서 탈출을 했다는 의미는 앞의 원소보다 더 작았다라는 뜻이다.
            // 그렇다면 타겟원소는 j원소 뒤로 와야한다.
            a[j + 1] = target;
        }
    }
}
