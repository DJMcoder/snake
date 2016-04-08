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
    private MyBoundedGrid<Block> grid;
    private BlockDisplay display;
    private Snake snake;
    public SnakeGame()
    {
        grid = new MyBoundedGrid<Block> (15,20);
        display = new BlockDisplay(grid);
        display.setArrowListener(this);
        display.setTitle("Snake");
    }
    
    public void play()
    {
        while(playay)
        {
            try 
            {
                Thread.sleep(1000);
                if(jinTetrad.translate(1,0)) //we need a new method to get the direction of the snake.
                {
                    display.showBlocks();
                }
                else
                {
                    clearCompletedRows();
                    jinTetrad = new Tetrad(grid);
                    display.showBlocks();
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
        if(snake.translate(0,1))
        {
            display.showBlocks();
        }
    }

    /**
     * Down is pressed.
     */
    public void downPressed()
    {
        if(snake.translate(1,0))
        {
            display.showBlocks();
        }
    }

    /**
     * Left is pressed.
     */
    public void leftPressed()
    {
        if(snake.translate(0,-1))
        {
            display.showBlocks();
        }
    }

    /**
     * Right is pressed.
     */
    public void rightPressed()
    {
        if(snake.translate(0,1))
        {
            display.showBlocks();
        }
    }
}
