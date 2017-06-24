//Nina Yang
//CSE 373
//Description: hashes two files into two different hashtables and compute the different
//squared difference, most different word and total comparative error between the two
//files
public class Test {

	public static void main(String[] args) {
		FileInput.init();
		
		// Initialize the two hash tables 
		QPHash qphash = new QPHash();
		ChainingHash chain = new ChainingHash();


		
		// Use the FileInput functions to read the two files.
		String[] reads = FileInput.readShakespeare();
		String[] readb = FileInput.readBacon();

		// Input the elements of those two files into two hash tables,
		// one file into a ChainingHash object and the other into a QPHash object.
		for (String s: reads) {
			qphash.insert(s);
		}
		for (String s: readb) {
			chain.insert(s);
		}
		
		// Initialize necessary variables for calculating the square error
		// and most distant word.
		double slength = reads.length;
		double blength = readb.length;
		double error = 0;
		double freq = 0;
		double freq1 = 0;
		String max = null;
		int distance = -1;
		String key;
		
		// Iterate through the first hash table(QPHash) and add sum the squared error
		// for these words.
		while((key = qphash.getNextKey()) != null){
			if (Math.abs(qphash.findCount(key)-chain.findCount(key)) > distance) {
				distance = Math.abs(qphash.findCount(key)-chain.findCount(key));
				max = key;
			}
			if (qphash.findCount(key) > 0 && chain.findCount(key) > 0 ) {
				freq = (double)qphash.findCount(key)/slength;
				freq1 = (double)chain.findCount(key)/blength;
				error += (freq-freq1)*(freq-freq1);
			} else if (qphash.findCount(key) > 0) {
				freq = (double)qphash.findCount(key)/slength;
				error += freq * freq;

			}
		}
		
		// Find  words in the second hash table(ChainingHash) that are not in the first 
		// and add their squared error to the total
		while ((key = chain.getNextKey()) != null) {
			if (Math.abs(qphash.findCount(key)-chain.findCount(key)) > distance) {
				distance = Math.abs(qphash.findCount(key)-chain.findCount(key));
				max = key;
			}
			if (chain.findCount(key) > 0 && qphash.findCount(key) <= 0) {
				freq = (double)chain.findCount(key)/blength;
				error += freq * freq;	
			}
		}

		// Print the total calculated squared error for the two tables and also the word with the highest distance.
		freq = (qphash.findCount(max)/slength)*(qphash.findCount(max)/slength);
		freq1 = (chain.findCount(max)/blength)*(chain.findCount(max)/blength);
		System.out.println("Most different square difference: " + max + " (square difference of " + Math.max(freq, freq1) + ")");
		System.out.println("Total comparative error: " + error);
	}

}
