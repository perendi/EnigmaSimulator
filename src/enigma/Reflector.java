package enigma;

/**
 * The Reflector class
 * 
 * @author Daniel Matyas Perendi
 */
public class Reflector {

	// M3 default reflector
	static final String REFLECTOR_B = "YRUHQSLDPXNGOKMIEBFZCWVJAT";
	static final String REFLECTOR_HUN = ""; //TODO

	// M4 default reflector
	static final String REFLECTOR_B_THIN = "ENKQAUYWJICOPBLMDXZVFTHRGS";
	static final String REFLECTOR_HUN_THIN = ""; //TODO

	/**
	 * Simulates the reflection for the M3 machine
	 * 
	 * @param index The index of the incoming character
	 * @return The reflected character's index
	 */
	public static int reflect_M3(int index, Alphabet a) {
		if(a.name.equals("Hungarian")){
			// Return hungarian reflection
			return 0;
		}
		else{
			return Rotor.toIndex(REFLECTOR_B.charAt(index));
		}
		
	}

	/**
	 * Simulates the reflection for the M4 machine
	 * 
	 * @param index The index of the incoming character
	 * @return The reflected character's index
	 */
	public static int reflect_M4(int index, Alphabet a) {
		if(a.name.equals("Hungarian")){
			// Return hungarian reflection
			return 0;
		}
		else{
			return Rotor.toIndex(REFLECTOR_B_THIN.charAt(index));
		}

	}

}
