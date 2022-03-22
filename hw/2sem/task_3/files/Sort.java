public interface Sort<T> {
    void sort(T[] data, MyComparator<T> comparator);
}
