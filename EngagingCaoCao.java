/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mydsass;
import java.util.*;
/**
 *
 * @author jiash
 */
public class EngagingCaoCao {
    private static final int[][] Maze = {
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {2, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1},
        {1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1},
        {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
        {1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1},
        {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1},
        {1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
        {1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1},
        {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 3},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    private static final int ROWS = Maze.length;
    private static final int COLS = Maze[0].length;
    private static final int START = 2;
    private static final int EXIT = 3;  

    private static List<Integer> path;
    static Scanner engage = new Scanner(System.in);
    
    public void EngagingCaoCaoSelection() {
        System.out.println("<<Engaging Cao Cao at Hua Rong Road>>");
        System.out.println("""
                           Finally, Cao Cao lost The Battle of Red Cliff. He retreated away from the river via Hua 
                           Rong Road and managed to escape. Hua Rong Road is a road with complex terrains. 
                           Show how Cao Cao might have retreated from Hua Rong Road so that Liu Bei and 
                           Zhao Yun can catch up with him. Besides, Guan Yu is ahead and is engaging Cao Cao 
                           at the exit of the maze.""");
        System.out.println("\n 2D maze of Hua Rong Road: ");
        System.out.println("\n (2 denotes the starting point. 3 denotes the exit of the maze)");
        
        for (int row = 0; row < Maze.length; row++) {
            for (int col = 0; col < Maze[row].length; col++) {
                System.out.print(Maze[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println("\n--------------------------------------------------------\n");

        System.out.println("Enter to find the path: ");
        engage.nextLine();

        path = findPath();
        displayPath();
        
    }
    
    private static List <Integer> findPath(){
        Queue <Integer> positionQueue = new LinkedList<>();
        Map <Integer, Integer> MapPositions = new HashMap();
        boolean [][] alreadyVisited = new boolean [ROWS][COLS];
        
        int startRow =-1, startCol=-1;
        
        // Locating the starting position
    for (int row = 0; row < ROWS; row++) {
        for (int col = 0; col < COLS; col++) {
            if (Maze[row][col] == START) {
                startRow = row;
                startCol = col;
                break;
            }
        }
    }
        // Return empty list if no valid starting point is found
    if (startRow == -1 || startCol == -1) {
        return new ArrayList<>(); 
    }
        
        //Breadth-First Search(BFS)
        positionQueue.offer(startRow * COLS + startCol);
        alreadyVisited[startRow][startCol] = true;

    while (!positionQueue.isEmpty()) {
        int currentPosition = positionQueue.poll();
        int row = currentPosition / COLS;
        int col = currentPosition % COLS;
        
        // If the current position is the exit
        if (Maze[row][col] == EXIT) {
            List<Integer> path = new ArrayList<>();
            int current = currentPosition;
            while (current != startRow * COLS + startCol) {
                path.add(current);
                current = MapPositions.get(current);
            }
            path.add(startRow * COLS + startCol);
            Collections.reverse(path);
            return path; // Return path
        }
        
         // Exploring adjacent positions
        int[] rowShifts = {-1, 0, 1, 0};
        int[] colShifts = {0, 1, 0, -1};
        for (int i = 0; i < 4; i++) {
            int newRow = row + rowShifts[i];
            int newCol = col + colShifts[i];
            int newPosition = newRow * COLS + newCol;

            if (newRow >= 0 && newRow < ROWS && newCol >= 0 && newCol < COLS && Maze[newRow][newCol] != 1
                    && !alreadyVisited[newRow][newCol]) {
                positionQueue.offer(newPosition);
                alreadyVisited[newRow][newCol] = true;
                MapPositions.put(newPosition, currentPosition);
            }
        }
    }
    return new ArrayList<>(); // Return empty list if no path is found
}
    private static void displayPath() {
    // Initialize a 2D char array for path visualization
    char[][] visualizedPath = new char[ROWS][COLS];

    // Fill the 2D array with the maze layout
    for (int row = 0; row < ROWS; row++) {
        for (int col = 0; col < COLS; col++) {
            if (Maze[row][col] == 1 || Maze[row][col] == 0) {
                visualizedPath[row][col] = (char) (Maze[row][col] + '0'); // Casting 1 and 0 to their char equivalents
            } else {
                visualizedPath[row][col] = ' '; // Leaving other numbers as spaces
            }
        }
    }

    // Mark the escape path on the visualized path
    for (int position : path) {
        int row = position / COLS;
        int col = position % COLS;
        visualizedPath[row][col] = '#'; // Using dot as path marker
    }

    // Printing the path matrix
    System.out.println("\nPath of how Cao Cao might have escaped:  ");
    System.out.println("Note: '#' represents the path");
    for (int row = 0; row < ROWS; row++) {
        for (int col = 0; col < COLS; col++) {
            System.out.print(visualizedPath[row][col] + " ");
        }
        System.out.println();
    }
    System.out.println("\n------------------------------------------------------------\n");
    }
}

