package oy.tol.tra;

import java.util.Stack;

/**
 * An implementation of the StackInterface.
 * <p>
 * TODO: Students, implement this so that the tests pass.
 * 
 * Note that you need to implement construtor(s) for your concrete StackImplementation, which
 * allocates the internal Object array for the Stack:
 * - a default constructor, calling the StackImplementation(int size) with value of 10.
 * - StackImplementation(int size), which allocates an array of Object's with size.
 *  -- remember to maintain the capacity and/or currentIndex when the stack is manipulated.
 */
public class StackImplementation<E> implements StackInterface<E> {

   private Object [] itemArray;
   private int capacity;
   private int currentIndex = -1;
   private static final int DEFAULT_STACK_SIZE = 10;
   Stack<E> stack01= new Stack<E>();

   /**
    * Allocates a stack with a default capacity.
    * @throws StackAllocationException
    */
   public StackImplementation() throws StackAllocationException {
      // TODO: call the constructor with size parameter with default size of 10.
      
   }

   /** TODO: Implement so that
    * - if the size is less than 2, throw StackAllocationException
    * - if the allocation of the array throws with Java exception,
    *   throw StackAllocationException.
    * @param capacity The capacity of the stack.
    * @throws StackAllocationException If cannot allocate room for the internal array.
    */
   public StackImplementation(int capacity) throws StackAllocationException {
      
   }

   @Override
   public int capacity() {
      return this.stack01.capacity();
      // TODO: Implement this
      
   }

   @Override
   public void push(E element) throws StackAllocationException, NullPointerException {
      // TODO: Implement this，
      this.stack01.push(element);
               
   }

   @SuppressWarnings("unchecked")
   @Override
   public E pop() throws StackIsEmptyException {
      return  this.stack01.pop();
   }

   @SuppressWarnings("unchecked")
   @Override
   public E peek() throws StackIsEmptyException {
      return this.stack01.peek();
   }

   @Override
   public int size() {
      // TODO: Implement this
      return this.stack01.size();
      
   }

   @Override
   public void clear() {
      // TODO: Implement this
      
   }

   @Override
   public boolean isEmpty() {
      // TODO: Implement this
      return this.stack01.empty();
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
