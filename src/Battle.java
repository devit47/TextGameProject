import javax.swing.*;

public class Battle{

    private static int totalEnemyStamina;
    static Player player;

    public static void manageBattle(){
        battle(player, defineMonsterArray());
    }

    public static Monster[] defineMonsterArray(){
        int numEnemies = Integer.parseInt(JOptionPane.showInputDialog("How many?"));
        Monster[] monsterArray = new Monster[numEnemies];

        for(int i = 0; i < monsterArray.length; i++){
            monsterArray[i] = new Monster();
        }
        return monsterArray;
    }

    public static void battle(Player player, Monster[] monsterArray){
        do{
            for(Monster monster: monsterArray){
                if(monster.getStamina() > 0){
                    battleTurn(player, monster);

                    // TESTING
                    System.out.println(player.toString());
                    System.out.println(monster.toString());
                }
            }

            totalEnemyStamina = 0;
            for(Monster monster: monsterArray){
                totalEnemyStamina += monster.getStamina();
            }
        }while(player.getStamina() > 0 && totalEnemyStamina > 0);
    }

    private static void battleTurn(Player player, Monster monster){
        int playerStrength = player.getSkill() + GameDriver.roll2Dice();
        int monsterStrength = monster.getSkill() + GameDriver.roll2Dice();

        if(playerStrength > monsterStrength){
            if(JOptionPane.showConfirmDialog(null, "Try your luck to increase damage inflicted on "
                            + monster.getName() + "?", "Try your luck?",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                if(player.testLuck()){
                    monster.setStamina(monster.getStamina() - 4);
                    Framework.playerLuckLabel.setText("Luck: " + player.getLuck());
                }else{
                    monster.setStamina(monster.getStamina() - 1);
                    Framework.playerLuckLabel.setText("Luck: " + player.getLuck());
                }
            }else{
                monster.setStamina(monster.getStamina() - 2);
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