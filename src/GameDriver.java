import javax.swing.*;

public class GameDriver{
    public static void main(String[] args){
        int option = JOptionPane.showConfirmDialog(null, "Are you a new player?");

        if(option == 0){

            Player player = new Player();

            Framework.player = player;
            Battle.player = player;
            Framework framework = new Framework();

            framework.frame();
        }else{
            String previousSession = FileManager.readFile();
            System.out.println(previousSession);

            String[] playerAttributes = previousSession.split(" ");
            int playerSkill = Integer.parseInt(playerAttributes[0]);
            int playerStamina = Integer.parseInt(playerAttributes[1]);
            int playerLuck = Integer.parseInt(playerAttributes[2]);
            int playerGold = Integer.parseInt(playerAttributes[3]);
            int playerPotions = Integer.parseInt(playerAttributes[4]);
            int playerProvisions = Integer.parseInt(playerAttributes[5]);

            // int currentParagraph = Integer.parseInt(playerAttributes[6]);
            int playerInitialSkill = Integer.parseInt(playerAttributes[7]);
            int playerInitialStamina = Integer.parseInt(playerAttributes[8]);
            int playerInitialLuck = Integer.parseInt(playerAttributes[9]);

            Player loadedPlayer = new Player(playerSkill, playerStamina, playerLuck, playerGold, playerPotions,
                    playerProvisions, playerInitialSkill, playerInitialStamina, playerInitialLuck);

            Framework.player = loadedPlayer;
            Battle.player = loadedPlayer;
            Framework framework = new Framework();

            framework.frame();

            framework.paraEntryField.setText(playerAttributes[6]);

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