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

    /**
     * Starting at target, the target, the method spreads outward labeling at each point the
     * mininum number of steps to get to that Location. It stops when it reaches the origin. 
     * 
     * @param target    usually the foodstuff
     * @param origin    usually the head
     */
    public int[][] makeNumberGrid(Location origin, Location target)
    {
        int gridNumRows = occupantArray.length;
        int gridNumCols = occupantArray[0].length;
        int[][] res = new int[gridNumRows][gridNumCols];

        int orgRow = origin.getRow();
        int orgCol = origin.getCol();
        int tarRow = target.getRow();
        int tarCol = target.getCol();

        // Represent filled locations as -2
        for (int row = 0; row < gridNumRows; row++)
        {
            for (int col = 0; col < gridNumCols; col++)
            {
                if (occupantArray[row][col]!=null)
                {
                    res[row][col] = -2;
                }
            }
        }

        // Represent foodstuff as -1
        res[tarRow][tarCol] = -1; 

        // Represent head as -3
        res[orgRow][orgCol] = -3;

        // Make adjacent locations of foodstuff = 1
        for (Location loc: getValidEmptyAdjacentLocations(target))
        {
            int locRow = loc.getRow();
            int locCol = loc.getCol();

            if (res[locRow][locCol]==0)
            {
                res[locRow][locCol]=1;
            }
        }

        int curNum = 2;
        int sum;

        // Expand the grid
        while (areAllAdjacentsZero(res, origin))
        {
            ArrayList<Location> matching = locationsOfInts(res, curNum-1);
            sum = 0;

            // For each of the curNums in the grid
            for (Location loc: matching)
            {
                ArrayList<Location> adjacents = getValidEmptyAdjacentLocations(loc);
                for (Location adj: adjacents)
                {
                    int adjRow = adj.getRow();
                    int adjCol = adj.getCol();
                    if (res[adjRow][adjCol]==0)
                    {
                        res[adjRow][adjCol] = curNum;
                        sum++;
                    }
                }
            }
            if (sum==0)
            {
                throw new IllegalArgumentException("Road block");
            }
            curNum++;
        }

        // Print out the current grid
        /*for (int[] row: res)
        {
        System.out.println();
        for (int ele: row)
        {
        System.out.print(ele + "\t");
        }
        System.out.println();
        }
        System.out.println();
        System.out.println();*/
        return res;
        
    }
    /**
     * Returns an ArrayList of valid, adjacent locations to a start point, going UP, DOWN, RIGHT, LEFT. 
     * 
     * @param start     the start point
     * 
     * @return  an ArrayList of valid, adjacent locations to a start point
     */
    public ArrayList<Location> getValidAdjacentLocations(Location start)
    {
        ArrayList<Location> res = new ArrayList<Location>();
        int startRow = start.getRow();
        int startCol = start.getCol();
        Location up     = new Location(startRow+1, startCol);
        Location down   = new Location(startRow-1, startCol);
        Location right  = new Location(startRow, startCol+1);
        Location left   = new Location(startRow, startCol-1);

        res.add(up);
        res.add(down);
        res.add(right);
        res.add(left);

        for (int i = res.size()-1; i>=0; i--)
        {
            Location cur = res.get(i);
            if (!(isValid(cur)))
            {
                res.remove(i);
            }
        }

        return res;
    }

    /**
     * Returns an ArrayList of valid, empty, adjacent locations to a start point. 
     * 
     * @param start     the start point
     * 
     * @return  an ArrayList of valid, empty, adjacent locations to a start point
     */
    public ArrayList<Location> getValidEmptyAdjacentLocations(Location start)
    {
        ArrayList<Location> res = getValidAdjacentLocations(start);

        for (int i = res.size()-1; i>=0; i--)
        {
            Location cur = res.get(i);
            if (get(cur)!=null)
            {
                res.remove(i);
            }
        }

        return res;
    }

    /**
     * Helper to makeNumberGrid(). Checks to see if the numbers around the origin are all 0.
     */
    public boolean areAllAdjacentsZero(int[][] array, Location origin)
    {
        ArrayList<Location> adjacents = getValidAdjacentLocations(origin);

        for (Location loc: adjacents)
        {
            if (array[loc.getRow()][loc.getCol()]!=0 && array[loc.getRow()][loc.getCol()]!=-2)
            {
                return false;
            }
        }

        return true;
    }

    /**
     * Helper to makeNumberGrid(). Returns the locations on an int[][] of certain ints. 
     */
    public ArrayList<Location> locationsOfInts(int[][] array, int check)
    {
        ArrayList<Location> res = new ArrayList<Location>();

        for (int row=0; row<array.length; row++)
            for (int col=0; col<array[0].length; col++)
                if (array[row][col]==check)
                    res.add(new Location(row, col));

        return res;
    }
}