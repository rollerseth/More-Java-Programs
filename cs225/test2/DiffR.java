public class DiffR {

    static class Node {
	String string;
	Node next;
    }
    
    static void diff(Node m, Node n) {

	if (m == null && n == null) System.exit(0);
	
	else if (m == null) {
	    System.out.printf("> %s\n", n.string);
	    diff(m, n.next);
	}

	else if (n == null) {
	    System.out.printf("< %s\n", m.string);
	    diff(m.next, n);
	}
    
	else if (m.string.compareTo(n.string) == 0) diff(m.next, n.next);
	
	else if (m.string.compareTo(n.string) < 0) {
	    System.out.printf("< %s\n", m.string);
	    diff(m.next, n);
	}

	else if (m.string.compareTo(n.string) > 0) {
	    System.out.printf("> %s\n", n.string);
	    diff(m, n.next);
	}

    
    }

}
