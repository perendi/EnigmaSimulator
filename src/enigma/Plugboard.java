package enigma;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Daniel Matyas Perendi Plugboard class
 */
public class Plugboard {

	private HashMap<Integer, Integer> wiring = new HashMap<Integer, Integer>();

	/**
	 * Adds a new wire to the plugboard
	 * 
	 * @param f Index of first letter
	 * @param s Index of second letter
	 */
	public void add(int f, int s) {
		// Check bounds and plugboard
		if (checkNewWire(f, s)) {
			this.wiring.put(f, s);
			this.wiring.put(s, f);
		}
	}

	/**
	 * Deletes the specified wire
	 * 
	 * @param f Index of first letter
	 * @param s Index of second letter
	 */
	public void delete(int f, int s) {
		if (this.wiring.containsKey(f)) {
			this.wiring.remove(f);
			this.wiring.remove(s);
		}
	}

	/**
	 * Gets the current wirings
	 * 
	 * @return The current plugboard wirings
	 */
	public HashMap<Integer, Integer> get() {
		return wiring;
	}

	/**
	 * Validates the new wire
	 * 
	 * @param f Index of the first letter
	 * @param s Index of the second letter
	 * @return True if valid, False if invalid
	 */
	public boolean checkNewWire(int f, int s) {
		// Check bounds and plugboard
		if (f != s && f > -1 && f < 26 && s > -1 && s < 26 && !this.wiring.containsKey(f) || this.wiring.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Simulates the plugboard's functionality
	 * 
	 * @param input The index of the input letter
	 * @return The other end of the "wire"
	 */
	public int stecker(int input) {
		// Self-stecker
		if (!this.wiring.containsKey(input)) {
			return input;
		}
		// Get the other end of the wire
		else {
			return this.wiring.get(input);
		}
	}

	/**
	 * Gets a String list of pairs
	 */
	public ArrayList<String> getPairs() {
		ArrayList<String> result = new ArrayList<>();

		Object[] list = this.wiring.values().toArray();
		ArrayList<Integer> aList = new ArrayList<Integer>();

		for (Object l : list) {
			aList.add((int) l);
		}

		for (int i = 0; i < aList.size(); i++) {
			String firstLetter = Character.toString(Rotor.toChar(this.wiring.get(aList.get(i))));
			String secondLetter = Character.toString(Rotor.toChar(aList.get(i)));

			// Remove second letter
			aList.remove(aList.indexOf(this.wiring.get(aList.get(i))));

			result.add(firstLetter + " - " + secondLetter);
		}

		return result;
	}
}
