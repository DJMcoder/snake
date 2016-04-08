import java.awt.Color;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
/**
 * A tetrad is a shape composed of four blocks.
 * 
 * @author David Melisso 
 * @version 1/11/16
 */
public class Tetrad
{
    Block[] blocks;
    MyBoundedGrid<Block> grid;
    Color color;
    private boolean gameoverTetrad;
    /**
     * Constructor for objects of class Tetrad
     * 
     * @param newGrid   the grid to put the Tetrad in
     */
    public Tetrad(MyBoundedGrid<Block> newGrid)
    {
        grid = newGrid;
        blocks = new Block[4];
        int rand = (int)(Math.random()*7);
        int cols = grid.getNumCols();
        Location[] locs = new Location[4];
        int mid = cols/2;
        /*
         * _____________
         * | a | b | c |
         * -------------
         * | d | e | f |
         * -------------
         * | g | h | i |
         * -------------
         *     | j |
         *     -----
         */
        Location locA = new Location(0, mid - 1);
        Location locB = new Location(0, mid);
        Location locC = new Location(0, mid + 1);
        Location locD = new Location(1, mid - 1);
        Location locE = new Location(1, mid);
        Location locF = new Location(1, mid + 1);
        Location locG = new Location(2, mid - 1);
        Location locH = new Location(2, mid);
        Location locI = new Location(2, mid + 1);
        Location locJ = new Location(3, mid);
        switch (rand)
        {
            // I
            case 0:  
                locs = new Location[] {locE, locB, locH, locJ};
                color = Color.RED;
                break;
            // T
            case 1:  
                locs = new Location[] {locB, locA, locC, locE};
                color = Color.GRAY;
                break;
            // O
            case 2:  
                locs = new Location[] {locB, locC, locE, locF};
                color = Color.CYAN;
                break;
            // L
            case 3:  
                locs = new Location[] {locE, locB, locH, locI};
                color = Color.YELLOW;
                break;
            // J
            case 4:  
                locs = new Location[] {locE, locB, locH, locG};
                color = Color.MAGENTA;
                break;
            // S
            case 5:  
                locs = new Location[] {locB, locE, locD, locC};
                color = Color.BLUE;
                break;
            // Z
            case 6:  
                locs = new Location[] {locB, locA, locE, locF};
                color = Color.GREEN;
                break;
            
            default: 
                throw new IllegalStateException("invalid tetrad");
        }
        addToLocations(grid, locs);
        gameoverTetrad=false;
    }
    
    /**
     * A constructor for the game over tetrad.
     * 
     * @param newGrid   the grid to put it in
     * @param gameover  true:   makes the game over tetrad
     *                  false:  makes a regular tetrad
     */
    public Tetrad(MyBoundedGrid<Block> newGrid, boolean gameover)
    {
        if (!gameover)
        {
            new Tetrad(newGrid);
        }
        else
        {
            grid = newGrid;
            int rand = (int)(Math.random()*7);
            int cols = grid.getNumCols();
            int mid = cols/2;
            /*
             * 
             *   __7___6___5___4___3___2___1___m___1___2___3___4___5___6___7__
             * 0 | • | • | • |   |   | • |   |   | • |   | • |   | • | • | • |
             * 1 | • |   |   |   | • |   | • |   | • | • | • |   | • |   |   |
             * 2 | • |   | • |   | • | • | • |   | • |   | • |   | • | • | • |
             * 3 | • |   | • |   | • |   | • |   | • |   | • |   | • |   |   |
             * 4 | • | • | • |   | • |   | • |   | • |   | • |   | • | • | • |
             * 5 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
             * 6 | • | • | • |   | • |   | • |   | • | • | • |   | • | • | • |
             * 7 | • |   | • |   | • |   | • |   | • |   |   |   | • |   | • |
             * 8 | • |   | • |   | • |   | • |   | • | • | • |   | • | • |   |
             * 9 | • |   | • |   |   | • |   |   | • |   |   |   | • |   | • |
             * 10| • | • | • |   |   | • |   |   | • | • | • |   | • |   | • |
             *
             * 
             */
            ArrayList<Location> locs = new ArrayList<Location>();
            
            //row 0
            locs.add(new Location(0, mid-7));
            locs.add(new Location(0, mid-6));
            locs.add(new Location(0, mid-5));
            locs.add(new Location(0, mid-2));
            locs.add(new Location(0, mid+1));
            locs.add(new Location(0, mid+3));
            locs.add(new Location(0, mid+5));
            locs.add(new Location(0, mid+6));
            locs.add(new Location(0, mid+7));
            
            //row 1
            locs.add(new Location(1, mid-7));
            locs.add(new Location(1, mid-3));
            locs.add(new Location(1, mid-1));
            locs.add(new Location(1, mid+1));
            locs.add(new Location(1, mid+2));
            locs.add(new Location(1, mid+3));
            locs.add(new Location(1, mid+5));
            
            //row 2
            locs.add(new Location(2, mid-7));
            locs.add(new Location(2, mid-5));
            locs.add(new Location(2, mid-3));
            locs.add(new Location(2, mid-2));
            locs.add(new Location(2, mid-1));
            locs.add(new Location(2, mid+1));
            locs.add(new Location(2, mid+3));
            locs.add(new Location(2, mid+5));
            locs.add(new Location(2, mid+6));
            locs.add(new Location(2, mid+7));
            
            //row 3
            locs.add(new Location(3, mid-7));
            locs.add(new Location(3, mid-5));
            locs.add(new Location(3, mid-3));
            locs.add(new Location(3, mid-1));
            locs.add(new Location(3, mid+1));
            locs.add(new Location(3, mid+3));
            locs.add(new Location(3, mid+5));
            
            //row 4
            locs.add(new Location(4, mid-7));
            locs.add(new Location(4, mid-6));
            locs.add(new Location(4, mid-5));
            locs.add(new Location(4, mid-3));
            locs.add(new Location(4, mid-1));
            locs.add(new Location(4, mid+7));
            locs.add(new Location(4, mid+6));
            locs.add(new Location(4, mid+5));
            locs.add(new Location(4, mid+3));
            locs.add(new Location(4, mid+1));
            
            //row 5
            
            //row 6
            locs.add(new Location(6, mid-7));
            locs.add(new Location(6, mid-6));
            locs.add(new Location(6, mid-5));
            locs.add(new Location(6, mid-3));
            locs.add(new Location(6, mid-1));
            locs.add(new Location(6, mid+1));
            locs.add(new Location(6, mid+2));
            locs.add(new Location(6, mid+3));
            locs.add(new Location(6, mid+5));
            locs.add(new Location(6, mid+6));
            locs.add(new Location(6, mid+7));
            
            //row 7
            locs.add(new Location(7, mid-7));
            locs.add(new Location(7, mid-5));
            locs.add(new Location(7, mid-3));
            locs.add(new Location(7, mid-1));
            locs.add(new Location(7, mid+1));
            locs.add(new Location(7, mid+5));
            locs.add(new Location(7, mid+7));
            
            //row 8
            locs.add(new Location(8, mid-7));
            locs.add(new Location(8, mid-5));
            locs.add(new Location(8, mid-3));
            locs.add(new Location(8, mid-1));
            locs.add(new Location(8, mid+1));
            locs.add(new Location(8, mid+2));
            locs.add(new Location(8, mid+3));
            locs.add(new Location(8, mid+5));
            locs.add(new Location(8, mid+6));
            
            //row 9
            locs.add(new Location(9, mid-7));
            locs.add(new Location(9, mid-5));
            locs.add(new Location(9, mid-2));
            locs.add(new Location(9, mid+1));
            locs.add(new Location(9, mid+5));
            locs.add(new Location(9, mid+7));
            
            //row 10
            locs.add(new Location(10, mid-7));
            locs.add(new Location(10, mid-6));
            locs.add(new Location(10, mid-5));
            locs.add(new Location(10, mid-2));
            locs.add(new Location(10, mid+1));
            locs.add(new Location(10, mid+2));
            locs.add(new Location(10, mid+3));
            locs.add(new Location(10, mid+5));
            locs.add(new Location(10, mid+7));
            
            blocks = new Block[locs.size()];
            color = Color.RED;
            Location[] alocs = new Location[locs.size()];
            for (int i = 0; i<locs.size(); i++)
            {
                alocs[i] = locs.get(i);
            }
            addToLocations(grid, alocs);
            gameoverTetrad = true;
        }
    }
    
    /**
     * Returns whether the Tetrad is a Game Over Tetrad
     */
    public boolean isGameOverTetrad()
    {
        return gameoverTetrad;
    }
    /**
     * Adds a tetrad
     * 
     * @precondition    tetrad blocks are not in a grid
     * 
     * @param gr  the grid to enter into
     * @param locs  the locations to put the blocks;
     */
    private void addToLocations(MyBoundedGrid<Block> gr, Location[] locs)
    {
        if(areEmpty(gr, locs))
        {
            for(int i = 0; i<blocks.length; i++)
            {
                blocks[i] = new Block();
                blocks[i].setColor(color);
                blocks[i].putSelfInGrid(gr, locs[i]);
            }
        }
    }
    
    /**
     * Removes blocks from grid
     * 
     * @precondition:   Blocks are in grid
     * @postcondition:  Returns old locations of blocks;
     *                  blocks have been removed from the grid
     * 
     * @return  the locations of the blocks
     */
    private Location[] removeBlocks()
    {
        Location[] res = new Location[4];
        for (int i = 0; i<blocks.length; i++)
        {
            res[i] = blocks[i].getLocation();
            blocks[i].removeSelfFromGrid();
        }
        return res;
    }
    
    /**
     * Tells whether all locations are valid and empty in the grid
     * 
     * @param gr  the grid to check in
     * @param locs  the locs to check
     * 
     * @return  true if all locations are valid and empty; otherwise,
     *          false
     */
    private boolean areEmpty(MyBoundedGrid<Block> gr, Location[] locs)
    {
        for (int i = 0; i<blocks.length; i++)
        {
            if (!(gr.isValid(locs[i])&&gr.get(locs[i])==null))
            {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Translates the Tetrad by the desired amount of rows and/or coloumns
     * 
     * @param deltaRow  the number of Blocks to translate the Tetrad up or down
     * @param deltaCol  the number of Blocks to translate the Tetrad left or right
     * 
     * @return  true if the Tetrad translated; otherwise,
     *          false
     */
    public boolean translate(int deltaRow, int deltaCol)
    {
        Location[] oldLocs = removeBlocks();
        Location[] newLocs = new Location[4];
        for (int i = 0; i<blocks.length; i++)
        {
            newLocs[i] = new Location(oldLocs[i].getRow()+deltaRow, oldLocs[i].getCol()+deltaCol);
        }
        if (!areEmpty(grid, newLocs))
        {
            addToLocations(grid, oldLocs);
            return false;
        }
        addToLocations(grid, newLocs);
        return true;
    }
    
    /**
     * Rotates the Tetrad
     * 
     * @param clockwise whether or not to rotate clockwise
     * @return  true if the Tetrad rotated; otherwise,
     *          false
     */
    public boolean rotate(boolean clockwise)
    {
        Location[] oldLocs = removeBlocks();
        Location[] newLocs = new Location[4];
        int rowNaught = oldLocs[0].getRow();
        int colNaught = oldLocs[0].getCol();
        if (clockwise)
        {
            for (int i = 0; i<blocks.length; i++)
            {
                newLocs[i] = new Location(rowNaught - colNaught + oldLocs[i].getCol(),
                                      rowNaught + colNaught - oldLocs[i].getRow());
            }
        }
        else
        {
            for (int i = 0; i<blocks.length; i++)
            {
                newLocs[i] = new Location(rowNaught + colNaught - oldLocs[i].getCol(),
                                      colNaught - rowNaught + oldLocs[i].getRow());
            }
        }
        if (!areEmpty(grid, newLocs))
        {
            addToLocations(grid, oldLocs);
            return false;
        }
        addToLocations(grid, newLocs);
        return true;
    }
    
    /**
     * Rotates the Tetrad clockwise
     * 
     * @return  true if the Tetrad rotated; otherwise,
     *          false
     */
    public boolean rotate()
    {
        return rotate(true);
    }
}
