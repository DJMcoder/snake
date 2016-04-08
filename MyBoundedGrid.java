import java.util.ArrayList;

/**
 * A MyBoundedGrid is a rectangular grid with a finite number of rows and columns.
 * 
 * @author  Jin Kim
 * @version 8 December 2015
 * 
 * @param   <E> elements that can be placed in the grid.
 */
public class MyBoundedGrid<E>
{
    private Object[][] occupantArray;  // the array storing the grid elements

    //Constructs an empty MyBoundedGrid with the given dimensions.
    //(Precondition:  rows > 0 and cols > 0.)
    /**
     * Constructor for the class MyBoundedGrid
     * 
     * @param   rows    row number of the array.
     * @param   cols    col number of the array.
     */
    public MyBoundedGrid(int rows, int cols)
    {
        occupantArray = new Object[rows][cols];
    }

    //returns the number of rows
    /**
     * returns the number rows in the array.
     * 
     * @return  the number of rows
     */
    public int getNumRows()
    {
        return occupantArray.length;
    }

    //returns the number of columns
    /**
     * returns the number columns in the array.
     * 
     * @return the number of columns
     */
    public int getNumCols()
    {
        return occupantArray[0].length;
    }

    //returns true if loc is valid in this grid, false otherwise
    //precondition:  loc is not null
    /**
     * Determines whether a location is valid.
     * 
     * @param   loc the location in question
     * @return  true if valid, otherwise;
     *          false.
     */
    public boolean isValid(Location loc)
    {
        int row = loc.getRow();
        int col = loc.getCol();

        return(row >= 0 && col >= 0 && row < this.getNumRows() && col < this.getNumCols());
    }

    //returns the object at location loc (or null if the location is unoccupied)
    //precondition:  loc is valid in this grid
    /**
     * Returns the object at location loc.
     * 
     * @param   loc location of the object.
     * @return  the object at location loc.
     */
    public E get(Location loc)
    {
        return (E)(occupantArray[loc.getRow()][loc.getCol()]);
    }

    //puts obj at location loc in this grid and returns the object
    //previously at that location (or null if the location is unoccupied)
    //precondition:  loc is valid in this grid
    /**
     * Puts e object in the location loc.
     * 
     * @param   loc new location of the object.
     * @param   obj object to be placed in the location.
     * @return  the object that was at location loc.
     */
    public E put(Location loc, E obj)
    {
        E temp = this.get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = (Object)obj;
        return temp;
    }

    //removes the object at location loc from this grid and returns the 
    //object that was removed (or null if the location is unoccupied
    //precondition:  loc is valid in this grid
    /**
     * Removes object at location loc.
     * 
     * @param   loc location where the object was removed from.
     * @return  the object removed from location loc.
     */
    public E remove(Location loc)
    {
        E temp = this.get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = null;
        return temp;
    }

    //returns an array list of all occupied locations in this grid
    /**
     * Gets the location that are occupied.
     * 
     * @return  returns the locations that are occupied.
     */
    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location>notEmpty = new ArrayList<Location>();
        for(int r = 0; r < occupantArray.length; r++)
        {
            for(int c = 0; c < occupantArray[r].length; c++)
            {
                if(occupantArray[r][c] != null)
                {
                    Location temp = new Location(r, c);
                    notEmpty.add(temp);
                }
            }
        }
        return notEmpty;
    }
}