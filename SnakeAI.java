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
    // instance variables - replace the example below with your own
    private ArrayList<Location> snakeLocations;
    private Location snakeHead;
    private Snake aiSnake;
    private MyBoundedGrid<Block> aiGrid;

    /**
     * Constructor for objects of class SnakeAI
     */
    public SnakeAI()
    {
        SnakeGame aIPlaying = new SnakeGame(10);
        aiSnake = aIPlaying.getSnakeUsed();
        snakeHead = aiSnake.getHeadLocation();
        aiGrid = aIPlaying.getGrid();
        
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void move()
    {
        if(disLeftWall() >= 1 && disRightWall() == aiGrid.getNumCols() - 1)
        {
            aiSnake.changeDirection("RIGHT");
        }
        else
        {
        }
    }
    
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
    
    public Location foodLocator()
    {
        return getFoodStuffSpawningLocation();
    }
}
