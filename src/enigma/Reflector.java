package enigma;

/**
 * The Reflector class
 * 
 * @author Daniel Matyas Perendi
 */
public class Reflector {

	// M3 default reflector
	static final String REFLECTOR_B = "YRUHQSLDPXNGOKMIEBFZCWVJAT";
	// M4 default reflector
	static final String REFLECTOR_B_THIN = "ENKQAUYWJICOPBLMDXZVFTHRGS";

	/**
	 * Simulates the reflection for the M3 machine
	 * 
	 * @param index The index of the incoming character
	 * @return The reflected character's index
	 */
	public static int reflect_M3(int index) {
		return REFLECTOR_B.charAt(index) - Rotor.ASCII_DIFF;
	}

	/**
	 * Simulates the reflection for the M4 machine
	 * 
	 * @param index The index of the incoming character
	 * @return The reflected character's index
	 */
	public static int reflect_M4(int index) {
		return REFLECTOR_B_THIN.charAt(index) - Rotor.ASCII_DIFF;
	}

}
