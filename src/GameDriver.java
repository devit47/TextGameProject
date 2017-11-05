import javax.swing.*;

public class GameDriver {
    public static void main(String[] args) {
        Framework framework = new Framework();
        framework.frame();

        int initialSkill = rollDice() + 6;
        int initialStamina = rollDice() + rollDice() + 12;
        int initialLuck = rollDice() + 6;
        Player player = new Player(initialSkill, initialStamina, initialLuck);

        Monster monster = new Monster(10, 10);

        System.out.println("Player: " + player.getStamina());
        System.out.println("Monster: " + monster.getStamina());

        battle(player, monster);

    }

    public static int rollDice(){
        return (int) (Math.random() * 6 + 1);
    }

    public static void battle(Player player, Monster monster){
        while(monster.getStamina() > 0 && player.getStamina() > 0){
            battleTurn(player, monster);
            System.out.println("Player: " + player.getStamina());
            System.out.println("Monster: " + monster.getStamina());
        }
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