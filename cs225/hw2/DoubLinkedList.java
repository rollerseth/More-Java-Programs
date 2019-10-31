import java.util.Iterator;

public class DoublyLinkedList<T> implements Iterable<T>, List<T> {
  private class Node {
    T item;
    Node next;
    Node previous;
  }

  private Node head;
  private Node tail;

  public DoublyLinkedList() {
    head = null;
    tail = null;
  }

  public boolean isEmpty() {
    return head == null;
  }
  
  public int length() {
    Node node = head;
    int length = 0;
    while (node != null) {
      node = node.next;
      length++;
    }
    return length;
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
    newNode.next = head;
    head = newNode;
  }

  public void insertLast(T item) {
    Node newNode = new Node();
    newNode.item = item;
    newNode.next = null;
    if (head == null) {
      head = newNode;
    }
    else {
      Node node = head;
      while (node.next != null) {
	node = node.next;
      }
      node.next = newNode;
    }
  }

  public void insert(int n, T item) {
    if (n < 0) {
      throw new IndexOutOfBoundsException(n);
    }
    else if (n == 0) {
      insertFirst(item);
    }
    else {
      Node before = find(n - 1);
      Node newNode = new Node();
      newNode.item = item;
      newNode.next = before.next;
      before.next = newNode;
    }
  }

  public T removeFirst() {
    if (head == null) {
      throw new IndexOutOfBoundsException(0);
    }
    else {
      T result = head.item;
      head = head.next;
      return result;
    }
  }

  public T removeLast() {
    if (head == null) {
      throw new IndexOutOfBoundsException(length());
    }
    else if (head.next == null) {
      T result = head.item;
      head = head.next;
      return result;
    }
    else {
      Node node = head;
      while (node.next.next != null) {
	node = node.next;
      }
      T result = node.next.item;
      node.next = null;
      return result;
    }
  }

  public T remove(int n) {
    if (head == null) {
      throw new IndexOutOfBoundsException(n);
    }
    else if (n == 0) {
      return removeFirst();
    }
    else {
      Node node = find(n - 1);
      if (node.next == null) {
	throw new IndexOutOfBoundsException(n);
      }
      else {
	T result = node.next.item;
	node.next = node.next.next;
	return result;
      }
    }
  }
  
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

  public T getFirst() {
  if (head == null) {
      throw new IndexOutOfBoundsException(0);
    }
    else {
      return head.item;
    }
  }
  
  public T getLast() {
    if (head == null) {
      throw new IndexOutOfBoundsException(length());
    }
    else {
      Node node = head;
      while (node.next != null) {
	node = node.next;
      }
      return node.item;
    }
  }

  public T get(int n) {
    if (n < 0) {
      throw new IndexOutOfBoundsException(n);
    }
    else {
      return find(n).item;
    }
  }
  
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
