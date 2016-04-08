import java.awt.Color;

/**
 * Class of blocks.
 * 
 * @author  Jin Kim
 * @version 8 December 2015
 */
public class Block
{
    private MyBoundedGrid<Block> grid;
    private Location location;
    private Color color;
    /**
     * constructs a blue block, because blue is the greatest color ever!
     */
    public Block()
    {
        color = Color.BLUE;
        grid = null;
        location = null;
    }

    //gets the color of this block
    /**
     * Gets the color of this block.
     * 
     * @return  the color of the block.
     */
    public Color getColor()
    {
        return color;
    }

    //gets the color of this block to newColor.
    /**
     * Sets the color of this block to newColor.
     * 
     * @param   newColor    new color of the block.
     */
    public void setColor(Color newColor)
    {
        color = newColor;
    }

    //gets the grid of this block, or null if this block is not contained in a grid
    /**
     * gets the grid of this block, or null if this block is not contained in a grid
     * 
     * @return  grid of this block.
     */
    public MyBoundedGrid<Block> getGrid()
    {
        return grid;
    }

    //gets the location of this block, or null if this block is not contained in a grid
    /**
     * gets the location of this block, or null if this block is not contained in a grid
     * 
     * @return  location of this block.
     */
    public Location getLocation()
    {
        return location;
    }

    //removes this block from its grid
    //precondition:  this block is contained in a grid
    /**
     * Removes block from its Grid.
     * @precondition:  this block is contained in a grid
     */
    public void removeSelfFromGrid()
    {
        grid.remove(location);
        grid = null;
        location = null;
    }

    /**
     * puts this block into location loc of grid gr
     * if there is another block at loc, it is removed
     * @precondition:  (1) this block is not contained in a grid
     *                 (2) loc is valid in gr
     * 
     * @param    gr  Grid of this block.
     * @param    loc location of this block.
     */
    public void putSelfInGrid(MyBoundedGrid<Block> gr, Location loc)
    {
        if(gr.get(loc)!= null)
        {
            gr.get(loc).removeSelfFromGrid();
        }
        gr.put(loc, this);
        grid = gr;
        location = loc;

    }

    //moves this block to newLocation
    //if there is another block at newLocation, it is removed
    //precondition:  (1) this block is contained in a grid
    //               (2) newLocation is valid in the grid of this block
    /**
     * moves this block to newLocation
     * if there is another block at newLocation, it is removed
     * @precondition:   (1) this block is contained in a grid
     *                  (2) newLocation is valid in the grid of this block
     *                  
     * @param newLocation   newLocation of the block.                 
     */
    public void moveTo(Location newLocation)
    {
        grid.remove(location);
        putSelfInGrid(grid, newLocation);
    }

    //returns a string with the location and color of this block
    /**
     * returns a string with the location and a color of this block.
     * 
     * @return  a string with the location and a color of this block.
     */
    public String toString()
    {
        return "Block[location=" + location + ",color=" + color + "]";
    }
}