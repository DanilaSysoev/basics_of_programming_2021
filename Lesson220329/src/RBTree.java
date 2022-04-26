import java.util.ArrayList;

public class RBTree<T> implements BinarySearchTree<T> {
    private MyComparator<T> comparator;
    private int size;
    private RBTreeNode root;

    private RBTreeNode nil;

    private class RBTreeNode {
        T data;
        RBTreeNode left;
        RBTreeNode right;
        RBTreeNode parent;
        boolean red;

        RBTreeNode(T data, RBTreeNode parent) {
            this.data = data;
            this.parent = parent;
            this.left = nil;
            this.right = nil;
            this.red = true;
        }

        void changeColor() {
            red = !red;
        }
        void toRed() {
            red = true;
        }
        void toBlack() {
            red = false;
        }
        boolean isRed() {
            return red;
        }
        boolean isBlack() {
            return !red;
        }
        void toColor(RBTreeNode node) {
            red = node.red;
        }
    }

    public RBTree(MyComparator<T> comparator) {
        this.comparator = comparator;
        nil = new RBTreeNode(null, null);
        nil.changeColor();
        root = nil;
    }

    @Override
    public boolean isEmpty() {
        return root == nil;
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
    private RBTreeNode minRec(RBTreeNode root) {
        if (root.left == nil)
            return root;
        return minRec(root.left);
    }

    @Override
    public T max() {
        if(isEmpty())
            return null;
        return maxRec(root).data;
    }
    private RBTreeNode maxRec(RBTreeNode root) {
        if (root.right == nil)
            return root;
        return maxRec(root.right);
    }

    private boolean isLeaf(RBTreeNode node) {
        return node.left == nil && node.right == nil;
    }

    private RBTreeNode rotateLeft(RBTreeNode center) {
        RBTreeNode right = center.right;
        replaceOnParent(center, right);
        center.right = right.left;
        center.right.parent = center;
        right.left = center;
        center.parent = right;
//        if(center == root)
//            root = right;
        return right;
    }
    private RBTreeNode rotateRight(RBTreeNode center) {
        RBTreeNode left = center.left;
        replaceOnParent(center, left);
        center.left = left.right;
        center.left.parent = center;
        left.right = center;
        center.parent = left;
//        if(center == root)
//            root = left;
        return left;
    }

    private void replaceOnParent(RBTreeNode node, RBTreeNode newNode) {
        if(node.parent == nil) {
            root = newNode;
            if(newNode != nil)
                newNode.parent = nil;
            return;
        }
        if(node.parent.left == node)
            node.parent.left = newNode;
        else
            node.parent.right = newNode;
        if(newNode != nil)
            newNode.parent = node.parent;
    }

    @Override
    public T predecessor(T value) {
        RBTreeNode pred = predecessorRec(root, null, value);
        if(pred == null)
            return null;
        return pred.data;
    }

    private RBTreeNode predecessorRec(RBTreeNode root, RBTreeNode lastRightParent, T value) {
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
        RBTreeNode succ = successorRec(root, null, value);
        if(succ == null)
            return null;
        return succ.data;
    }

    private RBTreeNode successorRec(RBTreeNode root, RBTreeNode lastLeftParent, T value) {
        if(root == null)
            return lastLeftParent;
        if(comparator.compare(root.data, value) == 0) {
            if(root.left != null)
                return maxRec(root.left);
            return lastLeftParent;
        }
        if(comparator.compare(root.data, value) < 0)
            return successorRec(root.right, lastLeftParent, value);
        return successorRec(root.left, root, value);
    }

    @Override
    public boolean search(T value) {
        RBTreeNode node = searchRec(root, value);
        return node != nil;
    }

    private RBTreeNode searchRec(RBTreeNode root, T value) {
        if(root == nil)
            return nil;
        if(comparator.compare(root.data, value) == 0)
            return root;
        if(comparator.compare(root.data, value) > 0)
            return searchRec(root.left, value);
        return searchRec(root.right, value);
    }

    @Override
    public ArrayList<T> getOrderedItems() {
        return getOrderedItemsRec(root);
    }

    private ArrayList<T> getOrderedItemsRec(RBTreeNode root) {
        ArrayList<T> res = new ArrayList<>();
        if(root == nil)
            return res;
        res.addAll(getOrderedItemsRec(root.left));
        res.add(root.data);
        res.addAll(getOrderedItemsRec(root.right));
        return res;
    }

    @Override
    public void insert(T value) {
        RBTreeNode inserted = null;
        if(root == nil) {
            root = new RBTreeNode(value, nil);
            inserted = root;
        } else {
            inserted = insertRec(root, value);
        }
        if(inserted == nil)
            return;
        TreeRestoreAfterInsert(inserted);
        ++size;
    }

    private void TreeRestoreAfterInsert(RBTreeNode node) {
        if(node == root)
            node.toBlack();
        else if(node.parent.isRed())
            RestoreAfterInsertNotRoot(node);
    }

    private void RestoreAfterInsertNotRoot(RBTreeNode node) {
        if(node.parent.left == node) {
            var parent = node.parent;
            if(parent.parent.left == parent) {
                if(parent.parent.right.isRed()) {
                    parent.parent.right.toBlack();
                    parent.parent.toRed();
                    parent.toBlack();
                    TreeRestoreAfterInsert(parent.parent);
                } else {
                    rotateRight(parent.parent);
                    node.parent.toBlack();
                    node.parent.right.toRed();
                }
            } else {
                if(parent.parent.left.isRed()) {
                    parent.parent.left.toBlack();
                    parent.parent.toRed();
                    parent.toBlack();
                    TreeRestoreAfterInsert(parent.parent);
                } else {
                    rotateRight(parent);
                    rotateLeft(node.parent.parent);
                    node.parent.toBlack();
                    node.parent.left.toRed();
                }
            }
        } else {
            var parent = node.parent;
            if(parent.parent.left == parent) {
                if(parent.parent.right.isRed()) {
                    parent.parent.right.toBlack();
                    parent.parent.toRed();
                    parent.toBlack();
                    TreeRestoreAfterInsert(parent.parent);
                } else {
                    rotateLeft(parent);
                    rotateRight(node.parent.parent);
                    node.parent.toBlack();
                    node.parent.right.toRed();
                }
            } else {
                if(parent.parent.left.isRed()) {
                    parent.parent.left.toBlack();
                    parent.parent.toRed();
                    parent.toBlack();
                    TreeRestoreAfterInsert(parent.parent);
                } else {
                    rotateLeft(node.parent.parent);
                    node.parent.toBlack();
                    node.parent.left.toRed();
                }
            }
        }
    }

    private RBTreeNode insertRec(RBTreeNode root, T value) {
        if(comparator.compare(value, root.data) == 0)
            return nil;
        else if(comparator.compare(value, root.data) < 0) {
            if(root.left == nil) {
                RBTreeNode newNode = new RBTreeNode(value, root);
                root.left = newNode;
                return newNode;
            }
            return insertRec(root.left, value);
        }
        else {
            if(root.right == nil) {
                RBTreeNode newNode = new RBTreeNode(value, root);
                root.right = newNode;
                return newNode;
            }
            return insertRec(root.right, value);
        }
    }

    @Override
    public void delete(T value) {
        var node = searchRec(root, value);
        if(node == nil)
            return;
        RBTreeNode replacer = deleteProcedure(node);
        if(deletedNode.isBlack())
            restoreAfterDeleting(replacer);
        --size;
    }

    private void restoreAfterDeleting(RBTreeNode node) {
        if(node == root)
            return;

        if(node.parent.left == node) {
            if(node.parent.right.isRed()) {
                rotateLeft(node.parent);
                node.parent.toRed();
                node.parent.parent.toBlack();
            }
            if(node.parent.right.left.isBlack() &&
               node.parent.right.right.isBlack()) {
                var oldParentIsBlack = node.parent.isBlack();
                node.parent.toBlack();
                node.parent.right.toRed();
                if (oldParentIsBlack)
                    restoreAfterDeleting(node.parent);
            } else {
                if (node.parent.right.right.isBlack()) {
                    rotateRight(node.parent.right);
                    node.parent.right.toBlack();
                    node.parent.right.right.toRed();
                }
                rotateLeft(node.parent);
                node.parent.parent.toColor(node.parent);
                node.parent.toBlack();
                node.parent.parent.right.toBlack();
            }
        } else {
            if(node.parent.left.isRed()) {
                rotateRight(node.parent);
                node.parent.toRed();
                node.parent.parent.toBlack();
            }
            if(node.parent.left.left.isBlack() &&
                node.parent.left.right.isBlack()) {
                var oldParentIsBlack = node.parent.isBlack();
                node.parent.toBlack();
                node.parent.left.toRed();
                if (oldParentIsBlack)
                    restoreAfterDeleting(node.parent);
            } else {
                if (node.parent.left.left.isBlack()) {
                    rotateLeft(node.parent.left);
                    node.parent.left.toBlack();
                    node.parent.left.left.toRed();
                }
                rotateRight(node.parent);
                node.parent.parent.toColor(node.parent);
                node.parent.toBlack();
                node.parent.parent.left.toBlack();
            }
        }
    }

    private RBTreeNode deletedNode;
    private RBTreeNode deleteProcedure(RBTreeNode node) {
        deletedNode = node;
        if(isLeaf(node)) {
            if(node == root)
                root = nil;
            replaceOnParent(node, nil);
            return nil;
        } else if(node.left == nil) {
            if(node == root)
                root = node.right;
            replaceOnParent(node, node.right);
            return node.right;
        } else if(node.right == nil) {
            if(node == root)
                root = node.left;
            replaceOnParent(node, node.left);
            return node.left;
        } else {
            var min = minRec(node.right);
            node.data = min.data;
            return deleteProcedure(min);
        }
    }
}
