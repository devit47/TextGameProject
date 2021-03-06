import javax.swing.*;

class Battle{

    static Player player;

    /**
     * Launches a battle if the Enemy array size is greater than 0 and less than 6
     */
    static void manageBattle(){
        int numEnemies = defineMonsterArraySize();
        if(numEnemies > 0 && numEnemies <= 6){
            battle(player, buildMonsterArray(numEnemies));
        }
    }

    /**
     * Creates and validates an Enemy object using user input
     */
    private static Enemy createAndValidateEnemy(int number){
        String name = JOptionPane.showInputDialog("Enter enemy no." + number + " name:");

        String skillAsString;
        do{
            skillAsString = JOptionPane.showInputDialog("Enter enemy no." + number + " skill:");
        }while(!Misc.checkIfInteger(skillAsString));
        int skill = Integer.parseInt(skillAsString);

        String staminaAsString;
        do{
            staminaAsString = JOptionPane.showInputDialog("Enter enemy no." + number + " stamina:");
        }while(!Misc.checkIfInteger(staminaAsString));
        int stamina = Integer.parseInt(staminaAsString);

        return new Enemy(name, skill, stamina);
    }

    /**
     * Uses user input to define the size of an Enemy array while also validating the input
     */
    private static int defineMonsterArraySize(){
        String userInput = JOptionPane.showInputDialog("How many enemies must you do battle with?\n" +
                "(Numeric values only (1 - 6))");
        if(Misc.checkIfInteger(userInput)){
            return Integer.parseInt(userInput);
        }else{
            return 0;
        }
    }

    /**
     * Builds an array of Enemy
     */
    private static Enemy[] buildMonsterArray(int numEnemies){
        Enemy[] enemyArray = new Enemy[numEnemies];

        for(int i = 0; i < enemyArray.length; i++){
            enemyArray[i] = createAndValidateEnemy(i + 1);
        }
        return enemyArray;
    }

    /**
     * Simulates a battle by calling the battleTurn method for each each enemy in the array in order.
     * The method will continue looping while both the player stamina and the total of the enemy arrays stamina
     * is greater than 0.
     * If the players stamina drops to 0 or below a message is displayed and the program ends.
     */
    private static void battle(Player player, Enemy[] enemyArray){
        int totalEnemyStamina;
        do{
            for(Enemy enemy : enemyArray){
                if(enemy.getStamina() > 0){
                    battleTurn(player, enemy);
                }
            }
            totalEnemyStamina = 0;
            for(Enemy enemy : enemyArray){
                totalEnemyStamina += enemy.getStamina();
            }
        }while(player.getStamina() > 0 && totalEnemyStamina > 0);

        if(player.getStamina() <= 0){
            JOptionPane.showMessageDialog(null, "Your quest has come to an end");
            System.exit(0);
        }
    }

    /**
     * At the start of each turn the player and enemies strength is determined, which ever is higher will inflict damage on the
     * others stamina.
     * The player has the option to try their luck to increase damage inflicted or reduce damage incurred.
     * If both characters strength is the same neither takes damage to stamina and a message is displayed.
     */
    private static void battleTurn(Player player, Enemy enemy){
        int playerStrength = player.getSkill() + Misc.roll2Dice();
        int monsterStrength = enemy.getSkill() + Misc.roll2Dice();

        if(playerStrength > monsterStrength){
            if(JOptionPane.showConfirmDialog(null, "Try your luck to increase damage inflicted on "
                            + enemy.getName() + "?", "Try your luck?",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                if(player.testLuck()){
                    enemy.setStamina(enemy.getStamina() - 4);
                    Framework.playerLuckLabel.setText("Luck: " + player.getLuck());
                    Framework.battleInfo.append("\nYou hit " + enemy.getName() + " for 4 points of Stamina\n" +
                            enemy.getName() + " stamina: " + enemy.getStamina());
                }else{
                    enemy.setStamina(enemy.getStamina() - 1);
                    Framework.playerLuckLabel.setText("Luck: " + player.getLuck());
                    Framework.battleInfo.append("\nYou hit " + enemy.getName() + " for 1 point of Stamina\n" +
                            enemy.getName() + " stamina: " + enemy.getStamina());
                }
            }else{
                enemy.setStamina(enemy.getStamina() - 2);
                Framework.battleInfo.append("\nYou hit " + enemy.getName() + " for 2 points of Stamina\n" +
                        enemy.getName() + " stamina: " + enemy.getStamina());
            }
        }else if(monsterStrength > playerStrength){
            if(JOptionPane.showConfirmDialog(null, "Try your luck to reduce damage taken by 1?"
                            + "\nShould you be unlucky the enemy hits for 3 points of stamina", "Try your luck?",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                if(player.testLuck()){
                    player.setStamina(player.getStamina() - 1);
                    Framework.playerLuckLabel.setText("Luck: " + player.getLuck());
                    Framework.playerStaminaLabel.setText("Stamina: " + player.getStamina());
                    Framework.battleInfo.append("\n" + enemy.getName() + " hit you for 1 point of Stamina");
                }else{
                    player.setStamina(player.getStamina() - 3);
                    Framework.playerLuckLabel.setText("Luck: " + player.getLuck());
                    Framework.playerStaminaLabel.setText("Stamina: " + player.getStamina());
                    Framework.battleInfo.append("\n" + enemy.getName() + " hit you for 3 points of Stamina");
                }
            }else{
                player.setStamina(player.getStamina() - 2);
                Framework.playerStaminaLabel.setText("Stamina: " + player.getStamina());
                Framework.battleInfo.append("\n" + enemy.getName() + " hit you for 2 points of Stamina");
            }
        }else{
            Framework.battleInfo.append("\nYou and your opponents attacks miss one another");
        }
    }
}