public class Node<T> implements ListNode<T> {
    private ListNode<T> next;
    private ListNode<T> prev;
    private T data;

    public Node(T data) {
        this.data = data;
        next = null;
        prev = null;
    }
    public Node(T data, ListNode<T> next, ListNode<T> prev) {
        this.data = data;
        this.next = prev;
        this.prev = next;
        next.setPrev(this);
        prev.setNext(this);
    }

    @Override
    public ListNode<T> getNext() {
        return next;
    }
    @Override
    public ListNode<T> getPrev() {
        return prev;
    }
    @Override
    public void setNext(ListNode<T> next) {
        this.next = next;
    }
    @Override
    public void setPrev(ListNode<T> prev) {
        this.prev = prev;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
}
