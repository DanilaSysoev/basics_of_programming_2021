import java.util.Arrays;

public class MergeSort<T> implements Sort<T> {
    MyComparator<T> comparator;

    @Override
    public void sort(T[] data, MyComparator<T> comparator) {
        this.comparator = comparator;
        T[] buffer = Arrays.copyOf(data, data.length);
        mergeSortRec(data, buffer, 0, data.length - 1);
    }

    private void mergeSortRec(T[] sorting, T[] buffer, int begin, int end) {
        if(begin == end)
            return;
        int middle = (begin + end) / 2;
        mergeSortRec(sorting, buffer, begin, middle);
        mergeSortRec(sorting, buffer, middle + 1, end);
        merge(buffer, sorting, begin, middle - begin, end - middle);
    }

    private void merge(T[] from, T[] to, int pos, int blockSize1, int blockSize2) {
        int firstFocus = pos;
        int secondFocus = pos + blockSize1;
        int firstEnd = pos + blockSize1;
        int secondEnd = pos + blockSize1 + blockSize2;

        int resPos = pos;
        int resSize = blockSize1 + blockSize2;
        for(int i = 0; i < resSize; ++i) {
            if(firstFocus == firstEnd)
                to[resPos] = from[secondFocus++];
            else if(secondFocus == secondEnd)
                to[resPos++] = from[firstFocus];
            else if (comparator.compare(from[firstFocus], from[secondFocus]) > 0)
                to[resPos++] = from[firstFocus++];
            else
                to[resPos++] = from[secondFocus++];
        }
    }
}
