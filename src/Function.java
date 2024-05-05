import java.util.*;;

public class Function {
    public boolean isWordExist(String word, ArrayList<String> words) {
        if(words.contains(word)) {
            return true;
        }
        return false;
    }

    public ArrayList<String> cuma_beda_satu(String word, ArrayList<String> words) {
        ArrayList<String> result = new ArrayList<String>();
        for (String w : words) {
            int count = 0;
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) != w.charAt(i)) {
                    count++;
                }
            }
            if (count == 1) {
                result.add(w);
            }
        }
        return result;
    }

    public ArrayList<String> get_same_Length(String word, ArrayList<String> words) {
        ArrayList<String> result = new ArrayList<String>();
        for (String w : words) {
            if (w.length() == word.length()) {
                result.add(w);
            }
        }
        return result;
    }

    public int diff_Letter (String word1, String word2) {
        int count = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                count++;
            }
        }
        return count;
    }

    public int get_index (Integer path_cost, ArrayList<Integer> costs) {
        for (int i = 0; i < costs.size(); i++) {
            if (costs.get(i) > path_cost) {
                return i;
            }
        }
        return costs.size();
    }

    public boolean the_only_minimum (ArrayList<Integer> costs, int path_cost) {
        for (int i = 0; i < costs.size(); i++) {
            if (costs.get(i) >= path_cost) {
                return false;
            }
        }
        return true;
    }
}
