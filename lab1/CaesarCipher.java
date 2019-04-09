import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CaesarCipher {
    private int step;

    CaesarCipher(int step){
        this.step = step;
    }

    public String toEncrypt(String s){
        String result = "";
        for (int i = 0; i < s.length(); i++){
            result += encryptChar(s.charAt(i));
        }
        return result;
    }

    private char encryptChar(char c){
        int code = (int)c;
        if ((1040 <= code) && (code <= 1103)){
            int first = code >= 1072 ? 1072 : 1040;
            code = 1072 + (code - first + step) % 32;
        }
        return (char)code;
    }

    void encryptFromFile(String inputFilePath, String outputFilePath) throws IOException {
        try (Scanner scanner = new Scanner(new FileReader(inputFilePath));
             FileWriter writer = new FileWriter(outputFilePath))
        {
            while (scanner.hasNextLine()) {
                writer.write(toEncrypt(scanner.nextLine()) + "\n");
            }
        }
    }
}
