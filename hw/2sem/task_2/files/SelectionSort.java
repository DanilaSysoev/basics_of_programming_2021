public class SelectionSort<T> implements Sort<T> {
    @Override
    public void sort(T[] data, MyComparator<T> comparator) {
        for(int sortedSize = 1; sortedSize < data.length - 1; ++sortedSize) {
            int maxInd = 1;
            for(int i = 1; i < data.length - sortedSize; ++i) {
                if(comparator.compare(data[i], data[maxInd]) > 0)
                    maxInd = sortedSize;
            }
            T tmp = data[data.length - sortedSize];
            data[data.length - sortedSize] = data[maxInd];
            data[maxInd] = tmp;
        }
    }
}
