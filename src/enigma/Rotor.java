package enigma;

/**
 * Rotor class
 * 
 * @author Daniel Matyas Perendi
 *
 */
public class Rotor {
	// Current position of the Rotor
	private int position;

	// Rotor specifications
	public String[] specs;

	// Alphabet
	public static String alphabet;

	// Ring settings
	public int ringSetting;


	/**
	 * Rotor constructor
	 * 
	 * @param nr The name of the Rotor
	 * @param a The alphabet
	 */
	public Rotor(String nr, Alphabet a) {
		for (String[] rm : RotorMappings.MAPPINGS) {
			if (rm[0].equals(nr) && rm[4].equals(a.name)) {
				this.specs = rm;
			}
		}
		this.ringSetting = 0;
		alphabet = a.alphabet;
	}

	/**
	 * Converts a position of the alphabet to a character
	 * 
	 * @param index The index of the character in the alphabet
	 * @return The character matching the index
	 */
	public static char toChar(int index) {
		return alphabet.charAt(index);
		// return alphabet.substring(index, index+1); RETURNS STRING
		// return (char) (index + ASCII_DIFF);
	}


	/**
	 * Converts a character to its position in the alphabet
	 * 
	 * @param c The character
	 * @return The position of the character in the alphabet
	 */
	public static int toIndex(char c) {
		return alphabet.indexOf(c);
		// return (int) (c - ASCII_DIFF);
	}


	/**
	 * Advances the rotor and wraps around if needed
	 */
	public void advance() {
		// Reset rotor
		if (getPosition() == (alphabet.length() - 1)) {
			setPosition(0);
		}
		// Advance
		else {
			setPosition(getPosition() + 1);
		}
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
	 * Gets the current position of the Rotor
	 * 
	 * @return The current position
	 */
	public int getPosition() {
		return position;
	}


	/**
	 * Sets the current position to the specified position
	 * 
	 * @param p The new position
	 */
	public void setPosition(int p) {
		if (p < alphabet.length() && p > -1) {
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
		int p = (this.position + index) % alphabet.length();

		int charShift = p - this.ringSetting;
		if(charShift < 0) charShift += alphabet.length();
		int output = (toIndex(this.specs[1].charAt(charShift))+this.ringSetting)%alphabet.length();

		int outputDiff = output - this.position;

		// Wrap around
		if (outputDiff < 0) {
			return outputDiff + alphabet.length();
		}
		return outputDiff ;
	}


	/**
	 * Converts the specific letter(passed as an index) backward
	 * 
	 * @param index The position of the letter in the alphabet
	 * @return The output letter's index
	 */
	public int convertBwd(int index) {
		int p = (this.position + index) % alphabet.length();

		int charShift = p - this.ringSetting;
		if(charShift < 0) charShift += alphabet.length();
		
		// int output = toIndex(this.specs[2].charAt(p));
		int output = (toIndex(this.specs[2].charAt(charShift))+this.ringSetting)%alphabet.length();

		int outputDiff = output - this.position;

		// Wrap around
		if (outputDiff < 0) {
			return outputDiff + alphabet.length() ;
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
		// Wehrmacht rotors
		else {
			if (this.getPosition() == toIndex(notch.charAt(0))) {
				return true;
			} else {
				return false;
			}
		}
	}
}
