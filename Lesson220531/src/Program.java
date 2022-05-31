public class Program {
    public static void main(String[] args) {
        HashTable hashTable = new ListHashTable(17);

        hashTable.insert(10);
        hashTable.print();
        System.out.println();

        hashTable.insert(20);
        hashTable.print();
        System.out.println();

        hashTable.insert(27);
        hashTable.print();
        System.out.println();

        hashTable.insert(37);
        hashTable.print();
        System.out.println();

        hashTable.insert(38);
        hashTable.print();
        System.out.println();

        hashTable.insert(19);
        hashTable.print();
        System.out.println();

        hashTable.insert(36);
        hashTable.print();
        System.out.println();

        hashTable.remove(20);
        hashTable.print();
        System.out.println();
    }
}
