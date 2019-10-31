//Author: Seth Roller

/**
 * This class contains a hash function 
 * that places and spaces out items in a hash table
 */

public class Hash {

  /**
   * Hash function that converts a string to a char
   * and applies a bitwise operation on this char by a
   * prime number
   * @param s String that will be converted to the hash value
   * @return hash value 
  */
  public static int hash(String s) {
      int h = 0;
      for(int i = 0; i < s.length(); i++)
	  h += (int)s.charAt(i)<<(i * 5);
      return h;
  }
}
