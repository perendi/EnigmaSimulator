package enigma;

/**
 * Rotor class
 * 
 * @author Daniel Matyas Perendi
 *
 */
public class Rotor {
	// Length of the English alphabet
	static final int ALPHABET_LENGTH = 26;

	// Index - ASCII difference
	static final int ASCII_DIFF = 65;

	// Current position of the Rotor
	private int position;

	// Rotor specifications
	public String[] specs;

	// Ring settings
	public int ringSetting;

	public Rotor(String nr) {
		for (String[] rm : RotorMappings.MAPPINGS) {
			if (rm[0].equals(nr)) {
				this.specs = rm;
			}
		}
		this.ringSetting = 0;
	}

	/**
	 * Converts a position of the alphabet to a character
	 * 
	 * @param index The index of the character in the alphabet
	 * @return The character matching the index
	 */
	public static char toChar(int index) {
		return (char) (index + ASCII_DIFF);
	}

	/**
	 * Converts a character to its position in the alphabet
	 * 
	 * @param c The character
	 * @return The position of the character in the alphabet
	 */
	public static int toIndex(char c) {
		return (int) (c - ASCII_DIFF);
	}

	/**
	 * Advances the rotor and wraps around if needed
	 */
	public void advance() {
		// Reset rotor
		if (getPosition() == (ALPHABET_LENGTH - 1)) {
			setPosition(0);
		}
		// Advance
		else {
			setPosition(getPosition() + 1);
		}
	}

	/**
	 * Gets the current position of the Rotor
	 * 
	 * @return The current position
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * Gets the name of the Rotor
	 * 
	 * @return The name
	 */
	public String getName() {
		return specs[0];
	}

	/**
	 * Sets the current position to the specified position
	 * 
	 * @param p The new position
	 */
	public void setPosition(int p) {
		if (p < ALPHABET_LENGTH && p > -1) {
			position = p;
		} else {
			throw new Error("Position out of bound!");
		}
	}

	/**
	 * Gets the ring setting of the Rotor
	 * @return The ring setting
	 */
	public int getRingSetting(){
		return ringSetting;
	}

	/**
	 * Sets the ring setting of the Rotor
	 * @param s The new ring setting
	 */
	public void setRingSetting(int s){
		if(s < 0 || s > 25){
			throw new Error("Ring setting out of bound!");
		}
		else{
			this.ringSetting = s;
		}
	}

	/**
	 * Converts the specific letter(passed as an index) forward
	 * 
	 * @param index The position of the letter in the alphabet
	 * @return The output letter's index
	 */
	public int convertFwd(int index) {
		int p = (this.position + index) % ALPHABET_LENGTH;
		int output = toIndex(this.specs[1].charAt(p));

		// WILL NEED TO ADD RINGSETTING SOMEWHERE HERE
		int outputDiff = output - this.position;

		// Wrap around
		if (outputDiff < 0) {
			return outputDiff + ALPHABET_LENGTH;
		}
		return outputDiff;
	}

	/**
	 * Converts the specific letter(passed as an index) backward
	 * 
	 * @param index The position of the letter in the alphabet
	 * @return The output letter's index
	 */
	public int convertBwd(int index) {
		int p = (this.position + index) % ALPHABET_LENGTH;
		int output = toIndex(this.specs[2].charAt(p));

		int outputDiff = output - this.position;

		// Wrap around
		if (outputDiff < 0) {
			return outputDiff + ALPHABET_LENGTH;
		}
		return outputDiff;
	}

	/**
	 * Checks whether the rotor is at its notch or not
	 * 
	 * @return True or false according to its notch position
	 */
	public boolean atNotch() {
		String notch = this.specs[3];

		// One of the M4 rotors
		if (notch.length() > 1) {
			if (this.getPosition() == toIndex(notch.charAt(0)) || this.getPosition() == toIndex(notch.charAt(1))) {
				return true;
			} else {
				return false;
			}
		}
		// M3 rotors
		else {
			if (this.getPosition() == toIndex(notch.charAt(0))) {
				return true;
			} else {
				return false;
			}
		}
	}
}
