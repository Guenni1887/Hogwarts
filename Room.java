import java.util.HashMap;
import java.util.Map;

/*
 * Class Room - a room in an adventure game.
 *
 * This class is the main class of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0 (February 2002)
 */

class Room {
    private String description;
    private HashMap<String, Room> exits;
    private int learningeffect;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     */
    public Room(String description) {
        this.description = description;
        exits = new HashMap<String, Room>();
    }

    /**
     * Define the exits of this room. Every direction either leads
     * to another room or is null (no exit there).
     */
    public void setExit(String direction, Room nextRoom) {
        exits.put(direction, nextRoom);
    }

   
    public String getExitsasString() {
        String exitsString = "exits: ";

        for (String key : exits.keySet()) {
            exitsString += (key + " ");
        }

        return exitsString;
    }

    /**
     * Return the description of the room (the one that was defined
     * in the constructor).
     */
    public String getDescription() {
        return description;
    }

    public Room getExit(String direction) {
        return (Room) exits.get(direction);
    }

}
