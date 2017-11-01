public class Monster {
    private int skill, stamina;

    public Monster(){

    }

    public Monster(int stamina){
        setStamina(stamina);
    }

    public Monster(int skill, int stamina){
        setSkill(skill);
        setStamina(stamina);
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

    @Override
    public String toString() {
        return "Stamina: " + getStamina();
    }
}