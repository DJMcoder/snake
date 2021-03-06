import java.awt.Color;
import java.util.ArrayList;
import java.io.*;
import java.util.*;
import java.lang.reflect.Field;
/**
 * Write a description of class Snake here.
 * 
 * @author Jin Kim
 * @author David Melisso
 * @version April 4, 2016
 */
public class SnakeGame implements ArrowListener
{
    public boolean playay;
    private MyBoundedGrid<Block> grid;
    public BlockDisplay display;
    public Snake snake;
    private int waitTime;
    private Location foodSpawningLocationVariable;
    /**
     * Constructor for the SnakeGame with waitTime and Color
     */
    public SnakeGame(Color color, int time)
    {
        playay = true;
        grid = new MyBoundedGrid<Block> (15,20);
        snake = new Snake(color, grid, this);
        display = new BlockDisplay(grid);
        display.setArrowListener(this);
        display.setTitle("Snake");
        waitTime = time;
        play();
    }

    /**
     * Constructor for SnakeGame with Wait Time
     * 
     * @param time  the wait time to put in
     */
    public SnakeGame(int time)
    {
        this(Color.BLUE, time);
    }

    /**
     * Constructor for SnakeGame with Color
     */
    public SnakeGame(Color color)
    {
        this(color, 100);
    }

    /**
     * Default Constructor for the SnakeGame
     */
    public SnakeGame()
    {
        this(Color.BLUE, 100);
    }

    public MyBoundedGrid<Block> getGrid()
    {
        return grid;
    }

    public int getWaitTime()
    {
        return waitTime;
    }

    /**
     * Runs the game
     */
    public void play()
    {
        spawnFoodStuff();
        while(playay)
        {
            try 
            {
                Thread.sleep(waitTime);
                if(snake.determineDirection()) //we need a new method to get the direction of the snake.
                {
                    display.showBlocks();
                }
                else
                {
                    //snake dies here.
                    gameOver(false);
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
        if(snake.changeDirection("UP"))
        {
            display.showBlocks();
        }
    }

    /**
     * Down is pressed.
     */
    public void downPressed()
    {
        if(snake.changeDirection("DOWN"))
        {
            display.showBlocks();
        }
    }

    /**
     * Left is pressed.
     */
    public void leftPressed()
    {
        if(snake.changeDirection("LEFT"))
        {
            display.showBlocks();
        }
    }

    /**
     * Right is pressed.
     */
    public void rightPressed()
    {
        if(snake.changeDirection("RIGHT"))
        {
            display.showBlocks();
        }
    }

    /**
     * Spawns a foodstuff
     */
    public void spawnFoodStuff()
    {
        ArrayList<Location> emptyLocs = grid.getUnoccupiedLocations();
        int size = emptyLocs.size();
        if (size==0)
        {
            gameOver(true);
            return;
        }
        int rand = (int)(Math.random()*size);
        Location newFoodStuff = emptyLocs.get(rand);
        foodSpawningLocationVariable = newFoodStuff;
        Block square = new Block();
        square.putSelfInGrid(grid, foodSpawningLocationVariable);
        square.setColor(Color.GREEN);
    }

    public Location getFoodStuffSpawningLocation()
    {
        return foodSpawningLocationVariable;
    }

    /**
     * Ends the game
     * 
     * @param won   whether game was won or lost
     */
    public void gameOver(boolean won)
    {
        playay = false;
        display.showBlocks();
        if (!won)
        {
            new Tetris(true);
            System.out.println("You lost");
            System.out.println("You ate " + snake.getNumItemsEaten() + " blocks.");
        }
        else
        {
            System.out.println("You won!");
        }
    }

    /**
     * Oversees the operation of this class.
     * 
     * @param args  user's information from the command line
     * 
     * @throws IOException  if file with the hurricane information cannot be found
     */
    public static void main (String [] args)
    {
        boolean playing = true;
        while(playing)
        {
            Scanner in = new Scanner(System.in);
            System.out.println("Choose Difficulty:");
            System.out.println("Type 1 for Hard");
            System.out.println("Type 2 for Medium");
            System.out.println("Type 3 for Easy");
            System.out.println("Type 4 for the AI to play it.");
            System.out.println("Type 5 for the Custom AI to play it.");
            System.out.println("Type 6 for a Custom game");
            System.out.println("Type 7 for a AI to play it forever");
            System.out.println("Type 8 to quit.");

            System.out.println("Note* Difficulty will vary by the speed of snake.");

            int choice = in.nextInt();

            if(choice==1)
            {
                SnakeGame hardGame = new SnakeGame(50);
            }
            else if(choice==2)
            {
                SnakeGame mediumGame = new SnakeGame(100);
            }
            else if(choice==3)
            {
                SnakeGame easyGame = new SnakeGame(200);
            }
            else if(choice == 4)
            {
                SnakeAI computer = new SnakeAI();
            }
            else if(choice == 5)
            {
                System.out.println("Enter the speed of the AI");
                int speed = in.nextInt();
                SnakeAI computer = new SnakeAI(speed);
            }
            else if(choice == 6)
            {
                System.out.println("Enter the speed of the game");
                int speed = in.nextInt();
                SnakeGame customGame = new SnakeGame(speed);
            }
            else if(choice == 7)
            {
                System.out.println("Enter the speed of the AI");
                int speed = in.nextInt();
                while (true)
                {
                    SnakeAI computer = new SnakeAI(speed);
                }
            }
            else if(choice == 8)
            {
                playing = false;
            }
            else
            {
                throw new IllegalArgumentException("Not a valid entry");
            }
        }
    }
}
