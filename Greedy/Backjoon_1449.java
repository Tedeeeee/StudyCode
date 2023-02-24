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

        double range = arr[0] - 0.5 + len;
        int count = 1;
        for (int i = 0; i < arr.length; i++) {
            if (range <= (double)(arr[i] - 0.5)) {
                count++;
                range = arr[i] - 0.5 + len;
            }
        }
        System.out.println(count);
    }
}
