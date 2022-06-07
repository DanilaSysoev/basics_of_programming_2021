import java.util.ArrayList;

public class AdjacencyListsGraph implements Graph {
    static class List{
        int data;
        List next;

        List(int data, List next) {
            this.data = data;
            this.next = next;
        }
    }

    ArrayList<List> lists;

    @Override
    public void initialize(int vertexCount) {
        lists = new ArrayList<>();
        for (int i = 0; i < vertexCount; i++) {
            lists.add(null);
        }
    }

    @Override
    public void addEdge(int vFrom, int vTo) {
        if(!existEdge(vFrom, vTo))
            lists.set(vFrom, new List(vTo, lists.get(vFrom)));
    }

    @Override
    public void removeEdge(int vFrom, int vTo) {
        List worker = lists.get(vFrom);
        List past = null;
        while (worker != null) {
            if(worker.data == vTo) {
                if(past == null) {
                    lists.set(vFrom, lists.get(vFrom).next);
                } else {
                    past.next = worker.next;
                }
                return;
            }
            past = worker;
            worker = worker.next;
        }
    }

    @Override
    public boolean existEdge(int vFrom, int vTo) {
        List worker = lists.get(vFrom);
        while (worker != null) {
            if(worker.data == vTo)
                return true;
            worker = worker.next;
        }
        return false;
    }

    @Override
    public java.util.List<Integer> getAdjacentVertexes(int v) {
        ArrayList<Integer> res = new ArrayList<>();
        List worker = lists.get(v);
        while (worker != null) {
            res.add(worker.data);
            worker = worker.next;
        }
        return res;
    }
}
