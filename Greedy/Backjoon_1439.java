import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backjoon_1439 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S =br.readLine();
        char[] ch = S.toCharArray();
        int c = ch.length;

        int[] count = new int[2];
        int prev = ch[0];
        for (int i = 1; i < c; i++) {
            if (prev != ch[i]) {
                count[prev - '0']++;
                prev = ch[i];
            }
        }
        count[prev - '0']++;
        System.out.println(Math.min(count[0],count[1]));
    }
}
// 들여오는 숫자를 문자로 구분하여 넣어주고
// 그 문자를 배열로 바꾸었고 연속되는 숫자라면 그냥 쭈욱 지나가다가 숫자가 바뀌면 카운트를한다.
// 그리고 다시 또 바뀌면 카운트를 세는데 카운트가 2개가 필요하다. 그래서 두칸을 만들어주었고
// 각각에 카운트 횟수를 넣어주고 그중 낮은것을 출력한다.
// 더욱 간단한 식을 발견했다. 매우 충격...

//String S = br.readLine();
//StringTokenizer st1 = new StringTokenizer(S, "0");
//StringTokenizer st2 = new StringTokenizer(S, "1");
//System.out.println(Math.min(st1.countToken(),st2countToken()))

// 하하..
