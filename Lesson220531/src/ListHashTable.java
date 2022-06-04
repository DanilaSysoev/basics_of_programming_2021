public class ListHashTable implements HashTable {
    private static class List {
        int data;
        List next;
        public List(int data, List next) {
            this.data = data;
            this.next = next;
        }
    }

    private final int size;
    private final List[] data;

    public ListHashTable(int size) {
        this.size = size;
        data = new List[size];
    }

    @Override
    public void insert(int val) {
        int pos = hash(val);
        if(!exist(val))
            data[pos] = insertToHead(data[pos], val);
    }

    @Override
    public void remove(int val) {
        int pos = hash(val);
        data[pos] = remove(data[pos], val);
    }

    @Override
    public boolean exist(int val) {
        int pos = hash(val);
        return find(data[pos], val) != null;
    }

    @Override
    public void print() {
        for (int i = 0; i < size; i++) {
            printList(data[i]);
            System.out.println();
        }
    }

    private void printList(List list) {
        if (list != null){
            System.out.print(list.data);
            System.out.print(" -> ");
            printList(list.next);
        } else {
            System.out.print('x');
        }
    }

    private List insertToHead(List head, int data) {
        return new List(data, head);
    }
    private List find(List head, int val) {
        List worker = head;
        while (worker != null) {
            if (worker.data == val)
                return worker;
            worker = worker.next;
        }
        return null;
    }
    private List remove(List head, int val) {
        List worker = head;
        List past = null;
        while (worker != null) {
            if (worker.data == val) {
                if(past == null)
                    return worker.next;
                past.next = worker.next;
                return head;
            }
            past = worker;
            worker = worker.next;
        }
        return head;
    }

    private int hash(int val) {
        return val % size;
    }
}
