import java.util.ArrayList;

public class Solution {
    public ArrayList<String> wordList;
    public int visited_nodes;
    public double time;

    public Solution() {
        wordList = new ArrayList<String>();
        visited_nodes = 0;
        time = 0;
    }

    public Solution(ArrayList<String> words, double time) {
        wordList = new ArrayList<String>();
        wordList = words;
        visited_nodes = 0;
        this.time = time;
    }
}