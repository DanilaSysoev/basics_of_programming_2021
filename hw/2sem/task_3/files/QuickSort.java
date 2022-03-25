public class QuickSort<T> implements Sort<T> {
    MyComparator<T> comparator;
    @Override
    public void sort(T[] data, MyComparator<T> comparator) {
        this.comparator = comparator;
        qSortRec(data, 0, data.length - 1);
    }

    private void qSortRec(T[] data, int b, int e) {
        if(b >= e)
            return;
        int base = split(data, e, b);
        qSortRec(data, b, base - 2);
        qSortRec(data, base + 2, e);
    }

    private int split(T[] data, int b, int e) {
        int baseElem = b;
        int left = b;
        int right = e;
        while (left < right) {
            while (left <= right && comparator.compare(data[left], data[baseElem]) <= 0)
                left++;
            swap(data, left, baseElem);
            baseElem = left;
            while (right > left && comparator.compare(data[right], data[baseElem]) > 0)
                right--;
            if(left < right)
				swap(data, left + 1, right);
        }
        return baseElem;
    }

    private void swap(T[] data, int i1, int i2) {
        T tmp = data[i1];
        data[i1] = data[i2];
        data[i2] = tmp;
    }
}
