import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Backjoon_1946 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

<<<<<<< HEAD
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());

            ArrayList<Grade> list = new ArrayList<Grade>();
            for (int j = 0; j < N; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
=======
        int N = Integer.parseInt(br.readLine());

        for(int i =0; i < N; i++){
            int T = Integer.parseInt(br.readLine());

            ArrayList<Grade> list =new ArrayList<>();

            for (int j = 0; j < T; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
>>>>>>> 017f0674bc0e245e5392b15c5a5e9cce78fba427
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                list.add(new Grade(a, b));
            }
<<<<<<< HEAD

=======
>>>>>>> 017f0674bc0e245e5392b15c5a5e9cce78fba427
            Collections.sort(list);

            int count = 1;
            int prev = list.get(0).b;
<<<<<<< HEAD
            for (int k = 1; k < N; k++) {
                if (prev > list.get(k).b) {
                    prev = list.get(k).b;
=======
            for (int k = 1; k < T; k++) {
                if (prev > list.get(i).b) {
                    prev = list.get(i).b;
>>>>>>> 017f0674bc0e245e5392b15c5a5e9cce78fba427
                    count++;
                }
            }
            System.out.println(count);
        }
    }
}

class Grade implements Comparable<Grade> {
    int a;
    int b;

    Grade(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int compareTo(Grade o) {
        if (this.a > o.a) {
            return 1;
        } else {
            return -1;
        }
    }
}
<<<<<<< HEAD
// 비교 할 수 있는 인자를 두개로 두는데 Comparable로 진행하여 0번째 인덱스(자신)과 1번째 인덱스를 비교한다
// Grade 클래스를 만들어 interface Comparable의 메서드 compareTo를 사용하여 비교를 하였고
// main클래스에서 list를 사용하여 불러왔다. 제네릭은 역시 grade로 받았고 나머지는 식의 계산이다
=======

>>>>>>> 017f0674bc0e245e5392b15c5a5e9cce78fba427
