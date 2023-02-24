package Sorts;

public class Shell_Sort {
    private final static int[] gap =
         // 사용자들이 정해놓은 관습적 gap
        { 1, 4, 10, 23, 57, 132, 301, 701, 1750, 3937,
        8858, 19930, 44842, 100894, 227011, 510774,
        1149241, 2585792, 5818032, 13090572, 29453787,
        66271020, 149109795, 335497038, 754868335, 1698453753};
    public static void shell_sort(int[] a) {

        // shell정렬의 배열크기를 정해준다
        shell_sort(a, a.length);
    }

    // gap 차이를 얼마나 둘 지 정한다.
    private static int getGap(int length) {
        int index = 0;

        // 최소한 부분 배열의 원소가 2개씩은 비교되어야 한다.
        int len = (int)(length/ 2.25);

        // gap[index]로 얼마나 나눌지 선택
        while (gap[index] < len) {
            index++;
        }
        return index;
    }
    private static void shell_sort(int[] a, int size) {

        // 배열 크기를 입력하면 getGap메소드로 들어가 index출력
        int gapIndex = getGap(size);

        while (gapIndex >= 0) {

            // 출력한 index로 gap배열에 넣어 gap찾기
            int step = gap[gapIndex--];


            // 우리는 gap만큼 원소를 대각선으로 나열하는데
            // 첫번째로 gap만큼 나열되는 수를 비교할 앞의 수가 없다.
            // 그래서 다음 계단 시작점인 step부터 찾기 시작한다
            for (int i = step; i < size; i++) {

                // step부터 시작하고 다음 계단 시작점 전의 숫자까지 포함하여 찾는 범위.
                for (int j = i; j >= step && a[j] < a[j - step]; j -= step) {

                    // j와 그 왼쪽 계단 같은 층 j-step 과 바꿔준다
                    swap(a, j , j - step);
                }
            }
        }
    }

    // swap을 통해 바꿔줄 메소드
    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}