package enigma;


/**
 * Alphabet class
 * 
 * @author Daniel Matyas Perendi
 */
public class Alphabet {

    public String alphabet, name;

    private String ENGLISH_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String HUNGARIAN_ALPHABET = "AÁBCDEÉFGHIÍJKLMNOÓÖŐPQRSTUÚÜŰVWXYZ";

    /**
     * Alphabet constructor takes either "Hungarian" 
     * or "English" and sets the alphabet accordingly
     */
    public Alphabet(String language){
        name = language;
        if(language.equals("Hungarian")){
            alphabet = HUNGARIAN_ALPHABET;
        }
        else{
            alphabet = ENGLISH_ALPHABET;
        }
    }
}
