package Study;

public class Tim_Sort {
    private static final int THRESHOLD = 32;

    /*
        최소 길이의 run 크기, 즉 minRun을 구하기 위한 메소드

        @param runSize minRun 을 구하고자 하는 초기 배열의 길이
    */
    public static int minRunLength(int runSize) {

        int r = 0; // 홀 수가 발생할 때 2^x가 초과하지 않고록 하는 여분의 수

        /*
            (runSize & 1)을 하면, runSize의 가장 오른쪽 비트가 1일 경우에는 홀수 이므로 반환 될것이고,
            r = r | (runSize & 1)에서 r은 1로 될 것이다.
            한 번 r이 1로 되면 OR 연산자의 특성상 이 값은 바뀌지 않는다.
        */
        while (runSize >= THRESHOLD) {
            r |= (runSize & 1);
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

            runBase = new int[40];
            runLength = new int[40];
        }

        public void pushRun(int runBase, int runLen) {
            this.runBase[stackSize] = runBase;
            this.runLength[stackSize] = runLen;
            stackSize++;
        }

        public void mergeForce() {

            // 남은 모든 run을 병합한다.
            while (stackSize > 1) {

                // 모든 run을 병합 할 것이기 때문에 2번 조건인 runLen[i - 2] > runLen[i - 4]만 체크해주면서 병합한다.
                if (stackSize > 2 && runLength[stackSize - 3] < runLength[stackSize - 1]) {
                    merge(stackSize - 3);
                } else {
                    merge(stackSize - 2);
                }
            }
        }

        public void merge() {

            /*
                기본 메커니즘

                1. runLen[i - 3] > runLen[i - 2] + runLen[i - 1]
                2. runLen[i - 2] > runLen[i - 1]

                스택에 요소가 5개 있을때 기본 pivot은 상위 3개 요소 중 가운데를 지정
                ex) A, B, C, D, E  ( A가 가장 낮은것 )
                pivot = D ( == stackSize - 2 )

                runLen[pivot - 1] = C, runLen[pivot] = D, runLen[pivot + 1] = E 를 스택의 상위 세 요소로 한다.

                1. C <= D + E 및 C < E일 경우 C 와 D 병합. 즉, pivot - 1 과 pivot 병합
                2. C <= D + E 및 C >= E일 경우, D와 E 병합. 즉, pivot 과 pivot + 1 병합
                3. C > D + E 및 D <= E일 경우, D와 E 병합. 즉, pivot 과 pivot + 1 병합
                4. C > D + E 및 D > E 일 경우 루프 종료

                while(stackSize > 1) {

                    // 1번 조건 C > B + A 에 위배 될 경우 ( == C <= B + A 일 경우 )
                    id ( stackSize > 2 && runLength[stackSize - 3] <= runLength[stackSize - 2] + runLength[stackSize - 1] ) {
                        // C < A가 크다면 (B, C)를 병합
                        if ( runLength[stackSize - 2] < runLength[stackSize - 1] ) {
                            merge(stackSize - 3);
                        } else {
                            merge(stackSize - 2);
                        }
                    }
                    // 2번 조건 B > A 에 위배 될 경우 ( == B <= A 일 경우 )
                    else if ( runLength[stackSize - 2] <= runLength[stackSize - 1] ) {
                        merge(stackSize - 2); // A, B 병합
                    }
                    // 그 외의 경우는 위 두 조건을 만족한다는 의미이므로 종료
                    else {
                        break;
                    }
                }

                하지만 위의 방식을 그대로 사용한다면 stack의 규칙이 깨진다

                예를 들어 stack[] = {120, 80, 25, 20} 의 배열 안에 30이 추가 되었다고 생각해보자

                첫 번째 반복문에서 첫 번째 merge() 시
                첫 번째 조건문의 runLen[pivot - 1] <= runLen[pivot] + runLen[pivot + 1]
                즉, 25 <= (20 + 30) 을 만족시키며, 해당 하위 조건문 runLen[pivot - 1] < runLen[pivot + 1] 인
                25 < 30 또한 만족시키면서 25와 20이 병합된다.

                그러면 결과는 {120, 80, 45, 30} 이 되는것이다

                하지만 다음 반복문으로 넘어갔을때 오류가 생긴다.
                80 <= 45 + 30 은 첫번째 조건문을 만족하지 못하고 다음 조건문인 45 < 30 또한 만족을 못 시키므로 반복문이 바로 종료될것이다.
                하지만 남은 스택인 120 <= 80 + 45 가 첫번째 조건문에 만족을 하기 때문에 종료가 되어선 안되는것이다.

                즉, 우린 상위 3개만 검사하는것이 아닌 남은것들또한 검사를 해야하는 것이다.

            */

            while (stackSize > 1) {
                /*
                    1번 조건 C > B + A 에 위배 될 경우 ( == C <= B + A 일 경우 )
                    혹은, D > C + B 에 위배 될 경우 ( == D <= C + B 일 경우 )
                */
                if (stackSize > 2 && runLength[stackSize - 3] <= runLength[stackSize - 2] + runLength[stackSize - 1]
                        || stackSize > 3 && runLength[stackSize - 4] <= runLength[stackSize - 3] + runLength[stackSize - 2]) {

                    if (runLength[stackSize - 3] < runLength[stackSize - 1]) {
                        merge(stackSize - 3);
                    } else {
                        merge(stackSize - 2);
                    }
                }
                // 2번 조건 B > A 에 위배 될 경우 ( == B <= A 일 경우)
                else if (runLength[stackSize - 2] <= runLength[stackSize - 1]) {
                    merge(stackSize - 2);
                }
                // 그 외의 경우는 위 두 조건을 만족한다는 의미이므로 종료!
                else {
                    break;
                }
            }
        }

        /*
            run[idx] 와 run[idx + 1] 이 병합 된다

            @param idx 병합되는 두 서브리스트(run) 중 낮은 인덱스
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
                stack[[A], [B], [C]]

                runLen[idx] = length1 + length2
                stack[[A + B], [B], [C]]

                C를 B위치로 당겨온다.
                stack[[A + B], [C], [C]

                이때 마지막 [C](stack[i - 1])는 어차피 더이상 참조될 일 없음
            */
            if (idx == (stackSize - 3)) {
                runBase[idx + 1] = runBase[idx + 2];
                runLength[idx + 1] = runLength[idx + 2];
            }
            stackSize++;

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

            // start point (RUN B의 시작점 보다 작으면서 RUN A 에서 merge를 시작할 위치)
            int low = gallopRight(array[start2], array, start1, length1);

            /*
                만약 RUN A의 길이와 merge를 시작할 지점이 같을 경우
                이미 정렬되어 있는 상태로 정렬 할 필요가 없음
            */
            if (length1 == low) {
                return;
            }
            start1 += low;
            length1 -= low;

            // end point (RUN A의 끝보다 크면서 RUN B에서 merge가 끝나는 위치)
            int high = gallopLeft(array[start1 + length1 - 1], array, start2, length2);

            if (high == 0) {
                return;
            }
            length2 = high;
            if (length1 <= length2) {
                mergeLow(start1, length1, start2, length2);
            } else {
                mergeHigh(start1, length1, start2, length2);
            }
        }

        /*
            gallop_right() 함수를 수행하여 RUN B 의 첫번째 원소보다
            큰 원소들이 첫번째 출현하는 위치를 RUN A에서 찾는다.

            @param key    run B의 key
            @param array  배열
            @param base   run A 의 시작 지점
            @param lenOfA run A 의 길이
            @return 제외되어야 할 부분의 위치 다음 인덱스를 반환
        */
        private int gallopRight(int key, int[] array, int base, int lenOfA) {

            int low = 0;  // 이전 탐색(gallop) 지점
            int high = 1; // 현재 탐색(gallop) 지점

            /*
                RUN B의 시작지점 값(key)이 RUN A의 시작지점 값보다 작을 경우
                제외 될 원소는 없으므로 0 리턴
            */
            if (key < array[base]) {
                return 0;
            } else {
                /*

				  gallopRight ->
				  				RUN A		      key	   RUN B
				  ______________________________ ______________________________
				  [   |   |   ||   |   |   |MAX] [MIN|   |   |   |   ||   |   ]
				  ------------------------------ ------------------------------
				  |___________| |______________| |___________________| |______|
				  less than MIN       RUN A'             RUN B'        greater than MAX

		  		                |____________________________________|
	 									merge RUN A' and RUN B'
		    	 */

                int maxLen = lenOfA; // galloping을 하여 가질 수 있는 최대 한계값
                while (high < maxLen && array[base + high] <= key) {
                    low = high;
                    high = (high << 1) + 1;

                    if (high <= 0) { // overflow 발생시 run A의 끝 점으로 초기화
                        high = maxLen;
                    }
                }

                if (high > maxLen) {
                    high = maxLen;
                }
            }

            low++;

                // binarySearch (Upper Bound)
            while (low < high) {
                int mid = low + ((high - low) >>> 1);

                if (key < array[base + mid]) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }
            return high;
        }

        /*
            gallop_left() 함수를 수행하여 RUN A의 첫번째 원소 (오른쪽 끝)보다
            큰 원소들이 첫번째로 출현하는 위치를 RUN B 에서 찾는다

            @param key     // run A의 key
            @param array   // 배열
            @param base    // run B의 시작 지점
            @param lenOfB  // run B의 길이
            @return 제외되어야 할 부분의 위치 다음 인덱스 반환
        */
        private int gallopLeft(int key, int[] array, int base, int lenOfB) {
            int low = 0;
            int high = 1;

            /*
                key가 B의 탐색의 첫 위치(오른쪽 끝)보다 크면 제외되어야 할 부븐은 없으므로
                run B의 길이를 반환
            */
            if (key > array[base + lenOfB - 1]) {
                return lenOfB;
            } else {
                /*

                 <- gallopLeft
                 RUN A					   RUN B
                 ______________________________ ______________________________
                 [   |   |   ||   |   |   |MAX] [MIN|   |   |   |   ||   |   ]
                 ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣ ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣
                 |___________| |______________| |___________________| |______|
                 less than MIN       RUN A'             RUN B'        greater than MAX

                 |____________________________________|
                 merge RUN A' and RUN B'
                 */

                // run B의 오른쪽부터 시작해야 하므로 뒤쪽 시작점을 가리키는 변수
                int startPointOfRun = base + lenOfB - 1;

                int maxLen = lenOfB; // galloping을 하여 가질 수 있는 최대 한계값

                while (high < maxLen && key <= array[startPointOfRun - high]) {
                    low = high;
                    high = (high << 1) + 1;

                    // overflow가 발생시 runB의 끝점(왼쪽 끝)으로 초기화
                    if (high <= 0) {
                        high = maxLen;
                    }
                }
                if (high > maxLen) {
                    high = maxLen;
                }

                /*
                    뒤에서부터 탐색했기 때문에 실제 가리키는 인덱스는 low > high 이므로
                    이분 탐색을 하기 위해 run B에 대해 가리키는 지점을 서로 바꿔준다.
                */

                int temp = low;
                low = lenOfB - 1 - high;
                high = lenOfB - 1 - temp;
            }

            low++;

            // binary search (Lower Bound)
            while (low < high) {
                int mid = low + ((high - low) >>> 1);

                if (key <= array[base + mid]) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }
            return high;
        }

        /*
            상대적으로 낮은 인덱스에 위치한 run을 기준으로 복사하여
            실제 배열 원소를 변합하는 메소드

            @param start1   RUN A 에서의 병합 시작 지점
            @param length1  RUN A 에서의 병합해야 할 길이(갯수)
            @param start2   RUN B 에서의 병합 시작 지점
            @param lenght2  RUN B 에서의 병합해야 할 길이(갯수)
        */
        private void mergeLow(int start1, int length1, int start2, int length2) {
            // RUN A 를 담을 임시 복사 배열
            int[] temp = new int[length1];
            System.arraycopy(array, start1, temp, 0, length1); // RUN A를 temp 배열에 복사

            int insertIdx = start1; // 재배치되는 위치
            int runBIdx = start2;   // RUN B의 탐색 위치
            int tempIdx = 0;        // 복사한 RUN A의 탐색 위치

            int leftRemain = length1;    // 배치해야 할 RUN A의 원소 갯수
            int rightRemain = length2;   // 배치해야 할 RUN B의 원소 갯수

            // 두 원소 중 먼저 소진 할 때 까지 반복
            while (leftRemain != 0 && rightRemain != 0) {

                // RUN B < RUN A 라면 RUN B 원소 삽입
                if (array[runBIdx] < temp[tempIdx]) {
                    array[insertIdx++] = array[runBIdx++];
                    rightRemain++;
                } else {
                    array[insertIdx++] = temp[tempIdx++];
                    leftRemain++;
                }
            }

            // 왼쪽 부분 리스트가 남아있을 경우
            if (leftRemain != 0) {
                System.arraycopy(temp, tempIdx, array, insertIdx, leftRemain);
            } else { // 오른쪽 부분 리스트가 남아있을 경우
                System.arraycopy(array, runBIdx, array, insertIdx, rightRemain);
            }
        }

        /*
            상대적으로 높은 인덱스에 위치한 run을 기준으로 복사하여
            실제 배열 원소를 병합하는 메소드

            @param start1   RUN A 에서의 병합 시작 지점
            @param length1  RUN A 에서의 병합해야 할 길이(갯수)
            @param start2   RUN B 에서의 병합 시작 지점
            @param length2  RUN B 에서의 병합해야 할 길이(갯수)
        */
        private void mergeHigh(int start1, int length1, int start2, int length2) {
            // RUN B 를 담을 임시 복사 배열
            int[] temp = new int[length2];
            System.arraycopy(array, start2, temp, 0, length2);

            int insertIdx = start2 + length2 - 1; // 재배치되는 위치
            int runIdx = start1 + length1 - 1;    // run A의 탐색 위치
            int tempIdx = length2 - 1;            // 복사한 run B의 탐색 위치

            int leftRemain = length1;             // 배치해야 할 RUN A의 원소 갯수
            int rightRemain = length2;            // 배치해야 할 RUN B의 원소 갯수

            while (leftRemain != 0 && rightRemain != 0) {

                // RUN A' > RUN B' 라면 RUN A' 원소 삽입 (내림차순이기 때문)
                if (array[runIdx] > temp[tempIdx]) {
                    array[insertIdx--] = temp[tempIdx--];
                    leftRemain--;
                } else {
                    array[insertIdx--] = temp[tempIdx--];
                    rightRemain--;
                }
            }

            // 오른쪽 부분 리스트가 남아있을 경우
            if (rightRemain != 0) {
                System.arraycopy(temp, 0, array, start1, rightRemain);
            } else {
                System.arraycopy(array, start1, array, start1, leftRemain);
            }
        }
    } // IntMergeStack class

    public static void sort(int[] a) {
        sort(a, 0, a.length);
    }

    public static void sort(int[] a, int low, int high) {
        int remain = high - low;
        // 정렬해야 할 원소가 1개 이하일 경우 정렬 할 필요가 없다.
        if (remain < 2) {
            return;
        }
        /*
            일정 크기 이하의 배열이라면
            binaryInsertionSort로 정렬 뒤 바로 변환
        */
        if (remain < THRESHOLD) {
            int increaseRange = getAscending(a, low, high);
            BinarySort(a, low, high, low + increaseRange);
            return;
        }

        IntMergeStack ims = new IntMergeStack(a);
        int minRun = minRunLength(remain); // run의 최소 길이
        do {

            // 정렬 된 구간의 길이를 구한다
            int runLen = getAscending(a, low, high);

            /*
                만약 정렬 된 부분의 길이가 minRun 보다 작다면
                정렬 된 부분 길이를 포함한 부분 배열에 대해
                이진 삽입 정렬을 시행한다.
            */
            if (runLen < minRun) {
                /*
                    [lo : lo + minRun] 구간에 대해 정렬

                    counts : run 에 있는 요소의 총 갯수
                    이 때 최소 run 크기가 남은 원소 갯수보다 클 수 있으므로 이를 처리해준다.
                */
                int counts = remain < minRun ? remain : minRun;

                /*
                    BinarySort(array, low, high, start);
                    index[low] ~ index[low + counts] 구간을 삽입 정렬을 하는데,
                    index[low + runLen] 부터 삽입정렬을 시작한다.
                    (앞서 구한 오름차순 길이인 runLen에 의해 low + runLen 의 이전 인덱스는 이미 오름차순 상태임)
                */
                BinarySort(a, low, low + counts, low + runLen);

                // 이진 삽입 정렬이 수행되었기에 증가하는 길이는 endPoint가 된다.
                runLen = counts;
            }

            // stack 에 run의 시작점과 해당 run의 길이를 스택에 push한다.
            ims.pushRun(low, runLen);
            ims.merge();

            low += runLen;
            remain -= runLen;
        } while (remain != 0); // 정렬 해야 할 원소가 0개가 될 때 까지 반복
        ims.mergeForce();      // 마지막으로 스택에 있던 run들 모두 병합
    }

    private static void BinarySort(int[] a, int low, int high, int start) {

        if (low == start) {
            start++;
        }

        for (; start < high; start++) {
            int target = a[start];

            int Ioc = binarySearch(a, target, low, start);

            int j = start - 1;

            while (j >= Ioc) {
                a[j + 1] = a[j];
                j--;
            }
            a[Ioc] = target;
        }
    }

    private static int binarySearch(int[] a, int key, int low, int high) {

        int mid;
        while (low < high) {
            mid = (low + high) >>> 1;
            if (key < a[mid]) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private static int getAscending(int[] a, int low, int high) {
        int limit = low + 1;
        if (limit == high) {
            return 1;
        }

        if (a[low] <= a[limit]) {
            while (limit < high && a[limit - 1] <= a[limit]) {
                limit++;
            }
        } else {
            while (limit < high && a[limit - 1] > a[limit]) {
                limit++;
            }
            reversing(a, low, limit);
        }
        return limit = low;
    }

    private static void reversing(int[] a, int low, int high) {
        high--;
        while (low < high) {
            int temp = a[low];
            a[low++] = a[high];
            a[high--] = temp;
        }
    }
}
