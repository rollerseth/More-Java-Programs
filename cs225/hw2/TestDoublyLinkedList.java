import org.junit.Test;
import org.junit.Assert;
import java.util.Iterator;

public class TestDoublyLinkedList {
  
  @Test
  public void testIsEmpty() {
    DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
    Assert.assertTrue(list.isEmpty());
    Assert.assertEquals(list.length(), 0);
  }

    
  @Test
  public void testInsertFirst() {
    DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
    for (int i = 0; i < 5; i++) {
      list.insertFirst(i);
      Assert.assertEquals(list.length(), i + 1);
      // Check contents of each position.
      for (int j = 0; j <= i; j++) {
	Assert.assertEquals((int) list.get(j), i - j);
      }
    }
  }
    
    
  @Test
  public void testInsertLast() {
    DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
    for (int i = 0; i < 5; i++) {
      list.insertLast(i);
      Assert.assertEquals(list.length(), i + 1);
      // Check contents of each position.
      for (int j = 0; j <= i; j++) {
	Assert.assertEquals((int) list.get(j), j);
      }
    }
  }
    
	
  @Test
  public void testInsertIntoEmpty() {
    DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
    list.insert(0, 0);
    Assert.assertEquals(list.length(), 1);
    Assert.assertEquals((int) list.get(0), 0);
    // Insert at position 1.
    boolean thrown = false;
    list = new DoublyLinkedList<>();
    try {
      list.insert(1, 1);
    }
    catch (IndexOutOfBoundsException e) {
      thrown = true;
    }
    Assert.assertTrue(thrown);
  }

  @Test
  public void testInsertIntoLength1() {
    // Insert at position 0.
    DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
    list.insertFirst(1);
    list.insert(0, 0);
    Assert.assertEquals((int) list.get(0), 0);
    Assert.assertEquals((int) list.get(1), 1);
    Assert.assertEquals(list.length(), 2);
    // Insert at position 1.
    list = new DoublyLinkedList<>();
    list.insertFirst(0);
    list.insert(1, 1);
    Assert.assertEquals((int) list.get(0), 0);
    Assert.assertEquals((int) list.get(1), 1);
    Assert.assertEquals(list.length(), 2);
    boolean thrown = false;
    // Insert at position 2.
    list = new DoublyLinkedList<>();
    list.insertFirst(0);
    try {
      list.insert(2, 1);
    }
    catch (IndexOutOfBoundsException e) {
      thrown = true;
    }
    Assert.assertTrue(thrown);
  }

  @Test
  public void testInsert() {
    for (int i = 0; i < 5; i++) {
      DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
      for (int j = 0; j < 5; j++) {
	list.insertLast(j);
      }
      list.insert(i, -1);
      for (int j = 0; j < i; j++) {
	Assert.assertEquals((int) list.get(j), j);
      }
      Assert.assertEquals((int) list.get(i), -1);
      for (int j = i + 1; j < 6; j++) {
	Assert.assertEquals((int) list.get(j), j - 1);
      }
      boolean thrown = false;
      try {
	list.insert(7, 0);
      }
      catch (IndexOutOfBoundsException e) {
	thrown = true;
      }
      Assert.assertTrue(thrown);
    }
  }

  @Test
  public void testRemoveFirst() {
    DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
    for (int i = 0; i < 5; i++) {
      list.insertLast(i);
    }
    for (int i = 0; i < 5; i++) {
      Assert.assertEquals((int) list.removeFirst(), i);
      Assert.assertEquals(list.length(), 4 - i);
    }
    Assert.assertTrue(list.isEmpty());
    boolean thrown = false;
    try {
      list.removeFirst();
    }
    catch (IndexOutOfBoundsException e) {
      thrown = true;
    }
    Assert.assertTrue(thrown);
  }

  @Test
  public void testRemoveLast() {
    DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
    for (int i = 0; i < 5; i++) {
      list.insertFirst(i);
    }
    for (int i = 0; i < 5; i++) {
      Assert.assertEquals((int) list.removeLast(), i);
      Assert.assertEquals(list.length(), 4 - i);
    }
    Assert.assertTrue(list.isEmpty());
    boolean thrown = false;
    try {
      list.removeFirst();
    }
    catch (IndexOutOfBoundsException e) {
      thrown = true;
    }
    Assert.assertTrue(thrown);
  }

  @Test
  public void testRemoveFromLength1() {
    // Remove from position 0.
    DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
    list.insertFirst(0);
    Assert.assertEquals((int) list.remove(0), 0);
    Assert.assertEquals(list.length(), 0);
    Assert.assertTrue(list.isEmpty());
    // Remove from position 1.
    list = new DoublyLinkedList<>();
    list.insertFirst(0);
    boolean thrown = false;
    try {
      list.remove(1);
    }
    catch (IndexOutOfBoundsException e) {
      thrown = true;
    }
    Assert.assertTrue(thrown);
  }

  @Test
  public void testRemove() {
    for (int i = 0; i < 5; i++) {
      DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
      for (int j = 0; j < 5; j++) {
	list.insertLast(j);
      }
      Assert.assertEquals((int) list.remove(i), i);
      for (int j = 0; j < i; j++) {
	Assert.assertEquals((int) list.get(j), j);
      }
      for (int j = i; j < 4; j++) {
	Assert.assertEquals((int) list.get(j), j + 1);
      }
      Assert.assertEquals((int) list.length(), 4);
    }
  }

  @Test
  public void testGetFirst() {
    DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
    for (int i = 0; i < 5; i++) {
      list.insertFirst(i);
      Assert.assertEquals((int) list.getFirst(), i);
    }
  }

  @Test
  public void testGetLast() {
    DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
    for (int i = 0; i < 5; i++) {
      list.insertLast(i);
      Assert.assertEquals((int) list.getLast(), i);
    }
  }

  @Test
  public void testSet() {
    DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
    boolean thrown = false;
    try {
      list.set(0, 0);
    }
    catch (IndexOutOfBoundsException e) {
      thrown = true;
    }
    Assert.assertTrue(thrown);
    for (int i = 0; i < 5; i++) {
      list = new DoublyLinkedList<>();
      for (int j = 0; j < 5; j++) {
	list.insertLast(j);
      }
      list.set(i, -1);
      for (int j = 0; j < i; j++) {
	Assert.assertEquals((int) list.get(j), j);
      }
      Assert.assertEquals((int) list.get(i), -1);
      for (int j = i + 1; j < 5; j++) {
	Assert.assertEquals((int) list.get(j), j);
      }
    }
  }

  @Test
  public void testIterator() {
    DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
    for (int i = 0; i < 5; i++) {
      list.insertLast(i);
    }
    Iterator<Integer> it = list.iterator();
    for (int i = 0; i < 5; i++) {
      Assert.assertTrue(it.hasNext());
      Assert.assertEquals((int) it.next(), i);
    }
    Assert.assertFalse(it.hasNext());
  }
}
