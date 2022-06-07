import java.util.ArrayList;
import java.util.List;

public class TwoArraysGraph implements Graph {
    int[] positions;
    ArrayList<Integer> adjacency;

    @Override
    public void initialize(int vertexCount) {
        positions = new int[vertexCount + 1];
    }

    @Override
    public void addEdge(int vFrom, int vTo) {
        for (int i = vFrom + 1; i < positions.length; i++) {
            positions[i]++;
        }
        adjacency.add(positions[vFrom], vTo);
    }

    @Override
    public void removeEdge(int vFrom, int vTo) {
        int pos = -1;
        for (int i = positions[vFrom]; i < positions[vFrom + 1]; i++) {
            if(adjacency.get(i) == vTo)
                pos = i;
        }
        if(pos >= 0) {
            for (int i = vFrom + 1; i < positions.length; i++) {
                positions[i]--;
            }
            adjacency.remove(pos);
        }
    }

    @Override
    public boolean existEdge(int vFrom, int vTo) {
        for (int i = positions[vFrom]; i < positions[vFrom + 1]; i++) {
            if(adjacency.get(i) == vTo)
                return true;
        }
        return false;
    }

    @Override
    public List<Integer> getAdjacentVertexes(int v) {
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = positions[v]; i < positions[v + 1]; i++) {
            res.add(adjacency.get(i));
        }
        return res;
    }
}
