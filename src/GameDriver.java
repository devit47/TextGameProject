import javax.swing.*;

public class GameDriver{
    public static void main(String[] args){

        int initialSkill = rollDice() + 6;
        int initialStamina = roll2Dice() + 12;
        int initialLuck = rollDice() + 6;

        Player player = new Player(initialSkill, initialStamina, initialLuck);

        Framework.player = player;
        Battle.player = player;
        Framework framework = new Framework();

        framework.frame();

    }

    public static int rollDice(){
        return (int) (Math.random() * 6 + 1);
    }

    public static int roll2Dice(){
        return rollDice() + rollDice();
    }
}