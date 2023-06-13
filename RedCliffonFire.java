/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mydsass;

import java.util.Scanner;

/**
 *
 * @author jiash
 */
public class RedCliffonFire {
    private static final int[][] cluster = {
        {1, 1, 0, 0, 1, 0, 0, 1, 1, 1},
        {1, 0, 0, 0, 1, 0, 0, 0, 1, 0},
        {1, 0, 1, 1, 1, 0, 1, 0, 1, 0},
        {1, 0, 0, 0, 0, 0, 1, 0, 0, 0},
        {1, 0, 1, 1, 1, 1, 1, 1, 1, 1},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {1, 1, 1, 1, 0, 1, 1, 0, 1, 0},
        {1, 0, 0, 0, 0, 0, 0, 0, 1, 0},
        {1, 0, 0, 0, 1, 0, 1, 1, 1, 1},
        {1, 0, 0, 0, 1, 0, 0, 0, 0, 0},
    };
    
    public static void redCliffonFireSelection() {
        System.out.println("<<Red Cliff On Fire>>");
        System.out.println("""
                           Everything is well prepared before the final battle.Sun Quan's forces have enough
                           arrows,enough food.They cleare the enemy's base camp on the battleground.Moreover,
                           Cao Cao had used The Chain Strategem as advised by spy Pang Tong,which is the 
                           decision that will make him regret.""");  
        System.out.println("\n2D matrix of Cao Cao chained battleships");
        
        // Print the original cluster matrix
        for (int row = 0; row < cluster.length; row++) {
            for (int col = 0; col < cluster[row].length; col++) {
                System.out.print(cluster[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println("\n----------------------------------------------------------------------------------------\n");
        System.out.println("*1 denotes the battleship,0 denotes the position without a battleship");
        System.out.println("\n Enter the 2D matrix:");

        Scanner sc = new Scanner(System.in);
        StringBuilder strbuild = new StringBuilder();
        String line;
        while (sc.hasNextLine()) {
            line = sc.nextLine();

            if (line.isEmpty()) {
                break;
            }
            strbuild.append(line).append("\n");
        }
        String structure = strbuild.toString().replaceAll(" ", "");

        char[][] battleships = Battleships(structure);
        if (battleships == null) {
            System.out.println("Invalid Input. The only input should be 0,1 and spaces.");
            return;
        }
        int numClusters = numOfClusters(battleships);
        System.out.printf("Number of Clusters: %d clusters%n", numClusters);
        System.out.print("\nPress Enter to go back to Main");
        sc.nextLine();
        System.out.println("\n-----------------------------------------------------------------------------------\n");
    }

    private static char[][] Battleships(String input) {
        String[] rows = input.split("\n");
        char[][] battleships = new char[rows.length][];
        for (int i = 0; i < rows.length; i++) {
            battleships[i] = rows[i].toCharArray();
            for (char nodes : battleships[i]) {
                if (nodes != '1' && nodes != '0') {
                    return null;
                }
            }
        }
        return battleships;
    }

    private static int numOfClusters(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int numClusters = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        
        // Iterate through the grid to find clusters
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                // If a battleship is found and it's not visited, increment the cluster count
                if (grid[i][j] == '1' && !visited[i][j]) {
                    numClusters++;
                    DFS(grid, i, j, visited);
                }
            }
        }
        return numClusters;
    }

    private static void DFS(char[][] grid, int i, int j, boolean[][] visited) {
        // Base cases for stopping the DFS traversal
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1' || visited[i][j]) {
            return;
        }
        // Mark the current cell as visited
        visited[i][j] = true;

        int[] dx = {0, 0, -1, 1, 1, 1, -1, -1};
        int[] dy = {1, -1, 0, 0, 1, -1, -1, 1};

        // Explore the neighbors of the current cell
        for (int k = 0; k < dx.length; k++) {
            int newI = i + dx[k];
            int newJ = j + dy[k];
            DFS(grid, newI, newJ, visited);
        }
    }
} 
                           
