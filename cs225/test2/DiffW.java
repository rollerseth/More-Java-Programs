public class DiffW {

    static class Node {
	String string;
	Node next;
    }

    static void diff(Node m, Node n) {

	while (m != null) {

	    if (n == null) {
		System.out.printf("< %s\n", m.string);
		m = m.next;
	    }
	    
	    else if (m.string.compareTo(n.string) < 0) {
		System.out.printf("< %s\n", m.string);
		m = m.next;
	    }

	    else if (m.string.compareTo(n.string) > 0) {
		System.out.printf("> %s\n", n.string);
		n = n.next;
	    }

	    else {
		m = m.next;
		n = n.next;
	    }

	} // end of first while 


	while (n != null) {
	    System.out.printf("> %s\n", n.string);
	    n = n.next;
	}
	

    }




}
