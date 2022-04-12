import java.util.ArrayList;

public class AVLTree<T> implements BinarySearchTree<T> {
    private MyComparator<T> comparator;
    private int size;
    private AVLTreeNode root;

    private class AVLTreeNode {
        T data;
        AVLTreeNode left;
        AVLTreeNode right;
        AVLTreeNode parent;
        int height;

        AVLTreeNode(T data, AVLTreeNode parent) {
            this.data = data;
            this.parent = parent;
            this.left = null;
            this.right = null;
            height = 1;
            size = 0;
        }

        void restoreHeight() {
            int lh = left != null ? left.height : 0;
            int rh = right != null ? right.height : 0;
            height = Math.max(lh, rh) + 1;
        }
        int balance() {
            int lh = left != null ? left.height : 0;
            int rh = right != null ? right.height : 0;
            return lh - rh;
        }
    }

    public AVLTree(MyComparator<T> comparator) {
        this.comparator = comparator;
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
    private AVLTreeNode minRec(AVLTreeNode root) {
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
    private AVLTreeNode maxRec(AVLTreeNode root) {
        if (root.right == null)
            return root;
        return maxRec(root.right);
    }

    @Override
    public void insert(T value) {
        if(isEmpty())
            root = new AVLTreeNode(value, null);
        else
            root = insertRec(root, value);
        size++;
    }

    private AVLTreeNode insertRec(AVLTreeNode root, T value) {
        if(comparator.compare(value, root.data) == 0)
            return root;
        else if(comparator.compare(value, root.data) < 0) {
            return insertToLeftRec(root, value);
        }
        else {
            return insertToRightRec(root, value);
        }

    }

    private AVLTreeNode insertToRightRec(AVLTreeNode root, T value) {
        if(root.right == null)
            insertToRight(root, value);
        else
            insertRec(root.right, value);
        root.restoreHeight();
        if(root.balance() == -2) {
            AVLTreeNode right = root.right;
            if(right.balance() == -1) {
                rotateLeft(root);
                return right;
            } else {
                right = rotateRight(right);
                root.restoreHeight();
                return rotateLeft(root);
            }
        }
        return root;
    }
    private AVLTreeNode insertToLeftRec(AVLTreeNode root, T value) {
        if(root.left == null)
            insertToLeft(root, value);
        else
            insertRec(root.left, value);
        root.restoreHeight();
        if(root.balance() == 2) {
            AVLTreeNode left = root.left;
            if(left.balance() == 1)
                return rotateRight(root);
            else {
                left = rotateLeft(left);
                root.restoreHeight();
                return rotateRight(root);
            }
        }
        return root;
    }

    private void insertToRight(AVLTreeNode root, T value) {
        root.right = new AVLTreeNode(value, root);
        root.restoreHeight();
    }

    private void insertToLeft(AVLTreeNode root, T value) {
        root.left = new AVLTreeNode(value, root);
        root.restoreHeight();
    }

    @Override
    public void delete(T value) {
        AVLTreeNode node = searchRec(root, value);
        if(node == null)
            return;
        root = deleteRec(node);
        --size;
    }

    private AVLTreeNode deleteRec(AVLTreeNode node) {
        if (isLeaf(node))
            replaceOnParent(node, null);
        else if(node.right != null) {
            return removeFromRight(node);
        } else {
            return removeFromLeft(node);
        }
        return null;
    }

    private AVLTreeNode removeFromLeft(AVLTreeNode node) {
        AVLTreeNode pred = maxRec(node.left);
        node.data = pred.data;
        deleteRec(pred);

        node.restoreHeight();
        if(node.balance() == -2) {
            AVLTreeNode right = root.right;
            if(right.balance() == -1) {
                rotateLeft(root);
                return right;
            } else {
                right = rotateRight(right);
                root.restoreHeight();
                return rotateLeft(root);
            }
        }
        return null;
    }

    private AVLTreeNode removeFromRight(AVLTreeNode node) {
        AVLTreeNode succ = minRec(node.right);
        node.data = succ.data;
        deleteRec(succ);

        node.restoreHeight();
        if(node.balance() == 2) {
            AVLTreeNode left = root.left;
            if(left.balance() == 1)
                return rotateRight(root);
            else {
                left = rotateLeft(left);
                root.restoreHeight();
                return rotateRight(root);
            }
        }
        return null;
    }

    private boolean isLeaf(AVLTreeNode node) {
        return node.left == null && node.right == null;
    }

    private AVLTreeNode rotateLeft(AVLTreeNode center) {
        AVLTreeNode right = center.right;
        replaceOnParent(center, right);
        center.right = right.left;
        center.right.parent = center;
        right.left = center;
        center.parent = right;
        center.restoreHeight();
        right.restoreHeight();
        return right;
    }
    private AVLTreeNode rotateRight(AVLTreeNode center) {
        AVLTreeNode left = center.left;
        replaceOnParent(center, left);
        center.left = left.right;
        center.left.parent = center;
        left.right = center;
        center.parent = left;
        center.restoreHeight();
        left.restoreHeight();
        return left;
    }

    private void replaceOnParent(AVLTreeNode node, AVLTreeNode newNode) {
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
        AVLTreeNode pred = predecessorRec(root, null, value);
        if(pred == null)
            return null;
        return pred.data;
    }

    private AVLTreeNode predecessorRec(AVLTreeNode root, AVLTreeNode lastRightParent, T value) {
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
        AVLTreeNode succ = successorRec(root, null, value);
        if(succ == null)
            return null;
        return succ.data;
    }

    private AVLTreeNode successorRec(AVLTreeNode root, AVLTreeNode lastLeftParent, T value) {
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
        AVLTreeNode node = searchRec(root, value);
        if(node == null)
            return false;
        return true;
    }

    @Override
    public ArrayList<T> getOrderedItems() {
        return getOrderedItemsRec(root);
    }

    private ArrayList<T> getOrderedItemsRec(AVLTreeNode root) {
        ArrayList<T> res = new ArrayList<>();
        if(root == null)
            return res;
        res.addAll(getOrderedItemsRec(root.left));
        res.add(root.data);
        res.addAll(getOrderedItemsRec(root.right));
        return res;
    }

    private AVLTreeNode searchRec(AVLTreeNode root, T value) {
        if(root == null)
            return null;
        if(comparator.compare(root.data, value) == 0)
            return root;
        if(comparator.compare(root.data, value) > 0)
            return searchRec(root.left, value);
        return searchRec(root.right, value);
    }
}
