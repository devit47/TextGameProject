public class Player extends GameCharacter{
    private int luck, potions, gold, provisions;

    public Player(){
    }

    public Player(int skill, int stamina, int luck){
        setSkill(skill);
        setStamina(stamina);
        setLuck(luck);
        setGold(0);
        setPotions(0);
    }

    public boolean testLuck(){
        int roll = GameDriver.roll2Dice();
        if(roll <= luck){
            setLuck(getLuck() - 1);
            return true;
        }else{
            setLuck(getLuck() - 1);
            return false;
        }
    }

    public void escape(){
        setStamina(getStamina() - 2);
        // Accommodate to test luck which would reduce stamina reduction to 1
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