public class Program {
    public static void main(String[] args) {
        HashTable hashTable = new OffsetHashTable(19);

        hashTable.insert(73);
        hashTable.print();
        System.out.println();

        hashTable.insert(36);
        hashTable.print();
        System.out.println();

        hashTable.insert(57);
        hashTable.print();
        System.out.println();

        hashTable.insert(33);
        hashTable.print();
        System.out.println();

        hashTable.insert(54);
        hashTable.print();
        System.out.println();

        hashTable.insert(12);
        hashTable.print();
        System.out.println();

        hashTable.insert(32);
        hashTable.print();
        System.out.println();

        hashTable.insert(24);
        hashTable.print();
        System.out.println();

        hashTable.insert(42);
        hashTable.print();
        System.out.println();

        hashTable.insert(41);
        hashTable.print();
        System.out.println();

        hashTable.insert(52);
        hashTable.print();
        System.out.println();

        hashTable.insert(3);
        hashTable.print();
        System.out.println();

        hashTable.insert(31);
        hashTable.print();
        System.out.println();

        hashTable.remove(12);
        hashTable.print();
        System.out.println();

        hashTable.remove(3);
        hashTable.print();
        System.out.println();

        hashTable.remove(36);
        hashTable.print();
        System.out.println();

        hashTable.insert(32);
        hashTable.print();
        System.out.println();

        hashTable.insert(20);
        hashTable.print();
        System.out.println();

        hashTable.insert(91);
        hashTable.print();
        System.out.println();

        hashTable.remove(33);
        hashTable.print();
        System.out.println();

        hashTable.remove(54);
        hashTable.print();
        System.out.println();

        hashTable.insert(77);
        hashTable.print();
        System.out.println();

        hashTable.insert(28);
        hashTable.print();
        System.out.println();

        hashTable.insert(36);
        hashTable.print();
        System.out.println();

        hashTable.remove(24);
        hashTable.print();
        System.out.println();

        hashTable.remove(32);
        hashTable.print();
        System.out.println();

        hashTable.remove(77);
        hashTable.print();
        System.out.println();

        hashTable.remove(57);
        hashTable.print();
        System.out.println();

        hashTable.remove(20);
        hashTable.print();
        System.out.println();
    }
}
