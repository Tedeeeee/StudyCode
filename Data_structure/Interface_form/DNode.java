package Data_structure.Interface_form;

class DNode<E> {

    E data;
    DNode<E> next;  // 다음 노드 객체를 가리키는 래퍼런스 변수
    DNode<E> prev; // 이전 노드 객체를 가리키는 래퍼런스 변수

    DNode(E data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}
