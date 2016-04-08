
/**
 * The Direction the Snake is going
 * 
 * @author David Melisso
 * @author Jin Kim
 * @version April 7, 2016
 */
public class Direction
{
    private String direction;
    public static final Direction UP = new Direction("UP");
    public static final Direction DOWN = new Direction("DOWN");
    public static final Direction LEFT = new Direction("LEFT");
    public static final Direction RIGHT = new Direction("RIGHT");

    /**
     * Constructor for objects of class Direction
     * 
     * @param dir   the direction to set it to
     */
    public Direction(String dir)
    {
        setDirection(dir);
    }

    /**
     * Sets the Direction to a new Direction.
     * 
     * @param dir   Direction to set it to
     */
    public void setDirection(String dir)
    {
        switch (dir)
        {
            case "UP": 
                direction = "UP";
            case "DOWN":
                direction = "DOWN";
            case "LEFT":
                direction = "LEFT";
            case "RIGHT":
                direction = "RIGHT";
            default:
                throw new IllegalArgumentException("Parameter is not a direction");
        }
        
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
        switch(direction)
        {
            case "UP":
                return "DOWN";
            case "DOWN":
                return "UP";
            case "RIGHT":
                return "LEFT";
            case "LEFT":
                return "RIGHT";
            default:
                return "";
        }
    }
}
