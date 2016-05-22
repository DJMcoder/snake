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

    /**
     * Gets the location that are unoccupied.
     * 
     * @return  returns the locations that are unoccupied.
     */
    public ArrayList<Location> getUnoccupiedLocations()
    {
        ArrayList<Location> empty = new ArrayList<Location>();
        for(int r = 0; r < occupantArray.length; r++)
        {
            for(int c = 0; c < occupantArray[r].length; c++)
            {
                if(occupantArray[r][c] == null)
                {
                    Location temp = new Location(r, c);
                    empty.add(temp);
                }
            }
        }
        return empty;
    }

    public int[][] makeNumberGrid(Location origin, Location target)
    {
        int tarRow = target.getRow();
        int tarCol = target.getCol();
        int orgRow = origin.getRow();
        int orgCol = origin.getCol();
        int[][] res = new int[occupantArray.length][occupantArray[0].length];

        ArrayList<Location> occupied = getOccupiedLocations();
        for (Location loc: occupied)
        {
            res[loc.getRow()][loc.getCol()] = -2;
        }

        int curNum = 1;
        res[tarRow][tarCol] = -1;
        res[orgRow][orgCol] = 0;
        ArrayList<Location> coords = new ArrayList<Location>();

        for(int x = -1; x <= 1; x+= 2)
        {
            if(tarRow+x >= 0 && tarRow+x < occupantArray.length)
            {
                if(res[tarRow+x][tarCol]==0) 
                {
                    res[tarRow+x][tarCol] = curNum;
                    coords.add(new Location(orgRow+x, orgCol));
                }
            }
        }
        for(int x = -1; x <= 1; x+= 2)
        {
            if(tarCol+x >= 0 && tarCol+x < occupantArray[0].length)
            {
                if(res[tarRow][tarCol+x]==0)
                {
                    res[tarRow][tarCol+x] = curNum;
                    coords.add(new Location(orgRow, orgCol+x));
                }
            }
        }
        curNum++;
        while (res[orgRow][orgCol]==0)
        {

            ArrayList<Location> newCoords = new ArrayList<Location>();
            for (Location loc: coords)
            {
                int locRow = loc.getRow();
                int locCol = loc.getCol();
                for(int x = -1; x <= 1; x+= 2)
                {
                    if(locRow+x >= 0 && locRow+x < occupantArray.length)
                    {
                        if(res[locRow+x][locCol]==0) 
                        {
                            res[locRow+x][locCol] = curNum;
                            newCoords.add(new Location(locRow+x, locCol));
                        }
                    }
                }
                for(int x = -1; x <= 1; x+= 2)
                {
                    if(locCol+x >= 0 && locCol+x < occupantArray[0].length)
                    {
                        if(res[locRow][locCol+x]==0)
                        {
                            res[locRow][locCol+x] = curNum;
                            newCoords.add(new Location(locRow, locCol+x));
                        }
                    }
                }
            }

            int len = newCoords.size();
            System.out.println("len = " + len);
            
            for (Location loc: newCoords)
                newCoords.add(loc);
            curNum++;

            for (int[] r: res)
            {
                for (int c: r)
                {
                    System.out.print(c + "\t");
                }
                System.out.println();
            }
            System.out.println();
            System.out.println();
        }

        for (int[] r: res)
        {
            for (int c: r)
            {
                System.out.print(c + "\t");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();

        return res;
    }
}