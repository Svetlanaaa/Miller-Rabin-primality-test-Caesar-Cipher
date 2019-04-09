import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

class Counter{

    private Map<Character, Integer> letters;
    private Map<String, Integer> bigrams;

    Counter() {
        letters = new HashMap<>();
        bigrams = new HashMap<>();
    }

    Map<Character, Integer> getAmountOfLetters() { return letters; }

    Map<String, Integer> getAmountOfBigrams() { return bigrams; }

    private boolean isInAlphabet(char letter){
        int code = (int)letter;
        return ((1072 <= code) && (code <= 1103));
    }

    private void countLetters(String text) {
        for (char letter: text.toCharArray()) {
            if (isInAlphabet(letter)){
                letters.put(letter, (letters.containsKey(letter) ?  letters.get(letter) : 0) + 1);
            }
        }
    }

    private void countBigrams(String text) {
        char[] chars = text.toCharArray();
        String bigram;

        for (int i = 0; i < chars.length - 1; i++) {
            if (isInAlphabet(chars[i])) {
                bigram = String.valueOf(chars[i]);
                i++;
                while (!isInAlphabet(chars[i]) && i < chars.length - 1) {
                    i++;
                }
                if (isInAlphabet(chars[i])) {
                    bigram += chars[i];
                } else {
                    break;
                }

                bigrams.put(bigram, (bigrams.containsKey(bigram) ? bigrams.get(bigram) : 0) + 1);
            }
        }
    }

    void countInFile(String filePath) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new FileReader(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                countLetters(line);
                countBigrams(line);
            }
        }
        catch (FileNotFoundException e) {}
    }

    void clean(){
        letters.clear();
        bigrams.clear();
    }
}
