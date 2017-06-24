//Nina Yang
//CSE 373
//Description: QPHash table that uses quadratic probing to deal with collisions

public class QPHash {
		
		private entry[] hasharray;
		private int key = 0;
		private int size = 0;
		private int index = -1;
	
		// Implement a default size constructor for QPHash
		public QPHash(){
			hasharray = new entry[50000];
			size = 50000;
		}
		
		// Implement a given size constructor
		public QPHash(int startSize){
			hasharray = new entry[startSize];
			size = startSize;
		}

		// returns the next key in the hash table only once, order does not matter,
		// null once it has gone through all element
		public String getNextKey(){
			String s = null;
			while (s == null) { 
			    index++; 
			    while (hasharray[index] == null){
			    	index++;
			    	 if (index >= size) {
						return null;
					}
			    }
				s = hasharray[index].getValue();
			}
			return s;
		}

		// inserts the string keyToAdd into the hash table, if keyToAdd is already
		// in hash table then increment its count. if there's collision then use quadratic probing
		// to relocate key in hash table
		public void insert(String keyToAdd){
			for(int i = 0; ;i++){
				key = Math.abs((keyToAdd.hashCode() + (i * i)) % size);
				if (hasharray[key] == null) {
					hasharray[key] = new entry(keyToAdd);
					break;
				} else if (hasharray[key].getValue().equals(keyToAdd)) {
					hasharray[key].increment();
					break;
				}
			}
		}

		// returns the number of times keyToFind has been added to the hash table
		public int findCount(String keyToFind){
			for (int i = 0; ; i++) {
				key = Math.abs((keyToFind.hashCode() + (i * i)) % size);
				if (hasharray[key] == null) {
					return 0;
				} else if (hasharray[key].getValue().equals(keyToFind)) {
					return hasharray[key].getCount();
				}
			}
		}
		
		// Extra Credit
		private int hash(String keyToHash){
			int hash = 0;
			for (int i = 0; i < keyToHash.length(); i++) {
				hash += keyToHash.charAt(i)* Math.pow(31, keyToHash.length() - i - 1);
			}
			return hash;
			//EXTRA CREDIT: Implement your own String hash function here.
		}
}
