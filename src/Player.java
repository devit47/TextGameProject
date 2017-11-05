public class Player{
    private int skill, stamina, luck, gold, potions;

    public Player(){
//        this.skill = 0;
//        this.stamina = 0;
//        this.luck = 0;
    }

    public Player(int stamina){
        setStamina(stamina);
    }

    public Player(int skill, int stamina, int luck){
        setSkill(skill);
        setStamina(stamina);
        setLuck(luck);
        setGold(0);
        setPotions(0);
    }

    public int attack(){
        int attackPower = (int) (Math.random() * 11 + 2);
        return attackPower;
    }

    public int getSkill() {
        return skill;
    }
    public void setSkill(int skill) {
        this.skill = skill;
    }

    public int getStamina() {
        return stamina;
    }
    public void setStamina(int stamina) {
        this.stamina = stamina;
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

    public String toString() {
        return "Skill: " + getSkill() + " Stamina: " + getStamina() + " Luck: " + getLuck();
    }
}