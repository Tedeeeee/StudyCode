public class Selection_Sort {
    public static void selection_sort(int[] a) {
        // 보다시피 정렬의 크기를 정해주었다
        selection_sort(a, a.length);
    }

    private static void selection_sort(int[] a, int size) {

        // -1 을 하는 이유는 마지막은 어차피 최댓값이다
        for (int i = 0; i < size - 1; i++) {
            // 스타트 지점 = 최솟값과 비교할 수
            int min_index = i;

            // 우선 우리가 해야할일은 최솟값인 인덱스를 찾는것이다.
            for (int j = i + 1; j < size; j++) {
                if (a[j] < a[min_index]) {
                    min_index = j;
                    // 현재값보다 작다면 최솟값은 그 수로 대체된다.
                }
            }

            // 가장 작은 인덱스와 가장 작은 수를 교환한다
            int temp = a[i];
            a[i] = a[min_index];
            a[i] = temp;

        }
    }
}


