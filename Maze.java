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
            { WALL,  WALL,  WALL,  WALL,  WALL,  WALL,  WALL,  WALL,  WALL, WALL},
            {START, EMPTY,  WALL,  WALL, EMPTY, EMPTY, EMPTY, EMPTY,  WALL, WALL},
            { WALL, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,  WALL, EMPTY,  WALL, WALL},
            { WALL,  WALL,  WALL,  WALL, EMPTY,  WALL, EMPTY,  WALL, EMPTY, WALL},
            { WALL, EMPTY, EMPTY, EMPTY, EMPTY,  WALL, EMPTY, EMPTY, EMPTY, WALL},
            { WALL,  WALL, EMPTY,  WALL,  WALL, EMPTY, EMPTY,  WALL, EMPTY, WALL},
            { WALL,  WALL, EMPTY, EMPTY, EMPTY, EMPTY,  WALL,  WALL, EMPTY,  END},
            { WALL,  WALL,  WALL,  WALL,  WALL,  WALL,  WALL,  WALL,  WALL, WALL},
        };
                
        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid[i].length; j++)
            {
                switch(grid[i][j])
                {
                    case Maze.EMPTY:
                        System.out.print("  ");
                        break;
                        
                    case Maze.WALL:
                        System.out.print("##");
                        break;
                        
                    case Maze.START:
                        System.out.print("^^");
                        break;
                        
                    case Maze.END:
                        System.out.print("$$");
                        break;
                        
                    case Maze.VISITED:
                        System.out.print("..");
                        break;
                        
                    default:
                        throw new AssertionError();
                }
            }
            System.out.println();
        }
        
        String solution;
        Scanner sc = new Scanner(System.in);
        System.out.println("Find a solution to get from ^^ to $$, using the characters " 
            + "'" + NORTH + "', '" + EAST + "', '" + SOUTH + "' and '" + WEST + "'"
            + " (for north, east, south and west).");
        System.out.print("Your solution: ");
        solution = sc.nextLine();
        
        int currentRow = 1;
        int currentCol = 0;
        boolean done = false;
        boolean solved = false;
        int charIndex = 0;
        int solutionLength = solution.length();
        
        while(!done && charIndex < solutionLength)
        {
            char direction = solution.charAt(charIndex);
            System.out.println("Location: (" + currentRow + ", " + currentCol 
                + "), next direction: '" + direction + "'");
            
            switch(direction)
            {
                case Maze.NORTH:
                    currentRow--;
                    break;
                
                case Maze.EAST:
                    currentCol++;
                    break;
                    
                case Maze.SOUTH:
                    currentRow++;
                    break;
                    
                case Maze.WEST:
                    currentCol--;
                    break;
            
                default:
                    System.out.println("MESSAGE 1"); // Invalid direction.
            }
            
            if(currentRow < 0 || currentCol < 0
                || currentRow >= grid.length || currentCol >= grid[currentRow].length)
            {
                done = true;
                System.out.println("MESSAGE 2"); // Out of bounds.
            }
            else
            {
                if(grid[currentRow][currentCol] == Maze.EMPTY)
                {
                    grid[currentRow][currentCol] = Maze.VISITED;
                }
                else if(grid[currentRow][currentCol] == Maze.WALL)
                {
                    done = true;
                    System.out.println("MESSAGE 3"); // Hit wall.
                }
                else if(grid[currentRow][currentCol] == Maze.END)
                {
                    done = true;
                    solved = true;
                    System.out.println("MESSAGE 4"); // Solved.
                }
                else
                {} // Do nothing
            }
            
            charIndex++;
        }
        
        if(!solved)
        {
            System.out.println("MESSAGE 5"); // Did not reach the end.
        }
        
        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid[i].length; j++)
            {
                switch(grid[i][j])
                {
                    case Maze.EMPTY:
                        System.out.print("  ");
                        break;
                        
                    case Maze.WALL:
                        System.out.print("##");
                        break;
                        
                    case Maze.START:
                        System.out.print("^^");
                        break;
                        
                    case Maze.END:
                        System.out.print("$$");
                        break;
                        
                    case Maze.VISITED:
                        System.out.print("..");
                        break;
                        
                    default:
                        throw new AssertionError();
                }
            }
            System.out.println();
        }
    }
}
