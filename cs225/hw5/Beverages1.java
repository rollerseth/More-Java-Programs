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
		
		if (n > 1) {
		    System.out.printf("%d bottles of %s on the wall, ",
				  n, beverage);
		    System.out.printf("%d bottles of %s.\n", n, beverage);
		}

		if (n == 1) {
		    System.out.printf("%d bottle of %s on the wall, ",
				  n, beverage);
		    System.out.printf("%d bottle of %s.\n", n, beverage);
		}
		
		System.out.printf("Take one down, pass it around,");

		if (n != 2)
		    System.out.printf(" %d bottles of %s on the wall.\n",
				      n - 1, beverage);

		if (n == 2)
		    System.out.printf(" %d bottle of %s on the wall.\n",
				      n - 1, beverage);
		System.out.println();

		if (n != 2)
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
	
	try {
	    ArrayBlockingQueue<Integer> st = new ArrayBlockingQueue<>(1);
	    st.put(num);

	    threads[num - 1].readQ = st;
	    //	    threads[num - 1].writeQ = st;
	    
	}

	catch (InterruptedException e) {
	}
	    
	for (int i = (num - 1); i >= 0; i--) {
	    System.out.println(i);
	    threads[i].start();
	    
	    if (i > 0) {
		threads[i - 1].writeQ = new ArrayBlockingQueue<>(1);
		threads[i - 1].readQ = new ArrayBlockingQueue<>(1);
		
		try {
		    Integer x = threads[i].writeQ.take();
		    threads[i - 1].readQ.put(x);
		}
		
		catch (InterruptedException e) {
		}
	    }

	}
	
    }
    
}
    

