// Author: Seth Roller

import java.util.concurrent.*;

public class Beverages {

    static subThread[] threads;
    static String beverage;

    static class subThread extends Thread {

	ArrayBlockingQueue<Integer> readQ;
	ArrayBlockingQueue<Integer> writeQ;
		
	public void run() {
	    try {
		Integer n = readQ.take();
		System.out.println();

		// different print options for singular and plural grammar
		if (n > 1) {
		    System.out.printf("%d bottles of %s on the wall, ",
				  n, beverage);
		    System.out.printf("%d bottles of %s.\n", n, beverage);
		}

		if (n == 1) {
		    System.out.printf("%d bottle of %s on the wall, ",
				  n, beverage);
		    System.out.printf(" %d bottle of %s.\n", n, beverage);
		}
		
		System.out.printf("Take one down, pass it around,");

		if (n != 2)
		    System.out.printf(" %d bottles of %s on the wall.\n",
				      n - 1, beverage);

		if (n == 2) 
		    System.out.printf(" %d bottle of %s on the wall.\n",
				      n - 1, beverage);

		
		if (n > 1)
		    writeQ.put(n - 1);
	    }

	    catch (InterruptedException e) {
	    }
	}
	
    }
    
    public static void main(String argv[]) {

	if (argv.length != 2) {
	    System.err.println("Usage: Incorrect number of arguments.\n");
	    System.exit(1);
	}
	
	String bev = argv[0];
	int num = Integer.parseInt(argv[1]);
	
	beverage = bev;
	threads = new subThread[num];

	for (int j = 0; j < num; j++)
	    threads[j] = new subThread();

	// assign num to the first readQ
	try {
	    ArrayBlockingQueue<Integer> st = new ArrayBlockingQueue<>(1);
	    st.put(num);
	    threads[num - 1].readQ = st;
	}

	catch (InterruptedException e) {
	}
	    
	for (int i = num - 1; i >= 0; i--) {

	    threads[i].start();
	    
	    if (i > 0) {
		// Dr. Sullivan helped with the code below
		
		ArrayBlockingQueue<Integer> q = new ArrayBlockingQueue<>(1);
		threads[i].writeQ = q;
		threads[i - 1].readQ = q;
	    }

	}
	
    }
    
}
    

