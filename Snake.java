import java.awt.Color;
import java.util.ArrayList;
/**
 * The Snake class is a collection of locations that represents the snake in the SnakeGame.
 * 
 * @author David Melisso
 * @author Jin Kim
 * @version April 7, 2016
 */
public class Snake
{
    private ArrayList<Location> locs;
    private MyBoundedGrid<Block> grid;
    private Color color;
    private Direction direction;
    /**
     * Constructor for the snake class
     * 
     * @precondition    snakeGr has a greater length and width than 3.
     */
    public Snake(MyBoundedGrid<Block> snakeGr)
    {
        locs = new ArrayList<Location>();
        grid = snakeGr;
        int horMid = snakeGr.getNumCols();
        int vertMid = snakeGr.getNumRows();
        for(int x = -1; x < 2; x++)
        {
            Location snakeLoc = new Location(vertMid, horMid+x);
            Block startBlocks = new Block();
            startBlocks.setColor(Color.BLUE);
            color = Color.BLUE;
            startBlocks.putSelfInGrid(snakeGr, snakeLoc);
            locs.add(snakeLoc);
        }
    }

    /**
     * Checks to see if the given locations in the given grid are empty.
     */
    private boolean areEmpty(MyBoundedGrid<Block> eGrid, Location loc)
    {
        boolean isEmpty = true;
        if(!eGrid.isValid(loc) || eGrid.get(loc) != null)
        {
            return false;
        }
        return isEmpty;
    }

    public ArrayList<Location> getLocations()
    {
        return locs;
    }

    public Location getHeadLocation()
    {
        return locs.get(0);
    }

    /**
     * Adds a Tetrad to a given location in the given grid.
     * 
     * @param   addGrid    grid where the tetrad will be placed in.
     * @param   locs    location where the tetrad will be placed in.
     */
    private void addToLocations(MyBoundedGrid<Block> addGrid, Location loc)
    {
            Block square = new Block();
            square.putSelfInGrid(addGrid, loc);
            square.setColor(color);
    }
    
    /**
     * Moves a snake by a given number of rows or columns.
     * 
     * @param   deltaRow    number of rows the snake block will be moved.
     * @param   deltaCol    number of columns the snake block will be moved.
     * 
     * @return  true if it is possible to translate the snake block to the
     *          location designated, otherwise;
     *          false.
     */
    public boolean translate(int deltaRow, int deltaCol)
    {
        Location old = getHeadLocation();
        Location target = new Location (getHeadLocation().getRow() + deltaRow,
                getHeadLocation().getCol() + deltaCol);
        ArrayList<Location> beforeMove = locs;
        if(areEmpty(grid, target))
        {
            addToLocations(grid, target);
        }

        for(int sn = 1; sn < beforeMove.size(); sn++)
        {
            Location current = beforeMove.get(sn);
            if(areEmpty(grid, old))
            {
                addToLocations(grid, old);
                old = current;
            }
            else
            {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Changes the direction that the snake is going based off of input.
     * Will not change direction if 
     *      it is already going in the direction requested
     *      it is going in the opposite off the direciton requested
     *      
     * @param dir   the direction requested
     * 
     */
    public void changeDirection(String dir)
    {
        String curDirec = direction.getDirection();
        String oppDirec = direction.getOppositeDirection();
        if (dir == curDirec || dir == oppDirec)
        {
            return;
        }
        direction = new Direction(dir);
    }
}
