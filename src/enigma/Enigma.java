package enigma;

/**
 * The Enigma class
 * 
 * @author Daniel Matyas Perendi
 */
public class Enigma {
	private Rotor lr, mr, rr, fourthRotor;
	private Plugboard p;
	public Alphabet a;

	/**
	 * Wehrmacht Constructor
	 * 
	 * @param lr Left rotor
	 * @param mr Middle rotor
	 * @param rr Right rotor
	 * @param p  Plugboard
	 * @param a Alphabet
	 */
	public Enigma(Rotor lr, Rotor mr, Rotor rr, Plugboard p, Alphabet a) {
		this.lr = lr;
		this.mr = mr;
		this.rr = rr;
		this.p = p;
		this.a = a;
	}

	/**
	 * Kriegsmarine M4(Naval enigma) constructor
	 * 
	 * @param first  the first rotor
	 * @param second the second rotor
	 * @param third  the third rotor
	 * @param fourth the fourth rotor
	 * @param p      the plugboard
	 * @param a 	the alphabet
	 */
	public Enigma(Rotor first, Rotor second, Rotor third, Rotor fourth, Plugboard p, Alphabet a) {
		this.fourthRotor = first;
		this.lr = second;
		this.mr = third;
		this.rr = fourth;
		this.p = p;
		this.a = a;
	}

	/**
	 * Advances all the rotors according to the current settings (BETA and GAMMA
	 * never advances)
	 */
	private void advanceRotors() {
		// Right rotor at notch
		if (rr.atNotch()) {
			rr.advance();
			mr.advance();
		}
		// Middle rotor at notch
		else if (mr.atNotch()) {
			lr.advance();
			mr.advance();
			rr.advance();
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
				// check for space
				if (input.charAt(i) == ' ') {
					output += " ";
				} else {
					// Get current index in the alphabet
					int pos = Rotor.toIndex(input.charAt(i));

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
					if (fourthRotor != null) {
						pos = Reflector.reflect_M4(pos, a);
					} else {
						pos = Reflector.reflect_M3(pos, a);
					}

					// Rotors
					if (fourthRotor != null) {
						pos = fourthRotor.convertBwd(pos);
					}
					pos = rr.convertBwd(mr.convertBwd(lr.convertBwd(pos)));

					// Plugboard
					pos = p.stecker(pos);

					output = output + Rotor.toChar(pos);
				}

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
			return "First Rotor:\t" + fourthRotor.getName() + "\tPosition:\t" + fourthRotor.getPosition() +
				"\tRing:\t" + fourthRotor.getRingSetting() + "\nSecond Rotor:\t" + lr.getName() + 
				"\t\tPosition:\t" + lr.getPosition() + "\tRing:\t" + lr.getRingSetting() + "\nThird Rotor:\t"
				+ mr.getName() + "\t\tPosition:\t" + mr.getPosition()+ "\tRing:\t" + mr.getRingSetting() +
				"\nFourth Rotor:\t" + rr.getName() + "\t\tPosition:\t" + rr.getPosition() + "\tRing:\t" +
				rr.getRingSetting();
		} else {
			return "Left Rotor:\t" + lr.getName() + "\tPosition:\t" + lr.getPosition() + "\tRing:\t" +
				lr.getRingSetting() + "\nMiddle Rotor:\t" + mr.getName() + "\tPosition:\t" +
				mr.getPosition() + "\tRing:\t" + mr.getRingSetting() + "\nRight Rotor:\t" +
				rr.getName() + "\tPosition:\t" + rr.getPosition() + "\tRing:\t" + rr.getRingSetting();
		}

	}

	/**
	 * Converts the input into an Enigma-compatible string
	 * 
	 * @param s The input string
	 * @return The string that is compatible with the Enigma
	 */
	public String formatInput(String s) {
		s = s.toUpperCase();
		String regexp = "[^"+ a.alphabet +" ]";
		s = s.replaceAll(regexp, "");
		return s;
	}
}
