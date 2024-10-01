public class Anagram {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length() || s.isEmpty())
            return false;
        int[] a = new int[26];
        for(int i = 0; i < 26; i++)
            a[i] = 0;


        for(int i = 0; i < s.length(); i++){
            a[s.charAt(i) - 'a']++;
            a[t.charAt(i) - 'a']--;
        }

        for(int i = 0; i < 26; i++){
            if(a[i] == 0)
                return false;
        }
        return true;
    }
}
