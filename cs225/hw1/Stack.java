// Author: Seth Roller

/**
 * A generic stack. A stack is a LIFO (last in first out) data structure. 
 * Access is provided only for the top item on the stack (the one last 
   pushed).
 */

public interface Stack<T> {

  /**
   * Pushes an item onto the top of the stack.
   * @param item The item to be pushed.
   */
  public void push(T item);

  /**
   * Pops the top item from the stack and returns it. Throws 
     EmptyStackException
   * if the stack is empty.
   * @return The top item from the stack.
   * @throws EmptyStackException
   */
  public T pop();

  /**
   * Determines whether the stack is empty.
   * @return true if the stack is empty, otherwise false.
   */
  public boolean isEmpty();
}

