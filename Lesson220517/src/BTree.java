import java.util.ArrayList;

public class BTree {
    static class BTreeNode {
        private ArrayList<Integer> keys;
        private ArrayList<BTreeNode> children;
        private BTreeNode parent;

        public BTreeNode() {
            keys = new ArrayList<>();
            children = null;
        }

        public boolean isLeaf() {
            return children == null;
        }
        public void removeKey(int pos) {
            keys.remove(pos);
        }
    }

    private BTreeNode root;
    private int t;

    public BTree(int t) {
        this.t = t;
    }

    public boolean search(int key) {
        return searchRec(root, key);
    }
    private static boolean searchRec(BTreeNode node, int key) {
        if(node == null)
            return false;
        int pos = getNearestIndex(node.keys, key, 0, node.keys.size() - 1);
        if (node.keys.get(pos) == key)
            return true;
        if (node.isLeaf())
            return false;
        if (node.keys.get(pos) > key)
            return searchRec(node.children.get(pos), key);
        return searchRec(node.children.get(pos + 1), key);
    }

    public void insert(int key) {

    }
    public void remove(int key) {

    }

    private void split(BTreeNode node) {
        int median = (2 * t - 1) / 2;
        BTreeNode left = new BTreeNode();
        BTreeNode right = new BTreeNode();
        fillNode(left, node, 0, median);
        fillNode(right, node, median, node.keys.size());
        if(node == root) {
            root = new BTreeNode();
            root.keys.add(node.keys.get(median));
            root.children = new ArrayList<>();
            root.children.add(left);
            root.children.add(right);
        } else {
            BTreeNode parent = node.parent;
            int pos = getNearestIndex(parent.keys, node.keys.get(median), 0, parent.keys.size() - 1);
            if(parent.keys.get(pos) > node.keys.get(median)) {
                parent.keys.add(pos, node.keys.get(median));
                parent.children.remove(pos);
                parent.children.add(pos, left);
                parent.children.add(pos + 1, right);
            } else {
                parent.keys.add(pos + 1, node.keys.get(median));
                parent.children.remove(pos + 1);
                parent.children.add(pos + 1, left);
                parent.children.add(pos + 2, right);
            }
        }
    }
    private void fillNode(BTreeNode node, BTreeNode src, int from, int to) {
        for(int i = from; i < to; ++i)
            node.keys.add(src.keys.get(i));
        if(!src.isLeaf()) {
            node.children = new ArrayList<>();
            for(int i = from; i <= to; ++i)
                node.children.add(src.children.get(i));
        }
    }


    private static int getNearestIndex(ArrayList<Integer> data, int key, int from, int to) {
        if(from == to)
            return from;
        int mid = (from + to) / 2;
        if (data.get(mid) == key)
            return mid;
        if (data.get(mid) < key)
            return getNearestIndex(data, key, mid + 1, to);
        return getNearestIndex(data, key, from, mid - 1);
    }
}
