public class Merge {

    static int[] merge(int[] a, int[]b) {
	int[] c = new int[a.length + b.length];
	
	int t = 0;
	int j = 0;
	int k = 0;

	while (t < a.length && j < b.length) {
	    if (a[t] < b[j]) {
		c[k] = a[t];
		k++;
		t++;
	    }

	    else {
		c[k] = b[j];
		k++;
		j++;
	    }
	}

	while (t < a.length) {
	    c[k] = a[t];
	    k++;
	    t++;
	}

	while (j < b.length) {
	    c[k] = b[j];
	    k++;
	    j++;
	}

	return c;
    }

}
