package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backjoon_13460 {
    public static int n, m;
    public static char[][] Graph;
    public static int[] Red = new int[2];
    public static int[] Blue = new int[2];
    public static int[] goal = new int[2];
    public static int answer = Integer.MAX_VALUE;
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        //char가 int형을 받는다는게 일단 신기했음..
        Graph = new char[n][m];
        for (int i = 0; i < n; i++) {
            // 배열화 해서 짚어넣기 그리고 검색할때 ''가 char, ""가 String임
            char[] ch = br.readLine().toCharArray();
            // 각 R,B,O지점을 각 배열에 지정해준다
            for (int j = 0; j < m; j++) {
                // 빨간색 공의 경우
                if (ch[j] == 'R') {
                    Red[0] = i;
                    Red[1] = j;
                    Graph[i][j] = '.';
                    // 파란색 공인 경우
                } else if (ch[j] == 'B') {
                    Blue[0] = i;
                    Blue[1] = j;
                    Graph[i][j] = '.';
                    // 골인 지점인 경우
                } else if (ch[j] == 'O') {
                    goal[0] = i;
                    goal[1] = j;
                    Graph[i][j] = '.';
                    // R도B,골인지점도 아니면 그냥 그거 대입
                } else {
                    Graph[i][j] = ch[j];
                }
            }
        }
        // 이와중에 이것도 메서드임
        solve();
    }
    public static void solve() {
        DFS(Red, Blue, 0);
        System.out.println(answer == Integer.MAX_VALUE? -1 : answer);
    }
    public static void DFS(int[] r,int[] b, int moveCnt) {
        // 여기서 -1로 지목해버리면 타입을 다양하게 사용하기 어려움
        if(moveCnt == 11) return;
        // 골지점에 도착 확인
        // 빨간색은 도착했는데 파란색이 도착을 못했다면
        if (r[0] == -100 && b[0] != -100) {
            // 3항 연산자는 return에도 강하다..
            // move가 현재 answer보다 큰 값이면 대채한다
            answer = answer > moveCnt ? moveCnt : answer;
            return;
        }
        // 파란색이 먼저 도착했다면
        if(b[0] == -100) return;

        for (int i = 0; i < 4; i++) {
            int[] curRed = {r[0], r[1]};
            int[] curBlue = {b[0], b[1]};
            // 해당 방향의 끝이 골인가?
            move(curRed, curBlue, i);
            // DFS함수로 다시 들어간다.
            // 한번 움직인것으로 올리고
            // 또 들어가면 해당 대입 지점이 골인지 확인
            DFS(curRed, curBlue, moveCnt + 1);
        }
    }

    // 역할 : 방문 확인
    public static void move(int[] r, int[] b, int arr) {
        int[] curRed = new int[]{r[0],r[1]};
        int[] curBlue = new int[]{b[0],b[1]};
        // 골지점에 방문 true나오면 골
        // Check로 인해 둘이 부딫히는 경우는 빠짐
        while (next(curRed, curBlue, arr)) {
            // 마지막으로 막힌곳이 골지점인지 확인
            // 골 지점이면 대입
            if (goalCheck(curRed)) {
                curRed[0] = -100;
                curRed[1] = -100;
            }
            if (goalCheck(curBlue)) {
                curBlue[0] = -100;
                curBlue[1] = -100;
            }
        }
        // while(골지점) 아니면 r1,2 b1,2에 대입
        // 이걸 어디서 쓰는거지....
        r[0] = curRed[0];
        r[1] = curRed[1];
        b[0] = curBlue[0];
        b[1] = curBlue[1];
    }

    // 골지점 goalCheck로 들어온다
    public static boolean goalCheck(int[] x) {
        // 여기 골지점임
        if (goal[0] == x[0] && goal[1] == x[1]) {
            return true;
        } else {
            return false;
        }
    }
    // 공이 벽에 부딫혔거나 범위밖으로 나갔는지 확인
    public static boolean checkRange(int x, int y) {
        // 범위를 벗어나면 false
        if (x < 0 || y < 0 || x >= n || y >= m) {
            return false;
            // 벽이면 false
        } else if (Graph[x][y] == '#') {
            return false;
            // 둘다 아니면 true
        } else {
            return true;
        }
    }

    // 움직일수 있다면 빨간구슬과 파란구슬이 가는 방향
    // 96번째 줄에 while조건을 판독한다 짧게 말해서 서로 부딫히는지 안부딫히는지 확인.
    public static boolean next(int[] Red, int[] Blue, int arr) {
        // 다음으로 간 구슬의 위치
        int nx1, ny1, nx2, ny2;
        // 빨간 구슬
        nx1 = Red[0] + dx[arr];
        ny1 = Red[1] + dy[arr];
        // 파란 구슬
        nx2 = Blue[0] + dx[arr];
        ny2 = Blue[1] + dy[arr];

        // Graph범위를 표현하는 것은 같은 행위 메서드화
        // 둘다 움직일수 있을때 움직이 하나 카운트 된다.
        if (checkRange(nx1, ny1) && checkRange(nx2, ny2)) {
            Red[0] = nx1;
            Red[1] = ny1;
            Blue[0] = nx2;
            Blue[1] = ny2;
            return true;
        // 빨간 구슬은 움직일 수 없고 파란 구슬은 움직일수 있을때
        } else if (!checkRange(nx1, ny1) && checkRange(nx2, ny2)) {
            // 만약 움직인 파란구슬이 가만히 있는 빨간구슬과 만난다면
            if (Red[0] == nx2 && Red[1] == ny2) {
                return false;
            } else {
                // 파란 구슬이 움직일수 있고 빨간 구슬과 만나지 않는다면
                // 다음 움직임을 대입해준다
                Blue[0] = nx2;
                Blue[1] = ny2;
                return true;
            }
        // 빨간 구슬은 움직일 수 있고, 파란구슬은 움직일수 없을때
        } else if (checkRange(nx1, ny1) && !checkRange(nx2, ny2)) {
            // 움직인 빨간 구슬의 위치가 파란구슬이 있는곳이라면
            if (Blue[0] == nx1 && Blue[1] == ny1) {
                return false;
            } else {
                Red[0] = nx1;
                Red[1] = ny1;
                return true;
            }
        }
        // 어느곳에도 해당되지않는다.
        return false;
    }
}