public class Counting_Sort {
    public static void main (String[] args) {
        int[] array = new int[100];
        //수열의 원소 : 100개
        int[] counting = new int[31];
        // 수의 범위 : 0 ~ 30
        // 카운팅배열은 배열을 직접 만들어줘야한다
        int[] result = new int[100];
        // 정렬 될 배열

        //수를 임의로 31개를 정해준다.(0이 포함되기 때문에 31개!)
        for (int i = 0; i < array.length; i++) {
            array[i] = (int)(Math.random()*31); // 0 ~ 30
        }

        // 첫번째 과정
        // 위에 랜덤한 31개의 수만큼 반복횟수를 잡고 들어있는 수를 counting해준다
        for (int i = 0; i < array.length; i++) {
            counting[array[i]]++;
        }

        // 두번째 과정
        // counting으로 들어온 수들의 갯수만큼 반복문을 만든다.
        for (int i = 1; i < counting.length; i++) {
            // 누적합
            counting[i] += counting[i - 1];
        }

        // 세번째 과정
        // 뒤에서 부터 하는 이유는 그것이 안정적이여서 그렇다.
        for (int i = array.length - 1; i >= 0; i--) {
            /*
                i번째 원소를 인덱스로 하는 counting 배열을 1 감소 시킨뒤
                counting 배열의 값을 인덱스로 하여 result에 value 값을 저장한다.
             */

            int value = array[i];
            // 임의로 정한 31개의 수로 이루어진 배열을 value에 저장

            counting[value]--;
            // 누적합으로 이루어진 counting정렬의 값의 인덱스 안에 있는 수에서 1씩 빼기
            // 1씩 빠진 value는 index로 교환되면서 그 숫자가 어디서 부터 시작할지 나타낸다.

            result[counting[value]] = value;
            // counting[value]의 값을 result인덱스 값으로 전환 그 안에 value를 넣는다.
            // 마지막으로 보일 정렬배열에 value값을 넣는다

        }

        // 이제 우리가 진행한 배열을 차례대로 출력한다.
        // 초기 배열
        System.out.println("array[]");
        for (int i = 0; i < array.length; i++) {
            if (i % 10 == 0) System.out.println();
            System.out.print(array[i] + "\t");
        }
        System.out.println("\n\n");

        // counting배열
        System.out.println("counting[]");
        for (int i = 0; i < counting.length; i++) {
            if( i % 10 == 0) System.out.println();
            System.out.print(counting[i] + "\t");
        }
        System.out.println("\n\n");
        // 이건 직접 해보면 느끼겠지만 카운팅에 value값으로 나온것은 순서대로
        // start ~ end-1 까지 순서대로 나온다는 순이다

        // 정렬된 배열
        System.out.println("result[]");
        for (int i = 0; i < result.length; i++) {
            if( i % 10 == 0) System.out.println();
            System.out.print(result[i] + "\t");
        }
        System.out.println();
    }
}

