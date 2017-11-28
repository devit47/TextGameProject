public class Enemy extends GameCharacter{
    private String name;

    Enemy(){

    }

    public Enemy(String name, int skill, int stamina){
        setName(name);
        setSkill(skill);
        setStamina(stamina);
    }

    String getName(){
        return name;
    }
    private void setName(String name){
        this.name = name;
    }

    public String toString() {
        return getName() + " stamina: " + getStamina();
    }
}