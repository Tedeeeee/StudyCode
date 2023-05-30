package Data_structure.Interface_form;

public interface List<E> {

    /**
     * 리스트에 요소를 추가합니다
     *
     * @param value 리스트에 추가할 요소
     * @return 리스트에서 중복을 허용하지 않을 경우에 리스트에 이미
     *          중복되는 요소가 있을 경우 {@code false}
     *          중복되는 요소가 없을 경우 {@code true}
     */
    boolean add(E value);


    /**
     * 리스트에 요소를 특정 위치에 추가합니다.
     * 특정 위치 및 이휴의 요소들은 한 칸씩 뒤로 밀립니다
     *
     * @param index 리스트에 요소를 추가할 특정 위치 변수
     * @param value 리스트에 추가할 요소
     */
    void add(int index, E value);


    /**
     * 리스트의 index 위치에 있는 요소를 삭제합니다.
     *
     * @param index 리스트에서 삭제 할 위치 변수
     * @return 삭제된 요소를 반환
     */
    E remove(int index);

    /**
     * 리스트에 있는 특정 위치의 요소를 반환한다
     *
     * @param index 리스트에 접근할 위치 변수
     * @return 리스트의 index위치에 있는 요소 반환
     */
    E get(int index);

    /**
     * 리스트에 서 특정 위치에 있는 요소를 새 요소로 대체합니다
     *
     * @param index 리스트에 접근할 위치 변수
     * @param value 새로 대체할 요소 변수
     */
    void set(int index, E value);

    /**
     * 리스트에 특정 요소가 리스트에 있는지 여부를 확인한다.
     *
     * @param value 리스트에서 찾을 특정 요소 변수
     * @return 리스트에 특정 요소가 존재할 경우 {@code true}
     * 존재하지 않을 경우 {@code false}
     */
    boolean contains(Object value);

    /**
     * 리스트에 특정 요소가 몇 번째 위치에 있는지를 반환한다
     *
     * @param value 리스트에서 위치를 찾을 요소 변수
     * @return 리스트에서 처음으로 요소와 일치하는 위치를 반환
     * 만약 일치하는 요소가 없을 경우 -1 반환
     */
    int indexOf(Object value);

    /**
     * 리스트에 있는 요소의 갯수를 반환한다.
     *
     * @return 리스트에 있는 요소 갯수를 반환
     */
    int size();

    /**
     * 리스트에 있는 요소를 모두 삭제한다
     */
    public void clear();


    /**
     * 리스트에 요소가 비어있는지를 확인한다.
     *
     * @return 리스트에 요소가 없을 경우 {@code true}, 요소가 있을 경우{@code false}를 반환
     */
    boolean isEmpty();


}
