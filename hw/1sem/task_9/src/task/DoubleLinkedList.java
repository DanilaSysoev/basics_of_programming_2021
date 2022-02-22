package task;

public class DoubleLinkedList<T extends Comparable<T>> {
    private DoubleLinkedListItem<T> first;
    private DoubleLinkedListItem<T> last;
    private int size;

    public DoubleLinkedList() {
        first = last = null;
        size = 0;
    }

    public DoubleLinkedListItem<T> findFirstItem(T value) {
        if(size == 0)
            return null;
        var worker = first;
        while (worker != null && !worker.getData().equals(value))
            worker = worker.getNext();
        return worker;
    }
    public DoubleLinkedListItem<T> findLastItem(T value) {
        if(size == 0)
            return null;
        var worker = last;
        while (worker != null && !worker.getData().equals(value))
            worker = worker.getPrev();
        return worker;
    }

    public void insertAfter(DoubleLinkedListItem<T> listItem, T data) {
        if(size == 0) {
            first = last = new DoubleLinkedListItem<>(data);
            ++size;
            return;
        }
        var item = new DoubleLinkedListItem<>(data);
        if(listItem == null) {
            item.setNext(first);
            first.setPrev(item);
            first = item;
        }
        else if(listItem == last) {
            last.setNext(item);
            item.setPrev(last);
            last = item;
        }
        else {
            item.setPrev(listItem);
            item.setNext(listItem.getNext());
            listItem.getNext().setPrev(item);
            listItem.setNext(item);
        }
        ++size;
    }
    public void insertBefore(DoubleLinkedListItem<T> listItem, T data) {
        if(size == 0) {
            first = last = new DoubleLinkedListItem<>(data);
            ++size;
            return;
        }
        var item = new DoubleLinkedListItem<>(data);
        if(listItem == null) {
            last.setNext(item);
            item.setPrev(last);
            last = item;
        }
        else if(listItem == first) {
            item.setNext(first);
            first.setPrev(item);
            first = item;
        }
        else {
            item.setNext(listItem);
            item.setPrev(listItem.getPrev());
            listItem.getPrev().setNext(item);
            listItem.setPrev(item);
        }
        ++size;
    }

    public void remove(DoubleLinkedListItem<T> item) {
        if(size == 1) {
            first = last = null;
        }
        else if(item == first) {
            item.getNext().setPrev(null);
            first = item.getNext();
        }
        else if(item == last) {
            item.getPrev().setNext(null);
            last = item.getPrev();
        }
        else {
            item.getNext().setPrev(item.getPrev());
            item.getPrev().setNext(item.getNext());
        }
        --size;
    }

    public int getSize() {
        return size;
    }
    public DoubleLinkedListItem<T> getFirst() {
        return first;
    }
    public DoubleLinkedListItem<T> getLast() {
        return last;
    }
}
