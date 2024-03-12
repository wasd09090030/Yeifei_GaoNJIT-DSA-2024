package oy.tol.tra;

import java.util.Queue;


public class QueueImplementation <E> implements QueueInterface<E> {
    private Object[] itemArray;
   private int capacity;
   private int front = -1;
   private int rear = -1;
   

   public  QueueImplementation(int capacity) throws QueueAllocationException {
    this.capacity = capacity;
    this.itemArray = new Object[capacity];

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
         {this.IncreaseCapacity();
         this.itemArray[++this.rear] = element;}
      else {
         this.itemArray[++this.rear] = element;
      }
    
}

public void IncreaseCapacity() {
    this.capacity = this.capacity * 2;
    Object[] itemArray01;
    itemArray01 = new Object[this.capacity];


    for(int i=0;i<this.itemArray.length;i++){
          itemArray01[i] = this.itemArray[i];
      }
    this.itemArray=itemArray01;


 }


@Override
public E dequeue() throws QueueIsEmptyException {
    if (this.isEmpty())
    throw new QueueIsEmptyException("QueueIsEmpty");
    else
    this.front++; 
    
    return (E) this.itemArray[this.front];
    
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
