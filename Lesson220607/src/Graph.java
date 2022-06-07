import java.util.List;

public interface Graph {
    void initialize(int vertexCount);
    void addEdge(int vFrom, int vTo);
    void removeEdge(int vFrom, int vTo);
    boolean existEdge(int vFrom, int vTo);
    List<Integer> getAdjacentVertexes(int v);
}
