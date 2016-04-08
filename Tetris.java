import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * Tetris runs the tetris game
 * 
 * @author David Melisso 
 * @version 1/11/16
 */
public class Tetris implements ArrowListener
{
    MyBoundedGrid<Block> grid;
    BlockDisplay display;
    Tetrad activeTetrad;
    boolean stop;
    private static boolean gameOver;
    /**
     * Constructor for objects of class Tetris
     */
    public Tetris()
    {
        grid = new MyBoundedGrid<Block>(20, 10);
        display = new BlockDisplay(grid);
        display.setTitle("Tetris");
        display.setArrowListener(this);
        
        activeTetrad = new Tetrad(grid);
        display.showBlocks();
        stop = false;
        play();
        gameOver = false;
    }
    public boolean isGameOver()
    {
        return gameOver;
    }
    /**
     * Rotates the active Tetrad when the up-arrow is pressed
     */
    public void upPressed()
    {
        activeTetrad.rotate();
        display.showBlocks();
        
    }
    /**
     * Translates the block down when the down-arrow is pressed
     */
    public void downPressed()
    {
        try
        {
            activeTetrad.translate(1,0);
            display.showBlocks();
            
        }
        catch (NullPointerException n)
        {
            gameOver();
        }
    }
    /**
     * If the game is not over, It moves the current tetrad to the bottom.
     * If the game is over, it restarts the game.
     */
    public void spacePressed()
    {
        if(activeTetrad.isGameOverTetrad())
        {
            //restart game, no longer in function
        }
        else
        {
            //move down
            while (activeTetrad.translate(1,0))
            {
                display.showBlocks();
            }
        }
    }
    /**
     * Closes the game
     */
    public void closeGame()
    {
        display.closeWindow();
    }
    /**
     * Translates the block to the right when the right-arrow is pressed
     */
    public void rightPressed()
    {
        activeTetrad.translate(0,1);
        display.showBlocks();
        
    }
    /**
     * Translates the block to the left when the left-arrow is pressed
     */
    public void leftPressed()
    {
        activeTetrad.translate(0,-1);
        display.showBlocks();
        
    }
    /**
     * Rotates the block counterclockwise when the q-key is pressed
     */
    public void qPressed()
    {
        activeTetrad.rotate(false);
        display.showBlocks();
        
    }
    /**
     * Rotates the block clockwise when the e-key is pressed
     */
    public void ePressed()
    {
        activeTetrad.rotate(true);
        display.showBlocks();
        
    }
    /**
     * Plays the game
     */
    public void play()
    {
        while(!stop)
        {
            try 
            {
                Thread.sleep(1000);
            }
            catch(InterruptedException e)
            {
                //ignore
            }
            display.showBlocks();
            try
            {
                if (!activeTetrad.translate(1,0))
                {
                    activeTetrad = new Tetrad(grid);
                    display.showBlocks();
                }
            }
            catch (NullPointerException n)
            {
                gameOver();
            }
            clearCompletedRows();
            display.showBlocks();
        }
    }
    /**
     * Checks to see if a row is completed
     * 
     * @param row   the row to check
     * @return  true if the row is completed; otherwise,
     *          false
     */
    private boolean isCompletedRow(int row)
    {
        for (int curCol = 0; curCol < grid.getNumCols(); curCol++)
        {
            if (grid.get(new Location(row, curCol))==null)
            {
                return false;
            }
        }
        return true;
    }
    /**
     * Clears a row
     * 
     * @param row   the row to check
     */
    private void clearRow(int row)
    {
        //clear the row
        for (int col = 0; col < grid.getNumCols(); col++)
        {
            grid.remove(new Location(row, col));
        }
        //move everything down one block
        for (int delRow = row-1; delRow >= 0; delRow--)
        {
            for (int col = 0; col < grid.getNumCols(); col++)
            {
                boolean dontStop = true;
                while (dontStop)
                {
                    try
                    {
                        grid.get(new Location(delRow, col)).moveTo(new Location(delRow+1, col));
                    }
                    catch (NullPointerException n)
                    {
                        dontStop = false;
                    }
                    catch (IndexOutOfBoundsException i)
                    {
                        dontStop = false;
                    }
                }
            }
        }
    }
    /**
     * Clears all the completed rows
     */
    private void clearCompletedRows()
    {
        for (int row=0; row < grid.getNumRows(); row++)
        {
            if(isCompletedRow(row))
            {
                clearRow(row);
            }
        }
    }
    /**
     * Stops the game and sets the title to Game Over
     */
    public void gameOver()
    {
        stop = true;
        display.closeWindow();
        grid = new MyBoundedGrid<Block>(11, 15);
        display = new BlockDisplay(grid);
        activeTetrad = new Tetrad(grid, true);
        display.setArrowListener(this);
        display.showBlocks();
        display.setTitle("Game Over");
        
    }
    public static void main(String[] args)
    {
        Tetris tet = new Tetris();
        while (true)
        {
            while(!tet.isGameOver())
            {
                
            }
            System.out.print("GameOver");
        }
    }
}
