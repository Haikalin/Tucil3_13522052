import java.util.*;

public class UCS {
    Function function = new Function();

    public Solution get_path_UCS(String startWord, String endWord, ArrayList<String> sameLengthWords) {
        Solution solution = new Solution();
        ListArray listArray = new ListArray(); // Create a new list array
        listArray.path.add(new ArrayList<String>(Arrays.asList(startWord))); // Add the start word to the path
        listArray.cost.add(0); // Add the cost to the path
        boolean found = false; // Set found to false
        int count = 0; // Set count to 0
        ArrayList<String> alreadyExpand = new ArrayList<String>();
        long startTime = System.nanoTime(); // Get the start time

        // Check new words
        while (found == false && listArray.path.size() > 0) {
            ArrayList<String> currentPath = listArray.path.get(0);
            String currentWord = currentPath.get(currentPath.size() - 1);

            if (currentWord.equals(endWord)) {
                System.out.println("Path found!");
                System.out.print("Path: ");
                System.out.println(currentPath);
                System.out.print("Cost: ");
                // System.out.println(currentCost + 1
                System.out.println("Node yang dilalui: " + count);
                long endTime = System.nanoTime();
                long timeElapsed = endTime - startTime;
                // System.out.println("Execution time in milliseconds: " + (timeElapsed / 1000000000.00) + " detik");
                // System.out.println(listArray.path);
                solution.wordList = currentPath;
                solution.time = (timeElapsed / 1000000000.00);
                solution.visited_nodes = count;
                return solution;
            }

            // System.out.println(currentWord);
            int currentCost = listArray.cost.get(0);
            listArray.cost.remove(0);
            listArray.path.remove(0);
            count += 1;
            ArrayList<String> nextPath = function.cuma_beda_satu(currentWord, sameLengthWords);
            if (alreadyExpand.contains(currentWord) == false) {
                alreadyExpand.add(currentWord);
            }

            for (String s : nextPath) {
                if(currentPath.contains(s) == false && alreadyExpand.contains(s) == false){
                    ArrayList<String> newPath = new ArrayList<String>(currentPath);
                    newPath.add(s);
                    listArray.path.add(newPath);
                    listArray.cost.add(currentCost + 1);
                }
            }
            // System.out.println(currentPath);
        // System.out.println(listArray.path.s/ize());
            // if (currentPath.contains("LASE")){
            // System.out.println(currentPath);
            // }
        }
        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;
        solution.time = timeElapsed / 1000000000.00;
        solution.visited_nodes = count;
        return solution;
    }
}