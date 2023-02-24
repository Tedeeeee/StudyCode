package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backjoon_1783 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 가로 1칸 밖에 없으면 움직일 곳이 없음...
        if( M == 1){
            System.out.println(1);

        // 가로 2칸이 있으면 조건 중 2번과 3번만 가능하다
        // 근데 최대 5칸 이상은 못 간다
        // 경우의 수를 적다보니 12,34,56 짝이라서 짝을 지어주었다.
        } else if (M == 2) {
            System.out.println(Math.min((M+1/2), 4));

        // 3부턴 자유로워 진다 오히려 단순해지는 계산
        } else if (M >= 3) {

            // 역설적이긴 한데
            // 그래도 7 밑으로는 움직임의 한계가 있다
            // 당연하게도 4칸이 최대이고 그전까진 칸수만큼 가더라
            if (M < 7) {
                System.out.println(Math.min(M,4));

            // 4가지를 다 사용해서 움직여야 하는 범위이다.
            // 오른쪽으로 가장 멀리가는 2개를 한번씩만 사용하고
            // start 지점 자체를 5로 잡는다.
            // 그리고 한칸씩 가는것을 최대한 많이 사용한다
            // 그러면 식이 M - 5 + 3 으로 미리 방문했던 3칸을 합해준다
            // 그걸 줄이면 M - 2가 된다.
            } else{
                System.out.println(M-2);
            }
        }
    }
}
// 나는 5칸 이후의 수를 어떻게 할지 고민이 많이 됐다.
// 그러던중 min을 알게되었고 그것을 도입해보았다 개굴...
