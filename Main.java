/* COP 3503C Assignment 2
This program is written by: Richard Magiday */

import java.util.*;

public class Main
{
    static Scanner in = new Scanner(System.in);
    private char[][] grid;
    private int rows, cols;

    public Main(int rows, int cols)
    {
        this.rows = rows;
        this.cols = cols;
        grid = new char[rows][cols];
    }

    // Returns true if (r, c) is in bounds, not yet visited, and matches target char
    private boolean isSafe(int r, int c, char target, boolean[][] visited)
    {
        // TODO: check bounds, visited, and grid[r][c] == target
        return false;
    }

    // Recursive DFS: tries to match word[idx...] starting at cell (r, c)
    // result holds the letter at each matched position (spaces elsewhere)
    private boolean search(int r, int c, String word, int idx, boolean[][] visited, String[][] result)
    {
        // Base case: all characters have been matched
        if (idx == word.length())
        {
            return true;
        }

        if (!isSafe(r, c, word.charAt(idx), visited))
        {
            return false;
        }

        // Choose: mark cell as visited and record letter
        visited[r][c] = true;
        result[r][c] = String.valueOf(grid[r][c]);

        // Explore all 8 neighbors (row delta, col delta)
        int[] dr = {-1, -1, -1,  0, 0,  1, 1, 1};
        int[] dc = {-1,  0,  1, -1, 1, -1, 0, 1};

        for (int d = 0; d < 8; d++)
        {
            // TODO: recurse into neighbor (r + dr[d], c + dc[d]) for idx + 1
        }

        // Un-choose: backtrack
        visited[r][c] = false;
        result[r][c] = " ";

        return false;
    }

    // Searches the grid for word and prints the result
    public void findWord(String word)
    {
        System.out.println("Looking for " + word);

        String[][] result = new String[rows][cols];
        for (String[] row : result)
        {
            Arrays.fill(row, " ");
        }

        boolean[][] visited = new boolean[rows][cols];
        boolean found = false;

        // TODO: try each cell (r, c) as a starting position; call search(r, c, word, 0, visited, result)

        if (found)
        {
            for (String[] row : result)
            {
                System.out.println(Arrays.toString(row));
            }
        }
        else
        {
            System.out.println(word + " not found!");
        }

        System.out.println();
    }

    public static void main(String[] args)
    {
        int R = in.nextInt();
        int C = in.nextInt();
        int W = in.nextInt();

        Main ms = new Main(R, C);

        // Read the grid
        for (int i = 0; i < R; i++)
        {
            for (int j = 0; j < C; j++)
            {
                ms.grid[i][j] = in.next().charAt(0);
            }
        }

        // Search for each word
        for (int i = 0; i < W; i++)
        {
            String word = in.next();
            ms.findWord(word);
        }
    }
}
