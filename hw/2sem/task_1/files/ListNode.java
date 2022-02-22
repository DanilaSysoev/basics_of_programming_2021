public interface ListNode<T> {
    ListNode<T> getNext();
    ListNode<T> getPrev();
    void setNext(ListNode<T> next);
    void setPrev(ListNode<T> prev);
}
