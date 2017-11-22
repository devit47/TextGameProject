import javax.swing.*;

public class GameDriver{
    public static void main(String[] args){

        if(JOptionPane.showConfirmDialog(null, "Are you a new player?") == 0){

            Player player = new Player();

            Framework.player = player;
            Battle.player = player;
            Framework framework = new Framework();

            framework.frame();
        }

    }

    // Simulates a single dice throw which returns a number between 1 and 6
    public static int rollDice(){
        return (int) (Math.random() * 6 + 1);
    }

    // Simulates a double dice throw which returns a number between 2 and 12
    public static int roll2Dice(){
        return rollDice() + rollDice();
    }
}