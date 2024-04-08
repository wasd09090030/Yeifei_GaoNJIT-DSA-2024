package oy.tol.tra;

public class KeyValueBSearchTree<K extends Comparable<K>, V> implements Dictionary<K, V> {

    // This is the BST implementation, KeyValueHashTable has the hash table
    // implementation

    private TreeNode<K, V> root;
    private int count = 0;
    private int maxTreeDepth = 0;




    @Override
    public Type getType() {
        return Type.BST;
    }

    @Override
    public int size() {

        return this.count;
    }


    @Override
    public String getStatus() {
        String toReturn = "Tree has max depth of " + this.maxTreeDepth + ".\n";
        toReturn += "Longest collision chain in a tree node is " + TreeNode.longestCollisionChain + "\n";
        TreeAnalyzerVisitor<K, V> visitor = new TreeAnalyzerVisitor<>();
        this.root.accept(visitor);
        toReturn += "Min path height to bottom: " + visitor.minHeight + "\n";
        toReturn += "Max path height to bottom: " + visitor.maxHeight + "\n";
        toReturn += "Ideal height if balanced: " + Math.ceil(Math.log(this.count)) + "\n";
        return toReturn;
    }

    @Override
    public boolean add(K key, V value) throws IllegalArgumentException, OutOfMemoryError {

        if (null == key || null == value) {
            throw new IllegalArgumentException("Keys or values cannot be null");
        }
        int i=0;


        if (null == this.root) {
            this.root = new TreeNode<>(key, value);
            this.count++;

            return true;}
        else{

            i= this.root.insert(key, value, key.hashCode());


        }

        if(i==0){
            return true;

        }
        else{
            this.count++;

        }

        return i==1;

    }





    @Override
    public V find(K key) throws IllegalArgumentException {
        V value=this.root.find(key, key.hashCode());
        return value;
    }

    @Override
    public void ensureCapacity(int size) throws OutOfMemoryError {
        // Nothing to do here. Trees need no capacity.
    }

    @Override
    public Pair<K, V>[] toSortedArray() {
        TreeToArrayVisitor<K, V> visitor = new TreeToArrayVisitor<>(this.count);
        this.root.accept(visitor);
        Pair<K, V>[] sorted = visitor.getArray();
        Algorithms.fastSort(sorted);
        return sorted;
    }

    @Override
    public void compress() throws OutOfMemoryError {
        // Nothing to do here, since BST does not use extra space like array based
        // structures.
    }

}
