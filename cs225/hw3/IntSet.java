//Modified by Seth Roller

/**
 * This class implements sets of integers as linked lists
 * stored in increasing order. The empty set is represented 
 * by null. All operations having running time O(n) or O(1).
 */

public class IntSet {
  int element;
  IntSet next;

  // Disable the default constructor.
  private IntSet() {
  }

  // Create a node in the linked list.
  private IntSet(int element, IntSet next) {
    this.element = element;
    this.next = next;
  }

  /**
   * Create an empty set.
   * @return An empty set.
   */
  public static IntSet emptySet() {
    return null;
  }

  /**
   * Create a set with one element.
   * @param e The element to be placed in the set.
   * @return A set containing e.
   */
  public static IntSet singleton(int e) {
    return new IntSet(e, null);
  }

  /**
   * Determine whether an element is a member of a set.
   * @param e The element whose membership is to be determined.
   * @param s The set which may or may not contain e.
   * @return true if e is in s; false otherwise.
   */
  public static boolean isElementOf(int e, IntSet s) {
    if (s == null) return false;
    else if (s.element == e) return true;
    else return isElementOf(e, s.next);
  }

  /**
   * Create a set containing a range of integers.
   * @param m The lower bound for the range.
   * @param n The upper bound for the range.
   * @return A set containing m, m+1, ... , n.
   */
  public static IntSet range(int m, int n) {
    if (m > n) return null;
    else return new IntSet(m, range(m + 1, n));
  }

  /**
   * Compute the union of two sets.
   * @param s1 The first set.
   * @param s2 The second set.
   * @return The union of s1 and s2 = 
   *         { e | e is in s1 or e is in s2 }.
   */
  public static IntSet union(IntSet s1, IntSet s2) {
    if (s1 == null) return s2;
    else if (s2 == null) return s1;
    else if (s1.element < s2.element)
      return new IntSet(s1.element, union(s1.next, s2));
    else if (s2.element < s1.element)
      return new IntSet(s2.element, union(s1, s2.next));
    else return new IntSet(s1.element, union(s1.next, s2.next));
  }
		   
  /**
   * Compute the intersection of two sets.
   * @param s1 The first set.
   * @param s2 The second set.
   * @return The intersection of s1 and s2 = 
   *         { e | e is in s1 and e is in s2 }.
   */
  public static IntSet intersection(IntSet s1, IntSet s2) {
      if (s1 == null || s2 == null) return null;
       else if (s1.element < s2.element)
	  return intersection(s1.next, s2);
      else if (s2.element < s1.element)
	  return intersection(s1, s2.next);
      else return new IntSet(s1.element, intersection(s1.next, s2.next));
    
  }
		   
  /**
   * Compute the difference of two sets.
   * @param s1 The first set.
   * @param s2 The second set.
   * @return The difference of s1 and s2 = 
   *         { e | e is in s1 and e is not in s2 }.
   */
  public static IntSet difference(IntSet s1, IntSet s2) {
      if (s1 == null)
	  return null;
      else if (s2 == null)
	  return s1;
      else if (s1.element < s2.element)
	  return new IntSet(s1.element, difference(s1.next, s2));
      else if (s1.element > s2.element)
	  return difference(s1, s2.next);
      else return difference(s1.next, s2.next);
      
  }

  /**
   * Determine whether s1 is a subset of s2.
   * @param s1 The first set.
   * @param s2 The second set.
   * @return true if s1 is a subset of s2; false otherwise.
   */
  public static boolean isSubsetOf(IntSet s1, IntSet s2) {
      if (s1 == null) return true;
      else if (s2 == null && s1 != null) return false;
      else if (s1.element < s2.element)
	  return false;
      else if (s1.element > s2.element)
	  return isSubsetOf(s1, s2.next);
      else return isSubsetOf(s1.next, s2.next);
  }

  /**
  * Determine whether s1 and s2 are equal.
  * @param s1 The first set.
  * @param s2 The second set.
  * @return true if s1 and s2 are equal; false otherwise.
  */
  public static boolean areEqual(IntSet s1, IntSet s2) {
    if (s1 == null) return s2 == null;
    else if (s2 == null) return false;
    else if (s1.element == s2.element) return areEqual(s1.next, s2.next);
    else return false;
  }
      
  /**
   * Create a set containing the elements of s and e. s is unaltered.
   * @param The element to be included.
   * @param The set into which e should be added.
   * @return The set s union { e }.
   */
  private static IntSet include(int e, IntSet s) {
    if (s == null) return new IntSet(e, null);
    else if (e < s.element) return new IntSet(e, s);
    else if (e == s.element) return s;
    else return include(e, s.next);
  }

  // A helper function. Creates a set containing the integers
  // in an array a from n to the right.
  private static IntSet arrayToIntSet(int[] a, int n) {
    if (n == a.length) return null;
    else return include(a[n], arrayToIntSet(a, n+1));
  }

  /**
   * Creates a set containing the integers in an array.
   * @param a The array containing the elements of the set.
   * @return A set containing all of the integers in a.
   */
  public static IntSet arrayToIntSet(int[] a) {
    return arrayToIntSet(a, 0);
  }
  
  // A helper function. Returns a string representation of a
  // set without enclosing braces.
  private static String toString(IntSet s) {
      if (s.next != null)
	  return Integer.toString(s.element) + "," + toString(s.next);
      else return Integer.toString(s.element);

  }

  /**
   * Creates a string representation of a set. For example, the set 
   * containing 1, 2, and 3 is represented by the string "{1,2,3}".
   * @return The string representation.
   */
  public String toString() {
    return "{" + toString(this) + "}";
  }
}
