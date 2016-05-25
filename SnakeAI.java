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
        String res = "LEFT";
        Location foodLocation = foodLocator();
        Location headLocation = snake.getHeadLocation();
        int foodCol = foodLocation.getCol();
        int headCol = headLocation.getCol();
        getGrid().makeNumberGrid(headLocation, foodLocation);
        String direction = snake.getDirection();
        if (foodCol<headCol)
        {
            res = "LEFT";
            if (direction.equals("RIGHT"))
            {
                foodCol = headCol;
            }
        }
        else if (foodCol>headCol)
        {
            res = "RIGHT";
            if (direction.equals("LEFT"))
            {
                foodCol = headCol;
            }
        }
        if(foodCol==headCol)
        {
            int foodRow = foodLocation.getRow();
            int headRow = headLocation.getRow();
            if (foodRow<headRow)
            {
                res = "UP";
                if (direction.equals("DOWN"))
                {
                    if(snake.isEmpty(getGrid(), new Location(headRow, headLocation.getCol()-1)))
                    {
                        res = "LEFT";
                    }
                    else
                    {
                        res = "RIGHT";
                    }
                }
            }
            else
            {
                res = "DOWN";
                if (direction.equals("UP"))
                {
                    if(snake.isEmpty(getGrid(), new Location(headRow, headLocation.getCol()-1)))
                    {
                        res = "LEFT";
                    }
                    else
                    {
                        res = "RIGHT";
                    }
                }
            }
        }
        /*Location moveToSpot = getLocationFromHead(res, 1);
        // if you are gonna hit yourself
        if (!snake.isEmpty(getGrid(), moveToSpot) && !getGrid().get(moveToSpot).getColor().equals(Color.GREEN))
        {
            
        }*/
        snake.changeDirection(res);
        return res;
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
