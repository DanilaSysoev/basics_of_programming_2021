package task;

public class DoubleLinkedListItem<T extends Comparable<T>> {
    private DoubleLinkedListItem<T> prev;
    private DoubleLinkedListItem<T> next;
    private T data;
    public DoubleLinkedListItem(T data) {
        prev = next = null;
        this.data = data;
    }

    public DoubleLinkedListItem<T> getNext() {
        return next;
    }
    public DoubleLinkedListItem<T> getPrev() {
        return prev;
    }
    public T getData() {
        return data;
    }
    public void setNext(DoubleLinkedListItem<T> value) {
        next = value;
    }
    public void setPrev(DoubleLinkedListItem<T> value) {
        prev = value;
    }
    public void setData(T value) {
        data = value;
    }
}
