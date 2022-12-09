/**
 * This class is the main class of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game. Users
 * can walk around some scenery. That's all. It should really be extended
 * to make it more interesting!
 * 
 * To play this game, create an instance of this class and call the "play"
 * method.
 * 
 * This main class creates and initialises all the others: it creates all
 * rooms, creates the parser and starts the game. It also evaluates and
 * executes the commands that the parser returns.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 1.0 (February 2002)
 */

class Game {
    // ~~~ initialization ~~~
    public static void main(String args[]) {
        Game g = new Game();
        g.play();
    }

    private Parser parser;
    private Room currentRoom;
    private Player player;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() {
        createRooms();
        parser = new Parser();
        player = new Player(10);
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms() {
        Room Bellcourt, HagridsHut, GreatHall, TrophyRoom, Staircase, Basement, BasementPortrait, SlytherinCommonRoom, PotionsClassRoom,
                FirstFloor, FirstFloorHallway, HerbalismClassRoom, SecondFloor, Libary, InnerCourtyard,
                TransformationClassRoom, SmartWizardPortrait, ThirdFloor, FourthFloor,DefenceAgainstTheDarkArts, FifthFloor, SixthFloor, SixthFloorHallway, SeventhFloor;
        // create the rooms
        Bellcourt = new Room("in the courtyard in front of the bell tower");
        HagridsHut = new Room("in front of Hagrids hut");
        GreatHall = new Room("in the great hall");
        TrophyRoom = new Room("in the trophy room of Hogwarts");
        Staircase = new Room("in the only Staircase in the world where the stairs are moving");
        Basement = new Room("in the basement");
        BasementPortrait =new Room("infront of the basement portrait");
        SlytherinCommonRoom = new Room("in front of the Slytherin common room. You need a password to enter");
        PotionsClassRoom = new Room("in the class room for the subject Potions");
        FirstFloor = new Room("in the first floor of the staircase");
        FirstFloorHallway =new Room("in the hallway of the first floor");
        HerbalismClassRoom = new Room("in the class room for herbalism");
        SecondFloor = new Room("in the second floor of the staircase");
        Libary = new Room("in the Libary of Hogwarts");
        InnerCourtyard = new Room("in the inner courtyard");
        TransformationClassRoom = new Room("in the class room for the subject transformation");
        SmartWizardPortrait = new Room("infront of the Portrait of the smart wizard. You are allowed to go through this portrait");
        ThirdFloor = new Room("in the third floor of the staircase");
        FourthFloor = new Room("in the fourth floor of the staircase");
        DefenceAgainstTheDarkArts= new Room("in the class room for defence against the dark arts");
        FifthFloor = new Room("in the fifth floor of the staircase");
        SixthFloor = new Room("in the sixth floor of the staircase");
        SixthFloorHallway =new Room("in the hallway of the sixth floor");
        SeventhFloor = new Room("in the seventh floor of the staircase");


        // initialise room exits
        // outside
        Bellcourt.setExit("north" , GreatHall);
        Bellcourt.setExit("south" , HagridsHut);
        HagridsHut.setExit("north" , HagridsHut );

        // inside
        GreatHall.setExit("north" , TrophyRoom);
        GreatHall.setExit( "south" , Bellcourt);
        GreatHall.setExit("west" ,  Staircase);
        TrophyRoom.setExit("south" , GreatHall);
        Staircase.setExit("up" , FirstFloor);
        Staircase.setExit("east" , GreatHall);
        Staircase.setExit("down" ,  Basement);

        // basement
        Basement.setExit("north" , SlytherinCommonRoom);
        Basement.setExit("up" ,  Staircase);
        Basement.setExit("west" , PotionsClassRoom);
        Basement.setExit("east" , BasementPortrait);
        BasementPortrait.setExit("west" , Basement);
        BasementPortrait.setExit("through" , SixthFloorHallway);
        SlytherinCommonRoom.setExit("south" , Basement);
        PotionsClassRoom.setExit("east" , Basement);
       
        // first floor
        FirstFloor.setExit("up" , SecondFloor);
        FirstFloor.setExit ("down" , Staircase);
        FirstFloor.setExit("north" , FirstFloorHallway);
        FirstFloorHallway.setExit("south" , FirstFloor);
        FirstFloorHallway.setExit("east" , HerbalismClassRoom);
        HerbalismClassRoom.setExit("west" , FirstFloorHallway);


        // second floor
        SecondFloor.setExit("up" , ThirdFloor);
        SecondFloor.setExit("east" , InnerCourtyard);
        SecondFloor.setExit("down" , FirstFloor);
        SecondFloor.setExit("west" , Libary);
        SecondFloor.setExit("north" , SmartWizardPortrait);
        SmartWizardPortrait.setExit("south" , SecondFloor);
        SmartWizardPortrait.setExit("through" , Bellcourt);
        Libary.setExit("east" , SecondFloor);
        InnerCourtyard.setExit("east" , TransformationClassRoom);
        InnerCourtyard.setExit("west" , SecondFloor);
        TransformationClassRoom.setExit("west" , InnerCourtyard);

        // third floor
        ThirdFloor.setExit("up" , FourthFloor);
        ThirdFloor.setExit("down" , SecondFloor);

        // fourth floor
        FourthFloor.setExit("up" , FifthFloor);
        FourthFloor.setExit("down" ,  ThirdFloor);
        FourthFloor.setExit("north" , DefenceAgainstTheDarkArts);
        DefenceAgainstTheDarkArts.setExit( "south" , FourthFloor);

        // fifth floor
        FifthFloor.setExit("up" , SixthFloor);
        FifthFloor.setExit("down" , FourthFloor);

        // sixth floor
        SixthFloor.setExit("up" , SeventhFloor);
        SixthFloor.setExit("down" , FifthFloor);
        SixthFloor.setExit("north" , SixthFloorHallway);
        SixthFloorHallway.setExit("south" , SixthFloor);

        // seventh floor
        SeventhFloor.setExit("down" , SixthFloor);

        currentRoom = Bellcourt; // start game outside
    }

    /**
     * Main play routine. Loops until end of play.
     */

    public void learn(){
       player.setLearn(2);

    }
    public void play() {
        printWelcome();

        // Enter the main command loop. Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to Hogwarts");
        System.out.println(
                "The other first years got thier breifing. If you dont have it, you need to find your way on your own");
        System.out.println("Type 'help' if you need help for that.");
        System.out.println();
        System.out.println( roomInfo() );

    }

    private String roomInfo() {
        /*
         * String ergebnis = ("Exits: ");
         * if (northExit != null)
         * ergebnis += "north ";
         * if (eastExit != null)
         * ergebnis +="east ";
         * if (southExit != null)
         * ergebnis +="south ";
         * if (westExit != null)
         * ergebnis += "west ";
         * System.out.println();
         */
        return currentRoom.getExitsasString();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * If this command ends the game, true is returned, otherwise false is
     * returned.
     */
    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        if (command.isUnknown()) {
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
    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println("learning progress " + player.getLearn());
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("   go quit help");
    }

    /**
     * Try to go to one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null)
            System.out.println("There is no door!");
        else {
            currentRoom = nextRoom;
            System.out.println("you are " +  currentRoom.getDescription());
            System.out.println (roomInfo());
        }
    }

    /**
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game. Return true, if this command
     * quits the game, false otherwise.
     */
    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else
            return true; // signal that we want to quit
    }
}
