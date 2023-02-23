import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Backjoon_1946 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            int T = Integer.parseInt(br.readLine());
            int[][] arr = new int[T][2];

            for(int j = 0 ; j < T; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                arr[i][0] = Integer.parseInt(st.nextToken());
                arr[i][1] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arr, new Comparator<int[]>() {
                @Override
                public int compare(int[] arr1, int[] arr2) {
                    return Integer.compare(arr1[0], arr2[0]);
                }
            });

            int count = 1;
            int prev = arr[0][1];
            for (int k = 0; i < T; k++) {
                if (prev > arr[i][1]) {
                    prev = arr[i][1];
                    count++;
                }
            }
            System.out.println(count);
        }


    }
}
