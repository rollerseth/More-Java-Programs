public class Fill {

    static void fill(int[] a, int value) {
	
	Thread[] threads = new Thread[4];
	int c = a.length;
	int b = a.length / 4;
	
	for (int i = 0; i < 4; i++) {

	    threads[i] = new Thread( () -> {
			for (int f = a.length - c; f < b; f++)
			    a[i] = value;
			});
	    threads[i].start();
	    b = (a.length / 4) - (i * 4);
	    c = a.length - b;
	
	}

	for (int g = 0; g < 4; g++)
	    threads[i].join();
    	
    }

}
