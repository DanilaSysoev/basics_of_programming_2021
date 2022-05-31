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
        public void addChildren(int index, BTreeNode node) {
            children.add(index, node);
            node.parent = this;
        }
        public void addChildren(BTreeNode node) {
            children.add(node);
            node.parent = this;
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
        if(root == null) {
            root = new BTreeNode();
            root.keys.add(key);
        } else {
            prepareForInsert(root);
            insertRec(root, key);
        }
    }

    private void insertRec(BTreeNode root, int key) {
        if(root.isLeaf()) {
            insertToLeaf(root, key);
        } else {
            int pos = getNearestIndex(root.keys, key, 0, root.keys.size() - 1);
            if (root.keys.get(pos) > key) {
                prepareForInsert(root.children.get(pos));
            } else if (root.keys.get(pos) < key) {
                prepareForInsert(root.children.get(pos + 1));
            }
            pos = getNearestIndex(root.keys, key, 0, root.keys.size() - 1);
            if (root.keys.get(pos) > key) {
                insertRec(root.children.get(pos), key);
            } else if (root.keys.get(pos) < key) {
                insertRec(root.children.get(pos + 1), key);
            }
        }
    }

    private void insertToLeaf(BTreeNode root, int key) {
        int pos = getNearestIndex(root.keys, key, 0, root.keys.size() - 1);
        if (root.keys.get(pos) > key) {
            root.keys.add(pos, key);
        } else if (root.keys.get(pos) < key) {
            root.keys.add(pos + 1, key);
        }
    }

    private void prepareForInsert(BTreeNode node) {
        if(node.keys.size() == 2 * t - 1)
            split(node);
    }

    public void remove(int key) {
        removeRec(root, key);
    }

    private void removeRec(BTreeNode root, int key) {
        var pos = getNearestIndex(root.keys, key, 0, root.keys.size() - 1);
        if(root.isLeaf() && root.keys.get(pos) == key) {
            root.keys.remove(pos);
            return;
        } else if (root.keys.get(pos) > key && !root.isLeaf() || root.keys.get(pos) == key) {
            prepareForRemoving(root, pos);
        } else if (root.keys.get(pos) < key && !root.isLeaf()) {
            prepareForRemoving(root, pos + 1);
        }

        if(root.keys.size() == 0)
            root = this.root;

        pos = getNearestIndex(root.keys, key, 0, root.keys.size() - 1);
        if(root.isLeaf() && root.keys.get(pos) == key) {
            root.keys.remove(pos);
        } else if (root.keys.get(pos) > key && !root.isLeaf()) {
            removeRec(root.children.get(pos), key);
        } else if (root.keys.get(pos) < key && !root.isLeaf()) {
            removeRec(root.children.get(pos + 1), key);
        } else if (root.keys.get(pos) == key) {
            var left = root.children.get(pos);
            var newKey = left.keys.get(left.keys.size() - 1);
            root.keys.set(pos, newKey);
            removeRec(root.children.get(pos), newKey);
        }
        if(this.root.keys.size() == 0)
            this.root = null;
    }

    private void prepareForRemoving(BTreeNode node, int pos) {
        if(node.children.get(pos).keys.size() >= t)
            return;

        if(pos > 0 && node.children.get(pos - 1).keys.size() >= t) {
            rotateKeysRight(node, pos - 1);
        } else if(pos < node.children.size() - 1 && node.children.get(pos + 1).keys.size() >= t) {
            rotateKeysLeft(node, pos);
        } else if(pos > 0) {
            merge(node, pos - 1);
        } else {
            merge(node, pos);
        }
    }

    private void rotateKeysRight(BTreeNode node, int pos) {
        var left = node.children.get(pos);
        var right = node.children.get(pos + 1);

        right.keys.add(0, node.keys.get(pos));
        if(!right.isLeaf())
            right.addChildren(0, left.children.get(left.children.size() - 1));

        node.keys.set(pos, left.keys.get(left.keys.size() - 1));

        left.keys.remove(left.keys.size() - 1);
        if(!left.isLeaf())
            left.children.remove(left.children.size() - 1);
    }
    private void rotateKeysLeft(BTreeNode node, int pos) {
        var left = node.children.get(pos);
        var right = node.children.get(pos + 1);

        left.keys.add(node.keys.get(pos));
        if(!left.isLeaf())
            left.addChildren(right.children.get(0));

        node.keys.set(pos, right.keys.get(0));

        right.keys.remove(0);
        if(!right.isLeaf())
            right.children.remove(0);
    }

    private void merge(BTreeNode node, int pos) {
        BTreeNode newNode = new BTreeNode();
        fillNode(newNode, node.children.get(pos), 0, node.children.get(pos).keys.size());
        newNode.keys.add(node.keys.get(pos));
        fillNode(newNode, node.children.get(pos + 1), 0, node.children.get(pos + 1).keys.size());
        node.keys.remove(pos);
        node.children.remove(pos);
        node.children.set(pos, newNode);
        if(node == root && node.keys.size() == 0) {
            root = newNode;
            root.parent = null;
        }
    }

    private void split(BTreeNode node) {
        int median = (2 * t - 1) / 2;
        BTreeNode left = new BTreeNode();
        BTreeNode right = new BTreeNode();
        fillNode(left, node, 0, median);
        fillNode(right, node, median + 1, node.keys.size());
        if(node == root) {
            root = new BTreeNode();
            root.keys.add(node.keys.get(median));
            root.children = new ArrayList<>();
            root.addChildren(left);
            root.addChildren(right);
        } else {
            BTreeNode parent = node.parent;
            int pos = getNearestIndex(parent.keys, node.keys.get(median), 0, parent.keys.size() - 1);
            if(parent.keys.get(pos) > node.keys.get(median)) {
                parent.keys.add(pos, node.keys.get(median));
                parent.children.remove(pos);
                parent.addChildren(pos, left);
                parent.addChildren(pos + 1, right);
            } else {
                parent.keys.add(pos + 1, node.keys.get(median));
                parent.children.remove(pos + 1);
                parent.addChildren(pos + 1, left);
                parent.addChildren(pos + 2, right);
            }
        }
    }
    private void fillNode(BTreeNode node, BTreeNode src, int from, int to) {
        for(int i = from; i < to; ++i)
            node.keys.add(src.keys.get(i));
        if(!src.isLeaf()) {
            if(node.children == null)
                node.children = new ArrayList<>();
            for(int i = from; i <= to; ++i)
                node.addChildren(src.children.get(i));
        }
    }

    private static int getNearestIndex(ArrayList<Integer> data, int key, int from, int to) {
        if(from >= to)
            return from;
        int mid = (from + to) / 2;
        if (data.get(mid) == key)
            return mid;
        if (data.get(mid) < key)
            return getNearestIndex(data, key, mid + 1, to);
        return getNearestIndex(data, key, from, mid - 1);
    }

    public void print() {
        if(root != null)
            printRec(root, 0);
    }

    private void printRec(BTreeNode root, int level) {
        for(int i = 0; i < root.keys.size(); ++i) {
            if(!root.isLeaf())
                printRec(root.children.get(i), level + 1);
            for(int k = 0; k < level; ++k)
                System.out.print('.');
            System.out.println(root.keys.get(i));
        }
        if(!root.isLeaf())
            printRec(root.children.get(root.keys.size()), level + 1);
    }
}
