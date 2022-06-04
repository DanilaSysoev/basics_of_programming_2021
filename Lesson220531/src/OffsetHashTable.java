public class OffsetHashTable implements HashTable {
    private final int size;
    private final int[] data;

    public OffsetHashTable(int size) {
        this.size = size;
        data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = -1;
        }
    }

    @Override
    public void insert(int val) {
        int pos = hash(val);
        int start = pos;
        while (isFull(pos) && data[pos] != val) {
            pos = next(pos);
            if(pos == start)
                throw new RuntimeException("Hash table overflow");
        }

        if(data[pos] != val)
            data[pos] = val;
    }

    @Override
    public void remove(int val) {
        int pos = hash(val);
        int maxOffsetHash = pos;
        while (data[pos] != val && isFull(pos))
            pos = next(pos);
        if(isFree(pos))
            return;
        clear(pos);
        int prevFree = pos;
        pos = next(pos);
        boolean cycle = pos < prevFree;
        while (isFull(pos)) {
            if(hash(data[pos]) <= prevFree && (!cycle || hash(data[pos]) > pos)) {
                data[prevFree] = data[pos];
                clear(pos);
                prevFree = pos;
            }
            pos = next(pos);
            cycle = pos < prevFree;
        }
    }

    @Override
    public boolean exist(int val) {
        int pos = hash(val);
        int start = pos;
        while (isFull(pos)) {
            if(data[pos] == val)
                return true;
            pos = next(pos);
            if(pos == start)
                return false;
        }
        return false;
    }

    @Override
    public void print() {
        System.out.print('(');
        for (int i = 0; i < size; i++) {
            if(data[i] >= 0)
                System.out.print(i + "-" + data[i] + ":" + hash(data[i]));
            else
                System.out.print('x');
            if(i < size - 1)
                System.out.print(" ");
        }
        System.out.print(')');
    }


    private void clear(int pos) {
        data[pos] = -1;
    }
    private boolean isFull(int pos) {
        return data[pos] >= 0;
    }
    private boolean isFree(int pos) {
        return data[pos] < 0;
    }
    private int hash(int val) {
        return val % size;
    }
    private int next(int index) {
        return (index + 1) % size;
    }
}
