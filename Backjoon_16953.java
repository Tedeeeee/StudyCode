import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backjoon_16953 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        int count = 1;
        while (b > a) {
            if (b % 2 == 0) {
                b = b / 2;
                count++;
            } else if (b % 10 == 1) {
                b = b / 10;
                count++;
            } else{
                break;
            }
        }
        if (a != b) {
            System.out.println(-1);
        } else {
            System.out.println(count);
        }
    }
}
// 규칙상 끝자리는 2 or 1이다. 2라면 2를 곱한것이고 1이라면 1을 붙였을것이다.
// 그러면 곱을 하였을 경우는 나머지가 0인것으로 판단
// 1인 경우는 1을 붙인것으로 판단
// 처음에는 else if를 안두고 했는데 그 두개의 경우 말고 다른 경우가 생길수있었다
// 그러면 아예 빠져나오게 만들어서 식을 아예 포기하고 -1을 출력시킨다.