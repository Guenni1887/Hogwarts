import java.util.ArrayList;
import java.util.List;

class Player {
    private int learn;
    private int health;
    private List<Item> inventory;
    private int inventoryMax;

    public Player(int lives) {
        this.learn = 0;
        this.health = lives;
        this.inventory = new ArrayList<Item>();
        this.inventoryMax = 5;
    }

    public int getLearn() {
        return this.learn;
    }

    public void setLearn(int count) {
        this.learn += count;
    }

    public int getHealth() {
        return this.health;
    }

    public void removeItems(Item i) {
        this.inventory.remove(i);
    }

    public boolean addItems(Item i) {
        int fuellung = 0;
        for (Item in : inventory){
            fuellung += in.getGewicht();
        }
        
        if (fuellung + i.getGewicht() <= this.inventoryMax) {
            this.inventory.add(i);
            return true;
        } else
            return false;
    }

    public String getItems() {
        String rueckgabe = "";
        for (Item i : this.inventory) {
            rueckgabe += " | " + i.getName();
        }
        rueckgabe += " | ";
        return rueckgabe;
    }
}
