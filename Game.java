
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
 * @author Finn Unger
 * @version 1.0 (October 2022)
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

    private Room Bellcourt, QuidditchStadium, TrainingField, GreatHall, TrophyRoom, Staircase, Basement,
            BasementPortrait,
            SlytherinCommonRoom, PotionsClassRoom,
            FirstFloor, FirstFloorHallway, HuffelpuffCommonRoom, HerbalismClassRoom, SecondFloor, Libary,
            InnerCourtyard, TransformationClassRoom,
            SmartWizardPortrait, ThirdFloor, ThirdFloorHallway, FluffysRoom, FourthFloor, FourthFloorHallway,
            DefenceAgainstTheDarkArts, FifthFloor, FifthFloorHallway, ProphesyClassRoom,
            RawenclawCommonRoom, SixthFloor, DumbeldorsOffice, SixthFloorHallway, SeventhFloor,
            GryffindorCommonRoom;

    private Book herbalismBook, potionsBook, transformationsBook, defenceAigainstTheDarkArtsBook;
    private MagicalItem broom, wand;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() {
        createItems();
        createRooms();
        parser = new Parser();
        player = new Player(10);
    }

    private void createItems() {
        herbalismBook = new Book("herbalism lexica", "herbalism", 2);
        potionsBook = new Book("book of the half-blood-prince", "potions", 2);
        transformationsBook = new Book("first year transformatoins", "transfromation", 2);
        defenceAigainstTheDarkArtsBook = new Book("useful curses for beginners", "defenceAiganstTheDarkArts", 2);

        broom = new MagicalItem("Nimbus 2000", 4);
        wand = new MagicalItem("your Wand", 1);

        // where the items are:

    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms() {

        // create the rooms

        Bellcourt = new Room("in the courtyard infront of the bell tower", 0, 0, wand);
        QuidditchStadium = new Room("in the Quidditch stadium", 0, 0, wand);
        TrainingField = new Room("on the training field where Madam Hooch helds her courses", 1, 0, broom);
        GreatHall = new Room("in the great hall", 0, 0, wand);
        TrophyRoom = new Room("in the trophy room of Hogwarts", 0, 0, wand);
        Staircase = new Room("in the only Staircase in the world where the stairs are moving", 0, 0, wand);
        Basement = new Room("in the basement", 0, 0, wand);
        BasementPortrait = new Room("infront of the basement portrait", 0, 0, wand);
        SlytherinCommonRoom = new Room(
                "infront of the portrait of the Slytherin common room. Only slytherins are allowed to enter", 0, 0,
                wand);
        PotionsClassRoom = new Room("in the class room for the subject Potions", 1, 0, potionsBook);
        FirstFloor = new Room("in the first floor of the staircase", 0, 0, wand);
        FirstFloorHallway = new Room("in the hallway of the first floor", 0, 0, wand);
        HuffelpuffCommonRoom = new Room(
                "infront of the portrait of the Huffelpuff common room. Only Huffelpuffs are allowed to enter", 0, 0,
                wand);
        HerbalismClassRoom = new Room("in the class room for the subject herbalism", 1, 0, herbalismBook);
        SecondFloor = new Room("in the second floor of the staircase", 0, 0, wand);
        Libary = new Room("in the Libary of Hogwarts", 1, 0, wand);
        InnerCourtyard = new Room("in the inner courtyard", 0, 0, wand);
        TransformationClassRoom = new Room("in the class room for the subject transformation", 1, 0,
                transformationsBook);
        SmartWizardPortrait = new Room(
                "infront of the Portrait of the smart wizard. You are allowed to go through this portrait", 0, 0, wand);
        ThirdFloor = new Room("in the third floor of the staircase", 0, 0, wand);
        ThirdFloorHallway = new Room("in the hallway of the third floor. You were told to stay away from this hallway!",
                0, 0, wand);
        FluffysRoom = new Room("you entered Fluffy`s room. He will try to kill you!", 0, 0, wand);
        FourthFloor = new Room("in the fourth floor of the staircase", 0, 0, wand);
        FourthFloorHallway = new Room("in the hallway of the fourth floor", 0, 0, wand);
        DefenceAgainstTheDarkArts = new Room("in the class room for defence against the dark arts", 1, 2,
                defenceAigainstTheDarkArtsBook);
        FifthFloor = new Room("in the fifth floor of the staircase", 0, 0, wand);
        FifthFloorHallway = new Room("in the hallway of the fifth floor", 0, 0, wand);
        RawenclawCommonRoom = new Room(
                "infront of the portrait of the Rawenclaw common room. Only Rawenclaws are allowed to enter", 0, 0,
                wand);
        ProphesyClassRoom = new Room("in the class room for the subject probhesy", 1, 0, wand);
        SixthFloor = new Room("in the sixth floor of the staircase", 0, 0, wand);
        DumbeldorsOffice = new Room("in Dubeldore`s office", 0, 4, wand);
        SixthFloorHallway = new Room("in the hallway of the sixth floor", 0, 0, wand);
        SeventhFloor = new Room("in the seventh floor of the staircase", 0, 0, wand);
        GryffindorCommonRoom = new Room(
                "infront of the portrait of the Gryfinndor common romm. Only Gryffindors are allowed to enter", 0, 0,
                wand);

        // initialise room exits
        // outside
        Bellcourt.setExit("north", GreatHall);
        Bellcourt.setExit("south", TrainingField);
        TrainingField.setExit("north", Bellcourt);
        TrainingField.setExit("south", QuidditchStadium);
        QuidditchStadium.setExit("north", TrainingField);

        // inside
        GreatHall.setExit("north", TrophyRoom);
        GreatHall.setExit("south", Bellcourt);
        GreatHall.setExit("west", Staircase);
        TrophyRoom.setExit("south", GreatHall);
        Staircase.setExit("up", FirstFloor);
        Staircase.setExit("east", GreatHall);
        Staircase.setExit("down", Basement);

        // basement
        Basement.setExit("north", SlytherinCommonRoom);
        Basement.setExit("up", Staircase);
        Basement.setExit("west", PotionsClassRoom);
        Basement.setExit("east", BasementPortrait);
        BasementPortrait.setExit("west", Basement);
        BasementPortrait.setExit("through", SixthFloorHallway);
        SlytherinCommonRoom.setExit("south", Basement);
        PotionsClassRoom.setExit("east", Basement);

        // first floor
        FirstFloor.setExit("up", SecondFloor);
        FirstFloor.setExit("down", Staircase);
        FirstFloor.setExit("north", FirstFloorHallway);
        FirstFloorHallway.setExit("south", FirstFloor);
        FirstFloorHallway.setExit("east", HerbalismClassRoom);
        FirstFloorHallway.setExit("north", HuffelpuffCommonRoom);
        HuffelpuffCommonRoom.setExit("south", FirstFloorHallway);
        HerbalismClassRoom.setExit("west", FirstFloorHallway);

        // second floor
        SecondFloor.setExit("up", ThirdFloor);
        SecondFloor.setExit("east", InnerCourtyard);
        SecondFloor.setExit("down", FirstFloor);
        SecondFloor.setExit("west", Libary);
        SecondFloor.setExit("north", SmartWizardPortrait);
        SmartWizardPortrait.setExit("south", SecondFloor);
        SmartWizardPortrait.setExit("through", Bellcourt);
        Libary.setExit("east", SecondFloor);
        Libary.setExit("south", InnerCourtyard);
        InnerCourtyard.setExit("east", TransformationClassRoom);
        InnerCourtyard.setExit("west", SecondFloor);
        InnerCourtyard.setExit("north", Libary);
        TransformationClassRoom.setExit("west", InnerCourtyard);

        // third floor
        ThirdFloor.setExit("up", FourthFloor);
        ThirdFloor.setExit("down", SecondFloor);
        ThirdFloor.setExit("north", ThirdFloorHallway);
        ThirdFloorHallway.setExit("south", ThirdFloor);
        ThirdFloorHallway.setExit("north", FluffysRoom);

        // fourth floor
        FourthFloor.setExit("up", FifthFloor);
        FourthFloor.setExit("down", ThirdFloor);
        FourthFloor.setExit("north", FourthFloorHallway);
        FourthFloorHallway.setExit("south", FourthFloor);
        FourthFloorHallway.setExit("west", DefenceAgainstTheDarkArts);
        DefenceAgainstTheDarkArts.setExit("east", FourthFloorHallway);

        // fifth floor
        FifthFloor.setExit("up", SixthFloor);
        FifthFloor.setExit("down", FourthFloor);
        FifthFloor.setExit("north", FifthFloorHallway);
        FifthFloorHallway.setExit("south", FifthFloor);
        FifthFloorHallway.setExit("west", RawenclawCommonRoom);
        FifthFloorHallway.setExit("up", ProphesyClassRoom);
        RawenclawCommonRoom.setExit("east", FifthFloorHallway);
        ProphesyClassRoom.setExit("down", FifthFloorHallway);

        // sixth floor
        SixthFloor.setExit("up", SeventhFloor);
        SixthFloor.setExit("down", FifthFloor);
        SixthFloor.setExit("north", SixthFloorHallway);
        SixthFloorHallway.setExit("south", SixthFloor);
        SixthFloorHallway.setExit("east", DumbeldorsOffice);

        // seventh floor
        SeventhFloor.setExit("down", SixthFloor);
        SeventhFloor.setExit("north", GryffindorCommonRoom);
        GryffindorCommonRoom.setExit("south", SeventhFloor);

        currentRoom = Bellcourt; // start game outside

        Libary.setItems(herbalismBook);
        Libary.setItems(potionsBook);
        Libary.setItems(transformationsBook);
        Libary.setItems(defenceAigainstTheDarkArtsBook);

        GreatHall.setItems(broom);
        Bellcourt.setItems(wand);
    }

    /**
     * Main play routine. Loops until end of play.
     */

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
        System.out.println("Welcome to Hogwarts, Henry Potter!");
        System.out.println(
                "The other first years got their breifing. If you dont have it, you need to find the sorting hat to get your briefing and to get sorted in one of the four houses.");
        System.out.println("Type 'help' if you need help for that.");
        System.out.println();
        System.out.println("you are in the courtyard infront of the belltower");
        System.out.println(roomInfo());

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
        else if (commandWord.equals("learn"))
            learn();
        else if (commandWord.equals("look"))
            look();
        else if (commandWord.equals("pick"))
            pick(command);
        else if (commandWord.equals("drop"))
            drop(command);
        else if (commandWord.equals("quit"))
            wantToQuit = quit(command);

        if (currentRoom == DumbeldorsOffice) {
            System.out.println("The Sorting Hat will give you your briefing. Congratulations, you won the Game!");
            return true;
        }

        if (currentRoom == FluffysRoom) {
            System.out.println("Fluffy the three headed dog killed you. You lost the Game.");
            return true;
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the
     * command words.
     */
    private void printHelp() {
        System.out.println("You need to find the Sorting Hat! you might need to learn new skills to find it");
        System.out.println("learning progress " + player.getLearn());
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("   go quit help learn look pick drop");
        System.out.println("Syntax for handling Items are drop and pick followed by the Item number");
    }

    private void learn() {
        if (currentRoom.getHasLearned() == false) {
            if (player.getInventoryList().contains(wand)) {
                // to something important
                System.out.println("Has learned: " + currentRoom.getLearningeffect());
                currentRoom.setHasLearned();
                player.setLearn(currentRoom.getLearningeffect());
            } else {
                System.out.println("you need your wand to start learning");
            }
        } else {
            System.out.println("You alredy learned in this room!");

        }
    }

    private void look() {

        System.out.println("the room contains following Items:" + currentRoom.getItems());
        System.out.println("your inventory contains following Items:" + player.getItems());
    }

    private void pick(Command command) {
        try {
            String itemtopick = command.getSecondWord();

            Item result = currentRoom.getItemsList().get(Integer.parseInt(itemtopick) - 1);

            if (player.addItems(result)) {
                System.out.println("successfully picked " + result.getName());
                currentRoom.removeItems(result);
            } else
                System.out.println("your inventory is full");
        } catch (Exception e) {
            System.out.println("You could not pick an item because of following error: " + e.toString());
            System.out.println("Please try again!");
        }
    }

    private void drop(Command command) {
        try {
            String itemtodrop = command.getSecondWord();

            Item dropItem = player.getInventoryList().get(Integer.parseInt(itemtodrop) - 1);

            if (player.removeItems(dropItem)) {
                System.out.println("successfully droped " + itemtodrop);
                currentRoom.setItems(dropItem);
            } else
                System.out.println("you cant drop that Item");
        } catch (Exception e) {
            System.out.println("You could not drop an item because of following error: " + e.toString());
            System.out.println("Please try again!");
        }
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

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }

        else if (player.getLearn() < nextRoom.getRequiredLearningPoints()) {
            System.out.println("You are not smart enough to enter this room. You need to learn more");
        }

        else if (player.getInventoryList().contains(nextRoom.getRequiredLearningItem()) == false) {
            System.out.println("you dont have the required item to enter this room!");
        } else {
            currentRoom = nextRoom;

            System.out.println("you are " + currentRoom.getDescription());
            System.out.println(roomInfo());
            System.out.println();
        }
    }

    public void learnCommand() {

        if (currentRoom.getHasLearned() == true) {
            System.out.println("You alredy learned in this room!");
        }

        else if (player.getLearn() > currentRoom.getRequiredLearningPoints() && currentRoom.getHasLearned() == false)

        {
            player.setLearn(currentRoom.getLearningeffect());
            System.out.println("Added " + currentRoom.getLearningeffect() + " learning points");
            System.out.println("your learning level: " + player.getLearn() + " points");
            currentRoom.setHasLearned();
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
