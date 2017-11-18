import javax.swing.*;

public class Monster{
    private String name;
    private int skill, stamina;

    public Monster(){
        setName(JOptionPane.showInputDialog("Enter name"));
        setSkill(Integer.parseInt(JOptionPane.showInputDialog("Enter skill")));
        setStamina(Integer.parseInt(JOptionPane.showInputDialog("Enter stamina")));
    }

    public Monster(String name, int skill, int stamina){
        setName(name);
        setSkill(skill);
        setStamina(stamina);
    }


    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
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

    public String toString() {
        return getName() + " stamina: " + getStamina();
    }
}