import java.util.*;

public class Greedy_Best_First {
    Function function = new Function();

    public Solution get_path_UCS(String startWord, String endWord, ArrayList<String> sameLengthWords) {
        Solution solution = new Solution();
        ListArray listArray = new ListArray();
        listArray.path.add(new ArrayList<String>(Arrays.asList(startWord)));
        listArray.cost.add(function.diff_Letter(startWord, endWord));
        boolean found = false;
        int count = 0;
        long startTime = System.nanoTime();
        ArrayList<String> alreadyExpand = new ArrayList<String>();

        while (found == false && listArray.path.size() > 0) {
            ArrayList<String> currentPath = listArray.path.get(0); // Get the first path
            String currentWord = currentPath.get(currentPath.size() - 1); // Get the last word in the path
            // Kata sesuai dengan kata akhir
            if (currentWord.equals(endWord)) {
                long endTime = System.nanoTime();
                long timeElapsed = endTime - startTime;
                solution.wordList = currentPath;
                solution.time = (timeElapsed / 1000000000.00);
                solution.visited_nodes = count;
                return solution;
            }

            // int currentCost = listArray.cost.get(0); // Get the cost of the first path
            ArrayList<String> nextPath = function.cuma_beda_satu(currentWord, sameLengthWords); // Get the next path

            if (alreadyExpand.contains(currentWord) == false) {
                alreadyExpand.add(currentWord);
            }
            listArray.path.clear();
            listArray.cost.clear();
            // listArray.cost.remove(0); // Remove the cost
            // listArray.path.remove(0); // Remove the path
            count++;

            for (String s : nextPath) { // Loop through the next path
                if ((currentPath.contains(s) == false) && (alreadyExpand.contains(s) == false)) { // If the path doesn't contain the word
                    ArrayList<String> newPath = new ArrayList<String>(currentPath); // Create a new path
                    newPath.add(s); // Add the word to the new path
                    int index = function.get_index(function.diff_Letter(s, endWord), listArray.cost); // Get the index
                    listArray.path.add(index, newPath); // Add the new path to the list
                    listArray.cost.add(index, function.diff_Letter(s, endWord)); // Add the cost to the list
                }
            }
            // System.out.println(listArray.path);
        }
        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;
        solution.time = timeElapsed / 1000000000.00;
        solution.visited_nodes = count;
        return solution;
            
    }
}
