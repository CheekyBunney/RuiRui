import java.util.Scanner;

public class Maze
{
    public static final int EMPTY = 0;
    public static final int WALL = 1;
    public static final int START = 2;
    public static final int END = 3;
    public static final int VISITED = 4;
    
    public static final char NORTH = 'n';
    public static final char EAST = 'e';
    public static final char SOUTH = 's';
    public static final char WEST = 'w';

    public static void main(String[] args)
    {
        int[][] grid = {
            { EMPTY,  EMPTY,  WALL,  WALL,  WALL,  WALL,  WALL,  WALL,  WALL, WALL},
            {START, EMPTY,  WALL,  WALL, EMPTY, EMPTY, EMPTY, EMPTY,  WALL, WALL},
            { WALL, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,  WALL, EMPTY,  WALL, WALL},
            { WALL,  WALL,  WALL,  WALL, EMPTY,  WALL, EMPTY,  WALL, EMPTY, WALL},
            { WALL, EMPTY, EMPTY, EMPTY, EMPTY,  WALL, EMPTY, EMPTY, EMPTY, WALL},
            { WALL,  WALL, EMPTY,  WALL,  WALL, EMPTY, EMPTY,  WALL, EMPTY, WALL},
            { WALL,  WALL, EMPTY, EMPTY, EMPTY, EMPTY,  WALL,  WALL, EMPTY,  END},
            { WALL,  WALL,  WALL,  WALL,  WALL,  WALL,  WALL,  WALL,  WALL, WALL},
        };
                
        Viewer.view(grid);
    
        String solution;
        Scanner sc = new Scanner(System.in);
        System.out.println("Find a solution to get from ^^ to $$, using the characters " 
            + "'" + NORTH + "', '" + EAST + "', '" + SOUTH + "' and '" + WEST + "'"
            + " (for north, east, south and west).");
        System.out.print("Your solution: ");
        solution = sc.nextLine();
        
        int row = 1;
        int col = 0;
        boolean done = false;
        boolean solved = false;
        int charIndex = 0;
        int solutionLength = solution.length();
        
        while(!done && charIndex < solutionLength)
        {
            char direction = solution.charAt(charIndex);
            System.out.println("Location: (" + row + ", " + col 
                + "), next direction: '" + direction + "'");
            
            switch(direction)
            {
                case Maze.NORTH:
                    row--;
                    break;
                
                case Maze.EAST:
                    col++;
                    break;
                    
                case Maze.SOUTH:
                    row++;
                    break;
                    
                case Maze.WEST:
                    col--;
                    break;
            
                default:
                    System.out.println("You have no idea where you're going."); // Invalid direction.
            }
            
            if(row < 0 || col < 0
                || row >= grid.length || col >= grid[row].length)
            {
                done = true;
                System.out.println("You fall into the chasm of doom."); // Out of bounds.
            }
            else
            {
                if(grid[row][col] == Maze.EMPTY)
                {
                    grid[row][col] = Maze.VISITED;
                }
                else if(grid[row][col] == Maze.WALL)
                {
                    done = true;
                    System.out.println("You stumbled blindly into a solid concrete wall."); // Hit wall.
                }
                else if(grid[row][col] == Maze.END)
                {
                    done = true;
                    solved = true;
                    System.out.println("SOLVED!"); // Solved.
                }
                else
                {} // Do nothing
            }
            
            charIndex++;
        }
        
        if(!solved)
        {
            System.out.println("You have failed to escape. Future archaeologists gaze upon your remains in bafflement."); // Did not reach the end.
        }
        
        Viewer.view(grid);   
    }
}
