import java.io.*;

public class HashTest {
  public static void main(String[] argv) throws IOException {
    if (argv.length != 1) {
      System.err.println("usage: java HashTest size");
      System.exit(1);
    }
			 
    int size = Integer.parseInt(argv[0]);
    int[] buckets = new int[size];

    String word;
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    while ((word = in.readLine()) != null) {
      buckets[Math.abs(Hash.hash(word) % size)]++;
    }
    double sum = 0;
    //    for (int i = 0; i < size; i++) {
    //      System.out.println(buckets[i]);
    //    }
    for (int i = 0; i < size; i++) {
      sum += buckets[i];
    }
    double mean = sum / size;
    double chisq = (buckets[0] - mean) * (buckets[0] - mean);
    int max = buckets[0];
    int min = buckets[0];
    for (int i = 1; i < size; i++) {
      chisq += (buckets[i] - mean) * (buckets[i] - mean);
      if (buckets[i] > max) max = buckets[i];
      else if (buckets[i] < min) min = buckets[i];
    }
    System.out.printf("%f\n", Math.sqrt(chisq));
  }
}
