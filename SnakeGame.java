import java.awt.Color;
import java.util.ArrayList;
/**
 * Write a description of class Snake here.
 * 
 * @author Jin Kim
 * @author David Melisso
 * @version April 4, 2016
 */
public class SnakeGame implements ArrowListener
{
    private boolean playay;
    private MyBoundedGrid<Block> grid;
    private BlockDisplay display;
    private Snake snake;
    private int waitTime;
    /**
     * Constructor for the SnakeGame
     */
    public SnakeGame()
    {
        playay = true;
        grid = new MyBoundedGrid<Block> (15,20);
        snake = new Snake(grid, this);
        display = new BlockDisplay(grid);
        display.setArrowListener(this);
        display.setTitle("Snake");
        waitTime = 100;
        play();
    }
    
    /**
     * Constructor for SnakeGame with Wait Time
     * 
     * @param time  the wait time to put in
     */
    public SnakeGame(int time)
    {
        playay = true;
        grid = new MyBoundedGrid<Block> (15,20);
        snake = new Snake(grid, this);
        display = new BlockDisplay(grid);
        display.setArrowListener(this);
        display.setTitle("Snake");
        waitTime = time;
        play();
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
                Thread.sleep(waitTime);
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

     /**
     * Up is pressed.
     */
    public void upPressed()
    {
        if(snake.changeDirection("UP"))
        {
            display.showBlocks();
        }
    }

    /**
     * Down is pressed.
     */
    public void downPressed()
    {
        if(snake.changeDirection("DOWN"))
        {
            display.showBlocks();
        }
    }

    /**
     * Left is pressed.
     */
    public void leftPressed()
    {
        if(snake.changeDirection("LEFT"))
        {
            display.showBlocks();
        }
    }

    /**
     * Right is pressed.
     */
    public void rightPressed()
    {
        if(snake.changeDirection("RIGHT"))
        {
            display.showBlocks();
        }
    }
    
    /**
     * Spawns a foodstuff
     */
    public void spawnFoodStuff()
    {
        ArrayList<Location> emptyLocs = grid.getUnoccupiedLocations();
        int size = emptyLocs.size();
        if (size==0)
        {
            gameOver(true);
            return;
        }
        int rand = (int)(Math.random()*size);
        Location newFoodStuff = emptyLocs.get(rand);
        Block square = new Block();
        square.putSelfInGrid(grid, newFoodStuff);
        square.setColor(Color.GREEN);
    }
    
    /**
     * Ends the game
     * 
     * @param won   whether game was won or lost
     */
    public void gameOver(boolean won)
    {
        playay = false;
        display.showBlocks();
        if (!won)
        {
            new Tetris(true);
            System.out.println("You lost");
            System.out.println("You ate " + snake.getNumItemsEaten() + " blocks.");
        }
        else
        {
            System.out.println("You won!");
        }
    }
}
