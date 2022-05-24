public class Program {
    public static void main(String[] args) {
        BTree tree = new BTree(2);

        tree.insert(100);
        tree.print();
        System.out.println("***********************");
        tree.insert(50);
        tree.print();
        System.out.println("***********************");
        tree.insert(150);
        tree.print();
        System.out.println("***********************");
        tree.insert(200);
        tree.print();
        System.out.println("***********************");
        tree.remove(200);
        tree.print();
        System.out.println("***********************");
        tree.remove(50);
        tree.print();
        System.out.println("***********************");
        tree.remove(100);
        tree.print();
        System.out.println("***********************");
        tree.remove(60);
        tree.print();
        System.out.println("***********************");
        tree.remove(150);
        tree.print();
        System.out.println("***********************");
    }
}
