package Data_structure.Interface_form.Sorts;

public class Bubble_Sort {
    public static void bubble_sort(int[] a) {
        // 배열의 크기를 정해주기
        bubble_sort(a, a.length);
    }

    private static void bubble_sort(int[] a, int size) {

        // 라운드는 배열의 크기 -1 만큼 진행
        for (int i = 1; i < size; i++) {

            // 이것의 존재 이유는 내려가면 알수 있다.
            boolean swapped = false;

            // 각 라운드의 비교 횟수는 배열의 크기에서 현 라운드를 뺀만큼 비교
            for (int j = 0; j < size - i; j++) {

                // 지금 원소가 앞에 있는 원소보다 작으면 바꾼다
                if (a[j] > a[j + 1]) {
                    swap(a , j , j+1);

                    // true로 바꿔서 swap이 됐다. 라는 표현이다
                    swapped = true;
                }
            }

            // 이게 뭔가 의아하다면 당신은 정상...
            // 이건 시간 복잡도 때문에 적었는데 swap이 안되면
            // 시간 복잡도가 O(n)이다
            // 이게 있으면 최선 O(n)  최악 O(n2)
            // 이게 없으면 최선 O(n2) 최악 O(n2)
            if (swapped == false) {
                break;
            }
        }
    }

    // 각자의 자리를 바꿔주는(swap) 메소드가 필요하다.
    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
