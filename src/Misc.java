class Misc{

    static boolean checkIfInteger(String string){
        try{
            int testNumber = Integer.parseInt(string);
        }catch(NumberFormatException nfe){
            return false;
        }
        return true;
    }
}
