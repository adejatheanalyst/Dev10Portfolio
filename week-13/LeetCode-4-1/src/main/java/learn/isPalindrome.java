package learn;

public class isPalindrome {


    public static boolean isPalindrome2(String s){
        if(s == " "){
            return true;
        }
        StringBuilder forward = new StringBuilder(s.length());
        StringBuilder backward = new StringBuilder(s.length());
        String toLowerCase = s.toLowerCase();



        for(int i = 0; i < toLowerCase.length(); i++) {
            char letter = toLowerCase.charAt(i);
            if(letter != ' '
                    && letter != ','
                    && letter != ':'
                    && letter != '.'
                    && letter != '@'
                    && letter != '#'
                    && letter != '!'
                    && letter != '$'
                    && letter != '%'
                    && letter != '^'
                    && letter != '&'
                    && letter != '*'
                    && letter != '('
                    && letter != ')'
                    && letter != '_'
                    && letter != '-'
                    && letter != '{'
                    && letter != '}'
                    && letter != '['
                    && letter != ']'
                    && letter != '\\'
                    && letter != ';'
                    && letter != '<'
                    && letter != '+'
                    && letter != '>'
                    && letter != '?'
                    && letter != '='
                    && letter != '/') {
                forward.append(letter);
            }
        }
        for(int i = toLowerCase.length()-1; i >= 0; i--) {
            char letter = toLowerCase.charAt(i);
            if(letter != ' '
                    && letter != ','
                    && letter != ':'
                    && letter != '.'
                    && letter != '@'
                    && letter != '#'
                    && letter != '!'
                    && letter != '$'
                    && letter != '%'
                    && letter != '^'
                    && letter != '&'
                    && letter != '*'
                    && letter != '('
                    && letter != ')'
                    && letter != '_'
                    && letter != '-'
                    && letter != '{'
                    && letter != '}'
                    && letter != '['
                    && letter != ']'
                    && letter != ';'
                    && letter != '<'
                    && letter != '+'
                    && letter != '>'
                    && letter != '?'
                    && letter != '='
                    && letter != '/') {
                backward.append(letter);
            }
        }
        boolean isMatch = forward.compareTo(backward) == 0;
        if(isMatch){
            return true;
        }

        return false;
    }

    public static String remove(String s){
        StringBuilder result = new StringBuilder(s.length());
        for(int i = 0; i < s.length(); i++) {
            char letter = s.charAt(i);
            if(letter != ' '
                    && letter != ','
                    && letter != ':'
                    && letter != '.'
                    && letter != '@'
                    && letter != '#'
                    && letter != '!'
                    && letter != '$'
                    && letter != '%'
                    && letter != '^'
                    && letter != '&'
                    && letter != '*'
                    && letter != '('
                    && letter != ')'
                    && letter != '_'
                    && letter != '-'
                    && letter != '{'
                    && letter != '}'
                    && letter != '['
                    && letter != ']'
                    && letter != ';'
                    && letter != '<'
                    && letter != '+'
                    && letter != '>'
                    && letter != '?'
                    && letter != '='
                    && letter != '/') {
                result.append(letter);
            }
        }



        return "";
    }
    public static void main(String[] args) {
        String s = "Marge, let's \\\"[went].\\\" I await {news} telegram.";
        System.out.println(isPalindrome2(s));

    }
}
