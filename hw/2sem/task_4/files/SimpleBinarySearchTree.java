import java.util.ArrayList;

public class SimpleBinarySearchTree<T> implements BinarySearchTree<T> {
    private class TreeNode {
        T data;
        TreeNode left;
        TreeNode right;
        TreeNode parent;

        TreeNode(T data, TreeNode parent) {
            this.data = data;
            this.parent = parent;
            this.left = null;
            this.right = null;
        }
    }
    private MyComparator<T> comparator;
    private int size;
    private TreeNode root;

    public SimpleBinarySearchTree(MyComparator<T> comparator) {
        this.comparator = comparator;
        root = null;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public T min() {
        if (isEmpty())
            return null;
        return minRec(root).data;
    }
    private TreeNode minRec(TreeNode root) {
        if (root.left == null)
            return root;
        return minRec(root.left);
    }

    @Override
    public T max() {
        if(isEmpty())
            return null;
        return maxRec(root).data;
    }
    private TreeNode maxRec(TreeNode root) {
        if (root.right == null)
            return root;
        return maxRec(root.right);
    }

    @Override
    public void insert(T value) {
        if(isEmpty()) {
            root = new TreeNode(value, null);
            return;
        }
        insertRec(root, value);
        ++size;
    }
    private void insertRec(TreeNode root, T value) {
        if(comparator.compare(value, root.data) < 0)
            if(root.left == null)
                root.left = new TreeNode(value, root);
            else
                insertRec(root.left, value);
        else if(comparator.compare(value, root.data) > 0)
            if(root.right == null)
                root.right = new TreeNode(value, root);
            else
                insertRec(root.right, value);
    }

    @Override
    public void delete(T value) {
        TreeNode node = searchRec(root, value);
        if(node == null)
            return;
        deleteNonRec(node);
        --size;
    }

    private void deleteRec(TreeNode node) {
        if (isLeaf(node))
            replaceOnParent(node, null);
        else if(node.right != null) {
            TreeNode succ = minRec(node.right);
            node.data = succ.data;
            deleteRec(succ);
        } else {
            TreeNode pred = maxRec(node.left);
            node.data = pred.data;
            deleteRec(pred);
        }
    }

    private boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }

    private void deleteNonRec(TreeNode node) {
        if(isLeaf(node))
            replaceOnParent(node, null);
        else if(node.left == null)
            replaceOnParent(node, node.right);
        else if(node.right == null)
            replaceOnParent(node, node.left);
        else if(node.right.left == null)
            removeIfRightNearest(node);
        else if(node.left.right == null)
            removeIfLeftNearest(node);
        else
            removeIfNearestDeep(node);
    }

    private void removeIfNearestDeep(TreeNode node) {
        TreeNode succ = minRec(node.right);
        node.data = succ.data;
        replaceOnParent(succ, succ.right);
    }

    private void removeIfLeftNearest(TreeNode node) {
        replaceOnParent(node, node.left);
        node.left.right = node.right;
        node.right.parent = node.left;
    }

    private void removeIfRightNearest(TreeNode node) {
        replaceOnParent(node, node.right);
        node.right.left = node.left;
        node.left.parent = node.right;
    }

    private void replaceOnParent(TreeNode node, TreeNode newNode) {
        if(node.parent == null) {
            root = newNode;
            if(newNode != null)
                newNode.parent = null;
            return;
        }
        if(node.parent.left == node)
            node.parent.left = newNode;
        node.parent.right = newNode;
        if(newNode != null)
            newNode.parent = node.parent;
    }

    @Override
    public T predecessor(T value) {
        TreeNode pred = predecessorRec(root, null, value);
        if(pred == null)
            return null;
        return pred.data;
    }

    private TreeNode predecessorRec(TreeNode root, TreeNode lastRightParent, T value) {
        if(root == null)
            return lastRightParent;
        if(comparator.compare(root.data, value) == 0) {
            if(root.left != null)
                return maxRec(root.left);
            return lastRightParent;
        }
        if(comparator.compare(root.data, value) > 0)
            return predecessorRec(root.left, lastRightParent, value);
        return predecessorRec(root.right, root, value);
    }

    @Override
    public T successor(T value) {
        TreeNode succ = successorRec(root, null, value);
        if(succ == null)
            return null;
        return succ.data;
    }

    private TreeNode successorRec(TreeNode root, TreeNode lastLeftParent, T value) {
        if(root == null)
            return lastLeftParent;
        if(comparator.compare(root.data, value) == 0) {
            if(root.left != null)
                return maxRec(root.left);
            return lastLeftParent;
        }
        if(comparator.compare(root.data, value) < 0)
            return predecessorRec(root.right, lastLeftParent, value);
        return predecessorRec(root.left, root, value);
    }

    @Override
    public boolean search(T value) {
        TreeNode node = searchRec(root, value);
        if(node == null)
            return false;
        return true;
    }

    @Override
    public ArrayList<T> getOrderedItems() {
        return getOrderedItemsRec(root);
    }

    private ArrayList<T> getOrderedItemsRec(TreeNode root) {
        ArrayList<T> res = new ArrayList<>();
        if(root == null)
            return res;
        res.addAll(getOrderedItemsRec(root.left));
        res.add(root.data);
        res.addAll(getOrderedItemsRec(root.right));
        return res;
    }

    private TreeNode searchRec(TreeNode root, T value) {
        if(root == null)
            return null;
        if(comparator.compare(root.data, value) == 0)
            return root;
        if(comparator.compare(root.data, value) > 0)
            return searchRec(root.left, value);
        return searchRec(root.right, value);
    }
}
