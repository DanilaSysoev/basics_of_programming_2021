public class HeapSort<T> implements Sort<T> {
    private T[] data;
    private MyComparator<T> comparator;
    @Override
    public void sort(T[] data, MyComparator<T> comparator) {
        this.data = data;
        this.comparator = comparator;
        heapify();
        for(int sortedSize = 0; sortedSize < data.length - 1; ++sortedSize) {
            swap(0, data.length - sortedSize);
            siftDown(0, 1, 2, data.length - sortedSize - 1);
        }
    }

    private void heapify(){
        int start = (data.length - 2) / 2;
        for (int i = start; i >= 1; --i)
            siftDown(i, 2 * i, 2 * i + 1, data.length);
    }

    private void siftDown(int parent, int left, int right, int size) {
        if(left >= size)
            return;
        if(right >= size) {
            if(comparator.compare(data[parent], data[left]) < 0)
                swap(parent, left);
            return;
        }
        if(comparator.compare(data[parent], data[right]) >= 0 &&
            comparator.compare(data[parent], data[left]) >= 0)
            return;
        if(comparator.compare(data[left], data[right]) >= 0) {
            swap(left, parent);
            siftDown(left, 2 * left, 2 * left + 1, size);
            return;
        }
        swap(right, parent);
        siftDown(right, 2 * right + 1, 2 * right + 2, data.length);
    }
    private void swap(int i1, int i2) {
        T tmp = data[i1];
        data[i1] = data[i2];
        data[i2] = tmp;
    }
}
