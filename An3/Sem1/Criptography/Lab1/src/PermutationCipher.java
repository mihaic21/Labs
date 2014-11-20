import java.util.ArrayList;

/**
 * Created by shobo on 23.10.2014.
 */
public class PermutationCipher {

    public static ArrayList<String> split(int size, String text){

        ArrayList<String> strings = new ArrayList<String>();
        if (text.equals("")){
            strings.add("");
            return strings;
        }

        int index = 0;
        String last = null;

        while (index < text.length()){
            strings.add(text.substring(index, Math.min(index + size, text.length())));
            index += size;
        }

        last = strings.get(strings.size() - 1);
        for (int i = last.length(); i < size; i++){
            last = last + " ";
        }

        strings.remove(strings.size() - 1);
        strings.add(last);

        return strings;
    }

    public static boolean validateKey(String[] key) {
        boolean[] exists = new boolean[key.length];
        for (int i = 0; i < key.length; i++) {
            if (Integer.parseInt(key[i]) < 0
                    || Integer.parseInt(key[i]) > key.length
                    || exists[Integer.parseInt(key[i]) - 1])
                return false;
            exists[Integer.parseInt(key[i]) - 1] = true;
        }
        return true;
    }

    public static boolean validateText(String alphabetInput, String plain) {
        String text = plain.toUpperCase();
        String alphabet = alphabetInput.toUpperCase();
        ArrayList<Character> list = new ArrayList<Character>();
        String[] tokens = alphabet.split(" ");
        for (String s : tokens) {
            if (s.length() > 1) {
                return false;
            }
            char c = s.charAt(0);
            list.add(c);
        }
        char[] charList = text.toCharArray();
        for (int i = 0; i < charList.length; i++) {
            if (!(charList[i] == ' ')) {
                if (!list.contains(charList[i])) {
                    return false;
                }
            }
        }
        return true;
    }

    public static String encrypt(String[] key, ArrayList<String> text) {
        String encryptedText = "";
        for (String s : text) {
            char[] encrypted = s.toCharArray();
            for (int i = 0; i < s.length(); i++) {
                Integer perm = Integer.parseInt(key[i]);
                encrypted[i] = s.charAt(perm - 1);
            }
            encryptedText += String.valueOf(encrypted);
        }
        return encryptedText;
    }

    public static String[] invert(String[] key) {
        String[] inverted = new String[key.length];
        for (int i = 0; i < key.length; i++) {
            inverted[Integer.parseInt(key[i]) - 1] = String.valueOf(i + 1);
        }
        return inverted;
    }

}
