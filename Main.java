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
        if(r < 0 || r >= rows)
            return false; //out of bounds row
        if(c < 0 || c >= cols)
            return false; //out of bounds col
        if(visited[r][c])
            return false; //already used the cell
        if(grid[r][c] != target)
            return false; //wrong letter
        return true;
    }

    // tries to match word[idx] starting at cell (r, c)
    // result holds the letter at each matched position
    private boolean search(int r, int c, String word, int idx, boolean[][] visited, String[][] result)
    {
        // all characters have been matched
        if (idx == word.length())
        {
            return true;
        }

        if (!isSafe(r, c, word.charAt(idx), visited))
        {
            return false;
        }

        // mark cell as visited and record letter
        visited[r][c] = true;
        result[r][c] = String.valueOf(grid[r][c]);

        // Explore all 8 neighbors: left, right, down, up, then diagonals
        int[] dr = { 0,  0, 1, -1, -1, -1, 1,  1};
        int[] dc = {-1,  1, 0,  0, -1,  1, -1, 1};

        for (int d = 0; d < 8; d++)
        {
            if(search(r + dr[d], c + dc[d], word, idx + 1, visited, result))
                return true;
        }

        // backtrack
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

        for(int r = 0; r < rows && !found; r++)
        {
            for(int c = 0; c < cols && !found; c++)
            {
                if (search(r, c, word, 0, visited, result))
                    found = true;
            }
        }

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
        // Loop to handle multiple test cases
        while (in.hasNextInt())
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
}
