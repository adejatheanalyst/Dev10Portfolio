package learn;

import java.util.*;

public class GroupAnagrams {

    public static  List<List<String>> groupAnagrams(String[] strs){
        HashSet<String> anagrams = new HashSet<String>();
        List<String> words = new ArrayList<>();

        for(int i = 0; i< strs.length; i++){
            String str = strs[i].toString();
            anagrams.add(str);
            words.add(str);
        };

        List<List<String>> result = new ArrayList<>();
        for(String i : new HashSet<>(anagrams)){
            if(!anagrams.contains(i))continue;
            List<String> anaList = new ArrayList<>();
            for(String toCom : words){
                String word = i;
                Boolean isAnagram = isAnagram(word, toCom);
                if (isAnagram) {
                        anaList.add(toCom);
                        anagrams.remove(toCom);
                    }
                }
            if(!anaList.isEmpty()){
                result.add(anaList);
            }
        }
        return result;
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
        String[] strs = new String[]{"a"};
        System.out.println(groupAnagrams(strs));

    }
}
