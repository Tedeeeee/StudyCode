package Data_structure.Interface_form;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

// Single LinkedList 이다.
public class SLinkedList<E> implements List<E>, Cloneable {

    private SNode<E> head;  // 노드의 첫 부분
    private SNode<E> tail;  // 노드의 마지막 부분
    private int size; // 요소의 갯수

    public SLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    //특정 위치의 노드를 반환하는 메소드
    private SNode<E> search(int index) {

        // 범위 밖(잘못된 위치)일 경우 예외 던지기
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        SNode<E> x = head; // head 를 가리키는 노드부터 시작

        for (int i = 0; i < index; i++) {
            x = x.next;  // x 노드의 다음 노드를 x에 저장한다.
        }
        return x;
    }


    // add 는 ArrayList 와 마찬가지로 3가지로 분류가 되어야한다
    // 1. 가장 앞부분 추가 - 노드의 번호가 0이였던 앞에 추가로 생성을 하게 되면 head 가 바뀌어야한다.
    // 2. 가장 마지막 부분에 추가 - 가장 기본이 되는 add 구조이다. LinkedListAPI 는 add 를 호출하면 기본적으로 addLast()를 호출한다.
    // 3. 특정 위치(중간)에 추가


    // 1. 가장 앞부분에 추가
    // 이것을 먼저 구현한 이유는 size 가 0일때 추가하는 노드는 어차피 가장 먼저 추가하는 노드이기 때문에 이것을 호출하면 된다.
    public void addFirst(E value) {
        SNode<E> newSNode = new SNode<E>(value);  // 새로운 노드를 생성하고
        newSNode.next = head;  // 새노드 다음 노드로 head 노드를 연결한다.
        head = newSNode;   // head 가 가리키는 노드를 새 노드로 변경한다.
        size++;

        /**
         *  다음에 가리킬 노드가 없는 경우 ( = 데이터가 새 노드 밖에 없는 경우 )
         *  데이터가 한 개(새 노드)밖에 없으므로 새 노드는 처음 시작노드이자 마지막 노드이다.
         *  즉, tail = head 이다.
         */
        if (head.next == null) {
            tail = head;
        }
    }
    @Override
    public boolean add(E value) {
        addLast(value);
        return true;
    }

    public void addLast(E value) {
        SNode<E> newSNode = new SNode<E>(value);  // 새 노드 생성

        if (size == 0) {
            addFirst(value);  // 처음 넣는 노드일 경우 addFirst 로 추가한다.
            return;
        }

        /**
         *  마지막 노드(tail)의 다음 노드(next)가 새 노드를 가리키도록 하고
         *  tail 이 가리키는 노드를 새 노드로 바꿔주어야한다.
         */
        tail.next = newSNode;
        tail = newSNode;
        size++;
    }

    /**
     *  넣으려는 위치의 이전노드를 prev_Node 라고 하고, 넣으려는 위치의 기존노드를 next_Node 라고 할때
     *  앞서 만든 메소드 search()를 사용하여 넣으려는 위치의 -1 위치의 노드(prev_node)를 찾아내고, next_Node 는 prev_Node.next 를 통해 찾는다.
     *
     *  그리고 prev_Node 의 링크를 새로 추가하려는 노드로 변경하고, 새로 추가하려는 노드의 링크는 next_node 로 변경해준다
     */
    @Override
    public void add(int index, E value) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        // 추가하려는 index 가 가장 앞에 추가하려는 경우 addFirst 호출
        if (size == 0) {
            addFirst(value);
            return;
        }

        // 추가하려는 index 가 가장 마지막 위치일 경우는 addLast 호출
        if (index == size) {
            addLast(value);
            return;
        }

        // 추가하려는 위치 이전의 노드
        SNode<E> prev_Single_Node = search(index - 1);

        // 추가 하려는 위치의 노드
        SNode<E> next_Single_Node = prev_Single_Node.next;

        // 추가하려는 노드
        SNode<E> newSNode = new SNode<E>(value);

        /**
         *  이전 노드가 가리키는 노드를 끊은 뒤 새 노드로 변경해준다
         *  또한 새노드가 가리키는 노드는 next_Node 로 설정해준다.
         */
        prev_Single_Node.next = null;
        prev_Single_Node.next = newSNode;
        newSNode.next = next_Single_Node;
        size++;
    }

    /**
     *  분해는 조립의 역순이다 말했다
     *  1. 가장 앞의 요소를 삭제 - 가장 기본이 되는 요소, 첫번째 요소 삭제이다.
     *  2. 특정 index 의 요소를 삭제
     *  3. 특정 요소를 삭제
     */

    // head 가 가리키는 요소만 없애주면 된다.
    public E remove() {
        SNode<E> headSNode = head;

        if (headSNode == null) {
            throw new NoSuchElementException();
        }

        // 삭제된 노드를 반환하기 위한 임시 변수
        E element = headSNode.data;

        // head 의 다음 노드
        SNode<E> nextSNode = head.next;

        // head 노드의 데이터들을 모두 삭제
        head.data = null;
        head.next = null;

        // head 가 다음 노드를 가리키도록 업데이트
        head = nextSNode;
        size--;

        /**
         *  삭제된 요소가 리스트의 유일한 요소였을 경우
         *  그 요소는 head 이자 tail 이였으므로
         *  삭제되면서 tail 도 가리키 요소가 없기 때문에
         *  size 가 0 일 경우 tail 도 null 로 변환
         */
        if (size == 0) {
            tail = null;
        }
        return element;
    }

    // 특정 위치를 리스트에서 찾아서 삭제하는것이다.
    @Override
    public E remove(int index) {

        // 삭제하려는노드가 첫 번째 원소일 경우
        if (index == 0) {
            return remove();
        }

        // 잘못된 범위에 대한 예외
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        SNode<E> prevSNode = search(index - 1);  // 삭제할 노드의 이전 노드
        SNode<E> removedSNode = prevSNode.next;         // 삭제할 노드
        SNode<E> nextSNode = removedSNode.next;         // 삭제할 노드의 다음 노드

        E element = removedSNode.data;  // 삭제되는 노드의 데이터를 반환하기 위한 임시 변수

        // 이전 노드가 가리키는 노드를 삭제하려는 노드의 다음노드로 변환
        prevSNode.next = nextSNode;

        // 만약 삭제했던 노드가 마지막 노드라면 tail 을 prevNode 로 갱신
        if (prevSNode.next == null) {
            tail = prevSNode;
        }

        // 데이터 삭제
        removedSNode.next = null;
        removedSNode.data = null;
        size--;

        return element;
    }

    // 사용자가 원하는 특정 요소(value)를 리스트에서 찾아서 삭제하는 것이다.
    // 주의해야 할 점은 "삭제하려는 요소가 존재하는지" 가 중요하다. 없다면 false 있다면 해당 요소를 remove(int index)를 사용한다
    @Override
    public boolean remove(Object value) {
        SNode<E> prevSNode = head;
        boolean hasValue = false;
        SNode<E> x = head;  // removedNode

        // value 와 일치하는 노드를 찾는다.
        for (; x != null; x = x.next) {
            if (value.equals(x.data)) {
                hasValue = true;
                break;
            }
            prevSNode = x;
        }

        // 원하는 요소가 없으면 false 반환
        if (x == null) {
            return false;
        }

        // 만약 삭제하려는 노드가 head 라면 기존 remove()를 사용한다.
        if (x.equals(head)) {
            remove();
            return true;
        } else {

            // 이전 노드의 링크를 삭제하려는 노드의 다음 노드로 연결한다.
            prevSNode.next = x.next;

            // 만약 삭제했던 노드가 마지막 노드라면 tail 을 prevNode 로 갱신한다.
            if (prevSNode.next == null) {
                tail = prevSNode;
            }
            x.data = null;
            x.next = null;
            size--;
            return true;
        }


    }


    // 가져오는것은 앞서만든 search()메소드를 이용하면 된다.
    @Override
    public E get(int index) {
        return search(index).data;
    }

    // 기존의 index 에 위치한 새로운 데이터(value)으로 "교체" 하는것이다. add 메소드는 데이터 '추가' 지만
    // set 은 교체가 다른점이다.
    // search()를 통해 값을 찾아내고 해당 data 만 쏠랑 바꿔주면 된다
    @Override
    public void set(int index, E value) {

        SNode<E> replaceSNode = search(index);
        replaceSNode.data = null;
        replaceSNode.data = value;
    }

    // 사용자가 찾고자 하는 요소(value)가 존재하는지 안하는지를 반환하는 메소드이다.
    // indexOf 와 굉장히 비슷하다 라는 생각이 든다면 정답이고 이를 사용하면 되는것이다 -1 을 반환한다는것이 음수 즉 false인것이다.
    @Override
    public boolean contains(Object item) {
        return indexOf(item) >= 0;
    }

    // 찾고자 하는 요소(value)의 위치(index)를 반환하는 메소드이다
    // 만약 값이 값다면? 먼저 마주치는 놈을 반환한다. 없다면? -1 반환해주면 된다.
    // 주의 점은 equal 을 사용해야 한다는 것이다 '==' 를 사용하면 값이 아닌 주소값을 비교한다.
    @Override
    public int indexOf(Object value) {
        int index = 0;

        for (SNode<E> x = head; x != null; x = x.next) {
            if (value.equals(x.data)) {
                return index;
            }
            index++;
        }
        // 만약 못찾으면 -1 반환
        return -1;
    }

    // 모든 리스트 자료구조는 기본적으로 동적 할당을 전제로 한다. 즉, 정해져있는 요소가 없다
    // 그냥 size 반환해주면 된다
    @Override
    public int size() {
        return size;
    }

    // 모든 요소를 비워버리는 작업이다. 초기화 할때 사용하는 메소드라 볼수 있다.
    // 객체 자체를 null 해주는것 보다는 모든 노드를 하나하나 null 해주는것이 더 좋다
    // 가비지 컬렉터가 명시적으로 해당 메모리를 안쓴다고 인지하기 때문에 메모리 관리 효율 측면에서 좋다.
    @Override
    public void clear() {
        for (SNode<E> x = head; x != null; ) {
            SNode<E> nextSNode = x.next;
            x.data = null;
            x.next = null;
            x = nextSNode;
        }
        head = tail = null;
        size = 0;
    }

    // 그냥 요소가 있는지 없는지만 확인하면 된다
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     *  LinkedList 를 복제하고 싶을때 사용하는 메소드로 clone()을 만들것이다.
     *  그냥 "=" 사용하면 되는거아냐? 할수 있겠지만 위에서 잠깐 말했듯이 "=" 는 주소까지 복사해오게 된다
     *  그러면 복사된 데이터를 다루어도 원본까지 다루게 되는것이다. 이를 방지하기 위해 clone()을 만드는것이다
     */
    public Object clone() throws CloneNotSupportedException {

        // 이것만 해주면 얕은 복사가 되버린다.
        @SuppressWarnings("unchecked")
        SLinkedList<? super E> clone = (SLinkedList<? super E>) super.clone();

        // 그래서 세부설정도 해주어야한다.
        // 모든 노드를 끊는다.
        clone.head = null;
        clone.tail = null;
        clone.size = 0;

        // 그리고 다시 설정해준다.
        for (SNode<E> x = head; x != null; x = x.next) {
            clone.addLast(x.data);
        }

        return clone;
    }

    public Object[] toArray() {
        Object[] array = new Object[size];
        int idx = 0;
        for (SNode<E> x = head; x != null; x = x.next) {
            array[idx++] = (E) x.data;
        }
        return array;
    }

    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            a = (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
        }
        int i = 0;
        Object[] result = a;
        for (SNode<E> x = head; x != null; x = x.next) {
            result[i++] = x.data;
        }
        return a;
    }

    /**
     *  대망의 sort()메소드이다 사람들이 정말 많이 사용하는 만큼 자세히 다뤄보자
     *  ArrayList 는 Object[] 배열을 사용하고 있기때문에 굳이 sort()를 구현할 필요는 없다.
     *
     *  객체 배열의 경우 Collection.sort()를 사용하는데 Collection.sort() 내부에서 Arrays.sort()를 사용한다.
     *  해당 리스트를 Object[] 배열로 변환시켜서 Array.sort()를 통해 정렬한 뒤, 정렬된 데이터를 다시 리스트의 노드에서 셋팅을 하는것이다.
     *
     *  만약 래퍼 클래스 타입이라면 따로 Comparator 를 구현해주지 않아도 되지만, 자체적으로 만든 클래스를 사용하는 경우 해당 객체에서 따로 Comparable 혹은 Comparator
     *  를 구현해주어 파라미터로 넘겨주어야한다.
     *
     *  1. Comparable 이 구현되어 따로 파라미터로 Comparator 를 넘겨주지 않는 경우
     *  2. Comparator 를 넘겨주어 정의 된 정렬 방식을 사용하는 경우
     */
    public void sort() {
        /**
         *  Comparator 를 넘겨주지 않는 경우 해당 객체의 Comparable 에 구현된 정렬 방식을 사용한다.
         *  만약 구현되지 않았다면 cannot be cast to class java.lang.Comparable 에러가 발생한다
         *  만약 구현되어있을 경우 null 로 파라미터를 넘기면 Arrays.sort()가 객체의 compareTo 메소드에 정의된 방식대로 정렬한다.
         */
        sort(null);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public void sort(Comparator<? super E> c) {
        Object[] a = this.toArray();
        Arrays.sort(a, (Comparator) c);

        int i = 0;
        for (SNode<E> x = head; x != null; x = x.next, i++) {
            x.data = (E) a[i];
        }
    }

}
