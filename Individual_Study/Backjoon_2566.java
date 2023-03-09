package Individual_Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backjoon_2566 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] arr = new int[10][10];

        int x =0;
        int y =0;
        int num = Integer.MIN_VALUE;
        StringTokenizer st;
        for (int i = 1; i <= 9; i++) {
            st = new StringTokenizer(br.readLine()," ");
            for (int j = 1; j <= 9; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());

                if (num <= arr[i][j]) {
                    num = arr[i][j];
                    x = i;
                    y = j;
                }
            }
        }
        System.out.print(num+"\n"+x+" "+y);
    }
}
