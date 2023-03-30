package Study;

public class Tim_Sort {

    private static final int THRESHOLD = 32;

    /*
     * 최소 길이의 run 크기, 즉 minRun 을 구하기 위한 메소드
     *
     * @param runSize minRun 을 구하고자 하는 초기 배열의 길이
     */
    public static int minRunLength(int runSize) {

        int r = 0; // 홀 수가 발생할 때 2^x 가 초과되지 않도록 하는 여분의 수

        /*
            (runSize & 1) 을 하면, runSize 의 가장 오른쪽 비트가 1일 경우에는
            홀수이므로 1이 반환 될 것이고, r = r | (runSize & 1) 에서 r은 1로 될 것이다.
            한번 r이 1로 되면 OR 연산자의 특성상 이 값은 바뀌지 않는다
        */
        while (runSize >= THRESHOLD) {
            r = r | (runSize & 1);
            // runSize의 비트열을 오른쪽으로 1만큼 이동한다
            runSize >>= 1;
        }
        return runSize + r;
    }

    // run을 스택에 담아 둘 내부 정적 클래스
    private static class IntMergeStack {

        private int[] array;
        private int[] runBase;
        private int[] runLength;
        private int stackSize = 0; // run 스택의 원소 갯수를 가리킬 변수

        public IntMergeStack(int[] a) {
            this.array = a;
            int len = a.length;

            // 최악의 경우를 계산해도 40개의 스택을 넘어서면
            // 21억개를 넘기기 때문에 배열의 최대 생성갯수가 넘어간다.
            // 그래서 아무리 많아도 40개면 충분하다
            runBase = new int[40];
            runLength = new int[40];
        }

        // pushRun의 메소드에서 우리가 들어가야 할 것은 각 run의 시작점과 길이이다
        public void pushRun(int runBase, int runLen) {
            this.runBase[stackSize] = runBase;
            this.runLength[stackSize] = runLen;
            stackSize++;
        }

        public void mergeForce() {

            // 나머지 모든 run을 병합한다.
            while (stackSize > 1) {

                // 모든 run을 병합 할 것이기 때문에 2번 조건인 runLen[i - 2] > runLen[i - 1]만 체크해주면 된다.
                if (stackSize > 2 && runLength[stackSize - 3] < runLength[stackSize - 1]) {
                    merge(stackSize - 3);
                } else {
                    merge(stackSize - 2);
                }
            }
        }

        public void merge() {
            /*
                기본 적인 메커니즘

                1. runLen[i - 3] > runLen[i - 2] + runLen[i - 1]
                2. runLen[i - 2] > runLen[i - 1]

                스택에 요소가 5개 있을 때 기본 pivot은 상위 3개 요소 중 가눙데를 지정
                EX) A, B, C, D, E 에서 A가 가장 깊은곳에 있다고 볼때 가장 위엔 E가 있고 E에서 3칸 내려온 C, D, E중 D를 pivoit으로 정한다

                runLen[pivot - 1] = C, runLen[pivot] = D, runLen[pivot + 1] = E를 스택의 상위 세 요소로 잡자

                1. C <= D + E 및 C < E일 경우 C 과 D 병합. 즉, pivot - 1 과 pivot 병합
			 	2. C <= D + E 및 C >= E일 경우, D와 E 병합. 즉, pivot 과 pivot + 1 병합
			 	3. C > D + E 및 D <= E일 경우,  D와 E 병합. 즉, pivot 과 pivot + 1 병합
			 	4. C > D + E 및 D > E일 경우 루프 종료
			 	즉 피라미드 형으로 생겨야 한다는것이다.

			 	while (stackSize > 1) {

                    // 1번 조건 C > B + A에 위배 될 경우 (==C <= B + A 일 경우)
                    if (stackSize > 2 && runLength[stackSize - 3] <= runLength[stackSize - 2] + runLength[stackSize - 1]) {
                        // C < A라면 (B, C)를 병합
                        if (runLength[stackSize - 3] < runLength[stackSize - 1]) {
                            merge(stackSize - 3);
                        } else { // A, B 병합
                            merge(stackSize - 2);
                        }
                    }

                    // 2번 조건 B > A에 위배 될 경우 (== B <= A 일 경우)
                    else if(runLength[stackSize - 2] <= runLength[stackSize - 1]) {
                        merge(stackSize - 2); // A, B 병합
                    }
                    // 그 외의 경우는 위 두 조건을 만족한다는 의미이므로 종료
                    else {
                        break;
                    }
                }

                위 방식을 그대로 조건식으로 구현할 경우 stack 규칙이 깨짐

                예를 들어 120, 80, 25 ,20이 스택에 있고, 30이 스택으로 주어졌다
                즉, stack[] = {120, 80, 25, 20, 30}

                첫번째 반복문에서 첫 번째 merge() 시
                첫 번째 조건문의 runLen[pivot - 1] <= runLen[pivot] + runLen[pivot + 1]
                즉, 25 <= 20 + 30 을 만족시키며, 해당 하위 조건문 runLen[pivot - 1] < runLrn[pivot + 1] 인
                25 < 30 더 만족시키기 때문에 25와 20이 병합된다.

                그러면 결과는, {120, 80, 45, 30} 이 되는것이다.

                다음 반복문으로 넘어가게 된다면 다음과 같다

                80 > 45 + 30 이기 때문에 첫 번째 조건문을 만족하지 못하며,
                그 다음 조건문인 runLen[pivot] <= runLen[pivot + 1] 또한 45 > 30 이라

                stack의 유지의 두 조건을 모두 만족한다는 의미로 반복문이 종료된다.

                하지만, 전체 남아있는 스택을 볼 때, 120 <= 80 + 45 를 만족하는게 있음에도 merge되지 않기때문에 stack조건이 깨진다는 말이다.

                즉, 상위 뿐 아니라 그 다음 아래의 3개의 요소 감안을 해야한다는 말이다.
            */

            while (stackSize > 1) {
                /*
                    1번 조건 C > B + A 에 위배 될 경우 (==C <= B + A 일 경우)
                    혹은, D > C + B 에 위배 될 경우 (== D <= C + B 일 경우)
                */
                if (stackSize > 2 && runLength[stackSize - 3] <= runLength[stackSize - 2] + runLength[stackSize - 1]
                        || stackSize > 3 && runLength[stackSize - 4] <= runLength[stackSize - 3] + runLength[stackSize - 2]) {
                    if (runLength[stackSize - 3] < runLength[stackSize - 1]) {
                        merge(stackSize - 3);
                    } else {
                        merge(stackSize - 2);
                    }
                } else if (runLength[stackSize - 2] <= runLength[stackSize - 1]) {  // 2번 조건 B > A 에 위배 될 경우 ( == B <= A 일 경우 )
                    merge(stackSize - 2);
                } else {
                    break;
                }
            }
        }

        /*
            이제 가장 중요한 merge를 알아보자

            @param idx 는 병합되는 두 서브리스트(run) 중 낮은 인덱스
         */
        private void merge(int idx) {

            int start1 = runBase[idx];
            int length1 = runLength[idx];
            int start2 = runBase[idx + 1];
            int length2 = runLength[idx + 1];

            // idx 와 idx + 1 번째 run을 병합
            runLength[idx] = length1 + length2;

            /*
                상위 3개 (A, B, C)에서 A, B가 병합 할 경우 C를 당겨온다.

                ex)
                stack [[A], [B], [C]]

                runLen[idx] = length1 + length2
                stack[[A + B], [B], [C]]

                C를 B위치로 당겨온다
                stack[[A + B], [C], [C]]

                이때 마지막 [C](stack[i - 1])은 어차피 더 이상 참조 될 일 없음
            */
            if (idx == (stackSize - 3)) {
                runBase[idx + 1] = runBase[idx + 2];
                runLength[idx + 1] = runLength[idx + 2];
            }
            stackSize--;
            /*

 			  gallopRight ->								<- gallopLeft
			  				RUN A					   RUN B
			  ______________________________ ______________________________
			  [   |   |   ||   |   |   |MAX] [MIN|   |   |   |   ||   |   ]
			  ------------------------------ ------------------------------
			  |___________| |______________| |___________________| |______|
			  less than MIN       RUN A'             RUN B'        greater than MAX

                            |____________________________________|
						            merge RUN A' and RUN B'
	    	 */

            // start point (RUN B의 시작점보다 작으면서 RUN A에서 merge를 시작할 위치)
            int lo = gallopRight(array[start2], array, start1, length1);

            /*
                만약 RUN A의 길이와 merge를 시작할 지점이 같을 경우
                이미 정렬되어있는 상태로 정렬 할 필요 없음
            */
            if (length1 == lo) {
                return;
            }
            start1 += lo;
            length1 -= lo;

            // end point (RUN A의 끝 점보다 크면서 RUN B에서 merge가 끝나는 위치)
        }
    }
}

