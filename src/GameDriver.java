public class GameDriver {
    public static void main(String[] args) {
//        JFrame jFrame = new JFrame("The Warlock of Firetop Mountain");
//        jFrame.setSize(400, 400);
//        jFrame.setVisible(true);

        final int INITIALSKILL = rollDice() + 6;
        final int INITIALSTAMINA = rollDice() + rollDice() + 12;
        final int INITIALLUCK = rollDice() + 6;
        Player player = new Player(INITIALSKILL, INITIALSTAMINA, INITIALLUCK);

        Monster monster = new Monster(10, 10);

        System.out.println("Player: " + player.getStamina());
        System.out.println("Monster: " + monster.getStamina());

        while(monster.getStamina() > 0 && player.getStamina() > 0){
            battleTurn(player, monster);
            System.out.println("Player: " + player.getStamina());
            System.out.println("Monster: " + monster.getStamina());
        }

    }

    public static int rollDice(){
        int diceRoll = (int) (Math.random() * 6 + 1);
        return diceRoll;
    }

    public static void battleTurn(Player player, Monster monster){
        int playerStrength = player.getSkill() + rollDice() + rollDice();
        int monsterStrength = monster.getSkill() + rollDice() + rollDice();

        if(playerStrength > monsterStrength){
            monster.setStamina(monster.getStamina() - 2);
        }else if(monsterStrength > playerStrength){
            player.setStamina(player.getStamina() - 2);
        }
    }
}