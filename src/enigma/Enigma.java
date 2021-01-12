package enigma;

/**
 * @author Daniel Matyas Perendi The Enigma class
 */
public class Enigma {
	private Rotor lr, mr, rr, fourthRotor;
	private Plugboard p;

	/**
	 * Wehrmacht Constructor
	 * 
	 * @param lr Left rotor
	 * @param mr Middle rotor
	 * @param rr Right rotor
	 * @param p  Plugboard
	 */
	public Enigma(Rotor lr, Rotor mr, Rotor rr, Plugboard p) {
		this.lr = lr;
		this.mr = mr;
		this.rr = rr;
		this.p = p;
	}

	/**
	 * Kriegsmarine(Naval enigma) constructor
	 * 
	 * @param first the first rotor
	 * @param lr    the second rotor
	 * @param mr    the third rotor
	 * @param rr    the fourth rotor
	 * @param p     the plugboard
	 */
	public Enigma(Rotor first, Rotor lr, Rotor mr, Rotor rr, Plugboard p) {
		this.fourthRotor = first;
		this.lr = lr;
		this.mr = mr;
		this.rr = rr;
		this.p = p;
	}

	/**
	 * Advances all the rotors according to the current settings
	 */
	private void advanceRotors() {
		// Right at notch
		if (rr.getPosition() == 25) {
			rr.advance();
			// Middle at notch too
			if (mr.getPosition() == 25) {
				// Left at notch too
				if (fourthRotor != null && lr.getPosition() == 25) {
					fourthRotor.advance();
				}
				lr.advance();
			}
			mr.advance();
		} else {
			rr.advance();
		}
	}

	/**
	 * Encrypts the given string input
	 * 
	 * @param input The plaintext
	 * @return The ciphertext
	 */
	public String encrypt(String input) {
		if (input.length() == 0) {
			return "";
		} else {
			String output = "";
			for (int i = 0; i < input.length(); i++) {
				// Get current index in the alphabet
				int pos = (int) input.charAt(i) - Rotor.ASCII_DIFF;

				// Send it through the machine

				// Plugboard
				pos = p.stecker(pos);

				// Rotors
				advanceRotors();
				pos = lr.convertFwd(mr.convertFwd(rr.convertFwd(pos)));
				if (fourthRotor != null) {
					pos = fourthRotor.convertFwd(pos);
				}

				// Reflector
				pos = Reflector.reflect(pos);

				// Rotors
				if (fourthRotor != null) {
					pos = fourthRotor.convertBwd(pos);
				}
				pos = rr.convertBwd(mr.convertBwd(lr.convertBwd(pos)));

				// Plugboard
				pos = p.stecker(pos);

				output = output + Rotor.toChar(pos);
			}
			return output;
		}
	}

	/**
	 * Gets the current settings of the Enigma
	 * 
	 * @return A string for a display area
	 */
	public String getSettings() {
		if (fourthRotor != null) {
			return "   First Rotor\tSecond Rotor\tThird Rotor\tFourth Rotor   \n" + "   " + fourthRotor.getName() + "\t"
					+ lr.getName() + "\t" + mr.getName() + "\t" + rr.getName() + "   \n" + "   "
					+ fourthRotor.getPosition() + "\t" + lr.getPosition() + "\t" + mr.getPosition() + "\t"
					+ rr.getPosition() + "   ";
		} else {
			return "   Left Rotor\tMiddle Rotor\tRight Rotor   \n" + "   " + lr.getName() + "\t" + mr.getName() + "\t"
					+ rr.getName() + "   \n" + "   " + lr.getPosition() + "\t" + mr.getPosition() + "\t"
					+ rr.getPosition() + "   ";
		}

	}

	public static String formatInput(String s) {
		s = s.toUpperCase();
		s = s.replaceAll("[^A-Z]", "");
		return s;
	}
}
