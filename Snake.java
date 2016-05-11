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
    private String direction;
    /**
     * Constructor for the snake class
     * 
     * @precondition    snakeGr has a greater length and width than 3.
     */
    public Snake(MyBoundedGrid<Block> snakeGr)
    {
        locs = new ArrayList<Location>();
        grid = snakeGr;
        direction = "LEFT";
        int horMid = snakeGr.getNumCols()/2;
        int vertMid = snakeGr.getNumRows()/2;
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
    private boolean isEmpty(MyBoundedGrid<Block> eGrid, Location loc)
    {
        if(eGrid.isValid(loc) && eGrid.get(loc) == null)
        {
            return true;
        }
        return false;
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
        Location headTarget = new Location (getHeadLocation().getRow() + deltaRow,
                getHeadLocation().getCol() + deltaCol);
        if (!grid.isValid(headTarget) || !isEmpty(grid, headTarget))
        {
            return false;
        }
        locs.add(0, headTarget);
        Location toRemove = locs.remove(locs.size()-1);
        addToLocations(grid, headTarget);
        grid.get(toRemove).removeSelfFromGrid();
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
        String oppDirec = getOppositeDirection();
        if (dir == direction || dir == oppDirec)
        {
            return;
        }
        direction = dir;
    }

    /**
     * Returns the Direction
     * 
     * @return the direciton
     */
    public String getDirection()
    {
        return direction;
    }

    /**
     * Returns the opposite Direction
     * 
     * @return the opposite Direction
     */
    public String getOppositeDirection()
    {
        if (direction == "UP")
        {
            return "DOWN";
        }
        else if (direction == "DOWN")
        {
            return "UP";
        }
        else if (direction == "RIGHT")
        {
            return "LEFT";
        }
        else if (direction =="LEFT")
        {
            return "RIGHT";
        }
        throw new IllegalArgumentException("Direction is not a direction.");
    }

    /**
     * Returns whether parameter is a valid Direction
     * 
     * @param dir   the direction to check
     * @return whether it was valid or not
     */
    public boolean validDirection(String dir)
    {
        return dir=="UP"||dir=="DOWN"||dir=="LEFT"||dir=="RIGHT";
    }

    /**
     * Sets the Direction to a new Direction.
     * 
     * @param dir   Direction to set it to
     */
    public void setDirection(String dir)
    {
        if (validDirection(dir))
        {
            direction = dir;
            return;
        }
        throw new IllegalArgumentException("Parameter is not a direction");
    }

    /**
     * Determines the direction of the snake and moves it.
     */
    public boolean determineDirection()
    {
        if (direction.equals("UP"))
        {
            return translate(-1,0);
        }
        else if (direction.equals("DOWN"))
        {
            return translate(1,0);
        }
        else if (direction.equals("RIGHT"))
        {
            return translate(0,1);
        }
        else if (direction.equals("LEFT"))
        {
            return translate(0,-1);
        }
        return false;
    }
}
