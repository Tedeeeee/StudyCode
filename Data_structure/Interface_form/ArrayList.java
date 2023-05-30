package Data_structure.Interface_form;

import java.util.Arrays;

public class ArrayList<E> implements List<E> {

    private static final int DEFAULT_CAPACITY = 10;  // 최소 용적(capacity) 크기
    private static final Object[] EMPTY_ARRAY = {};  // 비어있는 배열 생성

    private int size; // 요소 갯수, 인덱스를 담당할 size 이다.

    Object[] array; // 요소를 담을 배열


    // 공간을 할당하지 않은 1번 생성자
    public ArrayList() {
        this.array = EMPTY_ARRAY;
        this.size = 0;
    }

    // 공간을 할당하지 않은 2번 생성자
    public ArrayList(int capacity) {
        this.array = EMPTY_ARRAY;
        this.size = 0;
    }

    // 들어오는 데이터에 갯수에 따라 '최적화'된 용적을 갖는다.
    // 적절한 크기에 맞게 배열의 용적을 변경해야한다.
    private void resize() {
        int array_capacity = array.length;

        // 만약 용적크기가 0이라면?
        if (Arrays.equals(array, EMPTY_ARRAY)) {
            array = new Object[DEFAULT_CAPACITY];
            return;
        }

        // 용량이 꽉 찼을 경우
        if (size == array_capacity) {

            // 기존에 2배가 되는 양의 배열을 새로 생성해준다.
            int new_capacity = array_capacity * 2;

            // 새롭게 만들어준 배열에 복사를 해준다
            array = Arrays.copyOf(array, new_capacity);
            return;
        }

        // 용적의 절반 미만으로 요소가 차지하고 있을 경우
        if (size < (array_capacity / 2)) {

            // 2배로 늘린것처럼 절반으로 줄여주고
            int new_capacity = array_capacity / 2;

            // 줄인 배열에 복사한다.
            array = Arrays.copyOf(array, Math.max(new_capacity, DEFAULT_CAPACITY));
            return;
        }
    }

    @Override
    public boolean add(E value) {

        // 그저 뒤에 넣는 경우는 간단하다.
        addLast(value);
        return true;
    }

    public void addLast(E value) {

        // 용량이 꽉 차있다면 용적을 재할당 해줄것이다.
        if (size == array.length) {
            resize();
        }
        array[size] = value;  // 마지막 위치에 요소를 추가한다
        size++;  // 그리고 사이즈를 1 증가 시킨다.
    }

    // 중간에 원하는 값을 들어오게 하기위해 인덱스 번호와 값을 정해준다면?
    // 다른 값들이 한칸씩 밀려야하기 때문에 재조정해주어야 한다.
    @Override
    public void add(int index, E value) {

        if (index > size || index < 0) {  // 우리가 만들어 놓은 영역을 벗어나면 예외처리
            throw new IndexOutOfBoundsException();
        }
        if (index == size) {  // index 가 마지막 위치라면 addLast 메소드로 추가
            addLast(value);
        } else {

            if (size == array.length) { // 꽉 차있다면 용적을 재할당해준다
                resize();
            }

            // 추가될 요소의 index 를 기분으로 바로 뒤에 있는 index 부터 한칸씩 미루기
            for (int i = size; i > index; i--) {
                array[i] = array[i - 1];
            }

            array[index] = value;
            size++;
        }
    }

    // 첫번째 인덱스에 넣을 메소드이다.
    public void addFirst(E value) {
        add(0, value);
    }


    // 이게 무엇일까? 반환할수 없는 타입의 가능성이 있다는 경고등을 무시할수 있다.
    @SuppressWarnings("unchecked")
    @Override
    public E get(int index) {
        if (index >= size || index < 0) { // 범위를 벗어난다면 오류이다
            throw new IndexOutOfBoundsException();
        }

        return (E) array[index];
    }


    // 이 메소드는 해당 인덱스에 새로운 값으로 바꾸는 것이다.
    // add 와 반대되는 경우라고 생각하면 된다.
    @Override
    public void set(int index, E value) {
        if (index >= size || index < 0) { // 범위를 벗어나면 예외 발생
            throw new IndexOutOfBoundsException();
        } else {
            // 해당 위치의 요소를 교체
            array[index] = value;
        }

    }

    // 우리가 원하는 요소의 위치를 반환한다.
    // 만약 그렇다면 가장 먼저 마주치는 요소의 인덱스를 반환한다.
    // 이것은 실제 java 에서 제공하는 indexOf 와 같다
    @Override
    public int indexOf(Object value) {
        int i = 0;

        for (i = 0; i < size; i++) {
            if (array[i].equals(value)) {
                return i;
            }
        }
        // 만약 내가 찾고자하는 값이 없다면 -1로 반환
        return -1;
    }

    // 이번엔 앞이 아닌 뒤에서 부터 찾는 indexOf이다.
    public int lastIndexOf(Object value) {
        for (int i = size - 1; i >= 0; i--) {
            if (array[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }
    // 이번엔 index 값이 아닌 value 값으로 찾고자 한다면
    @Override
    public boolean contains(Object value) {

        // 0 이상이면 요소가 존재한다.
        if (indexOf(value) >= 0) {
            return true;
        } else {
            return false;
        }
    }

    // 우리가 원하는 인덱스 번호에 있는 요소를 삭제한다.
    // 물론 그 후에 있는 모든 요소들을 당겨와야한다.
    @SuppressWarnings("unchecked")
    @Override
    public E remove(int index) {

        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        E element = (E) array[index]; // 삭제될 요소를 반환하기 위해 임시로 담는 곳
        array[index] = null; // 지우고 해당 인덱스의 요소는 null 로 지정

        // 삭제한 요소의 뒤에 있는 요소들을 한칸씩 당겨서 가져온다
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i - 1];
            array[i + 1] = null;  // 당겨오면서 생긴 맨 마지막 인덱스의 요소는 null 로 전환
        }

        size--; // 사이즈를 요소의 갯수에 맞게 맞춘다
        resize();
        return element;
    }

    // 우리가 원하는 요소의 값을 지운다
    public boolean remove(Object value) {

        // 삭제하고자 하는 요소의 인덱스를 찾는다
        int index = indexOf(value);

        // -1 이라면 array 에 요소가 없다는 의 미이므로 false 변환
        if (index == -1) {
            return false;
        }

        remove(index);
        return true;
    }

    // 우리가 사용하던 배열의 크기를 까먹을수 있다.
    @Override
    public int size() {
        return size; // 요소 갯수 변환
    }

    // 해당 ArrayList 에 요소가 단 한개도 존재하지 않고 비어있는지 확인
    // 비어있다면 true 아니라면 false 이다.
    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    @Override
    public void clear() {
        // 모든 공간을 null 처리 해준다.
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
        resize();
    }
}
