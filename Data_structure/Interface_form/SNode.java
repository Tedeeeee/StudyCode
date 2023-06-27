package Data_structure.Interface_form;

class SNode<E> {

    E data;
    SNode<E> next;  // 다음 노드 객체를 가리키는 래퍼런스 변수

    SNode(E data) {
        this.data = data;
        this.next = null;
    }

}
