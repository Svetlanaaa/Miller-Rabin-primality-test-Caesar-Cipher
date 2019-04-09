import java.io.IOException;
import java.util.*;


public class Main {

    private static int HashFunc(int n){
        String s = Integer.toBinaryString(n);
        char[] num = Integer.toBinaryString(n).toCharArray();

        int result = 0;
        int k = 0;
        result += ((int)num[0] ^ (int)num[0]) * Math.pow(10, k++);
        for (int i = 1; i < num.length; i++){
            result += ((int)num[0] ^ (int)num[i]) * Math.pow(10, k++);
        }
        return result;
    }

    public static void main(String[] args) throws IOException {

        System.out.println(HashFunc(0));
        System.out.println(HashFunc(11));

        Counter counter = new Counter();
        CaesarCipher caesarCipher = new CaesarCipher(2);

        caesarCipher.encryptFromFile("chapter.txt", "encryptedChapter.txt");

        counter.countInFile("book.txt");
        Map<Character, Integer> bookAmountOfLetters = new HashMap<>();
        bookAmountOfLetters.putAll(counter.getAmountOfLetters());
        Map<String, Integer> bookAmountOfBigrams = new HashMap<>();
        bookAmountOfBigrams.putAll(counter.getAmountOfBigrams());
        counter.clean();

        counter.countInFile("encryptedChapter.txt");
        Map<Character, Integer> chapterAmountOfLetters = new HashMap<>();
        chapterAmountOfLetters.putAll(counter.getAmountOfLetters());
        Map<String, Integer> chapterAmountOfBigrams = new HashMap<>();
        chapterAmountOfBigrams.putAll(counter.getAmountOfBigrams());
        counter.clean();

        Analyzer analyzer = new Analyzer();
        Map<Character, Character> decryptionTable = analyzer.performAnalysis(chapterAmountOfLetters, bookAmountOfLetters);

        Decryptor decryptor = new Decryptor(decryptionTable);
        decryptor.decryptFromFile("encryptedChapter.txt", "decryptedChapter.txt");

        analyzer.addBigrams(decryptionTable, chapterAmountOfBigrams, bookAmountOfBigrams);
        decryptor.setDecryptionTable(decryptionTable);
        decryptor.decryptFromFile("encryptedChapter.txt", "decryptedChapterBigrams.txt");
    }
}