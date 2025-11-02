package StriverSheet.Recursion.Hard;

import java.io.*;
import java.util.*;

public class WordSearch {

    public boolean solve(int row , int col, int i, int n, int m, String word, char[][] board){

        if(i == word.length()) return true;

        if(row < 0 || col < 0 || row >= n || col >= m || board[row][col] != word.charAt(i) || board[row][col] == '!'){
            return false;
        }

        char c = word.charAt(i);
        board[row][col] = '!';

        boolean top = solve(row - 1, col, i + 1, n, m, word, board);

        boolean right = solve(row, col + 1, i + 1, n, m, word, board);

        boolean down = solve(row + 1, col, i + 1, n, m, word, board);

        boolean left = solve(row , col - 1, i + 1, n, m, word, board);

        board[row][col] = c;

        return top || right || down || left;
    }

    public boolean exist(char[][] board, String word) {

        int index = 0;
        int n = board.length;
        int m = board[0].length;

        for(int i = 0 ; i < n; i++){
            for(int j = 0 ; j < m; j++){
                if(board[i][j] == word.charAt(index)){
                    if(solve(i, j, index, n, m, word, board)) return true;
                }
            }
        }

        return false;
    }

    // Helper method to print the board in a readable format
    public void printBoard(char[][] board, PrintWriter writer) {
        for(int i = 0; i < board.length; i++) {
            writer.print("[");
            for(int j = 0; j < board[i].length; j++) {
                writer.print("'" + board[i][j] + "'");
                if(j < board[i].length - 1) {
                    writer.print(", ");
                }
            }
            writer.print("]");
            if(i < board.length - 1) {
                writer.println();
            }
        }
        writer.println();
    }

    // Helper method to find and display one possible path (for debugging)
    public boolean findPath(int row, int col, int i, int n, int m, String word, char[][] board, List<String> path) {
        if(i == word.length()) return true;

        if(row < 0 || col < 0 || row >= n || col >= m || board[row][col] != word.charAt(i) || board[row][col] == '!'){
            return false;
        }

        char c = word.charAt(i);
        board[row][col] = '!';
        path.add("(" + row + "," + col + ")");

        boolean found = findPath(row - 1, col, i + 1, n, m, word, board, path) ||
                findPath(row, col + 1, i + 1, n, m, word, board, path) ||
                findPath(row + 1, col, i + 1, n, m, word, board, path) ||
                findPath(row, col - 1, i + 1, n, m, word, board, path);

        if(!found) {
            path.remove(path.size() - 1);
        }

        board[row][col] = c;
        return found;
    }

    public static void main(String[] args) {
        WordSearch solution = new WordSearch();

        try {
            // Read input from file
            Scanner scanner = new Scanner(new File("input.txt"));

            // Write output to file
            PrintWriter writer = new PrintWriter(new FileWriter("output.txt"));

            int testCase = 1;

            // Process multiple test cases
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                // Skip empty lines
                if(line.isEmpty()) {
                    continue;
                }

                // Read dimensions
                String[] dimensions = line.split("\\s+");
                if(dimensions.length < 2) continue;

                int n = Integer.parseInt(dimensions[0]); // rows
                int m = Integer.parseInt(dimensions[1]); // cols

                // Read the board
                char[][] board = new char[n][m];
                for(int i = 0; i < n; i++) {
                    if(!scanner.hasNextLine()) break;
                    String boardRow = scanner.nextLine().trim().replaceAll("\\s+", "");
                    for(int j = 0; j < Math.min(m, boardRow.length()); j++) {
                        board[i][j] = boardRow.charAt(j);
                    }
                }

                // Read the word to search
                if(!scanner.hasNextLine()) break;
                String word = scanner.nextLine().trim();

                // Create a copy of the board for path finding
                char[][] boardCopy = new char[n][m];
                for(int i = 0; i < n; i++) {
                    System.arraycopy(board[i], 0, boardCopy[i], 0, m);
                }

                // Search for the word
                boolean found = solution.exist(board, word);

                // Write output for this test case
                writer.println("Test Case " + testCase + ":");
                writer.println("Board dimensions: " + n + " x " + m);
                writer.println("Board:");
                solution.printBoard(boardCopy, writer);

                writer.println("Word to search: \"" + word + "\"");
                writer.println("Word length: " + word.length());

                writer.println("Result: " + (found ? "FOUND" : "NOT FOUND"));

                if(found) {
                    // Try to find and show one possible path
                    List<String> path = new ArrayList<>();
                    boolean pathFound = false;

                    // Find starting positions and try to get path
                    outerLoop:
                    for(int i = 0; i < n && !pathFound; i++) {
                        for(int j = 0; j < m && !pathFound; j++) {
                            if(boardCopy[i][j] == word.charAt(0)) {
                                path.clear();
                                // Create fresh copy for path finding
                                char[][] pathBoard = new char[n][m];
                                for(int x = 0; x < n; x++) {
                                    System.arraycopy(boardCopy[x], 0, pathBoard[x], 0, m);
                                }

                                if(solution.findPath(i, j, 0, n, m, word, pathBoard, path)) {
                                    writer.println("One possible path: " + path);
                                    pathFound = true;
                                    break outerLoop;
                                }
                            }
                        }
                    }
                }
//                else {
//                    writer.println("Analysis: Word cannot be formed from adjacent cells");
//
//                    // Check if all characters exist
//                    Set<Character> boardChars = new HashSet<>();
//                    for(int i = 0; i < n; i++) {
//                        for(int j = 0; j < m; j++) {
//                            boardChars.add(boardCopy[i][j]);
//                        }
//                    }
//
//                    Set<Character> wordChars = new HashSet<>();
//                    for(char c : word.toCharArray()) {
//                        wordChars.add(c);
//                    }

//                    boolean allCharsPresent = boardChars.containsAll(wordChars);
//                    writer.println("All characters present in board: " + allCharsPresent);
//
//                    if(!allCharsPresent) {
//                        writer.print("Missing characters: ");
//                        for(char c : wordChars) {
//                            if(!boardChars.contains(c)) {
//                                writer.print("'" + c + "' ");
//                            }
//                        }
//                        writer.println();
//                    }
//                }

//                // Show board character frequency
//                writer.println("\nBoard character frequency:");
//                Map<Character, Integer> charCount = new HashMap<>();
//                for(int i = 0; i < n; i++) {
//                    for(int j = 0; j < m; j++) {
//                        char c = boardCopy[i][j];
//                        charCount.put(c, charCount.getOrDefault(c, 0) + 1);
//                    }
//                }
//                for(Map.Entry<Character, Integer> entry : charCount.entrySet()) {
//                    writer.println("'" + entry.getKey() + "': " + entry.getValue());
//                }

                writer.println(); // Empty line for separation
                testCase++;
            }

            scanner.close();
            writer.close();

            System.out.println("Program executed successfully!");
            System.out.println("Processed " + (testCase - 1) + " test case(s).");
            System.out.println("Check output.txt for results.");

        } catch (FileNotFoundException e) {
            System.err.println("Error: input.txt file not found!");
        } catch (IOException e) {
            System.err.println("Error writing to output.txt!");
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}

/*
3 4
ABCE
SFCS
ADEE
ABCCED

3 4
ABCE
SFCS
ADEE
SEE

3 4
ABCE
SFCS
ADEE
ABCB

4 4
ABCD
EFGH
IJKL
MNOP
HELLO

2 2
AB
CD
AC

1 1
A
A

1 4
WORD
WORD

3 3
AAA
AAA
AAA
AAA

2 3
ABC
DEF
BE

4 3
SUN
DAY
CAN
SIT
SCAN

3 4
ABCE
SFES
ADEE
SEES

1 5
ABCDE
EDCBA

5 1
A
B
C
D
E
ACE
 */
