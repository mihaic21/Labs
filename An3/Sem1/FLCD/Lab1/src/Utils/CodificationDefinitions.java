package Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Created by shobo on 09.11.2014.
 */
public class CodificationDefinitions {

    private Map<String, Integer> reservedWords;
    private Map<String, Integer> operators;
    private Map<String, Integer> separators;

    public CodificationDefinitions() {
        try{
            this.reservedWords = this.codificationFromFile("reserved_words.txt");
            this.operators = this.codificationFromFile("operators.txt");
            this.separators = this.codificationFromFile("separators.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Integer codeForString(String string) {
        Integer code = null;

        code = this.reservedWords.get(string);
        if (code == null) {
            code = this.operators.get(string);

            if (code == null) {
                code = this.separators.get(string);
            }
        }

        return code;
    }

    public Boolean isSeparator(char c) {
        String separator = new String();
        separator += c;

        Integer code = null;
        code = this.separators.get(separator);

        return code != null;
    }

    public Boolean isOperator(String operator){
        Integer code = null;
        code = this.operators.get(operator);

        return code != null;
    }

    private Map<String, Integer> codificationFromFile(String filename) throws IOException {

        Map<String, Integer> codes = new LinkedHashMap<String, Integer>();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] tokens = line.split(" ");
            codes.put(tokens[0], Integer.parseInt(tokens[1]));
        }

        return codes;
    }

}
