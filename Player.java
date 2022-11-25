class Player {
    private int learn;
    private int health;

    public Player(int lives) {
        this.learn = 0;
        this.health = lives;
    }

    public int getLearn() {
        return this.learn;
    }

    public int getHealth() {
        return this.health;
    }
}
