public class Player extends GameCharacter{
    private int luck, potions, gold, provisions;

    private int initialSkill = GameDriver.rollDice() + 6;
    private int initialStamina = GameDriver.roll2Dice() + 12;
    private int initialLuck = GameDriver.rollDice() + 6;

    public Player(){
        setSkill(initialSkill);
        setStamina(initialStamina);
        setLuck(initialLuck);
        setGold(0);
        setPotions(0);
        setProvisions(10);
    }

    public Player(int skill, int stamina, int luck, int gold, int potions, int provisions, int initialSkill,
                  int initialStamina, int initialLuck){
        setSkill(skill);
        setStamina(stamina);
        setLuck(luck);
        setGold(gold);
        setPotions(potions);
        setProvisions(provisions);
        setInitialSkill(initialSkill);
        setInitialStamina(initialStamina);
        setInitialLuck(initialLuck);
    }

    // Allows the player to test their Luck which decrements by 1 before returning true or false
    public boolean testLuck(){
        if(getLuck() > 0){
            int roll = GameDriver.roll2Dice();
            if(roll <= luck){
                setLuck(getLuck() - 1);
                return true;
            }else{
                setLuck(getLuck() - 1);
                return false;
            }
        }else{
            return false;
        }
    }

    // Checks if the player has any provisions left and returns a true value if greater than 0
    public boolean hasProvisions(){
        if(getProvisions() > 0){
            return true;
        }else{
            return false;
        }
    }

    // Allows the player to use provisions which increases Stamina by 4 but never exceed the initial value
    public void useProvisions(){
        if(getStamina() + 4 >= initialStamina){
            setStamina(initialStamina);
            setProvisions(getProvisions() - 1);
        }else{
            setStamina(getStamina() + 4);
            setProvisions(getProvisions() - 1);
        }
    }

    public void escape(){
        setStamina(getStamina() - 2);
        // Accommodate to test luck which would reduce stamina reduction to 1
    }

    public int getInitialSkill(){
        return initialSkill;
    }
    public void setInitialSkill(int initialSkill){
        this.initialSkill = initialSkill;
    }

    public int getInitialStamina(){
        return initialStamina;
    }
    public void setInitialStamina(int initialStamina){
        this.initialStamina = initialStamina;
    }

    public int getInitialLuck(){
        return initialLuck;
    }
    public void setInitialLuck(int initialLuck){
        this.initialLuck = initialLuck;
    }

    public int getLuck() {
        return luck;
    }
    public void setLuck(int luck) {
        this.luck = luck;
    }

    public int getGold() {
        return gold;
    }
    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getPotions() {
        return potions;
    }
    public void setPotions(int potions) {
        this.potions = potions;
    }

    public int getProvisions() {
        return provisions;
    }
    public void setProvisions(int provisions) {
        this.provisions = provisions;
    }

    public String toString() {
        return "Player Stamina: " + getStamina();
    }
}