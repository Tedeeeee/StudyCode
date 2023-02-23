// 위에는 하는 과정에서 비교를 하기위해 사용한 것이다.
// 수의 범위를 알고 입출력만 하는것이 목표라면
// 굳이 index0 부터 입력하지 말고 counting으로 받자마자 바로 array배열의 인덱스로
// array배열 값을 증가시킨뒤, 누적합 스킵하고 array[0]부터 해당 인덱스의 값이 0이 될때까지
// 인덱스를 출력한다.
public class Counting_Sort2 {
    public static void main(String[] args) {

        // 수의 범위는 0~100으로 잡는다
        int[] arr = new int[101];

        // 수열의 크기를 50으로 잡아주고
        for (int i = 0; i < 50; i++) {
            arr[(int) (Math.random() * 101)]++;
        }

        for (int i = 0; i < arr.length; i++) {
            while (arr[i]-- > 0) {
                System.out.println(i + " ");
            }
        }
    }
}
