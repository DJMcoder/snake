import java.awt.Color;
import java.util.ArrayList;
/**
 * AI for the Snake Game.
 * 
 * @author Jin Kim
 * @author David Melisso
 * @version 13 May 2016
 */
public class SnakeAI extends SnakeGame
{
    /**
     * Constructor for objects of class SnakeAI
     */
    public SnakeAI()
    {
        super(10);
        play();
    }

    /**
     * Changes the direction of the snake
     * 
     * @return     the direction it changed to
     */
    public String change()
    {
        Location foodLocation = foodLocator();
        Location headLocation = snake.getHeadLocation();
        int headRow = headLocation.getRow();
        int headCol = headLocation.getCol();
        MyBoundedGrid<Block> grid = getGrid();
        int[][] mat = grid.makeNumberGrid(headLocation, foodLocation);
        
        ArrayList<Location> adjacents = grid.getValidAdjacentLocations(headLocation);
        int smallestVal = Integer.MAX_VALUE;
        int dir = 0;
        for(int i = 0; i < adjacents.size(); i++)
        {
            Location loc = adjacents.get(i);
            int cur = mat[loc.getRow()][loc.getCol()];
            if (cur < smallestVal && cur!=0 && cur!=-2)
            {
                smallestVal = cur;
                dir = i;
            }
        }
        String res = directionFromHead(adjacents.get(dir));
        snake.changeDirection(res);
        return res;
    }
    
    public String directionFromHead(Location loc)
    {
        Location head = snake.getHeadLocation();
        int locRow = loc.getRow();
        int locCol = loc.getCol();
        
        Location locUP      = new Location(locRow-1, locCol);
        Location locDOWN    = new Location(locRow+1, locCol);
        Location locRIGHT   = new Location(locRow, locCol+1);
        Location locLEFT    = new Location(locRow, locCol-1);
        
        if (head.equals(locUP))
        {
            return "DOWN";
        }
        if (head.equals(locDOWN))
        {
            return "UP";
        }
        if (head.equals(locRIGHT))
        {
            return "LEFT";
        }
        if (head.equals(locLEFT))
        {
            return "RIGHT";
        }
        
        return "";
    }
    
    public Location getLocationFromHead(Location loc, String direc, int spaces)
    {
        int row = loc.getRow();
        int col = loc.getCol();
        Location res = snake.getHeadLocation();
        if (direc.equals("RIGHT"))
        {
            res = new Location(row, col+spaces);
        }
        else if (direc.equals("LEFT"))
        {
            res = new Location(row, col-spaces);
        }
        else if (direc.equals("DOWN"))
        {
            res = new Location(row+spaces, col);
        }
        else if (direc.equals("UP"))
        {
            res = new Location(row-spaces, col);
        }
        return res;
    }
    
    /**
     * Runs the game
     */
    public void play()
    {   
        spawnFoodStuff();
        while(playay)
        {
            try 
            {
                Thread.sleep(getWaitTime());
                change();
                
                if(snake.determineDirection()) //we need a new method to get the direction of the snake.
                {
                    display.showBlocks();
                }
                else
                {
                    //snake dies here.
                    gameOver(false);
                }
            }
            catch(InterruptedException e)
            {
                //ignore
            }
        }
    }
    
    /*
    public int disLeftWall()
    {
        return snakeHead.getCol();
    }
    
    public int disRightWall()
    {
        return aiGrid.getNumCols() - snakeHead.getCol();
    }
    
    public int disDownWall()
    {
        return aiGrid.getNumRows() - snakeHead.getRow();
    }
    
    public int disUpWall()
    {
        return snakeHead.getRow();
    }
    */
    /**
     * Gets the Location of the Food
     */
    public Location foodLocator()
    {
        return getFoodStuffSpawningLocation();
    }
}
