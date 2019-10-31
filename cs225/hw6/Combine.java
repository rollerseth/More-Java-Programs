import java.io.*;
import java.util.*;

public class Combine {

    public static void main(String arg[]) throws IOException {

	if (arg.length == 0) {
	    System.exit(0);
	}

	ArrayList<FileReader> list = new ArrayList<>(arg.length);
	ArrayList<Results> answers = new ArrayList<>(arg.length);
	ArrayList<BufferedReader> buffer = new ArrayList<>(arg.length);
	ArrayList<BufferedReader> finBuffer = new ArrayList<>();
	
	for (int i = 0; i < arg.length; i++) {
	    FileReader adding = new FileReader(arg[i]);
	    list.add(i, adding);
	    BufferedReader buffing = new BufferedReader(adding);
	    buffer.add(i, buffing);
	    String text;
	    if ((text = buffer.get(i).readLine()) != null) {
		FileReader addin = new FileReader(arg[i]);
                BufferedReader buff = new BufferedReader(addin);
                finBuffer.add(buff);
	    }
	}

	for (int i = 0; i < finBuffer.size(); i++) {
            String text;
            if ((text = finBuffer.get(i).readLine()) != null) {
                String holder[] = text.split(" ");
                Results huh = new Results(holder[0], holder[1],
                                          Integer.parseInt(holder[2]));
                answers.add(huh);
            }
        }

	//	int count = 0;
	while (answers.isEmpty() != true) {
	    
	    Results min = answers.get(0);
	    int removal = 0;
	    if (answers.size() > 1) {
		for (int j = 0; j < (answers.size() - 1); j++) {
		    if (Results.compareTo(min, answers.get(j + 1)) > 0) {
			min = answers.get(j + 1);
			removal = j + 1;
		    }
		}
	    }

	    //  count++;
	    System.out.printf("%s %s %d\n", min.first, min.last, min.score);
	    
	    String moreText;
	    if ((moreText = finBuffer.get(removal).readLine()) != null) {
		String hold[] = moreText.split(" ");
		answers.set(removal, new Results(hold[0], hold[1],
						 Integer.parseInt(hold[2])));
	    }

	    else if ((moreText = finBuffer.get(removal).readLine()) == null) 
		answers.remove(removal); 
	}
	//System.out.println(count);
    }
}


class Results {

    String first;
    String last;
    int score;
    
    public Results(String first, String last, int score) {
	this.first = first;
	this.last = last;
	this.score = score; 
    }

    public static int compareTo(Results r1, Results r2) {
	if (r1.last.compareTo(r2.last) > 0) 
	    return 1;

	else if (r1.last.compareTo(r2.last) < 0) 
	    return -1;

	else {
	    if (r1.first.compareTo(r2.first) > 0)
		return 1;
	    
	    else if (r1.first.compareTo(r2.first) < 0)
		return -1;

	    else {
		if (r1.score > r2.score)
		    return 1;
		
		else if (r2.score > r1.score)
		    return -1;
		
		else
		    return 0;
	    }
	}
    }
}
