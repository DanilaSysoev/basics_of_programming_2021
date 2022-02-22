package task;

public class DoubleLinkedListSorter {
    public static <T extends Comparable<T>> void sort(DoubleLinkedList<T> list) {
        if(list.getSize() == 0)
            return;
        int sorted = 0;
        while (sorted != list.getSize() - 1) {
            var worker = list.getFirst();
            for(int i = 0; i < list.getSize() - sorted - 1; ++i) {
                if(worker.getData().compareTo(worker.getNext().getData()) >= 0) {
                    var data = worker.getData();
                    worker.setData(worker.getNext().getData());
                    worker.getNext().setData(data);
                }
                worker = worker.getNext();
            }
            ++sorted;
        }
    }
}
