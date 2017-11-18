import javax.swing.*;

public class GameDriver {
    public static void main(String[] args) {

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

    public static void testLuckForButton(Player player){
        int roll = roll2Dice();

        if(roll <= player.getLuck()){
            player.setLuck(player.getLuck() - 1);
            JOptionPane.showMessageDialog(null, "Lucky!!", "Luck Test", JOptionPane.INFORMATION_MESSAGE);
        }else{
            player.setLuck(player.getLuck() - 1);
            JOptionPane.showMessageDialog(null, "Unlucky :(", "Luck Test", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}