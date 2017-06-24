//Nina Yang
//CSE 373
//Description: extra entry class to help input values into hash table
//includes increment, getValue and getCount
public class entry {
	String value;
	int count;
	
	public entry(String value) {
		this.value = value;
		this.count = 1; 
	}
	
	public void increment() {
		count ++;
	}
	
	public String getValue() {
		return value;
	}
	
	public int getCount() {
		return count;
	}
}
