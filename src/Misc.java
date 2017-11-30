class Misc{

    /**
     * Checks if a String can be converted to an int
     */
    // Ref #6 start
    static boolean checkIfInteger(String string){
        try{
            Integer.parseInt(string);
        }catch(NumberFormatException nfe){
            return false;
        }
        return true;
    }
    // Ref #6 end

    /**
     * Simulates a single dice throw which returns a number between 1 and 6
     */
    // Ref #7 start
    static int rollDice(){
        return (int) (Math.random() * 6 + 1);
    }
    // Ref #7 end

    /**
     * Simulates a double dice throw which returns a number between 2 and 12
     */
    static int roll2Dice(){
        return rollDice() + rollDice();
    }
}