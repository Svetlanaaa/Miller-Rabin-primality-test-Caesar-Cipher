import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Decryptor {
    private Map<Character, Character> decryptionTable;

    public Decryptor(Map<Character, Character> decryptionTable) {
        this.decryptionTable = decryptionTable;
    }

    public void setDecryptionTable(Map<Character, Character> decryptionTable) {
        this.decryptionTable = decryptionTable;
    }

    private String toDecrypt(String s) {
        String result = "";
        for (int i = 0; i < s.length(); i++){
            result += decryptChar(s.charAt(i));
        }
        return result;
    }

    private char decryptChar(char c){
        return ( decryptionTable.containsKey(c) ? decryptionTable.get(c) : c);
    }

    public void decryptFromFile(String inputFilePath, String outputFilePath) throws IOException {
        try (Scanner scanner = new Scanner(new FileReader(inputFilePath));
             FileWriter writer = new FileWriter(outputFilePath))
        {
            while (scanner.hasNextLine()) {
                writer.write(toDecrypt(scanner.nextLine()) + "\n");
            }
        }
    }
}
