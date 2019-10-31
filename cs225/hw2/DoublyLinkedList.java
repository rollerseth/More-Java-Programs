import java.util.Iterator;

/**
 *Implements a doubly linked list by utilizing Nodes through a 
 *specified Node class.
 */
public class DoublyLinkedList<T> implements Iterable<T>, List<T> {

    /**
     *Creates a Node in which it contains an 
     *item and two other Nodes pointing toward other Nodes
     */
    private class Node {
	T item;
	Node next;
	Node previous;
    }

    private Node head;
    private Node tail;
    private int howLong;

    /**
     *Creates an empty Doubly Linked List
     */
    public DoublyLinkedList() {
	head = null;
	tail = null;
	howLong = 0;
    }

    public boolean isEmpty() {
	return (head == null && tail == null);
    }

    public int length() {
	return howLong;
    }

    public int search (T item) {
	Node node = head;
	int position = 0;
	while (node != null && !node.item.equals(item)) {
	    node = node.next;
	    position++;
	}
	return node == null ? -1 : position;
    }

    public void insertFirst(T item) {
	Node newNode = new Node();
	newNode.item = item;
	newNode.previous = null;
	
	if (length() == 0) {
	    newNode.next = null;
	    head = newNode;
	    tail = newNode;
	}

	else if (length() == 1) {
	    head.previous = newNode;
	    newNode.next = tail;
	    head = newNode;
	}
	
	else {
	    head.previous = newNode;
	    newNode.next = head;
	    head = newNode;
	}
	howLong++;
    }

    public void insertLast(T item) {
	Node newNode = new Node();
	newNode.item = item;
	newNode.next = null;
	if (length() == 0) {
	    insertFirst(item);
	}
	
	else if (length() == 1) {
	    newNode.previous = head;
	    tail = newNode;
	    head.next = tail;
	    howLong++;
	}
	
	else {
	    newNode.previous = tail;
	    tail.next = newNode;
	    tail = newNode;
	    howLong++;
	}

	
    }
    
    /**
     *@throws IndexOutOfBoundsException
     */
    public void insert(int n, T item) {
	if (n < 0) {
	    throw new IndexOutOfBoundsException(n);
	}
	
	else if (n == 0) {
	    insertFirst(item);
	}
	
	else if (n == length()) {
	    insertLast(item);
	}
	
	else {
	    Node before = find(n - 1);
	    Node newNode = new Node();
	    newNode.item = item;
	    newNode.next = before.next;
	    newNode.previous = before;
	    before.next.previous = newNode;
	    before.next = newNode;
	    howLong++;
	}
    }

    /**
     *@throws IndexOutOfBoundsException
     */
    public T removeFirst() {
	if (head == null) {
	    throw new IndexOutOfBoundsException(0);
	}
	
	else if (length() == 1) {
	    T result = head.item;
	    head = null;
	    tail = null;
	    howLong--;
	    return result;
	}

	else if (length() == 2) {
	    T result = head.item;
	    tail.previous = null;
	    head = head.next;
	    howLong--;
	    return result;
	}
	
	else {
	    T result = head.item;
	    head = head.next;
	    howLong--;
	    return result;
	}
    
    }
    
    /**
     *@throws IndexOutOfBoundsException
     */
    public T removeLast() {
	if (tail == null) {
	    throw new IndexOutOfBoundsException(length());
	}
	
	else if (length() == 1) {
	    T result = tail.item;
	    head = null;
	    tail = null;
	    howLong--;
	    return result;
	}
	
	else if (length() == 2){
	    T result = tail.item;
	    head.next = null;
	    Node newNode = head;
	    tail = newNode;
	    howLong--;
	    return result;
	    
	}

	else {
	    T result = tail.item;
	    tail = tail.previous;
	    tail.next = null;
	    howLong--;
	    return result;
	    
	}

    }
    
    /**
     *@throws IndexOutOfBoundsException
     */
    public T remove(int n) {
	if (head == null) {
	    throw new IndexOutOfBoundsException(n);
	}
	else if (n == 0) {
	    return removeFirst();
	}
	
	else if (n == (length() - 1)) {
	    return removeLast();
	}
	
	else {
	    Node node = find(n - 1);
	    if (node.next == null) {
		throw new IndexOutOfBoundsException(n);
	    }
	    else {
		T result = node.next.item;
		node.next.previous = node;
		node.next = node.next.next;
		howLong--;
		return result;
	    }
	}
    }

    /**
     *Returns a node specified by an index n
     *@param n The position of the node
     *@return node at position n 
     *@throws IndexOutOfBoundsException
     */
    private Node find(int n) {
	Node node = head;
	int i = n;
	while (node != null && i > 0) {
	    node = node.next;
	    i--;
	}
	
	if (node == null) {
	    throw new IndexOutOfBoundsException(n);
	}
	
	else {
	    return node;
	}
    }
    
    /**
     *@throws IndexOutOfBoundsException
     */
    public T getFirst() {
	if (head == null) {
	    throw new IndexOutOfBoundsException(0);
	}
	
	else {
	    return head.item;
	}
    }
    
    /**
     *@throws IndexOutOfBoundsException
     */
    public T getLast() {
	if (head == null) {
	    throw new IndexOutOfBoundsException(length());
	}
	
	else if (length() == 1)
	    return head.item;
	
	else
	    return tail.item;

    }
    
    /**
     *@throws IndexOutOfBoundsException
     */
    public T get(int n) {
	if (n < 0) {
	    throw new IndexOutOfBoundsException(n);
	}
	
	else {
	    return find(n).item;
	}
    }
    
    /**
     *@throws IndexOutOfBoundsException
     */
    public void set(int n, T item) {
	if (head == null) {
	    throw new IndexOutOfBoundsException(n);
	}
	
	else {
	    find(n).item = item;
	}
    }
    
    private class DoublyLinkedListIterator implements Iterator<T> {
	private DoublyLinkedList<T> list;
	private Node next;
	
	DoublyLinkedListIterator(DoublyLinkedList<T> list) {
	    this.list = list;
	    this.next = head;
	    
	}
	
	public boolean hasNext() {
	    return next != null;
	}
	
	public T next() {
	    T result = next.item;
	    next = next.next;
	    return result;
	}
    }

    public DoublyLinkedListIterator iterator() {
	return new DoublyLinkedListIterator(this);
    }
  
}
