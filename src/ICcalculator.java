/**
 * The IC calculator class
 * 
 * @author Daniel Matyas Perendi
 */
public class ICcalculator {
    public final static String[] ALPHABET_HUN = { "A", "Á", "B", "C", "Cs", "D", "Dz", "Dzs", "E", "É", "F", "G", "Gy",
            "H", "I", "Í", "J", "K", "L", "Ly", "M", "N", "Ny", "O", "Ó", "Ö", "Ő", "P", "Q", "R", "S", "Sz", "T", "Ty",
            "U", "Ú", "Ü", "Ű", "V", "W", "X", "Y", "Z", "Zs" };
    public final static String[] ALPHABET_ENG = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

    /**
     * Counts the number of occurences in a string
     * 
     * @param letter The letter we are looking for
     * @param input  The string we are searching in
     * @return The total number of occurences
     */
    private Integer countFreq(String letter, String input) {
        input = input.toUpperCase();
        String findStr = letter.toUpperCase();

        if (findStr.length() == 1) {
            long count1 = input.chars().filter(ch -> ch == findStr.charAt(0)).count();
            return Math.toIntExact(count1);
        } else {
            int lastIndex = 0;
            int count = 0;

            while (lastIndex != -1) {

                lastIndex = input.indexOf(findStr, lastIndex);

                if (lastIndex != -1) {
                    count++;
                    lastIndex += findStr.length();
                }
            }
            return count;
        }
    }

    /**
     * Calculates the IC for the given input and alphabet
     * 
     * @param input    The input string
     * @param alphabet The alphabet
     * @return The Index of coincidence
     */
    public Double calcIC(String input, String[] alphabet) {
        double sum = 0;

        for (String s : alphabet) {
            int freq = countFreq(s, input);
            sum += freq * (freq - 1);
        }

        input = input.replaceAll("\\s+", "");
        double leftSide = 1.0 / (input.length() * (input.length() - 1));

        double result = leftSide * sum;
        return result;
    }

    public static void main(String[] args) {
        ICcalculator calc = new ICcalculator();

        System.out.println(calc.calcIC(
                "Itt nem csak a levágott állatok mennyisége a probléma, hanem, hogy milyen mennyiségű pazarlás megy és a sok hús feldolgozással, állat tenyésztéssel milyen szinten romboljuk a bolygónkat. Ez csak egy probléma a sok közül, de valahol el kell kezdeni visszafogni magunkat és átalakítani az életünket. Ha ezt senki nem veszi észre, akkor 100 éven belül ki fog halni az emberiség. Én is minden nap húst ettem sokáig, de csökkentettem heti kettő napra a hús evéses napok számát. Álljunk át növényi eredetű táplálékokra",
                ALPHABET_HUN));

        System.out.println(calc.calcIC(
                "WKHUH DUHWZ RZDBV RIFRQ VWUXF WLQJD VRIWZ DUHGH VLJQR QHZDB LVWRP DNHLW VRVLP SOHWK DWWKH UHDUH REYLR XVOBQ RGHIL FLHQF LHVDQ GWKHR WKHUZ DBLVW RPDNH LWVRF RPSOL FDWHG WKDWW KHUHD UHQRR EYLRX VGHIL FLHQF LHVWK HILUV WPHWK RGLVI DUPRU HGLII LFXOW",
                ALPHABET_ENG));
    }
}
