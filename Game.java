import javax.management.Descriptor;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0 (February 2002)
 */

class Game 
{
    // ~~~ initialization ~~~
    public static void main(String args[]) {
        Game g = new Game();
        g.play();
    }

    private Parser parser;
    private Room currentRoom;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room Bellcourt, HagridsHut, GreatHall,TrophyRoom, Staircase, Basement, SlytherinCommonRoom, PotionsClassRoom, FirstFloor, SecondFloor, Libary, InnerCourtyard, 
        TransformationClassRoom, ThirdFloor, FourthFloor, FifthFloor;
        // create the rooms
        Bellcourt = new Room("in the courtyard in front of the bell tower");
        HagridsHut = new Room("in front of Hagrids hut");
        GreatHall = new Room("in the great hall");
        TrophyRoom = new Room("in the trophy room of Hogwarts");
        Staircase = new Room ("in the only Staircase in the world where the stairs are moving");
        Basement = new Room("in the basement");
        SlytherinCommonRoom = new Room ("in front of the Slytherin common room. You need a password to enter");
        PotionsClassRoom = new Room ("in the class room for the subject Potions");
        FirstFloor = new Room ("in the first floor of the staircase");
        SecondFloor = new Room ("in the second floor of the staircase");
        Libary = new Room("in the Libary of Hogwarts");
        InnerCourtyard = new Room("in the inner courtyard");
        TransformationClassRoom =new Room("in the class room for the subject transformation");
        ThirdFloor = new Room("in the third floor of the staircase");
        FourthFloor =new Room("in the fourth floor of the staircase");
        FifthFloor= new Room("in the fifth floor of the staircase");
 
        // initialise room exits
        //outside
        Bellcourt.setExits(GreatHall, null,  HagridsHut, null);
        HagridsHut.setExits(Bellcourt, null, null, null);

        //inside
        GreatHall.setExits(TrophyRoom, null, Bellcourt, Staircase);
        TrophyRoom.setExits(null, null, GreatHall, null);
        Staircase.setExits(FirstFloor, GreatHall, null,Basement);

        //basement
        Basement.setExits(SlytherinCommonRoom, Staircase, null, PotionsClassRoom);  
        SlytherinCommonRoom.setExits(null, null, Basement,null);
        PotionsClassRoom.setExits(null, Basement, null, null);
        //first floor

        FirstFloor.setExits(SecondFloor, null, Staircase, null);

        //second floor
        SecondFloor.setExits(ThirdFloor, InnerCourtyard, FirstFloor, Libary);
        Libary.setExits(null, SecondFloor, null, null);
        InnerCourtyard.setExits(null, TransformationClassRoom, null, SecondFloor);
        TransformationClassRoom.setExits(null, null, null, InnerCourtyard);

        //third floor
        ThirdFloor.setExits(FourthFloor, null, SecondFloor, null);

        //fourth floor
        FourthFloor.setExits(FifthFloor, null, ThirdFloor, null);

        //fifth floor
        FifthFloor.setExits(null, null, FourthFloor, null);
        currentRoom = Bellcourt;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to Adventure!");
        System.out.println("Adventure is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println("You are " + currentRoom.getDescription());
        System.out.print("Exits: ");
        if(currentRoom.northExit != null)
            System.out.print("north ");
        if(currentRoom.eastExit != null)
            System.out.print("east ");
        if(currentRoom.southExit != null)
            System.out.print("south ");
        if(currentRoom.westExit != null)
            System.out.print("west ");
        System.out.println();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * If this command ends the game, true is returned, otherwise false is
     * returned.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help"))
            printHelp();
        else if (commandWord.equals("go"))
            goRoom(command);
        else if (commandWord.equals("quit"))
            wantToQuit = quit(command);

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("   go quit help");
    }

    /** 
     * Try to go to one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = null;
        if(direction.equals("north"))
            nextRoom = currentRoom.northExit;
        if(direction.equals("east"))
            nextRoom = currentRoom.eastExit;
        if(direction.equals("south"))
            nextRoom = currentRoom.southExit;
        if(direction.equals("west"))
            nextRoom = currentRoom.westExit;

        if (nextRoom == null)
            System.out.println("There is no door!");
        else {
            currentRoom = nextRoom;
            System.out.println("You are " + currentRoom.getDescription());
            System.out.print("Exits: ");
            if(currentRoom.northExit != null)
                System.out.print("north ");
            if(currentRoom.eastExit != null)
                System.out.print("east ");
            if(currentRoom.southExit != null)
                System.out.print("south ");
            if(currentRoom.westExit != null)
                System.out.print("west ");
            System.out.println();
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game. Return true, if this command
     * quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else
            return true;  // signal that we want to quit
    }
}
