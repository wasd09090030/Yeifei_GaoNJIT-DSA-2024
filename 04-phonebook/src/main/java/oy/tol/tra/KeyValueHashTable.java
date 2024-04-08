package oy.tol.tra;





public class KeyValueHashTable<K extends Comparable<K>, V> implements Dictionary<K, V> {



    private Pair<K, V>[] values = null;
    private int count = 0;
    private int collisionCount = 0;
    private int maxProbingSteps = 0;
    private int reallocationCount = 0;
    private int capacity01 =0;

    private static final double LOAD_FACTOR = 0.45;
    private static final int DEFAULT_CAPACITY = 20;

    public KeyValueHashTable(int capacity) throws OutOfMemoryError {
        this.ensureCapacity(capacity);
    }

    public KeyValueHashTable() throws OutOfMemoryError {
        this.ensureCapacity(DEFAULT_CAPACITY);
    }

    @Override
    public Type getType() {
        return Type.HASHTABLE;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void ensureCapacity(int capacity) throws OutOfMemoryError {
        if (capacity < DEFAULT_CAPACITY) {
            capacity = DEFAULT_CAPACITY;
        }

        this.values = (Pair<K, V>[]) new Pair[(int) ((double) capacity * (1.0 + LOAD_FACTOR))];
        this.reallocationCount = 0;
        this.count = 0;
        this.collisionCount = 0;
        this.maxProbingSteps = 0;
        this.capacity01=(int) ((double) capacity * (1.0 + LOAD_FACTOR));
    }

    @Override
    public int size() {

        return this.count;
    }


    @Override
    public String getStatus() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Hash table load factor is %.2f%n", LOAD_FACTOR));
        builder.append(String.format("Hash table capacity is %d%n", this.values.length));
        builder.append(String.format("Current fill rate is %.2f%%%n", (this.count / (double)this.values.length) * 100.0));
        builder.append(String.format("Hash table had %d collisions when filling the hash table.%n", this.collisionCount));
        builder.append(String.format("Hash table had to probe %d times in the worst case.%n", this.maxProbingSteps));
        builder.append(String.format("Hash table had to reallocate %d times.%n", this.reallocationCount));
        return builder.toString();
    }

    @Override
    public boolean add(K key, V value) throws IllegalArgumentException, OutOfMemoryError {

        if (null == key || null == value) {
            throw new IllegalArgumentException("Keys or values cannot be null");
        }


        int i=0;
        int h = key.hashCode();
        int index=h%this.capacity01;
        Pair<K,V> element = new Pair<>(key,value);


        if (this.values[index] == null) {
            this.values[index] = element;
            this.count++;
            i=1;

        }else {

            if (h==this.values[index].getKey().hashCode()&&this.values[index].getKey().equals(key)) {
                this.values[index].setValue(value);
                i=1;


            }
            else{

                while(index>=0){
                    h++;
                    index=h%this.capacity01;
                    if(this.values[index]==null)
                        break;
                }

                this.values[index] = element;
                this.count++;
                this.collisionCount++;
                i=1;

            }

        }

        if (((double)this.count * (1.0 + LOAD_FACTOR)) >= this.values.length) {
            this.reallocate((int)((double)(this.values.length) * (1.0 / LOAD_FACTOR)));

        }
        return i==1;




        // Remember to get the hash key from the Person,
        // hash table computes the index for the Person (based on the hash value),
        // if index was taken by different Person (collision), get new hash and index,
        // insert into table when the index has a null in it,
        // return true if existing Person updated or new Person inserted.


    }

    @Override
    public V find(K key) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        int h = key.hashCode();
        int index = h % this.capacity01;

        Pair<K, V> node = this.values[index];
        if (node != null && node.getKey().equals(key)) {
            return node.getValue();
        } else {
            // Linear probing
            for (int i = 1; i < this.capacity01; i++) {
                int newIndex = (index + i) % this.capacity01;
                node = this.values[newIndex];
                if (node != null && node.getKey().equals(key)) {
                    return node.getValue();
                }
            }
        }

        return null;
    }


    @Override
    @java.lang.SuppressWarnings({"unchecked"})
    public Pair<K,V> [] toSortedArray() {
        Pair<K, V> [] sorted = (Pair<K,V>[])new Pair[this.count];
        int newIndex = 0;
        for (int index = 0; index < this.values.length; index++) {
            if (this.values[index] != null) {
                sorted[newIndex++] = new Pair<>(this.values[index].getKey(), this.values[index].getValue());
            }
        }
        Algorithms.fastSort(sorted);
        return sorted;
    }

    @SuppressWarnings("unchecked")
    private void reallocate(int newSize) throws OutOfMemoryError {
        if (newSize < DEFAULT_CAPACITY) {
            newSize = DEFAULT_CAPACITY;
        }
        this.reallocationCount++;
        Pair<K, V>[] oldPairs = this.values;
        this.values = (Pair<K, V>[]) new Pair[(int)((double)newSize * (1.0 + LOAD_FACTOR))];
        this.capacity01=(int)((double)newSize * (1.0 + LOAD_FACTOR));
        this.count = 0;
        this.collisionCount = 0;
        this.maxProbingSteps = 0;
        for (int index = 0; index < oldPairs.length; index++) {
            if (oldPairs[index] != null) {
                this.add(oldPairs[index].getKey(), oldPairs[index].getValue());
            }
        }
    }

    @Override
    public void compress() throws OutOfMemoryError {
        int newCapacity = (int)(this.count * (1.0 / LOAD_FACTOR));
        if (newCapacity < this.values.length) {
            this.reallocate(newCapacity);
        }
    }

}