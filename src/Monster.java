import javax.swing.*;

public class Monster extends GameCharacter{
    private String name;

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

    public String toString() {
        return getName() + " stamina: " + getStamina();
    }
}