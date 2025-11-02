package StriverSheet.Recursion.Hard;

import java.io.*;
import java.util.*;

public class RatInMaze {

    public void solve(int i, int j, int n, int m, String path, int[][] grid, List<String> result){

        if(i < 0 || j < 0 || i >= n || j >= m || grid[i][j] == 0) return;

        if(i == n-1 && j == m-1){
            result.add(new String(path));
            return;
        }

        grid[i][j] = 0;

        //top
        solve(i - 1, j, n, m, path + "U", grid, result);
        //right
        solve(i , j + 1, n, m, path + "R", grid, result);
        //down
        solve(i + 1, j, n, m, path + "D", grid, result);
        //left
        solve(i, j - 1, n, m, path + "L", grid, result);

        grid[i][j] = 1;
    }

    public List<String> findPath(int[][] grid) {
        List<String> result = new LinkedList<>();
        String path = "";

        if(grid[0][0] != 1) return result;

        int n = grid.length;
        int m = grid[0].length;

        solve(0, 0, n, m, path, grid, result);

        return result;
    }

    // Helper method to print the maze in a readable format
    public void printMaze(int[][] grid, PrintWriter writer) {
        writer.println("Maze:");
        for(int i = 0; i < grid.length; i++) {
            writer.print("[");
            for(int j = 0; j < grid[i].length; j++) {
                writer.print(grid[i][j]);
                if(j < grid[i].length - 1) {
                    writer.print(", ");
                }
            }
            writer.print("]");
            writer.println();
        }
    }

    // Helper method to visualize a path on the maze
    public void visualizePath(int[][] originalGrid, String path, PrintWriter writer) {
        int n = originalGrid.length;
        int m = originalGrid[0].length;
        char[][] visual = new char[n][m];

        // Initialize with original maze
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                visual[i][j] = originalGrid[i][j] == 1 ? '.' : '#';
            }
        }

        // Mark the path
        int x = 0, y = 0;
        visual[x][y] = 'S'; // Start

        for(char direction : path.toCharArray()) {
            switch(direction) {
                case 'U': x--; break;
                case 'D': x++; break;
                case 'L': y--; break;
                case 'R': y++; break;
            }
            if(x >= 0 && x < n && y >= 0 && y < m) {
                if(x == n-1 && y == m-1) {
                    visual[x][y] = 'E'; // End
                } else {
                    visual[x][y] = '*'; // Path
                }
            }
        }

        writer.println("Path visualization (S=Start, E=End, *=Path, .=Open, #=Blocked):");
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                writer.print(visual[i][j] + " ");
            }
            writer.println();
        }
    }

    // Helper method to validate a path
    public boolean validatePath(int[][] grid, String path) {
        int n = grid.length;
        int m = grid[0].length;
        int x = 0, y = 0;

        // Check if start is valid
        if(grid[x][y] != 1) return false;

        for(char direction : path.toCharArray()) {
            switch(direction) {
                case 'U': x--; break;
                case 'D': x++; break;
                case 'L': y--; break;
                case 'R': y++; break;
                default: return false;
            }

            // Check bounds and if cell is open
            if(x < 0 || x >= n || y < 0 || y >= m || grid[x][y] != 1) {
                return false;
            }
        }

        // Check if we reached the destination
        return x == n-1 && y == m-1;
    }

    public static void main(String[] args) {
        RatInMaze solution = new RatInMaze();

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

                // Read maze size
                int n = Integer.parseInt(line);

                // Read the maze
                int[][] grid = new int[n][n];
                for(int i = 0; i < n; i++) {
                    if(!scanner.hasNextLine()) break;
                    String[] row = scanner.nextLine().trim().split("\\s+");
                    for(int j = 0; j < Math.min(n, row.length); j++) {
                        grid[i][j] = Integer.parseInt(row[j]);
                    }
                }

                // Create a copy of the grid for display (original gets modified during search)
                int[][] originalGrid = new int[n][n];
                for(int i = 0; i < n; i++) {
                    System.arraycopy(grid[i], 0, originalGrid[i], 0, n);
                }

                // Find all paths
                List<String> paths = solution.findPath(grid);

                // Write output for this test case
                writer.println("Test Case " + testCase + ":");
                writer.println("Maze size: " + n + "x" + n);
                solution.printMaze(originalGrid, writer);

                writer.println("Start: (0, 0), Destination: (" + (n-1) + ", " + (n-1) + ")");

                if(paths.isEmpty()) {
                    writer.println("Result: NO PATH FOUND");

                    // Analysis for why no path exists
                    if(originalGrid[0][0] == 0) {
                        writer.println("Reason: Starting position (0,0) is blocked");
                    } else if(originalGrid[n-1][n-1] == 0) {
                        writer.println("Reason: Destination position (" + (n-1) + "," + (n-1) + ") is blocked");
                    } else {
                        writer.println("Reason: No valid path exists from start to destination");
                    }

                } else {
                    writer.println("Result: " + paths.size() + " PATH(S) FOUND");
                    writer.println("\nAll possible paths:");

                    // Sort paths by length for better readability
                    paths.sort(Comparator.comparing(String::length));

                    for(int i = 0; i < paths.size(); i++) {
                        String path = paths.get(i);
                        writer.println((i + 1) + ". \"" + path + "\" (Length: " + path.length() + ")");
                    }

                    // Show detailed analysis for first few paths
//                    writer.println("\nDetailed path analysis:");
//                    for(int i = 0; i < Math.min(3, paths.size()); i++) {
//                        String path = paths.get(i);
//                        writer.println("\nPath " + (i + 1) + ": \"" + path + "\"");
//
//                        // Validate the path
//                        boolean isValid = solution.validatePath(originalGrid, path);
//                        writer.println("Validation: " + (isValid ? "VALID ✓" : "INVALID ✗"));
//
//                        // Show step by step movement
//                        writer.println("Step-by-step movement:");
//                        int x = 0, y = 0;
//                        writer.println("Start at (0, 0)");
//
//                        for(int j = 0; j < path.length(); j++) {
//                            char direction = path.charAt(j);
//                            String directionName = "";
//                            switch(direction) {
//                                case 'U': x--; directionName = "Up"; break;
//                                case 'D': x++; directionName = "Down"; break;
//                                case 'L': y--; directionName = "Left"; break;
//                                case 'R': y++; directionName = "Right"; break;
//                            }
//                            writer.println("Step " + (j + 1) + ": " + direction + " (" + directionName + ") -> (" + x + ", " + y + ")");
//                        }
//
//                        // Visualize this path
//                        solution.visualizePath(originalGrid, path, writer);
//                    }
//
//                    // Statistics
//                    writer.println("\nStatistics:");
//                    int minLength = paths.stream().mapToInt(String::length).min().orElse(0);
//                    int maxLength = paths.stream().mapToInt(String::length).max().orElse(0);
//                    double avgLength = paths.stream().mapToInt(String::length).average().orElse(0);
//
//                    writer.println("Shortest path length: " + minLength);
//                    writer.println("Longest path length: " + maxLength);
//                    writer.printf("Average path length: %.2f%n", avgLength);
//
//                    // Count direction usage
//                    Map<Character, Integer> directionCount = new HashMap<>();
//                    directionCount.put('U', 0);
//                    directionCount.put('D', 0);
//                    directionCount.put('L', 0);
//                    directionCount.put('R', 0);
//
//                    for(String path : paths) {
//                        for(char c : path.toCharArray()) {
//                            directionCount.put(c, directionCount.get(c) + 1);
//                        }
//                    }
//
//                    writer.println("Direction usage across all paths:");
//                    writer.println("Up (U): " + directionCount.get('U'));
//                    writer.println("Down (D): " + directionCount.get('D'));
//                    writer.println("Left (L): " + directionCount.get('L'));
//                    writer.println("Right (R): " + directionCount.get('R'));
                }

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
