public class DummyNode<T> implements ListNode<T> {
    private ListNode<T> next;
    private ListNode<T> prev;

    public DummyNode() {
        next = this;
        prev = this;
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
}
