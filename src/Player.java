public class Player extends GameCharacter{
    private int luck, potions, gold, provisions;

    private int initialSkill = GameDriver.rollDice() + 6;
    private int initialStamina = GameDriver.roll2Dice() + 12;
    private int initialLuck = GameDriver.rollDice() + 6;

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

    // Allows the player to test their Luck which decrements by 1 before returning true or false
    boolean testLuck(){
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
    boolean hasProvisions(){
        if(getProvisions() > 0){
            return true;
        }else{
            return false;
        }
    }

    // Allows the player to use provisions which increases Stamina by 4 but never exceed the initial value
    void useProvisions(){
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

    int getInitialSkill(){
        return initialSkill;
    }
    private void setInitialSkill(int initialSkill){
        this.initialSkill = initialSkill;
    }

    int getInitialStamina(){
        return initialStamina;
    }
    private void setInitialStamina(int initialStamina){
        this.initialStamina = initialStamina;
    }

    int getInitialLuck(){
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

    public String toString() {
        return "Player Stamina: " + getStamina();
    }
}