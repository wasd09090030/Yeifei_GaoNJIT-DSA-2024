package oy.tol.tra;




public class QueueImplementation <E> implements QueueInterface<E> {
    private Object[] itemArray;
    private int capacity;
    private int front = -1;
    private int rear = -1;


    public  QueueImplementation(int capacity) throws QueueAllocationException {
        if (capacity < 2)
            throw new QueueAllocationException("size-is-less-than-two");
        this.capacity = capacity;
        this.itemArray = new Object[capacity];
        this.front = -1;
        this.rear = -1;

    }


    public  QueueImplementation() throws QueueAllocationException {
        this.capacity = 10;
        this.itemArray = new Object[this.capacity];
        this.front = -1;
        this.rear = -1;

    }


    @Override
    public int capacity() {
        return this.capacity;

    }
    @Override
    public void enqueue(E element) throws QueueAllocationException, NullPointerException {

        if (element == null)
            throw new NullPointerException();
        else if (this.rear + 1 == this.capacity)
        {this.increaseCapacity();
            this.itemArray[++this.rear] = element;}
        else {
            this.itemArray[++this.rear] = element;
        }

    }

    public void increaseCapacity() {
        this.capacity = this.capacity * 2;
        Object[] itemArray01;
        itemArray01 = new Object[this.capacity];
        int t=0;



        for(int i = 0; i < this.itemArray.length; i++){
            if(this.itemArray[i]!=null){

                itemArray01[t] = this.itemArray[i];
                t++;

            }

        }
        this.itemArray=itemArray01;
        this.front=-1;
        this.rear=t-1;


    }







    @Override
    public E dequeue() throws QueueIsEmptyException {
        if (this.isEmpty())
            throw new QueueIsEmptyException("QueueIsEmpty");
        else
            this.front++;
        E element01=(E) this.itemArray[this.front];
        this.itemArray[this.front]=null;




        return element01;

    }
    @Override
    public E element() throws QueueIsEmptyException {
        if (this.isEmpty())
            throw new QueueIsEmptyException("QueueIsEmpty");
        else
            return (E) this.itemArray[this.front+1];


    }
    @Override
    public int size() {

        return this.rear-this.front;

    }
    @Override
    public boolean isEmpty() {
        return  this.rear == this.front;

    }
    @Override
    public void clear() {

        this.front=this.rear=-1;

    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        for (var index = this.front+1; index <= this.rear; index++) {
            builder.append(this.itemArray[index].toString());
            if (index < this.rear) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }






}
