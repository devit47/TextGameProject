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
            int[] playerAttributesAsInts = new int[playerAttributes.length];
            for(int i = 0; i < playerAttributes.length; i++){
                playerAttributesAsInts[i] = Integer.parseInt(playerAttributes[i]);
            }

            Player loadedPlayer = new Player(playerAttributesAsInts[0], playerAttributesAsInts[1],
                    playerAttributesAsInts[2], playerAttributesAsInts[3], playerAttributesAsInts[4],
                    playerAttributesAsInts[5], playerAttributesAsInts[6], playerAttributesAsInts[7],
                    playerAttributesAsInts[8]);

            Framework.player = loadedPlayer;
            Battle.player = loadedPlayer;
            Framework framework = new Framework();

            framework.frame();

            framework.changeParagraphImage(playerAttributesAsInts[9]);
        }
    }

}