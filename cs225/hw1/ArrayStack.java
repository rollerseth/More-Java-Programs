import java.util.EmptyStackException;

//Author: Seth Roller

/**
 * Implements a stack using an array of size 10. If the array fills
 * or an attempt is made to pop an empty stack, the code breaks.
 */
public class ArrayStack<T> implements Stack<T> {
    int top;
    T[] items;
    int capacity;
    int increment;
    
  /**
   * Creates ArrayStack of capacity and increment 10
   */
    @SuppressWarnings("unchecked")  
    public ArrayStack() {
	this(10, 10);
    }

    
    public void push(T item) {
	int theSize = ++top;
	if (theSize == capacity)
	{
	    T[] theMore;
	    int theMerrier = increment + capacity;
	    theMore = (T[]) new Object[theMerrier];
	    for(int i = 0; i < capacity; i++)
		theMore[i] = items[i];
	    capacity += increment;
	    items = theMore;
	}
	    
	items[theSize] = item;
    }

  
    public T pop() {	
	if (top == -1)
	    throw new EmptyStackException();

	return items[top--];
    }

  
    public boolean isEmpty() {
	return top == -1;
    }

    /**
     * @if Creates the stack 
     * @throws IllegalArgumentException if params are illegal 
     */
    public ArrayStack(int capacity, int increment) {
	if (capacity > 0 && increment > 0)
	{
	    top = -1;
	    this.capacity = capacity;
	    this.increment = increment;
	    items = (T[]) new Object[capacity];		    
        
	}
	else
	    throw new IllegalArgumentException();
	  
    }

    /**
     * Creates a stack in which capacity and increment
     * are equal 
     */
    
    public ArrayStack(int capacity) {
	this(capacity, capacity);
      
    }

  
  
}
