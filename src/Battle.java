import javax.swing.*;

public class Battle{

    static Player player;
    static int totalEnemyStamina;

    public static void manageBattle(){
        battle(player, defineMonsterArray());
    }


    public static Enemy[] defineMonsterArray(){
        String userInput;
        do{
            userInput = JOptionPane.showInputDialog("How many enemies must you do battle with?\n(Numeric values only)");
        }while(Misc.checkIfInteger(userInput) == false);

        int numEnemies = Integer.parseInt(userInput);
        Enemy[] enemyArray = new Enemy[numEnemies];

        for(int i = 0; i < enemyArray.length; i++){
            enemyArray[i] = new Enemy();
        }
        return enemyArray;
    }

    public static void battle(Player player, Enemy[] enemyArray){

        do{
            for(Enemy enemy : enemyArray){
                if(enemy.getStamina() > 0){
                    battleTurn(player, enemy);

                    // TESTING
                    System.out.println(player.toString());
                    System.out.println(enemy.toString());
                }
            }
            totalEnemyStamina = 0;
            for(Enemy enemy : enemyArray){
                totalEnemyStamina += enemy.getStamina();
            }
        }while(player.getStamina() > 0 && totalEnemyStamina > 0);
    }

    private static void battleTurn(Player player, Enemy enemy){
        int playerStrength = player.getSkill() + GameDriver.roll2Dice();
        int monsterStrength = enemy.getSkill() + GameDriver.roll2Dice();

        if(playerStrength > monsterStrength){
            if(JOptionPane.showConfirmDialog(null, "Try your luck to increase damage inflicted on "
                            + enemy.getName() + "?", "Try your luck?",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                if(player.testLuck()){
                    enemy.setStamina(enemy.getStamina() - 4);
                    Framework.playerLuckLabel.setText("Luck: " + player.getLuck());
                }else{
                    enemy.setStamina(enemy.getStamina() - 1);
                    Framework.playerLuckLabel.setText("Luck: " + player.getLuck());
                }
            }else{
                enemy.setStamina(enemy.getStamina() - 2);
                Framework.battleInfo.append("\nYou hit " + enemy.getName() + " for 2 points of Stamina");
            }
        }else if(monsterStrength > playerStrength){
            if(JOptionPane.showConfirmDialog(null, "Try your luck to reduce damage taken by 1?" +
                            "\nShould you be unlucky the enemy hits for 3 points of stamina", "Try your luck?",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                if(player.testLuck()){
                    player.setStamina(player.getStamina() - 1);
                    Framework.playerLuckLabel.setText("Luck: " + player.getLuck());
                    Framework.playerStaminaLabel.setText("Stamina: " + player.getStamina());
                }else{
                    player.setStamina(player.getStamina() - 3);
                    Framework.playerLuckLabel.setText("Luck: " + player.getLuck());
                    Framework.playerStaminaLabel.setText("Stamina: " + player.getStamina());
                }
            }else{
                player.setStamina(player.getStamina() - 2);
                Framework.playerStaminaLabel.setText("Stamina: " + player.getStamina());
            }
        }else{
            JOptionPane.showMessageDialog(null, "You and your foes attacks narrowly miss each other");
        }
    }
}