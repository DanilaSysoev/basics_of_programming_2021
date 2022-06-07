import java.util.ArrayList;
import java.util.List;

public class AdjacencyMatrixGraph implements Graph {
    boolean[][] matrix;

    @Override
    public void initialize(int vertexCount) {
        matrix = new boolean[vertexCount][vertexCount];
    }

    @Override
    public void addEdge(int vFrom, int vTo) {
        matrix[vFrom][vTo] = true;
    }

    @Override
    public void removeEdge(int vFrom, int vTo) {
        matrix[vFrom][vTo] = false;
    }

    @Override
    public boolean existEdge(int vFrom, int vTo) {
        return matrix[vFrom][vTo];
    }

    @Override
    public List<Integer> getAdjacentVertexes(int v) {
        List<Integer> vertexes = new ArrayList<>();
        for (int i = 0; i < matrix[v].length; i++) {
            if(matrix[v][i])
                vertexes.add(i);
        }
        return vertexes;
    }
}
