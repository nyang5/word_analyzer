//Nina Yang
//CSE 373
//Description: ChainingHash that uses chaining technique to deal with collision
import java.util.*;

public class ChainingHash {
		private int size = 0;
		private ArrayList<ArrayList<entry>> hasharray;
		private int key = 0;
		private int index = 0;
		private int index2 = 0;
		
		// Implement a default size constructor for ChainingHash
		public ChainingHash(){
			hasharray = new ArrayList<ArrayList<entry>>();
			size = 5000;
			for(int i = 0; i < size; i++){ 
				hasharray.add(null);
			}
		}
		
		// Implement a given size constructor for ChainingHash
		public ChainingHash(int startSize){
			hasharray = new ArrayList<ArrayList<entry>>(startSize);
			size = startSize;
			for(int i = 0; i < size; i++){ 
				hasharray.add(null);
			}
		}


		// returns the next key on the hash table only once, the ordering does not matter
		// return null once it has gone through all element
		public String getNextKey(){
			String s = null;
			while (s == null) {
				if (hasharray.get(index) != null && index2 < hasharray.get(index).size()) {
					s = hasharray.get(index).get(index2).getValue();
					index2 ++;
				} else {
					index ++;
					index2 = 0;
				}
				if(index >= size) {
					return null;
				}
			}
			return s;
		}

		// insert keyToAdd into the hash table, if keyToAdd already exists in hash table
		// then increment its count. uses chaining to deal with collision 
		public void insert(String keyToAdd){
			key = Math.abs(keyToAdd.hashCode() % size);
			if (hasharray.get(key) == null) {
				hasharray.set(key, new ArrayList<entry>()); 
				hasharray.get(key).add(new entry(keyToAdd));
			} else {		
				boolean found=false;
				for (int i = 0; i < hasharray.get(key).size(); i++) {
					if (hasharray.get(key).get(i).getValue().equals(keyToAdd)) {
						hasharray.get(key).get(i).increment();
						found=true;
						break;
					}
				}
				if(!found){
					hasharray.get(key).add(new entry(keyToAdd)); 
				}
			}
		}

		// returns the number of times keyToFind has been added to the hash table
		public int findCount(String keyToFind){
			key = Math.abs(keyToFind.hashCode() % size);
			if (hasharray.get(key) == null) {
				return 0;
			} else {
				ArrayList<entry> array = hasharray.get(key);
				for (int i = 0; i < array.size(); i++) {
					if (array.get(i).getValue().equals(keyToFind)) {
						return array.get(i).getCount();
					}
				}
				return 0;
			}
		}
		
		// Extra Credit
		private int hash(String keyToHash){
			int hash = 0;
			for (int i = 0; i < keyToHash.length(); i++) {
				hash += keyToHash.charAt(i)* Math.pow(31, keyToHash.length() - i - 1);
			}
			return hash;
		}
}
