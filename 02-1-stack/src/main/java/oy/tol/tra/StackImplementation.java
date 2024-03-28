package oy.tol.tra;


import java.util.Stack;


public class StackImplementation<E> implements StackInterface<E> {

   private Object[] itemArray;
   private int capacity;
   private int currentIndex = -1;
   private static final int DEFAULT_STACK_SIZE = 10;

   Stack<E> stack01 = new Stack<E>();

   
   public StackImplementation() throws StackAllocationException {
      this.capacity = DEFAULT_STACK_SIZE;
      this.itemArray = new Object[this.capacity];


   }

  
   public StackImplementation(int capacity) throws StackAllocationException {
      if (capacity < 2)
         throw new StackAllocationException("size-is-less-than-two");
      this.capacity = capacity;
      this.itemArray = new Object[capacity];

   }

   @Override
   public int capacity() {
      return this.capacity;
      

   }

   @Override
   public void push(E element) throws StackAllocationException, NullPointerException {
      
      if (element == null)
         throw new NullPointerException();
      else if (this.currentIndex + 1 == this.capacity)
         {this.IncreaseCapacity();
         this.itemArray[++this.currentIndex] = element;}
      else {
         this.itemArray[++this.currentIndex] = element;
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

   @SuppressWarnings("unchecked")
   @Override
   public E pop() throws StackIsEmptyException {
      if (this.currentIndex == -1) {
         throw new StackIsEmptyException("StackIsEmptyException");
      } else {
         E popElement = (E) this.itemArray[this.currentIndex];
         this.itemArray[this.currentIndex] = 0;
         this.currentIndex--;

         return popElement;
       

      }

   }

   @SuppressWarnings("unchecked")
   @Override
   public E peek() throws StackIsEmptyException {
      if (this.currentIndex == -1) {
         throw new StackIsEmptyException("StackIsEmptyException");
      } else {
         return (E) this.itemArray[this.currentIndex];
      }

   }

   @Override
   public int size() {
      
      return this.currentIndex+1;

   }

   @Override
   public void clear() {
     
      this.itemArray=null;
      this.currentIndex=-1;


   }

   @Override
   public boolean isEmpty() {

      return this.currentIndex == -1;
   

   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder("[");
      for (var index = 0; index <= this.currentIndex; index++) {
         builder.append(this.itemArray[index].toString());
         if (index < this.currentIndex) {
            builder.append(", ");
         }
      }
      builder.append("]");
      return builder.toString();
   }
}
