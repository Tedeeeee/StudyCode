package Data_structure.Interface_form;

public class DLinkedList<E> implements List<E> {

    private DNode<E> head;  // 노드의 첫 부분
    private DNode<E> tail;  // 노드의 마지막 부분
    private int size;  // 요소 갯수

    public DLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // 특정 위치의 노드를 반환하는 메소드
    private DNode<E> search(int index) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        // 뒤에서 부터 검색한다.
        if (index + 1 > size / 2) {
            DNode<E> x = tail;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
            return x;
        }
        // 앞에서부터 검색하는 것
        else {
            DNode<E> x = head;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        }
    }

    public void addFirst(E value) {
        DNode<E> newNode = new DNode<E>(value); // 새로운 노드를 생성한다
        newNode.next = head; // 새 노드의 다음 노드로 head 노드를 연결

        /**
         *  head가 null이 아닐 경우에만 기존 head노드의 prev 변수가 새 노드를 가리키도록 한다.
         *  이유는 기존 head노드가 없는 경우 데이터가 아무것도 없던 상태였으므로 head.prev를 하면 잘못된 참조가 되나.
         */
        if (head != null) {
            head.prev = newNode;
        }

        head = newNode;
        size++;

        /**
         *  다음에 가리킬 노드가 없는 경우(=데이터가 새 노드밖에 없는 경우)
         *  데이터가 한 개밖에 없으므로 새 노드는 처음 시작하는 노드이자 마지막 노드이다.
         */
    }
    @Override
    public boolean add(E value) {
        addLast(value);
        return true;
    }

    public void addLast(E value) {
        DNode<E> newNode = new DNode<E>(value);  // 새로운 노드를 생성한다.

        if (size == 0) {
            addFirst(value);  // 노드를 새롭게 넣는 경우 addFirst 로 추가한다
            return;
        }

        /**
         *  마지막 노드(tail)의 다음 노드(next)가 새 노드를 가리키도록 하고 tail이 가리키는 노드를 새 노드로 바꿔준다.
         */
        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
        size++;
    }

    @Override
    public void add(int index, E value) {

        // 잘못된 인덱스를 참조 할 경우 예외가 발생한다.
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        // 추가하려는 index가 가장 앞에 추가하려는 경우 addFirst 를 호출한다.
        if (index == 0) {
            addFirst(value);
            return;
        }

        // 추가하려는 index 가 마지막 위치일 경우 addLast 호출
        if (index == size) {
            addLast(value);
            return;
        }

        // 추가하려는 위치의 이전 노드
        DNode<E> prev_node = search(index - 1);

        // 추가하려는 위치의 노드
        DNode<E> next_node = prev_node.next;

        // 추가하려는 노드
        DNode<E> newNode = new DNode<E>(value);

        // 링크 끊기
        prev_node.next = null;
        next_node.prev = null;

        // 링크 연결하기
        prev_node.next = newNode;

    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public boolean remove(Object value) {
        return false;
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public void set(int index, E value) {

    }

    @Override
    public boolean contains(Object value) {
        return false;
    }

    @Override
    public int indexOf(Object value) {
        return 0;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
