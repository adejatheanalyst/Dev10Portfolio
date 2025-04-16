package learn;

public class RansomNote {

    public  static boolean isRansomNote(String r, String m) {
//        int isMatch = 0;
//
//        for (int i = 0; i < r.length(); i++) {
//            char ransom = r.charAt(i);
//            for (int j = 0; j < m.length(); j++) {
//                char mag = m.charAt(j);
//                if (ransom == mag) {
//                    isMatch++;
//                    System.out.println(isMatch);
//                    if(){
//                        return true;
//                    }
//                }
//            }
//        }
//        return false;


        for(int i = 0; i < r.length(); i++){
            char ransom = r.charAt(i);
            int isMatch = m.indexOf(ransom);
            if(isMatch == -1){
                return false;
            }
            m = m.substring(0, isMatch) + m.substring(isMatch + 1);
        }
        return true;

    }
    public static boolean isAnagram(String s, String t) {
        if(s.length() != t.length()){
            return false;
        }
        for(int i = 0; i < s.length(); i++){
            char anagram = s.charAt(i);
            int isMatch = t.indexOf(anagram);
            if(isMatch == -1){
                return false;
            }
            t = t.substring(0, isMatch) + t.substring(isMatch + 1);
        }
        return true;

    }




    public static void main(String[] args) {
        String ransomNote = "fihjjjjei";
        String magazine = "hjibagacbhadfaefdjaeaebgi";
        String s = "a";
        String t = "ab";
//        isRansomNote(ransomNote, magazine);
        System.out.println(isAnagram(s, t));
    }
}
