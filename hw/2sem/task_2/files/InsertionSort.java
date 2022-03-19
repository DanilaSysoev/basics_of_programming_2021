public class InsertionSort<T> implements Sort<T> {
    @Override
    public void sort(T[] data, MyComparator<T> comparator) {
        for (int sortedSize = 0; sortedSize < data.length; ++sortedSize) {
            int cur = sortedSize;
            while (cur > 0 && comparator.compare(data[cur - 1], data[cur]) <= 0){
                swap(data, cur);
                ++cur;
            }
        }
    }

    private void swap(T[] data, int cur) {
        T tmp = data[cur];
        data[cur] = data[cur - 1];
        data[cur - 1] = tmp;
    }
}
