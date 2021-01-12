package enigma;

/**
 * Reflector class
 * 
 * @author Daniel Matyas Perendi
 */
public class Reflector {

	static final String REFLECTOR_B = "YRUHQSLDPXNGOKMIEBFZCWVJAT";

	/**
	 * Simulates the reflection
	 * 
	 * @param index The index of the incoming character
	 * @return The reflected character's index
	 */
	public static int reflect(int index) {
		return REFLECTOR_B.charAt(index) - Rotor.ASCII_DIFF;
	}

}
