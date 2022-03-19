public class BubbleSort<T> implements Sort<T> {
    @Override
    public void sort(T[] data, MyComparator<T> comparator) {
        for(int sps = 1; sps < data.length - 1; ++sps) {
            for(int i = 0; i < data.length - sps; ++i) {
                if(comparator.compare(data[i - 1], data[i]) < 0) {
                    T tmp = data[i - 1];
                    data[i - 1] = data[i];
                    data[i] = tmp;
                }
            }
        }
    }
}
