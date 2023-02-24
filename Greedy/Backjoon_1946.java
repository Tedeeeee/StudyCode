import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Backjoon_1946 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());

            ArrayList<Grade> list = new ArrayList<Grade>();
            for (int j = 0; j < N; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                list.add(new Grade(a, b));
            }

            Collections.sort(list);

            int count = 0;
            int prev = list.get(0).b;
            for(int k = 1; k < N; k++){
                if(prev > list.get(i).b){
                    prev = list.get(i).b;
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

    Grade(int a, int b){
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
// 설명은 나중에
