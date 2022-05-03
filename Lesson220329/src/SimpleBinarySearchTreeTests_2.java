import org.junit.*;

public class SimpleBinarySearchTreeTests_2 extends Assert {
    class Comparator implements MyComparator<Integer> {
        @Override
        public int compare(Integer first, Integer second) {
            return first - second;
        }
    }
    @Test
    public void creation_NewTree_SizeEqualsZero() {
        BinarySearchTree<Integer> tree = new AVLTree<>(new Comparator());
        assertEquals(0, tree.size());
    }
    @Test
    public void min_InsertAscendingThreeItems_ReturnMin() {
        BinarySearchTree<Integer> tree = new AVLTree<>(new Comparator());
        tree.insert(2);
        tree.insert(4);
        tree.insert(6);
        assertEquals(2, tree.min().intValue());
    }
    @Test
    public void min_InsertDescendingThreeItems_ReturnMin() {
        BinarySearchTree<Integer> tree = new RBTree<>(new Comparator());
        tree.insert(6);
        tree.insert(4);
        tree.insert(2);
        assertEquals(2, tree.min().intValue());
    }
    @Test
    public void min_InsertUnorderedFourItems_ReturnMin() {
        BinarySearchTree<Integer> tree = new RBTree<>(new Comparator());
        tree.insert(4);
        tree.insert(6);
        tree.insert(2);
        tree.insert(8);
        assertEquals(2, tree.min().intValue());
    }
    @Test
    public void getOrderedItems_InsertUnorderedFourItems_ReturnCorrectOrder() {
        BinarySearchTree<Integer> tree = new RBTree<>(new Comparator());
        tree.insert(4);
        tree.insert(6);
        tree.insert(2);
        tree.insert(8);
        assertArrayEquals(new Integer[] {2, 4, 6, 8}, tree.getOrderedItems().toArray());
    }

    @Test
    public void delete_DeleteOnlyOneElement_TreeIsEmpty() {
        BinarySearchTree<Integer> tree = new AVLTree<>(new Comparator());
        tree.insert(4);
        tree.delete(4);
        assertArrayEquals(new Integer[] {}, tree.getOrderedItems().toArray());
        assertEquals(true, tree.isEmpty());
    }
    @Test
    public void delete_DeleteHalfElements_TreeStructureIsCorrect() {
        BinarySearchTree<Integer> tree = new RBTree<>(new Comparator());
        tree.insert(4);
        tree.insert(1);
        tree.insert(5);
        tree.insert(8);
        tree.insert(0);
        tree.insert(-1);

        tree.delete(4);
        tree.delete(8);
        tree.delete(0);

        assertArrayEquals(new Integer[] {-1, 1, 5}, tree.getOrderedItems().toArray());
        assertEquals(3, tree.size());
    }

    @Test
    public void insert_TreeStructureIsCorrect() {
        BinarySearchTree<Integer> tree = new RBTree<>(new Comparator());
        tree.insert(5);
        tree.insert(53);
        tree.insert(32);

        assertArrayEquals(new Integer[] {5, 32, 53}, tree.getOrderedItems().toArray());
        assertEquals(3, tree.size());
    }
    public void insert_TreeStructureIsCorrect_2() {
        BinarySearchTree<Integer> tree = new RBTree<>(new Comparator());
        tree.insert(5);
        tree.insert(3);
        tree.insert(4);

        assertArrayEquals(new Integer[] {5, 32, 53}, tree.getOrderedItems().toArray());
        assertEquals(3, tree.size());
    }

    @Test
    public void S_1() {
        BinarySearchTree<Integer> tree = new SimpleBinarySearchTree<>(new Comparator());
        tree.insert(11);
        tree.insert(31);
        tree.insert(18);
        tree.insert(22);
        tree.insert(72);
        tree.insert(72);
        tree.insert(78);
        tree.insert(5);
        tree.insert(40);
        tree.insert(15);
        tree.insert(52);
        tree.insert(47);

        System.out.println();
    }
    @Test
    public void S_2() {
        BinarySearchTree<Integer> tree = new SimpleBinarySearchTree<>(new Comparator());
        tree.insert(2);
        tree.insert(97);
        tree.insert(50);
        tree.insert(83);
        tree.insert(65);
        tree.insert(89);
        tree.insert(54);
        tree.insert(45);
        tree.insert(57);
        tree.insert(61);
        tree.insert(65);
        tree.insert(34);
        tree.insert(40);
        tree.insert(53);
        tree.insert(57);

        tree.delete(53);
        tree.delete(50);
        tree.delete(54);
        tree.delete(34);
        tree.delete(57);

        System.out.println();
    }

    @Test
    public void S_3() {
        BinarySearchTree<Integer> tree = new SimpleBinarySearchTree<>(new Comparator());
        tree.insert(83);
        tree.insert(46);
        tree.insert(6);
        tree.insert(91);
        tree.insert(6);
        tree.insert(71);
        tree.insert(62);
        tree.insert(86);
        tree.insert(73);
        tree.insert(86);
        tree.insert(68);
        tree.insert(34);
        tree.delete(73);
        tree.delete(34);
        tree.delete(46);
        tree.insert(61);
        tree.insert(24);
        tree.insert(31);
        tree.delete(31);
        tree.delete(68);
        tree.insert(49);
        tree.insert(87);
        tree.insert(56);
        tree.delete(87);
        tree.delete(6);
        tree.delete(24);
        tree.delete(86);
        tree.delete(71);

        System.out.println();
    }

    @Test
    public void AVL_1() {
        BinarySearchTree<Integer> tree = new AVLTree<>(new Comparator());
        tree.insert(91);
        tree.insert(14);
        tree.insert(47);
        tree.insert(48);
        tree.insert(45);
        tree.insert(75);
        tree.insert(7);
        tree.insert(64);
        tree.insert(96);
        tree.insert(79);
        tree.insert(17);
        tree.insert(3);

        System.out.println();
    }

    @Test
    public void AVL_2() {
        BinarySearchTree<Integer> tree = new AVLTree<>(new Comparator());
        tree.insert(37);
        tree.insert(68);
        tree.insert(26);
        tree.insert(31);
        tree.insert(18);
        tree.insert(68);
        tree.insert(61);
        tree.insert(97);
        tree.insert(69);
        tree.insert(44);
        tree.insert(87);
        tree.insert(78);
        tree.insert(86);
        tree.insert(67);
        tree.insert(17);

        tree.delete(97);
        tree.delete(87);
        tree.delete(67);
        tree.delete(18);
        tree.delete(17);

        System.out.println();
    }

    @Test
    public void AVL_3() {
        BinarySearchTree<Integer> tree = new AVLTree<>(new Comparator());
        tree.insert(11);
        tree.insert(94);
        tree.insert(35);
        tree.insert(14);
        tree.insert(17);
        tree.insert(2);
        tree.insert(20);
        tree.insert(87);
        tree.insert(13);
        tree.insert(60);
        tree.insert(98);
        tree.insert(64);
        tree.delete(64);
        tree.delete(20);
        tree.delete(11);
        tree.insert(67);
        tree.insert(65);
        tree.insert(85);
        tree.delete(94);
        tree.delete(60);
        tree.insert(38);
        tree.insert(97);
        tree.insert(82);
        tree.delete(97);
        tree.delete(65);
        tree.delete(87);
        tree.delete(38);
        tree.delete(35);

        System.out.println();
    }

    @Test
    public void RB_1() {
        BinarySearchTree<Integer> tree = new RBTree<>(new Comparator());
        tree.insert(20);
        tree.insert(36);
        tree.insert(89);
        tree.insert(66);
        tree.insert(25);
        tree.insert(63);
        tree.insert(3);
        tree.insert(80);
        tree.insert(71);
        tree.insert(93);
        tree.insert(20);
        tree.insert(80);

        System.out.println();
    }

    @Test
    public void RB_2() {
        BinarySearchTree<Integer> tree = new RBTree<>(new Comparator());
        tree.insert(77);
        tree.insert(20);
        tree.insert(98);
        tree.insert(5);
        tree.insert(27);
        tree.insert(68);
        tree.insert(30);
        tree.insert(85);
        tree.insert(65);
        tree.insert(93);
        tree.insert(35);
        tree.insert(86);
        tree.insert(34);
        tree.insert(69);
        tree.insert(11);
        tree.delete(98);
        tree.delete(11);
        tree.delete(20);
        tree.delete(68);
        tree.delete(34);

        System.out.println();
    }

    @Test
    public void RB_3() {
        BinarySearchTree<Integer> tree = new RBTree<>(new Comparator());
        tree.insert(5);
        tree.insert(53);
        tree.insert(32);
        tree.insert(23);
        tree.insert(14);
        tree.insert(99);
        tree.insert(1);
        tree.insert(28);
        tree.insert(88);
        tree.insert(77);
        tree.insert(17);
        tree.insert(9);
        tree.delete(88);
        tree.delete(77);
        tree.delete(28);
        tree.insert(28);
        tree.insert(80);
        tree.insert(90);
        tree.delete(32);
        tree.delete(28);
        tree.insert(84);
        tree.insert(60);
        tree.insert(43);
        tree.delete(9);
        tree.delete(17);
        tree.delete(60);
        tree.delete(5);
        tree.delete(90);

        System.out.println();
    }
}
