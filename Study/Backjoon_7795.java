package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Backjoon_7795 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] A = new int[Integer.parseInt(st.nextToken())];
            int[] B = new int[Integer.parseInt(st.nextToken())];

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < A.length; j++) {
                A[j] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < B.length; j++) {
                B[j] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(B);
            // 결과적으로 나타나야할 값을 설정 한다.
            int count = 0;

            // 우선 A의 배열에 있는 값들을 하나씩 조정해주어야한다.
            for (int j = 0; j < A.length; j++) {
                // 그리고 B의 배열을 이분 탐색할 기준점을 잡는다.
                int low = 0;
                int high = B.length - 1;
                // 숫자들의 갯수를 더할 기준값을 잡는다
                int index = 0;

                // low가 high를 따라잡는순간 끝낸다.
                while (low <= high) {
                    // mid값을 정해주는 구문을 만든다
                    // 해당 mid의 값은 해당 숫자보다 이전의 수 갯수를 나타낸다
                    int mid = (low + high) / 2;
                    // 만약 A[j]의 수가 B[mid]보다 크다면 어느 지점까지 큰지 확인한다.
                    if (B[mid] < A[j]) {
                        // low지점을 mid에서 한칸 더 간 숫자로 해주어야한다.
                        low = mid + 1;
                        // 그리고 mid 전까지의 숫자를 저장시키기 위한 index에 집어넣는다.
                        index = mid + 1;
                    }
                    // 만약 A[j]의 수가 B[mid]보다 작다면 수를 저장하지 않고 high의 수만 올린다.
                    else {
                        high = mid - 1;
                    }
                }
                // 그리고 한개의 A[j]의 수가 끝날때 마다 count의 변수에 저장한다.
                count += index;
            }
            // 그렇게 한번의 묶음이 끝나면 그 수를 나타내고 다음걸로 넘어간다
            sb.append(count + "\n");
        }
        System.out.println(sb);
    }
}
