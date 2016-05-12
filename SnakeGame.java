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
    public SnakeGame()
    {
        playay = true;
        grid = new MyBoundedGrid<Block> (15,20);
        snake = new Snake(grid);
        display = new BlockDisplay(grid);
        display.setArrowListener(this);
        display.setTitle("Snake");
        play();
    }
    
    public void play()
    {
        while(playay)
        {
            try 
            {
                Thread.sleep(200);
                if(snake.determineDirection()) //we need a new method to get the direction of the snake.
                {
                    display.showBlocks();
                }
                else
                {
                    //snake dies here.
                    playay = false;
                    display.showBlocks();
                    new Tetris(true);
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
}
