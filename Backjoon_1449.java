import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Backjoon_1449 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st1.nextToken());
        int len = Integer.parseInt(st1.nextToken());
        int[] arr = new int[N];

        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st2.nextToken());
        }

        Arrays.sort(arr);

        int count = 0;
        // 사이의 거리를 세는것이기 때문에 마지막 길이는 상관없다....
        for (int i = 0; i < arr.length-1; i++) {
            if (arr[i + 1] - arr[i] == (len - 1)) {
                count++;
                i++;
                // 만약 테이프-1 의 길이를 넘긴다면 칸을 옮겨서 계산
            } else {
                count++;
            }
        }
        System.out.println(count);
    }
}
