import java.util.ArrayList;

public interface BinarySearchTree<T> {
    boolean isEmpty();
    int size();

    T min();
    T max();
    void insert(T value);
    void delete(T value);
    T predecessor(T value);
    T successor(T value);
    boolean search(T value);
    ArrayList<T> getOrderedItems();
}
