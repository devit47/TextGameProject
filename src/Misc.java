class Misc{

    // Checks if a String can be converted to an int
    static boolean checkIfInteger(String string){
        try{
            int testNumber = Integer.parseInt(string);
        }catch(NumberFormatException nfe){
            return false;
        }
        return true;
    }

    // Simulates a single dice throw which returns a number between 1 and 6
    static int rollDice(){
        return (int) (Math.random() * 6 + 1);
    }

    // Simulates a double dice throw which returns a number between 2 and 12
    static int roll2Dice(){
        return rollDice() + rollDice();
    }
}
