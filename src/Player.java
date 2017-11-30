public class Player extends GameCharacter implements PlayerAbilities{
    private int luck, potions, gold, provisions;
    private int initialSkill = Misc.rollDice() + 6;
    private int initialStamina = Misc.roll2Dice() + 12;
    private int initialLuck = Misc.rollDice() + 6;

    Player(){
        setSkill(initialSkill);
        setStamina(initialStamina);
        setLuck(initialLuck);
        setGold(0);
        setPotions(0);
        setProvisions(10);
    }

    Player(int skill, int stamina, int luck, int gold, int potions, int provisions, int initialSkill,
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

    /**
     * Allows the player to test their Luck which decrements by 1 before returning true or false
     */
    public boolean testLuck(){
        if(getLuck() > 0){
            int roll = Misc.roll2Dice();
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

    /**
     * Checks if the player has any provisions left and returns a true value if greater than 0
     */
    public boolean hasProvisions(){
        return getProvisions() > 0;
    }

    /**
     * Allows the player to use provisions which increases Stamina by 4 but never exceed the initial value
     */
    public void useProvisions(){
        if(getStamina() + 4 >= initialStamina){
            setStamina(initialStamina);
            setProvisions(getProvisions() - 1);
        }else{
            setStamina(getStamina() + 4);
            setProvisions(getProvisions() - 1);
        }
    }

    /**
     * Getter and setters for Player attributes
     */
    private int getInitialSkill(){
        return initialSkill;
    }
    private void setInitialSkill(int initialSkill){
        this.initialSkill = initialSkill;
    }

    private int getInitialStamina(){
        return initialStamina;
    }
    private void setInitialStamina(int initialStamina){
        this.initialStamina = initialStamina;
    }

    private int getInitialLuck(){
        return initialLuck;
    }
    private void setInitialLuck(int initialLuck){
        this.initialLuck = initialLuck;
    }

    int getLuck() {
        return luck;
    }
    private void setLuck(int luck) {
        this.luck = luck;
    }

    int getGold() {
        return gold;
    }
    private void setGold(int gold) {
        this.gold = gold;
    }

    int getPotions() {
        return potions;
    }
    private void setPotions(int potions) {
        this.potions = potions;
    }

    int getProvisions() {
        return provisions;
    }
    private void setProvisions(int provisions) {
        this.provisions = provisions;
    }

    public String toString(){
        return "Player Stamina: " + getStamina();
    }

    /**
     * Returns a String with all of the Players attributes
     */
    String playerAttributeValues(){
        return getSkill() + " " + getStamina() + " " + getLuck() + " " + getGold() + " " + getPotions() + " " +
                getProvisions() + " " + getInitialSkill() + " " + getInitialStamina() + " " + getInitialLuck() + " ";
    }
}